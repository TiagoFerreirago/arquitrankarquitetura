package com.architrack.test.unit;

import static org.junit.jupiter.api.Assertions.*;
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

import com.architrack.entities.Arquiteto;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.repository.ArquitetoRepository;
import com.architrack.service.ArquitetoService;
import com.architrack.test.mocks.MockArquiteto;
import com.architrack.vo.v1.ArquitetoVo;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class TestUnitArquiteto {

	private MockArquiteto input;
	@InjectMocks
	private ArquitetoService service;
	@Mock
	private ArquitetoRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		input = new MockArquiteto();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() throws ParseException {
		
		Arquiteto Arquiteto = input.mockArquiteto(1);
		Arquiteto.setId(1L);
				
		when(repository.findById((long)1)).thenReturn(Optional.of(Arquiteto));
		
		var arquiteto = service.findById(1L);
		
		assertNotNull(arquiteto);
		assertNotNull(arquiteto.getKey());
		assertNotNull(arquiteto.getLinks());
		assertNotNull(arquiteto.getEnderecos());
		assertNotNull(arquiteto.getProjetos());
		assertTrue(arquiteto.toString().contains("</api/v1/arquiteto/1>;rel=\"self\""));
		assertEquals("987@gmail.com 1", arquiteto.getEmail());
		assertEquals(1, arquiteto.getKey());
		assertEquals("Harry Kane 1", arquiteto.getNome());
		assertEquals("987654321 1", arquiteto.getTelefone());
		assertEquals(5, arquiteto.getTipo().getId());
		assertEquals("Pessoa Fisica 1", arquiteto.getTipo().getTipo());
		
	}
	
	@Test
	void testCreate() throws ParseException {

		Arquiteto arquiteto = input.mockArquiteto(1);
		arquiteto.setId(1L);
		Arquiteto persistence = arquiteto;
		persistence.setId(1L);
		
		ArquitetoVo arquitetoVo = input.mockArquitetoVo(1);
		arquitetoVo.setKey(1L);
		
		when(repository.save(any(Arquiteto.class))).thenReturn(persistence);
		
		var result = service.create(arquitetoVo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertNotNull(result.getEnderecos());
		assertNotNull(result.getProjetos());
		assertTrue(result.toString().contains("</api/v1/arquiteto/1>;rel=\"self\""));
		assertEquals("987@gmail.com 1", result.getEmail());
		assertEquals(1, result.getKey());
		assertEquals("Harry Kane 1", result.getNome());
		assertEquals("987654321 1", result.getTelefone());
		assertEquals(5, result.getTipo().getId());
		assertEquals("Pessoa Fisica 1", result.getTipo().getTipo());
	}
	
	@Test
	void testUpdate() throws ParseException {
		
		Arquiteto Arquiteto = input.mockArquiteto(1);
		Arquiteto.setId((long)1);
		
		Arquiteto persistence = Arquiteto;
		persistence.setId((long)1);
		
		ArquitetoVo ArquitetoVo = input.mockArquitetoVo(1);
		ArquitetoVo.setKey((long)1);
		
		when(repository.findById((long)1)).thenReturn(Optional.of(Arquiteto));
		when(repository.save(Arquiteto)).thenReturn(persistence);
		
		var arquiteto = service.update(ArquitetoVo);
		
		assertNotNull(arquiteto);
		assertNotNull(arquiteto.getKey());
		assertNotNull(arquiteto.getLinks());
		assertNotNull(arquiteto.getEnderecos());
		assertNotNull(arquiteto.getProjetos());
		assertTrue(arquiteto.toString().contains("</api/v1/arquiteto/1>;rel=\"self\""));
		assertEquals("123@gmail.com 1", arquiteto.getEmail());
		assertEquals(1, arquiteto.getKey());
		assertEquals("Anna Green 1", arquiteto.getNome());
		assertEquals("123456789 1", arquiteto.getTelefone());
		assertEquals(5, arquiteto.getTipo().getId());
		assertEquals("Pessoa Juridica 1", arquiteto.getTipo().getTipo());
	}
	
	@Test
	void testDelete() throws ParseException {
		
		Arquiteto Arquiteto = input.mockArquiteto(1);
		Arquiteto.setId((long)1);
		
		when(repository.findById((long)1)).thenReturn(Optional.of(Arquiteto));
		
		service.delete((long)1);
		
	}
	
	@Test
	void testListArquiteto() throws ParseException {
		
		List<Arquiteto> listArquiteto = input.mockListArquiteto();
		
		when(repository.findAll()).thenReturn(listArquiteto);
		
		var listas = service.findAll();
		
		assertEquals(10,listas.size());
		assertNotNull(listas);
		
		ArquitetoVo arquiteto = listas.get(1);
		
		assertNotNull(arquiteto);
		assertNotNull(arquiteto.getKey());
		assertNotNull(arquiteto.getLinks());
		assertNotNull(arquiteto.getEnderecos());
		assertNotNull(arquiteto.getProjetos());
		assertTrue(arquiteto.toString().contains("</api/v1/arquiteto/1>;rel=\"self\""));
		assertEquals("987@gmail.com 1", arquiteto.getEmail());
		assertEquals(1, arquiteto.getKey());
		assertEquals("Harry Kane 1", arquiteto.getNome());
		assertEquals("987654321 1", arquiteto.getTelefone());
		assertEquals(5, arquiteto.getTipo().getId());
		assertEquals("Pessoa Fisica 1", arquiteto.getTipo().getTipo());
		
		ArquitetoVo arquitetoFive = listas.get(5);
		
		assertNotNull(arquitetoFive);
		assertNotNull(arquitetoFive.getKey());
		assertNotNull(arquitetoFive.getLinks());
		assertNotNull(arquitetoFive.getEnderecos());
		assertNotNull(arquitetoFive.getProjetos());
		assertTrue(arquitetoFive.toString().contains("</api/v1/arquiteto/5>;rel=\"self\""));
		assertEquals("987@gmail.com 5", arquitetoFive.getEmail());
		assertEquals(5, arquitetoFive.getKey());
		assertEquals("Harry Kane 5", arquitetoFive.getNome());
		assertEquals("987654321 5", arquitetoFive.getTelefone());
		assertEquals(5, arquitetoFive.getTipo().getId());
		assertEquals("Pessoa Fisica 5", arquitetoFive.getTipo().getTipo());
		
		ArquitetoVo arquitetoEigth = listas.get(8);
		
		assertNotNull(arquitetoEigth.getKey());
		assertNotNull(arquitetoEigth.getLinks());
		assertNotNull(arquitetoEigth.getEnderecos());
		assertNotNull(arquitetoEigth.getProjetos());
		assertTrue(arquitetoEigth.toString().contains("</api/v1/arquiteto/8>;rel=\"self\""));
		assertEquals("123@gmail.com 8", arquitetoEigth.getEmail());
		assertEquals(8, arquitetoEigth.getKey());
		assertEquals("Anna Green 8", arquitetoEigth.getNome());
		assertEquals("123456789 8", arquitetoEigth.getTelefone());
		assertEquals(5, arquitetoEigth.getTipo().getId());
		assertEquals("Pessoa Juridica 8", arquitetoEigth.getTipo().getTipo());
	}
		
	
	@Test
	void testCreateIsNull() throws ParseException {
		
		Exception exception = assertThrows(ResponseBadRequestHandlerException.class, () -> 
		service.create(null));
		
		String messageActual = exception.getMessage();
		String messageExpected = "id request failed";
		
		assertTrue(messageActual.contains(messageExpected));
	}
	
	@Test
	void testUpdateIsNull() throws ParseException {
		
		Exception exception = assertThrows(ResponseBadRequestHandlerException.class, () -> 
		service.update(null));
		
		String messageActual = exception.getMessage();
		String messageExpected = "id request failed";
		
		assertTrue(messageActual.contains(messageExpected));
	}
}
