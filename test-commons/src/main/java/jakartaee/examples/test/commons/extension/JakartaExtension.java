package jakartaee.examples.test.commons.extension;


import org.jboss.arquillian.core.spi.LoadableExtension;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rmpestano
 */
public class JakartaExtension implements LoadableExtension {
    
    @Override
    public void register(ExtensionBuilder builder) {
        builder.observer(SetupJakartaEEContainer.class);
    }
    
}