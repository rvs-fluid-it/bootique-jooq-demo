package com.foo;

import io.bootique.jetty.test.junit.JettyTestFactory;
import io.bootique.test.BQTestRuntime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class CountryApiIT {
    @Rule
    public JettyTestFactory testFactory = new JettyTestFactory();

    @Before
    public void before() {

        BQTestRuntime runtime = testFactory.app("-c", "demo-test.yml")
                .autoLoadModules()
                .start();
    }

    @Test
    public void testGet() {
        Client client = ClientBuilder.newClient();
        Response r = client.target("http://127.0.0.1:8080/be").request().get();
        assertEquals(200, r.getStatus());
        assertEquals(new Country("be", "Belgium"), r.readEntity(Country.class));
    }
}
