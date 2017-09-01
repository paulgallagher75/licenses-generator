package org.jboss.snowdrop.licenses;

import org.jboss.snowdrop.licenses.maven.MavenArtifact;
import org.jboss.snowdrop.licenses.properties.GeneratorProperties;
import org.jboss.snowdrop.licenses.xml.DependencyElement;
import org.jboss.snowdrop.licenses.xml.LicenseSummary;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="mailto:gytis@redhat.com">Gytis Trikleris</a>
 */
public class LicenseSummaryFactoryTest {

    @Test
    public void shouldGetLicenseSummaryForCoordinates() {
        LicenseSummaryFactory factory = new LicenseSummaryFactory();
        MavenArtifact mavenArtifact = new MavenArtifact("junit", "junit", "4.12", "jar");
        LicenseSummary dependencyContainer = factory.getLicenseSummary(mavenArtifact);
        assertThat(dependencyContainer.getDependencies()).containsOnly(
                new DependencyElement("org.hamcrest", "hamcrest-core", "1.3"));
    }

    @Test
    public void shouldGetLicenseSummaryForFile() {
        LicenseSummaryFactory factory = new LicenseSummaryFactory();
        LicenseSummary dependencyContainer = factory.getLicenseSummary("target/test-classes/test-pom.xml");
        assertThat(dependencyContainer.getDependencies()).containsOnly(
                new DependencyElement("junit", "junit", "4.11"),
                new DependencyElement("junit", "junit", "4.12"),
                new DependencyElement("org.hamcrest", "hamcrest-core", "1.3"));
    }

    @Test
    public void shouldGetLicenseSummaryForFileWithoutDependencyManagement() {
        GeneratorProperties properties = new GeneratorProperties("test_properties/no-dependency-management.properties");
        LicenseSummaryFactory factory = new LicenseSummaryFactory(properties);
        LicenseSummary dependencyContainer = factory.getLicenseSummary("target/test-classes/test-pom.xml");
        assertThat(dependencyContainer.getDependencies()).containsOnly(
                new DependencyElement("junit", "junit", "4.12"),
                new DependencyElement("org.hamcrest", "hamcrest-core", "1.3"));
    }

}