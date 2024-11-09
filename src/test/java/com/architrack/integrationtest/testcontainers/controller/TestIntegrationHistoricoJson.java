package com.architrack.integrationtest.testcontainers.controller;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.architrack.integrationtest.testcontainers.swagger.AbstractIntegrationTest;
import com.architrack.test.vo.HistoricoVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class TestIntegrationHistoricoJson extends AbstractIntegrationTest {

	
	private static RequestSpecification specification;
	
	private static ObjectMapper objectMapper;
	
	private static HistoricoVo historico;
	
	@BeforeAll
	public static void setup() {
		
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		historico = new HistoricoVo();
	}
	
	public void mockHistorico() {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			historico.setDataMudanca(format.parse("15/12/2024"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		historico.setDescricaoMudanca("Licenca");
		historico.setKey(1L);
		historico.setProjeto(null);
		
	}
	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		mockHistorico();
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://localhost:8080")
				.setBasePath("/api/v1/historico")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		
		var content = given().spec(specification)
				.contentType("application/json")
				.body(historico)
				.when()
				.post()
				.then()
				.statusCode(200)
				.extract()
				.body().asString();
		
		HistoricoVo person = objectMapper.readValue(content, HistoricoVo.class);
		historico = person;
		
		assertTrue(person.getKey() > 0);
		assertNotNull(person);
		assertNotNull(person.getKey());
		assertNotNull(person.getDataMudanca());
		assertNotNull(person.getDescricaoMudanca());
		assertNull(person.getProjeto());
		
		assertEquals(1, person.getKey());
		assertEquals("Sun Dec 15 00:00:00 BRT 2024", person.getDataMudanca().toString());
		assertEquals("Licenca", person.getDescricaoMudanca());
			
	}
	@Test
	@Order(2)
	public void testCreateWithOriginInvalid() {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://www.siteinvalido.com")
				.setBasePath("api/v1/historico")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given().spec(specification)
				.contentType("application/json")
				.body(historico)
				.when()
				.post()
				.then()
				.statusCode(403)
				.extract()
				.body()
				.asString();
		
		assertNotNull(content);
		assertEquals("Invalid CORS request",content);
		
	}
	
	@Test
	@Order(3)
	public void testFindById() throws JsonMappingException, JsonProcessingException {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://localhost:8080")
				.setBasePath("api/v1/historico")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given().spec(specification)
				.contentType("application/json")
				.pathParam("id", historico.getKey())
				.when()
				.get("{id}")
				.then()
				.statusCode(200)
				.extract()
				.body().asString();
		
		HistoricoVo person = objectMapper.readValue(content, HistoricoVo.class);
		historico = person;
		
		assertTrue(person.getKey() > 0);
		assertNotNull(person);
		assertNotNull(person.getKey());
		assertNotNull(person.getDataMudanca());
		assertNotNull(person.getDescricaoMudanca());
		assertNull(person.getProjeto());
		
		assertEquals(1, person.getKey());
		assertEquals("Sun Dec 15 00:00:00 BRT 2024", person.getDataMudanca().toString());
		assertEquals("Licenca", person.getDescricaoMudanca());
	}
	
	@Test
	@Order(4)
	public void testFindByIdOriginInvalid() {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://www.siteinvalido.com")
				.setBasePath("api/v1/historico")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given().spec(specification)
				.contentType("application/json")
				.pathParam("id",historico.getKey())
				.when()
				.get("{id}")
				.then()
				.statusCode(403)
				.extract()
				.body()
				.asString();
		
		assertNotNull(content);
		assertEquals("Invalid CORS request",content);
				
	}
}
