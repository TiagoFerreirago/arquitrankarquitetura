package com.architrack.integrationtest.testcontainers.controller;

import com.architrack.integrationtest.testcontainers.swagger.AbstractIntegrationTest;
import com.architrack.test.vo.DocumentoVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class TestIntegrationDocumentoJson extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper objectMapper;
    private static DocumentoVo documento;

    @BeforeAll
    public static void setup() {
        // Inicializa o objeto DocumentoVo e ObjectMapper
        documento = new DocumentoVo();
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

   

    @Test
    @Order(1)
    public void testCreate() throws JsonMappingException, JsonProcessingException {
        // Prepara o documento para o teste
        mockDocumento();

        // Configura a especificação da requisição
        specification = new RequestSpecBuilder()
                .addHeader("Origin", "http://localhost:8080")
                .setBasePath("/api/v1/documento")
                .setPort(8888)
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();

        // Envia a requisição POST e captura a resposta
        var content = given()
                .spec(specification)
                .contentType("application/json")
                .body(documento)
                .when()
                .post()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        System.out.println(content);
        DocumentoVo person = objectMapper.readValue(content, DocumentoVo.class);
        documento = person;
        System.out.println("Documento being sent: " + objectMapper.writeValueAsString(documento));

        // Asserções para validar a resposta
        assertTrue(person.getKey() > 0);
        assertNotNull(person);
        assertNotNull(person.getKey());
        assertNotNull(person.getDataEnvio());
        assertNotNull(person.getNomeDocumento());
        assertNull(person.getProjeto());
        assertNull(person.getCliente());
        assertNotNull(person.getTipoDocumento());

        assertEquals(1, person.getKey());
        assertEquals("Projeto Estrutural", person.getNomeDocumento());
        assertEquals("Tecnico", person.getTipoDocumento());
        assertEquals("Wed Dec 25 00:00:00 BRT 2024", person.getDataEnvio().toString());
      
    }
    
    public void mockDocumento() {
        // Prepara um documento de teste com dados fictícios
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        
        // Configura a data de envio
        try {
            documento.setDataEnvio(format.parse("25/12/2024"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        documento.setCliente(null);
        documento.setKey(1L);
        documento.setNomeDocumento("Projeto Estrutural");
        documento.setProjeto(null);
        documento.setTipoDocumento("Tecnico");
    }
    @Test
	@Order(2)
	public void testCreateWithOriginInvalid() {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://www.siteinvalido.com")
				.setBasePath("api/v1/documento")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given().spec(specification)
				.contentType("application/json")
				.body(documento)
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
				.setBasePath("api/v1/documento")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given()
				.spec(specification)
				.contentType("application/json")
				.pathParam("id", documento.getKey())
				.when()
				.get("{id}")
				.then()
				.statusCode(200)
				.extract()
				.body()
				.asString();
		
		
		DocumentoVo person = objectMapper.readValue(content, DocumentoVo.class);
		documento = person;
		
		 assertTrue(person.getKey() > 0);
	        assertNotNull(person);
	        assertNotNull(person.getKey());
	        assertNotNull(person.getDataEnvio());
	        assertNotNull(person.getNomeDocumento());
	        assertNull(person.getProjeto());
	        assertNull(person.getCliente());
	        assertNotNull(person.getTipoDocumento());

	        assertEquals(1, person.getKey());
	        assertEquals("Projeto Estrutural", person.getNomeDocumento());
	        assertEquals("Tecnico", person.getTipoDocumento());
	        assertEquals("Wed Dec 25 00:00:00 BRT 2024", person.getDataEnvio().toString());
	}
	@Test
	@Order(4)
	public void testFindByIdOriginInvalid() {
		
		specification = new RequestSpecBuilder()
				.addHeader("Origin", "http://www.siteinvalido.com")
				.setBasePath("api/v1/documento")
				.setPort(8888)
				.addFilter(new RequestLoggingFilter(LogDetail.ALL))
				.addFilter(new ResponseLoggingFilter(LogDetail.ALL)).build();
		
		var content = given()
				.spec(specification)
				.contentType("application/json")
				.pathParam("id", documento.getKey())
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
