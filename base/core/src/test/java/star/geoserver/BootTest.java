package star.geoserver;

import java.io.File;
import java.util.Properties;

import org.geoserver.catalog.Catalog;
import org.geoserver.catalog.impl.CatalogImpl;
import org.geoserver.config.GeoServer;
import org.geoserver.config.GeoServerDataDirectory;
import org.geoserver.config.impl.GeoServerImpl;
import org.geoserver.ows.ClasspathPublisher;
import org.geoserver.ows.Dispatcher;
import org.geoserver.ows.OWSHandlerMapping;
import org.geoserver.platform.GeoServerExtensions;
import org.geoserver.platform.GeoServerResourceLoader;
import org.geoserver.wfs.WFSInfoImpl;
import org.geoserver.wms.WMSInfoImpl;
import org.geoserver.wms.dimension.DimensionDefaultValueSelectionStrategyFactory;
import org.geoserver.wms.dimension.impl.DimensionDefaultValueSelectionStrategyFactoryImpl;
import org.geotools.filter.FilterFactoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opengis.filter.FilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BootTest {

	static {
		System.setProperty("org.geotools.referencing.forceXY", "true");
	}
	
	@Test
	public void testLoad() {
		
		
		
	}
	
	@Configuration
	@ImportResource({
		"classpath:/gs-wfs-context.xml",
		"classpath:/gs-wms-context.xml",
	})
	public static class Context {
		
		@Bean
		GeoServer geoServer(Catalog catalog) {
			GeoServerImpl server = new GeoServerImpl();
			WMSInfoImpl wmsInfo = new WMSInfoImpl();
			server.add(wmsInfo);
			WFSInfoImpl wfsInfo = new WFSInfoImpl();
			server.add(wfsInfo);
			server.setCatalog(catalog);
			return server;
		}
		
		@Bean
		GeoServerDataDirectory dataDirectory() {
			return new GeoServerDataDirectory(new File("target/datadir"));
		}
		
		@Bean
		GeoServerResourceLoader resourceLoader(GeoServerDataDirectory dataDirectory) throws Exception {
			return new GeoServerResourceLoader(dataDirectory);
		}
		
		@Bean
		Catalog catalog() {
			return new CatalogImpl();
		}

		@Bean
		ClasspathPublisher classpathPublisher() {
			return new ClasspathPublisher();
		}
		
		@Bean
		FilterFactory filterFactory() {
			return new FilterFactoryImpl();
		}

		@Bean
		Dispatcher dispatcher() {
			return new Dispatcher();
		}
		
//		@Bean
//		OWSHandlerMapping owsDispatherMapping(Catalog catalog, Dispatcher dispatcher) {
//			OWSHandlerMapping mapping = new OWSHandlerMapping(catalog);
//			mapping.setAlwaysUseFullPath(true);
//			mapping.setOrder(Integer.MAX_VALUE - 2);
//			Properties mappings = new Properties();
//			mappings.put("/ows", dispatcher);
//			mappings.put("/ows/**", dispatcher);
//			mappings.put("/wms", dispatcher);
//			mappings.put("/wms/**", dispatcher);
//			mapping.setMappings(mappings);
//			return mapping;
//		}

		@Bean
		DimensionDefaultValueSelectionStrategyFactory dimensionDefaultValueSelectionStrategyFactory() {
			DimensionDefaultValueSelectionStrategyFactoryImpl dim = new DimensionDefaultValueSelectionStrategyFactoryImpl();
			return dim;
		}

		@Bean 
		GeoServerExtensions extensions() {
			return new GeoServerExtensions();
		}
		
	}
	
}
