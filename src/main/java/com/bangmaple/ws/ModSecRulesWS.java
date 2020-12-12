package com.bangmaple.ws;

import com.bangmaple.daos.ModSecRulesDAO;
import com.bangmaple.entities.Rules;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

@Path("modsec")
public class ModSecRulesWS {

    @Inject
    private ModSecRulesDAO dao;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Rules getRuleFile(@PathParam("id") Integer id) {
        return dao.get(id);
    }

    @POST
    @Path("/uploadRule")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadRule(
            @FormDataParam("file") InputStream ruleFile,
            @FormDataParam("name") String name,
            @FormDataParam("description") String desc) throws IOException {
        Rules rule = buildRuleEntity(desc, name, ruleFile);
        dao.insertRule(rule);
        return Response.ok().header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Headers",
                        "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Methods",
                        "GET, POST, PUT, DELETE, OPTIONS, HEAD").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRulesBrief() {
        return Response.ok().entity(dao.getBriefRules()).build();
    }

    private Rules buildRuleEntity(String desc, String name, InputStream ruleFile) throws IOException {
        Rules modSecRule = new Rules();
        modSecRule.setRuleDescription(desc);
        modSecRule.setRuleName(name);
        modSecRule.setRuleFile(getByteDataFromInpuStream(ruleFile));
        return modSecRule;
    }

    private static byte[] getByteDataFromInpuStream(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
}
