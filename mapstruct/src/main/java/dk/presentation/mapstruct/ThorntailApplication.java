package dk.presentation.mapstruct;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Boot class responsible for activating the JAX-RS framework and the application.
 * <pre>
 *   For running the application from the IDE, create a run configuration with the main class {@link org.wildfly.swarm.Swarm}
 *   and point the working directory to the thorntail directory
 * </pre>
 */
@ApplicationPath("/rest")
public class ThorntailApplication extends Application {
}