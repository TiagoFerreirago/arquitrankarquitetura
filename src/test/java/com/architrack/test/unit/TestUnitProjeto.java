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

import com.architrack.entities.Projeto;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.repository.ProjetoRepository;
import com.architrack.service.ProjetoService;
import com.architrack.test.mocks.MockProjeto;
import com.architrack.vo.v1.ProjetoVo;


@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class TestUnitProjeto {

	private MockProjeto input;
	@InjectMocks
	private ProjetoService service;
	@Mock
	private ProjetoRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		
		input = new MockProjeto();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() throws ParseException {
		
		Projeto projeto = input.mockProjeto(1);
		projeto.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(projeto));
		
		var result = service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("</api/v1/projeto/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Arquitetura Comercial 1", result.getAreaEmpreendimento());	
		
	}
	
	@Test
	void testCreate() throws ParseException {
		
		Projeto projeto = input.mockProjeto(1);
		projeto.setId(1L);
		Projeto persistence = projeto;
		persistence.setId(1L);
		
		ProjetoVo vo = input.mockProjetoVo(1);
		vo.setKey(1L);
		
		when(repository.save(any(Projeto.class))).thenReturn(persistence);
		
		var result = service.create(vo);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("</api/v1/projeto/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Arquitetura Comercial 1", result.getAreaEmpreendimento());	
		
		
	}
	
	@Test
	void testUpdate() throws ParseException {
		
		Projeto projeto = input.mockProjeto(1);
		
		Projeto persistence = projeto;
		persistence.setId(1L);
		
		ProjetoVo vo = input.mockProjetoVo(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(projeto));
		when(repository.save(projeto)).thenReturn(persistence);
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("</api/v1/projeto/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Construção civil 1", result.getAreaEmpreendimento());	
	}
	
	@Test
	void testListAgenda() throws ParseException {
		
		List<Projeto>projetosList = input.mockListProjeto();
		
		when(repository.findAll()).thenReturn(projetosList);
		
		var projetos = service.findAll();
		
		assertNotNull(projetos);
		assertEquals(10,projetos.size());
		
		var projetoTwo = projetos.get(2);
		
		assertNotNull(projetoTwo);
		assertNotNull(projetoTwo.getKey());
		assertNotNull(projetoTwo.getLinks());
		assertTrue(projetoTwo.toString().contains("</api/v1/projeto/2>;rel=\"self\""));
		assertEquals(2, projetoTwo.getKey());
		assertEquals("Construção civil 2", projetoTwo.getAreaEmpreendimento());	
		
		var projetoFour = projetos.get(4);
		
		assertNotNull(projetoFour);
		assertNotNull(projetoFour.getKey());
		assertNotNull(projetoFour.getLinks());
		assertTrue(projetoFour.toString().contains("</api/v1/projeto/4>;rel=\"self\""));
		assertEquals(4, projetoFour.getKey());
		assertEquals("Construção civil 4", projetoFour.getAreaEmpreendimento());	
		
		var projetoNine = projetos.get(9);
		
		assertNotNull(projetoNine);
		assertNotNull(projetoNine.getKey());
		assertNotNull(projetoNine.getLinks());
		assertTrue(projetoNine.toString().contains("</api/v1/projeto/9>;rel=\"self\""));
		assertEquals(9, projetoNine.getKey());
		assertEquals("Arquitetura Comercial 9", projetoNine.getAreaEmpreendimento());	
		
	}
		
	@Test
	void testDeleteAgenda() throws ParseException {
		
		Projeto projeto = input.mockProjeto(1);
		projeto.setId(1L);
		
		when(repository.findById(1l)).thenReturn(Optional.of(projeto));
		
		service.delete(1L);
	
	}
	@Test
	void testCreateIsNull() throws ParseException {
		
		Exception exception = assertThrows(ResponseBadRequestHandlerException.class, () -> {
			service.create(null);
		});
		
		String messageActual = exception.getMessage();
		String messageExpected = "id request failed";
		
		assertTrue(messageActual.contains(messageExpected));
	
	}
	
	@Test
	void testUpdateIsNull(){
		


		Exception exception = assertThrows(ResponseBadRequestHandlerException.class, () -> {
			service.update(null);
		});
		
		String messageActual = exception.getMessage();
		String messageExpected = "id request failed";
		
		assertTrue(messageActual.contains(messageExpected));
	}
}
