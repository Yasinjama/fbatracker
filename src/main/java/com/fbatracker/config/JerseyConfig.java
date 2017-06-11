package com.fbatracker.config;

import javax.annotation.PostConstruct;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fbatracker.rest.v1.AuthenticationResource;
import com.fbatracker.rest.v1.CORSFilter;
import com.fbatracker.rest.v1.UserAccountResource;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

@Component
public class JerseyConfig extends ResourceConfig {

	@Value("${spring.jersey.application-path:/}")
	private String apiPath;
	
	@PostConstruct
	public void init(){
		this.configureSwagger();
	}

	public JerseyConfig() {
		this.registerEndpoints();
	}

	private void configureSwagger() {
	     // Available at localhost:port/api/swagger.json
		this.register(ApiListingResource.class);
		this.register(SwaggerSerializers.class);
		BeanConfig config = new BeanConfig();
		config.setConfigId("FBA Tracker");
		config.setTitle("FBA Tracker swagger");
		config.setVersion("v1");
		config.setContact("Yasin Jama");
		config.setSchemes(new String[] { "https", "http" });
		config.setBasePath(this.apiPath);
		config.setResourcePackage("com.fbatracker.rest.v1");
		config.setPrettyPrint(true);
		config.setScan(true);
		
	}

	
	private void registerEndpoints() {

//		this.register(CORSFilter.class);
		this.register(UserAccountResource.class);
		this.register(AuthenticationResource.class);
	}
}
