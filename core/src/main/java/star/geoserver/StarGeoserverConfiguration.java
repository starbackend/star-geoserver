package star.geoserver;

import org.cwatch.geoserver.CwatchGeoserverConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:/star-geoserver.properties"})
@Import(CwatchGeoserverConfiguration.class)
public class StarGeoserverConfiguration {

}
