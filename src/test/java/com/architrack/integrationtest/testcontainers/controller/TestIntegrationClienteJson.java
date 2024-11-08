package com.architrack.integrationtest.testcontainers.controller;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.architrack.entities.Cliente;
import com.architrack.entities.PessoaFisica;
import com.architrack.entities.PessoaJuridica;
import com.architrack.entities.Projeto;
import com.architrack.integrationtest.testcontainers.swagger.AbstractIntegrationTest;
import com.architrack.test.vo.ClienteVo;
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
public class TestIntegrationClienteJson extends AbstractIntegrationTest {

	
	private static RequestSpecification specification;
	
	private static ObjectMapper objectMapper;
	
	private static ClienteVo cliente;
	
	@BeforeAll
	public static void setup() {
		cliente = new ClienteVo();
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	
	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		mockCliente();
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://localhost:8080")
				.setBasePath("/api/v1/cliente")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		
		var content = given().spec(specification)
				.contentType("application/json")
				.body(cliente)
				.when()
				.post()
				.then()
				.statusCode(200)
				.extract()
				.body().asString();
		
		ClienteVo person = objectMapper.readValue(content, ClienteVo.class);
		cliente = person;
		
		assertTrue(person.getKey() > 0);
		assertNotNull(person);
		assertNotNull(person.getEmail());
		assertNotNull(person.getKey());
		assertNotNull(person.getNome());
		assertNotNull(person.getPf());
		assertNotNull(person.getTelefone());
		
		assertEquals(1, person.getKey());
		assertEquals("gustavo@gmail.com", person.getEmail());
		assertEquals("Gustavo Lima", person.getNome());
		assertEquals("81-123456789", person.getTelefone());
		assertEquals("765321456-10", person.getPf().getRg());
		assertEquals("897456321-59", person.getPf().getCpf());
				
				
	}
	@Test
	@Order(2)
	public void testCreateWithOriginInvalid() {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://www.siteinvalido.com")
				.setBasePath("/api/v1/cliente")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given().spec(specification)
				.contentType("application/json")
				.body(cliente)
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
				.setBasePath("/api/v1/cliente")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given()
				.spec(specification)
				.contentType("application/json")
				.pathParam("id", cliente.getKey())
				.when()
				.get("{id}")
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();
		
		
		ClienteVo person = objectMapper.readValue(content, ClienteVo.class);
		cliente = person;
		
		assertTrue(person.getKey() > 0);
		assertNotNull(person);
		assertNotNull(person.getEmail());
		assertNotNull(person.getKey());
		assertNotNull(person.getNome());
		assertNotNull(person.getTelefone());
		
		assertEquals(1, person.getKey());
		assertEquals("gustavo@gmail.com", person.getEmail());
		assertEquals("Gustavo Lima", person.getNome());
		assertEquals("81-123456789", person.getTelefone());
		
	}
	@Test
	@Order(4)
	public void testFindByIdOriginInvalid() {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://www.siteinvalido.com")
				.setBasePath("/api/v1/cliente")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given()
				.spec(specification)
				.contentType("application/json")
				.pathParam("id", cliente.getKey())
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
	
	private void mockCliente() {
		PessoaFisica tipo = new PessoaFisica("765321456-10", "897456321-59", null);
		Projeto projeto = new Projeto();
		projeto.setId(1L);
		cliente.setEmail("gustavo@gmail.com");
		cliente.setKey(1L);
		cliente.setNome("Gustavo Lima");
		cliente.setPf(tipo);
		cliente.setPj(null);
		cliente.setProjeto(projeto);
		cliente.setTelefone("81-123456789");
	}
	
	private void mockClienteJuridico() {
		PessoaJuridica tipo = new PessoaJuridica("12.345.678/0001-90", "123.456.789.123", null);
		Projeto projeto = new Projeto();
		projeto.setId(1L);
		cliente.setEmail("gustavo@gmail.com");
		cliente.setKey(1L);
		cliente.setNome("Gustavo Lima");
		cliente.setPf(null);
		cliente.setPj(tipo);
		cliente.setProjeto(projeto);
		cliente.setTelefone("81-123456789");
	}
}
