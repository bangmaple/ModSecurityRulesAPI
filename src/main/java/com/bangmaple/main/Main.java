package com.bangmaple.main;

import com.bangmaple.daos.ModSecRulesDAO;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import javax.inject.Singleton;
import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/api/";

    public static HttpServer startServer() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return createHttpServerWith(new ResourceConfig()
                .packages("com.bangmaple.ws")
                .register(JacksonFeature.class)
                .register(MultiPartFeature.class)
                .register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(ModSecRulesDAO.class).to(ModSecRulesDAO.class).in(Singleton.class);
            }
        }));
    }

    private static HttpServer createHttpServerWith(ResourceConfig rc) {
        HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        return httpServer;
    }

    public static void main(String[] args) throws Exception {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        server.start();
        System.in.read();
        server.shutdownNow();
    }

}