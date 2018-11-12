package dk.presentation.thorntail;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Boot class responsible for activating the jaxrs framework.
 * <pre>
 *   For running the application from the IDE, create a run configuration with the main class {@link org.wildfly.swarm.Swarm}
 *   and point the working directory to the thorntail directory
 * </pre>
 */
@ApplicationPath("/rest")
public class ThorntailApplication extends Application {
}