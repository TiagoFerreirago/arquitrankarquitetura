package com.architrack.integrationtest.testcontainers.controller;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.architrack.entities.TipoPessoa;
import com.architrack.integrationtest.testcontainers.swagger.AbstractIntegrationTest;
import com.architrack.test.vo.ArquitetoVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class TestIntegrationArquitetoJson extends AbstractIntegrationTest {

	
	private static RequestSpecification specification;
	
	private static ObjectMapper objectMapper;
	
	private static ArquitetoVo arquiteto;
	
	@BeforeAll
	public static void setup() {
		arquiteto = new ArquitetoVo();
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	public void mockArquiteto() {
		TipoPessoa tipo = new TipoPessoa();
		tipo.setArquiteto(null);
		tipo.setId(1L);
		tipo.setTipo("Pessoa Juridica");
		
		arquiteto.setEmail("123@gmail.com");
		arquiteto.setKey(1L);
		arquiteto.setNome("Anna Green");
		arquiteto.setTelefone("123456789");
		arquiteto.setTipo(tipo);
	}
	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		mockArquiteto();
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://localhost:8080")
				.setBasePath("/api/v1/arquiteto")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		
		var content = given().spec(specification)
				.contentType("application/json")
				.body(arquiteto)
				.when()
				.post()
				.then()
				.statusCode(200)
				.extract()
				.body().asString();
		
		ArquitetoVo person = objectMapper.readValue(content, ArquitetoVo.class);
		arquiteto = person;
		
		assertTrue(person.getKey() > 0);
		assertNotNull(person);
		assertNotNull(person.getKey());
		assertNotNull(person.getEmail());
		assertNotNull(person.getNome());
		assertNotNull(person.getTelefone());
		assertNotNull(person.getTipo());
		
		
		assertEquals(1, person.getKey());
		assertEquals("123@gmail.com", person.getEmail());
		assertEquals("Anna Green", person.getNome());
		assertEquals("123456789", person.getTelefone());
		assertEquals("Pessoa Juridica", person.getTipo().getTipo());
		assertEquals(1, person.getTipo().getId());
				
				
	}
	
	@Test
	@Order(2)
	public void testCreateWithOriginInvalid() {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://www.siteinvalido.com")
				.setBasePath("api/v1/arquiteto")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given().spec(specification)
				.contentType("application/json")
				.body(arquiteto)
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
				.addHeader("Origin","http://localhost:8080")
				.setBasePath("api/v1/arquiteto")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given()
				.spec(specification)
				.contentType("application/json")
				.pathParam("id", arquiteto.getKey())
				.when()
				.get("{id}")
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();
		
		
		ArquitetoVo person = objectMapper.readValue(content, ArquitetoVo.class);
		arquiteto = person;
		
		assertTrue(person.getKey() > 0);
		assertNotNull(person);
		assertNotNull(person.getKey());
		assertNotNull(person.getEmail());
		assertNotNull(person.getNome());
		assertNotNull(person.getTelefone());
		
		
		assertEquals(1, person.getKey());
		assertEquals("123@gmail.com", person.getEmail());
		assertEquals("Anna Green", person.getNome());
		assertEquals("123456789", person.getTelefone());
	
	}
	@Test
	@Order(4)
	public void testFindByIdOriginInvalid() {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://www.siteinvalido.com")
				.setBasePath("api/v1/arquiteto")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given()
				.spec(specification)
				.contentType("application/json")
				.pathParam("id", arquiteto.getKey())
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
