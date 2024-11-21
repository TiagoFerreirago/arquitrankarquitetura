package com.architrack.config;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.architrack.contenttype.yml.YamlAbstractJackson2HttpMessage;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${cors.originpatterns:default}")
	private String originPatterns = "";
	/*@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	
		configurer.favorParameter(true)
		.parameterName("MediaType")
		.ignoreAcceptHeader(true)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML);
		
	}*/

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorParameter(false)
		.ignoreAcceptHeader(false)
		.useRegisteredExtensionsOnly(false)
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json",MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlAbstractJackson2HttpMessage() );
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
	    // Divide a string de origens permitidas em um array
	    String[] allowedOrigins = originPatterns.split(",");

	    registry.addMapping("/**")
	            .allowedMethods("*")
	            .allowedOrigins(allowedOrigins)
	            .allowCredentials(true);
	}

	
	
	
	
}
