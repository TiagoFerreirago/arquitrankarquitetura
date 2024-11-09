package com.architrack.integrationtest.testcontainers.controller;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.architrack.entities.Arquiteto;
import com.architrack.entities.Cliente;
import com.architrack.integrationtest.testcontainers.swagger.AbstractIntegrationTest;
import com.architrack.test.vo.EnderecoVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class TestIntegrationEnderecoJson extends AbstractIntegrationTest {

	
	private static RequestSpecification specification;
	
	private static ObjectMapper objectMapper;
	
	private static EnderecoVo endereco;
	
	@BeforeAll
	public static void setup() {
		endereco = new EnderecoVo();
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	public void mockEndereco() {
		Arquiteto arquiteto = new Arquiteto();
		arquiteto.setId(2L);
		Cliente cliente = new Cliente();
		cliente.setId(9L);
		
		endereco.setArquiteto(arquiteto);
		endereco.setBairro("Marilia");
		endereco.setCep("41235-415");
		endereco.setCidade("Sao Bernado");
		endereco.setCliente(cliente);
		endereco.setEstado("SP");
		endereco.setKey(1L);
		endereco.setRua("Rua das Neves");
	}
	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		mockEndereco();
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://localhost:8080")
				.setBasePath("/api/v1/endereco")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		
		var content = given().spec(specification)
				.contentType("application/json")
				.body(endereco)
				.when()
				.post()
				.then()
				.statusCode(200)
				.extract()
				.body().asString();
		
		EnderecoVo person = objectMapper.readValue(content, EnderecoVo.class);
		endereco = person;
		
		assertTrue(person.getKey() > 0);
		assertNotNull(person);
		assertNull(person.getCliente());
		assertNotNull(person.getKey());
		assertNull(person.getArquiteto());
		assertNotNull(person.getBairro());
		assertNotNull(person.getCep());
		assertNotNull(person.getCidade());
		assertNotNull(person.getEstado());
		assertNotNull(person.getRua());
		
		assertEquals(1, person.getKey());
		assertEquals("Marilia", person.getBairro());
		assertEquals("41235-415", person.getCep());
		assertEquals("Sao Bernado", person.getCidade());
		assertEquals("SP", person.getEstado());
		assertEquals("Rua das Neves", person.getRua());
		
				
				
	}
		@Test
		@Order(2)
		public void testCreateWithOriginInvalid() {
			
			specification = new RequestSpecBuilder()
					.addHeader("Origin", "http://www.siteinvalido.com")
					.setBasePath("api/v1/endereco")
					.setPort(8888)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
			
			var content = given().spec(specification)
					.contentType("application/json")
					.body(endereco)
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
					.setBasePath("api/v1/endereco")
					.setPort(8888)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
			
			var content = given()
					.spec(specification)
					.contentType("application/json")
					.pathParam("id", endereco.getKey())
					.when()
					.get("{id}")
					.then()
					.statusCode(200)
					.extract()
					.body()
					.asString();
			
			
			EnderecoVo person = objectMapper.readValue(content, EnderecoVo.class);
			endereco = person;
			
			assertTrue(person.getKey() > 0);
			assertNotNull(person);
			assertNull(person.getCliente());
			assertNotNull(person.getKey());
			assertNull(person.getArquiteto());
			assertNotNull(person.getBairro());
			assertNotNull(person.getCep());
			assertNotNull(person.getCidade());
			assertNotNull(person.getEstado());
			assertNotNull(person.getRua());
			
			assertEquals(1, person.getKey());
			assertEquals("Marilia", person.getBairro());
			assertEquals("41235-415", person.getCep());
			assertEquals("Sao Bernado", person.getCidade());
			assertEquals("SP", person.getEstado());
			assertEquals("Rua das Neves", person.getRua());
		}
		@Test
		@Order(4)
		public void testFindByIdOriginInvalid() {
			
			specification = new RequestSpecBuilder()
					.addHeader("Origin", "http://www.siteinvalido.com")
					.setBasePath("api/v1/endereco")
					.setPort(8888)
					.addFilter(new RequestLoggingFilter(LogDetail.ALL))
					.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
			
			var content = given()
					.spec(specification)
					.contentType("application/json")
					.pathParam("id", endereco.getKey())
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
