package com.architrack.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.architrack.entities.Documento;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.repository.DocumentoRepository;
import com.architrack.service.DocumentoService;
import com.architrack.test.mocks.MockDocumento;
import com.architrack.vo.v1.DocumentoVo;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class TestUnitDocumento {

	
	private MockDocumento input;
	@InjectMocks
	private DocumentoService service;
	@Mock
	private DocumentoRepository repository;
	@BeforeEach
	void setUp() throws Exception {
		input = new MockDocumento();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() throws ParseException {
		Documento documento = input.mockDocumento(1);
		documento.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(documento));
		
		var result =service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.getKey());
		assertTrue(result.toString().contains("</api/v1/documento/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Wed Dec 25 00:00:00 BRT 2024", result.getDataEnvio().toString());
		assertEquals("Licença Para Construção 1", result.getNomeDocumento());
		assertEquals("Licenças 1", result.getTipoDocumento());
	}
	
	@Test
	void testCreate() throws ParseException {

		Documento documento = input.mockDocumento(1);
		documento.setId(1L);
		Documento persistence = documento;
		persistence.setId(1L);
		
		DocumentoVo documentoVo = input.mockDocumentoVo();
		documentoVo.setKey(1L);
		when(repository.save(any(Documento.class))).thenReturn(persistence);
		
		var result = service.create(documentoVo);
		
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.getKey());
		assertTrue(result.toString().contains("</api/v1/documento/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Wed Dec 25 00:00:00 BRT 2024", result.getDataEnvio().toString());
		assertEquals("Licença Para Construção 1", result.getNomeDocumento());
		assertEquals("Licenças 1", result.getTipoDocumento());
	}
	
	@Test
	void testUpdate() throws ParseException {
		
		Documento documento = input.mockDocumento(1);
		
		Documento persistence = documento;
		persistence.setId(1L);
		
		DocumentoVo documentoVo = input.mockDocumentoVo(1);
		documentoVo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(documento));
		when(repository.save(documento)).thenReturn(persistence);
		var result = service.update(documentoVo);
		
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.getKey());
		assertTrue(result.toString().contains("</api/v1/documento/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Wed Dec 11 00:00:00 BRT 2024", result.getDataEnvio().toString());
		assertEquals("Projeto Estrutural 1", result.getNomeDocumento());
		assertEquals("Técnico 1", result.getTipoDocumento());
	}
	
	@Test
	void testListAgenda() throws ParseException {
		
		List<Documento>documentosList = input.mockListDocumento();
		
		when(repository.findAll()).thenReturn(documentosList);
		
		
		var documentos = service.findAll();
		
		assertNotNull(documentos);
		assertEquals(10,documentos.size());
		
		var documentoTwo = documentos.get(2);
		
		assertNotNull(documentoTwo);
		assertNotNull(documentoTwo.getLinks());
		assertNotNull(documentoTwo.getKey());
		assertTrue(documentoTwo.toString().contains("</api/v1/documento/2>;rel=\"self\""));
		assertEquals(2, documentoTwo.getKey());
		assertEquals("Wed Dec 25 00:00:00 BRT 2024", documentoTwo.getDataEnvio().toString());
		assertEquals("Projeto Estrutural 2", documentoTwo.getNomeDocumento());
		assertEquals("Técnico 2", documentoTwo.getTipoDocumento());

		
		var documentoFour = documentos.get(4);
		
		assertNotNull(documentoFour);
		assertNotNull(documentoFour.getLinks());
		assertNotNull(documentoFour.getKey());
		assertTrue(documentoFour.toString().contains("</api/v1/documento/4>;rel=\"self\""));
		assertEquals(4, documentoFour.getKey());
		assertEquals("Wed Dec 25 00:00:00 BRT 2024", documentoFour.getDataEnvio().toString());
		assertEquals("Projeto Estrutural 4", documentoFour.getNomeDocumento());
		assertEquals("Técnico 4", documentoFour.getTipoDocumento());

		
		var documentoSeven = documentos.get(7);
		
		assertNotNull(documentoSeven);
		assertNotNull(documentoSeven.getLinks());
		assertNotNull(documentoSeven.getKey());
		assertTrue(documentoSeven.toString().contains("</api/v1/documento/7>;rel=\"self\""));
		assertEquals(7, documentoSeven.getKey());
		assertEquals("Wed Dec 25 00:00:00 BRT 2024", documentoSeven.getDataEnvio().toString());
		assertEquals("Licença Para Construção 7", documentoSeven.getNomeDocumento());
		assertEquals("Licenças 7", documentoSeven.getTipoDocumento());

	}
		
	@Test
	void testDeleteAgenda() throws ParseException {
		
		Documento documento = input.mockDocumento();
		documento.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(documento));
		
		service.delete(1L);
	}
	@Test
	void testCreateIsNull() throws ParseException {
		
	
		Exception exception = assertThrows(ResponseBadRequestHandlerException.class, () -> {
		service.create(null);});
		
		String messageActual = exception.getMessage();
		String messageExpected = "id request failed";
		
		assertTrue(messageActual.contains(messageExpected));
	}
	
	@Test
	void testUpdateIsNull(){
		

		Exception exception = assertThrows(ResponseBadRequestHandlerException.class, () -> {
		service.update(null);});
		
		String messageActual = exception.getMessage();
		String messageExpected = "id request failed";
		
		assertTrue(messageActual.contains(messageExpected));
	}
}
