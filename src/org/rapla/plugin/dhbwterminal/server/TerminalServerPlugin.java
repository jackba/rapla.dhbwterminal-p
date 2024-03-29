/*--------------------------------------------------------------------------*
 | Copyright (C) 2006 Christopher Kohlhaas                                  |
 |                                                                          |
 | This program is free software; you can redistribute it and/or modify     |
 | it under the terms of the GNU General Public License as published by the |
 | Free Software Foundation. A copy of the license has been included with   |
 | these distribution in the COPYING file, if not go to www.fsf.org         |
 |                                                                          |
 | As a special exception, you are granted the permissions to link this     |
 | program with every library, which license fulfills the Open Source       |
 | Definition as published by the Open Source Initiative (OSI).             |
 *--------------------------------------------------------------------------*/
package org.rapla.plugin.dhbwterminal.server;
import org.rapla.framework.Configuration;
import org.rapla.framework.PluginDescriptor;
import org.rapla.framework.RaplaContextException;
import org.rapla.framework.logger.Logger;
import org.rapla.server.ServerServiceContainer;

/**
   This is a demonstration of a rapla-plugin. It adds a sample usecase and option
   to the rapla-system.
 */

public class TerminalServerPlugin implements PluginDescriptor<ServerServiceContainer>
{
    Logger logger;
    
    public TerminalServerPlugin(Logger logger) {
        this.logger = logger;
    }
   
    public Logger getLogger() {
        return logger;
    }

    /**
     * @throws RaplaContextException 
     * @see org.rapla.framework.PluginDescriptor
     */
    public void provideServices(ServerServiceContainer container, Configuration config) throws RaplaContextException {
        if ( !config.getAttributeAsBoolean("enabled", false) )
        	return;

    	container.addWebpage("terminal-export",SteleExportPageGenerator.class, config  );
    	container.addWebpage("terminal-kurse", SteleKursUebersichtPageGenerator.class, config );
    	container.addWebpage("terminal-kurse2", SteleKursUebersichtPageGenerator2.class, config );
    	container.addWebpage("terminal-kurse3", SteleKursUebersichtPageGenerator3.class, config );
//        try {
//            RaplaResourcePageGenerator resourcePageGenerator = container.getContext().lookup(RaplaResourcePageGenerator.class);
//            // registers the standard calendar files
//            
//            URL resource = this.getClass().getResource("/org/rapla/plugin/dhbwterminal/kursuebersicht.css");
//			resourcePageGenerator.registerResource( "kursuebersicht.css", "text/css", resource);
//
//            resource = this.getClass().getResource("/org/rapla/plugin/dhbwterminal/kursuebersicht2.css");
//            resourcePageGenerator.registerResource( "kursuebersicht2.css", "text/css", resource);
//
//            resource = this.getClass().getResource("/org/rapla/plugin/dhbwterminal/kursuebersicht3.css");
//            resourcePageGenerator.registerResource( "kursuebersicht3.css", "text/css", resource);
//
//
//        } catch ( Exception ex) {
//        	getLogger().error("Could not initialize terminal plugin on server" , ex);
//        }
    }

}

