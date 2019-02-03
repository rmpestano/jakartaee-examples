/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakartaee.examples.test.commons.extension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.jboss.arquillian.container.spi.event.SetupContainer;
import org.jboss.arquillian.core.api.annotation.Observes;
import org.jboss.arquillian.core.spi.EventContext;

/**
 *
 * @author rmpestano
 */
class SetupJakartaEEContainer {

    private String chameleonTarget;

    public void beforeSetup(@Observes(precedence = 100) EventContext<SetupContainer> setup) throws Exception {
        SetupContainer event = setup.getEvent();
        chameleonTarget = event.getContainer().getContainerConfiguration().getContainerProperty("chameleonTarget");
        setup.proceed();
    }

    public void afterSetup(@Observes(precedence = -1) EventContext<SetupContainer> setup) throws Exception {
        SetupContainer event = setup.getEvent();
        if (isWildfly(event)) {
            addWildflyCustomConfigFile(event);
        }
        setup.proceed();
    }

    private boolean isWildfly(SetupContainer event) {
        return chameleonTarget.startsWith("wildfly");
    }

    private void addWildflyCustomConfigFile(SetupContainer event) {
        String[] nameVersionAndMode = chameleonTarget.split(":");
        String serverVersion = nameVersionAndMode[1];
        // server distribution will be at:
        // target/server/wildfly-dist_version/wildfly-version
        String serverDistFolder = "target/server/wildfly-dist_" + serverVersion + "/wildfly-" + serverVersion;
        File configFolder = new File(serverDistFolder + "/standalone/configuration");
        if (!configFolder.exists()) {
            throw new RuntimeException(String.format("Could not find server %s configuration folder at %s",
                chameleonTarget, configFolder.getAbsolutePath()));
        }
        addCustomConfig(configFolder);
    }

    private void addCustomConfig(File configFolder) {
        try {
            File defaultConfigFile = new File(configFolder.getPath() + "/standalone.xml");
            Files.copy(Paths.get(SetupJakartaEEContainer.class.getResource("/custom-standalone.xml").getPath()),
                defaultConfigFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(
                "Could not copy custom config file into folder " + configFolder.getAbsolutePath(), e);
        }
    }

}
