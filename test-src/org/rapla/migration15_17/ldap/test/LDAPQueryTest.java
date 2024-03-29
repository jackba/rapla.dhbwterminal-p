package org.rapla.migration15_17.ldap.test;

import java.util.Locale;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.rapla.ServletTestBase;
import org.rapla.facade.ClientFacade;
import org.rapla.framework.Container;
import org.rapla.server.ServerService;
import org.rapla.server.ServerServiceContainer;


public class LDAPQueryTest extends ServletTestBase {

    ServerService raplaServer;

    ClientFacade adminFacade;
    Locale locale;

    public LDAPQueryTest(String name) {
        super(name);
    }

    public static Test suite() {
        return new TestSuite(LDAPQueryTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        // start the server
        Container container = getContainer();
		ServerServiceContainer raplaServerContainer = container.lookup(ServerServiceContainer.class,getStorageName());
        raplaServer = raplaServerContainer.getContext().lookup( ServerService.class);
       // raplaServer = (ServerService)getContext().lookup(ServerService.class); //+ "/storage-file");
        // start the client service
/*
        adminFacade = (ClientFacade)getContext().lookup(ClientFacade.ROLE + "/remote-facade");
        boolean canLogin = adminFacade.login("admin","".toCharArray());
*/
        locale = Locale.getDefault();
        //assertTrue( "Can't login", canLogin );
    }

    protected void tearDown() throws Exception {
       // adminFacade.logout();
        super.tearDown();
    }

    public void testLDAPConnection () throws Exception {
        String password =  LDAPQuery.PASSWORD;
        LDAPQuery ldapQuery = new LDAPQueryImpl(raplaServer.getContext());
        Map<String,Map<String,String>> ldapValues = ldapQuery.getLDAPValues(
                LDAPQuery.SEARCH_TERM_ABTEILUNGEN, password
        );
        for (Map.Entry<String, Map<String, String>> stringMapEntry : ldapValues.entrySet()) {
            System.out.println(stringMapEntry.getKey());
        }
        System.out.println(ldapValues.size());


        ldapValues = ldapQuery.getLDAPValues(
                LDAPQuery.SEARCH_TERM_ALL, password
        );
        for (Map.Entry<String, Map<String, String>> stringMapEntry : ldapValues.entrySet()) {
            System.out.println(stringMapEntry.getKey());
        }
        System.out.println(ldapValues.size());



    }
}
