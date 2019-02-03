package jakartaee.examples.test.commons;
import static org.jboss.shrinkwrap.api.ShrinkWrap.create;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.arquillian.container.chameleon.runner.ArquillianChameleon;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import jakartaee.examples.test.commons.JakartaEEServer;

@RunWith(ArquillianChameleon.class)
@JakartaEEServer
public class CustomConfigTest {

	@Deployment
	public static WebArchive createDeployment() {
		return create(WebArchive.class);
	}
	
	@Test
	public void shouldGetSystemPropertyFromCustoConfigFile() {
		assertNotNull(System.getProperty("jakartaee.rocks"));
		assertTrue(System.getProperty("jakartaee.rocks").equals("true"));
	}
}
