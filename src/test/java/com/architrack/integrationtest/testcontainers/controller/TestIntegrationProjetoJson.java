package com.architrack.integrationtest.testcontainers.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.architrack.entities.Cliente;
import com.architrack.integrationtest.testcontainers.swagger.AbstractIntegrationTest;
import com.architrack.test.vo.ProjetoVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class TestIntegrationProjetoJson extends AbstractIntegrationTest  {

	
	private static ObjectMapper objectMapper;
	
	private static RequestSpecification specification;
	
	private static ProjetoVo projeto;
	
	@BeforeAll
	private static void setup() {
		
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		projeto = new ProjetoVo();
	}
	
	public void mockProjeto() {
		
		Cliente cliente = new Cliente();
		cliente.setId(4L);
		
		projeto.setAreaEmpreendimento("Construção civil");
		projeto.setCliente(cliente);
		projeto.setKey(1L);
	}
	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		mockProjeto();
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://localhost:8080")
				.setPort(8888)
				.setBasePath("/api/v1/projeto")
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given()
				.spec(specification)
				.contentType("application/json")
				.body(projeto)
				.when()
				.post()
				.then()
				.statusCode(200)
				.extract()
				.body().asString();
		
		ProjetoVo person = objectMapper.readValue(content, ProjetoVo.class);
		person = projeto;
		
		assertNotNull(person);
		assertNotNull(person.getCliente());
		assertNotNull(person.getKey());
		assertNotNull(person.getAreaEmpreendimento());

		assertEquals(1, person.getKey());
		assertEquals(4, person.getCliente().getId());
		assertEquals("Construção civil", person.getAreaEmpreendimento());


	}
	
	@Test
	@Order(2)
	public void testCreateWithOriginInvalid() {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://www.siteinvalido.com")
				.setBasePath("api/v1/projeto")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = given().spec(specification)
				.contentType("application/json")
				.body(projeto)
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
				.setBasePath("api/v1/projeto")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given().spec(specification)
				.contentType("application/json")
				.pathParam("id", projeto.getKey())
				.when()
				.get("{id}")
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();
		
		ProjetoVo person = objectMapper.readValue(content, ProjetoVo.class);
		projeto = person;
		
		assertNotNull(person);
		assertNotNull(person.getKey());
		assertNotNull(person.getAreaEmpreendimento());

		assertEquals(1, person.getKey());
		assertEquals("Construção civil", person.getAreaEmpreendimento());
	}
	
	@Test
	@Order(4)
	public void testFindByIdOriginInvalid() {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://www.siteinvalido.com")
				.setBasePath("api/v1/projeto")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given().spec(specification)
				.contentType("application/json")
				.pathParam("id", projeto.getKey())
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
