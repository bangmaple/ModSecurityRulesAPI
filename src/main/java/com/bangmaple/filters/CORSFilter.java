/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bangmaple.filters;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author bangmaple
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext reqCtx, ContainerResponseContext resCtx) throws IOException {
        resCtx.getHeaders().add("Access-Control-Allow-Origin", "*");
        resCtx.getHeaders().add("Access-Control-Allow-Headers", "*");
        resCtx.getHeaders().add("Access-Control-Allow-Credentials", "true");
        resCtx.getHeaders().add("Access-Control-Allow-Method", "GET, POST, PUT, DELETE, PATCH");

    }

}
