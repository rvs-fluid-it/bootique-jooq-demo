package com.foo;

import io.bootique.jdbc.test.DatabaseChannel;
import io.bootique.jdbc.test.Table;
import io.bootique.jdbc.test.TestDataManager;
import io.bootique.jetty.test.junit.JettyTestFactory;
import io.bootique.test.BQTestRuntime;
import org.junit.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class CountryApiIT {
    @ClassRule
    public static JettyTestFactory testFactory = new JettyTestFactory();
    public static Table COUNTRY;


    @Rule
    public TestDataManager dataManager = new TestDataManager(true, COUNTRY);

    @BeforeClass
    public static void startServerAndSetupDB() throws SQLException {
        BQTestRuntime runtime = testFactory.app("-c", "demo-test.yml")
                .autoLoadModules()
                .start();

        DatabaseChannel channel = DatabaseChannel.get(runtime);

        channel.update("CREATE TABLE \"COUNTRY\" (\"CODE\" CHAR(2), \"NAME\" VARCHAR(25))");
        COUNTRY = channel.newTable("COUNTRY").columnNames("CODE", "NAME").initColumnTypesFromDBMetadata().build();
    }

    @Test
    public void testGet() {
        COUNTRY.insert("be", "Belgium");
        Client client = ClientBuilder.newClient();
        Response r = client.target("http://127.0.0.1:8080/be").request().get();
        assertEquals(200, r.getStatus());
        assertEquals(new Country("be", "Belgium"), r.readEntity(Country.class));
    }
}
