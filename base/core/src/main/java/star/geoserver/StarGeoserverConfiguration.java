package star.geoserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource({"classpath:/star-geoserver.properties"})
public class StarGeoserverConfiguration {

	static {
		System.setProperty("org.geotools.referencing.forceXY", "true");
	}

}
