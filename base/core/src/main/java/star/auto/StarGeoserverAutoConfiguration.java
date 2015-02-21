package star.auto;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import star.geoserver.StarGeoserverConfiguration;

@Configuration
@Import(StarGeoserverConfiguration.class)
public class StarGeoserverAutoConfiguration {

}
