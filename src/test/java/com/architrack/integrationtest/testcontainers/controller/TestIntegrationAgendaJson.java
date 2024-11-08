package com.architrack.integrationtest.testcontainers.controller;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.architrack.integrationtest.testcontainers.swagger.AbstractIntegrationTest;
import com.architrack.test.vo.AgendaVo;
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
public class TestIntegrationAgendaJson extends AbstractIntegrationTest {

	
	private static RequestSpecification specification;
	
	private static ObjectMapper objectMapper;
	
	private static AgendaVo agenda;
	
	@BeforeAll
	public static void setup() {
		agenda = new AgendaVo();
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	public void mockAgenda() {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			agenda.setDataInicio(format.parse("12/10/2024"));
			agenda.setDataFim(format.parse("15/10/2024"));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		agenda.setKey(1L);
		agenda.setProjeto(null);
	}
	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		mockAgenda();
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://localhost:8080")
				.setBasePath("/api/v1/agenda")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		
		var content = given().spec(specification)
				.contentType("application/json")
				.body(agenda)
				.when()
				.post()
				.then()
				.statusCode(200)
				.extract()
				.body().asString();
		
		AgendaVo person = objectMapper.readValue(content, AgendaVo.class);
		agenda = person;
		
		assertTrue(person.getKey() > 0);
		assertNull(person.getProjeto());
		assertNotNull(person);
		assertNotNull(person.getDataFim());
		assertNotNull(person.getDataInicio());
		assertNotNull(person.getKey());
		
		assertEquals(1, person.getKey());
		assertEquals("Sat Oct 12 00:00:00 BRT 2024", person.getDataInicio().toString());
		assertEquals("Tue Oct 15 00:00:00 BRT 2024", person.getDataFim().toString());
				
				
	}
	@Test
	@Order(2)
	public void testCreateWithOriginInvalid() {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://www.siteinvalido.com")
				.setBasePath("api/v1/agenda")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given().spec(specification)
				.contentType("application/json")
				.body(agenda)
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
				.setBasePath("api/v1/agenda")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given()
				.spec(specification)
				.contentType("application/json")
				.pathParam("id", agenda.getKey())
				.when()
				.get("{id}")
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();
		
		
		AgendaVo person = objectMapper.readValue(content, AgendaVo.class);
		agenda = person;
		
		assertTrue(person.getKey() > 0);
		assertNull(person.getProjeto());
		assertNotNull(person);
		assertNotNull(person.getDataFim());
		assertNotNull(person.getDataInicio());
		assertNotNull(person.getKey());
		
		assertEquals(1, person.getKey());
		assertEquals("Sat Oct 12 00:00:00 BRT 2024", person.getDataInicio().toString());
		assertEquals("Tue Oct 15 00:00:00 BRT 2024", person.getDataFim().toString());
	}
	@Test
	@Order(4)
	public void testFindByIdOriginInvalid() {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://www.siteinvalido.com")
				.setBasePath("api/v1/agenda")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given()
				.spec(specification)
				.contentType("application/json")
				.pathParam("id", agenda.getKey())
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
