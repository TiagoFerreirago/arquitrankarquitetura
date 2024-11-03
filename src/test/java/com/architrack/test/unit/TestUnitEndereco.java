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

import com.architrack.entities.Endereco;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.repository.EnderecoRepository;
import com.architrack.service.EnderecoService;
import com.architrack.test.mocks.MockEndereco;
import com.architrack.vo.v1.EnderecoVo;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class TestUnitEndereco {

	
	private MockEndereco input;
	@InjectMocks
	private EnderecoService service;
	@Mock
	private EnderecoRepository repository;
	@BeforeEach
	void setUp() throws Exception {
		input = new MockEndereco();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() throws ParseException {
		Endereco endereco = input.mockEndereco(1);
		endereco.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(endereco));
		
		var result =service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.getKey());
		assertTrue(result.toString().contains("</api/v1/endereco/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Pituba 1", result.getBairro());
		assertEquals("41830-480 1", result.getCep());
		assertEquals("Salvador 1", result.getCidade());
		assertEquals("BA", result.getEstado());
		assertEquals("Rua Ceará 1", result.getRua());
	}
	
	@Test
	void testCreate() throws ParseException {

		Endereco endereco = input.mockEndereco(1);
		endereco.setId(1L);
		Endereco persistence = endereco;
		persistence.setId(1L);
		
		EnderecoVo enderecoVo = input.mockEnderecoVo(1);
		enderecoVo.setKey(1L);
		when(repository.save(any(Endereco.class))).thenReturn(persistence);
		
		var result = service.create(enderecoVo);
		

		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.getKey());
		assertTrue(result.toString().contains("</api/v1/endereco/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Pituba 1", result.getBairro());
		assertEquals("41830-480 1", result.getCep());
		assertEquals("Salvador 1", result.getCidade());
		assertEquals("BA", result.getEstado());
		assertEquals("Rua Ceará 1", result.getRua());
	}
	
	@Test
	void testUpdate() throws ParseException {
		
		Endereco endereco = input.mockEndereco(1);
		
		Endereco persistence = endereco;
		persistence.setId(1L);
		
		EnderecoVo enderecoVo = input.mockEnderecoVo(1);
		enderecoVo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(endereco));
		when(repository.save(endereco)).thenReturn(persistence);
		var result = service.update(enderecoVo);
		

		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.getKey());
		assertTrue(result.toString().contains("</api/v1/endereco/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Marilia 1", result.getBairro());
		assertEquals("41235-415 1", result.getCep());
		assertEquals("São Bernado 1", result.getCidade());
		assertEquals("SP", result.getEstado());
		assertEquals("Rua das Neves 1", result.getRua());
	}
	
	@Test
	void testListAgenda() throws ParseException {
		
		List<Endereco>enderecosList = input.mockListEndereco();
		
		when(repository.findAll()).thenReturn(enderecosList);
		
		
		var enderecos = service.findAll();
		
		assertNotNull(enderecos);
		assertEquals(10,enderecos.size());
		
		var enderecoOne = enderecos.get(1);
		

		assertNotNull(enderecoOne);
		assertNotNull(enderecoOne.getLinks());
		assertNotNull(enderecoOne.getKey());
		assertTrue(enderecoOne.toString().contains("</api/v1/endereco/1>;rel=\"self\""));
		assertEquals(1, enderecoOne.getKey());
		assertEquals("Pituba 1", enderecoOne.getBairro());
		assertEquals("41830-480 1", enderecoOne.getCep());
		assertEquals("Salvador 1", enderecoOne.getCidade());
		assertEquals("BA", enderecoOne.getEstado());
		assertEquals("Rua Ceará 1", enderecoOne.getRua());

		
		var enderecoThree= enderecos.get(3);
		

		assertNotNull(enderecoThree);
		assertNotNull(enderecoThree.getLinks());
		assertNotNull(enderecoThree.getKey());
		assertTrue(enderecoThree.toString().contains("</api/v1/endereco/3>;rel=\"self\""));
		assertEquals(3, enderecoThree.getKey());
		assertEquals("Pituba 3", enderecoThree.getBairro());
		assertEquals("41830-480 3", enderecoThree.getCep());
		assertEquals("Salvador 3", enderecoThree.getCidade());
		assertEquals("BA", enderecoThree.getEstado());
		assertEquals("Rua Ceará 3", enderecoThree.getRua());

		
		var enderecoEigth= enderecos.get(8);
		

		assertNotNull(enderecoEigth);
		assertNotNull(enderecoEigth.getLinks());
		assertNotNull(enderecoEigth.getKey());
		assertTrue(enderecoEigth.toString().contains("</api/v1/endereco/8>;rel=\"self\""));
		assertEquals(8, enderecoEigth.getKey());
		assertEquals("Marilia 8", enderecoEigth.getBairro());
		assertEquals("41235-415 8", enderecoEigth.getCep());
		assertEquals("São Bernado 8", enderecoEigth.getCidade());
		assertEquals("SP", enderecoEigth.getEstado());
		assertEquals("Rua das Neves 8", enderecoEigth.getRua());

	}
		
	@Test
	void testDeleteAgenda() throws ParseException {
		
		Endereco endereco = input.mockEndereco(1);
		endereco.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(endereco));
		
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
