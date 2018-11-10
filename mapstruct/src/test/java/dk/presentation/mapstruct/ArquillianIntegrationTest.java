package dk.presentation.mapstruct;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.wildfly.swarm.undertow.WARArchive;

import java.io.File;

public class ArquillianIntegrationTest {

    /**
     * Setup the deployment for test with test dependencies and runtime dependencies
     */
    @Deployment
    public static WARArchive createDeployment() throws Exception {
        File[] files = Maven.resolver()
            .loadPomFromFile("pom.xml")
            .importTestDependencies()
            .resolve()
            .withTransitivity()
            .asFile();

        final WARArchive warArchive = ShrinkWrap.create(WARArchive.class)
            .addPackages(true, ThorntailApplication.class.getPackage())
            .addAsWebInfResource("META-INF/persistence.xml", "classes/META-INF/persistence.xml")
            .addAsWebInfResource("META-INF/bootstrap.sql", "classes/META-INF/bootstrap.sql")
            .addAsWebInfResource("project-defaults.yml", "classes/project-defaults.yml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addAsLibraries(files)
            .addAllDependencies();
        return warArchive;
    }

}
