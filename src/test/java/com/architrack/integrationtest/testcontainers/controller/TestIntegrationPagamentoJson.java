package com.architrack.integrationtest.testcontainers.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.architrack.entities.Projeto;
import com.architrack.integrationtest.testcontainers.swagger.AbstractIntegrationTest;
import com.architrack.test.vo.PagamentosVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class TestIntegrationPagamentoJson extends AbstractIntegrationTest  {

	private static ObjectMapper objectMapper;
	
	private static RequestSpecification specification;
	
	private static PagamentosVo pagamento;
	
	@BeforeAll
	private static void setup() {
		
		objectMapper = new ObjectMapper();
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		pagamento = new PagamentosVo();
	}
	
	public void mockPagamentos() {
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Projeto projeto = new Projeto();
		projeto.setId(4L);
		
		try {
			pagamento.setDataPagamento(format.parse("08/11/2024"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		pagamento.setKey(1L);
		pagamento.setProjeto(projeto);
		pagamento.setStatus("Concluido");
		pagamento.setValor(54000.00);
	}
	@Test
	@Order(1)
	public void testCreate() throws JsonMappingException, JsonProcessingException {
		mockPagamentos();
		specification = new RequestSpecBuilder().
				addHeader("Origin","http://localhost:8080")
				.setPort(8888)
				.setBasePath("/api/v1/pagamento")
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
				
		
		var content = given()
				.spec(specification)
				.contentType("application/json")
				.body(pagamento)
				.when()
				.post()
				.then()
				.statusCode(200)
				.extract()
				.body().asString();
		
		PagamentosVo person = objectMapper.readValue(content, PagamentosVo.class);
		person = pagamento;
		
		assertNotNull(person);
		assertNotNull(person.getDataPagamento());
		assertNotNull(person.getKey());
		assertNotNull(person.getStatus());
		assertNotNull(person.getValor());
		assertNotNull(person.getProjeto());
		
		assertEquals("Concluido", person.getStatus());
		assertEquals(54000D, person.getValor());
		assertEquals(1, person.getKey());
		assertEquals(4, person.getProjeto().getId());
		assertEquals("Fri Nov 08 00:00:00 BRT 2024", person.getDataPagamento().toString());
		
	}
	
	@Test
	@Order(2)
	public void testCreateWithOriginInvalid() {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://www.siteinvalido.com")
				.setBasePath("api/v1/pagamento")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL))
				.build();
		
		var content = given().spec(specification)
				.contentType("application/json")
				.body(pagamento)
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
		mockPagamentos();
		specification = new RequestSpecBuilder()
				.addHeader("Origin","http://localhost:8080")
				.setBasePath("api/v1/pagamento")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given().spec(specification)
				.contentType("application/json")
				.pathParam("id", pagamento.getKey())
				.when()
				.get("{id}")
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();
		
		PagamentosVo person = objectMapper.readValue(content, PagamentosVo.class);
		pagamento = person;
		
		assertNotNull(person);
		assertNotNull(person.getDataPagamento());
		assertNotNull(person.getKey());
		assertNotNull(person.getStatus());
		assertNotNull(person.getValor());
		
		assertEquals("Concluido", person.getStatus());
		assertEquals(54000D, person.getValor());
		assertEquals(1, person.getKey());
		assertEquals("Fri Nov 08 00:00:00 BRT 2024", person.getDataPagamento().toString());
	}
	
	@Test
	@Order(4)
	public void testFindByIdOriginInvalid() {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://www.siteinvalido.com")
				.setBasePath("api/v1/pagamento")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given().spec(specification)
				.contentType("application/json")
				.pathParam("id", pagamento.getKey())
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
