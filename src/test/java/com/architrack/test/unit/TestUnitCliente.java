package com.architrack.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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

import com.architrack.entities.Cliente;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.repository.ClienteRepository;
import com.architrack.service.ClienteService;
import com.architrack.test.mocks.MockCliente;
import com.architrack.vo.v1.ClienteVo;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class TestUnitCliente {

	
	private MockCliente input;
	@InjectMocks
	private ClienteService service;
	@Mock
	private ClienteRepository repository;
	@BeforeEach
	void setUp() throws Exception {
		input = new MockCliente();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() throws ParseException {
		Cliente cliente = input.mockCliente(1);
		cliente.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(cliente));
		
		var result =service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.getKey());
		assertNull(result.getPj());
		assertTrue(result.toString().contains("</api/v1/cliente/1>;rel=\"self\""));
		assertEquals(1,result.getKey());
		assertEquals("imobile@gmail.com 1", result.getEmail());
		assertEquals("Cristine Gomes 1", result.getNome());
		assertEquals("897456321-59", result.getPf().getCpf());
		assertEquals("765321456-10", result.getPf().getRg());
	}
	
	@Test
	void testCreate() throws ParseException {

		Cliente cliente = input.mockCliente(1);
		cliente.setId(1L);
		Cliente persistence = cliente;
		persistence.setId(1L);
		
		ClienteVo clienteVo = input.mockClienteVo();
		clienteVo.setKey(1L);
		when(repository.save(any(Cliente.class))).thenReturn(persistence);
		
		var result = service.create(clienteVo);
		
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.getKey());
		assertNull(result.getPj());
		assertTrue(result.toString().contains("</api/v1/cliente/1>;rel=\"self\""));
		assertEquals(1,result.getKey());
		assertEquals("imobile@gmail.com 1", result.getEmail());
		assertEquals("Cristine Gomes 1", result.getNome());
		assertEquals("897456321-59", result.getPf().getCpf());
		assertEquals("765321456-10", result.getPf().getRg());
	}
	
	@Test
	void testUpdate() throws ParseException {
		
		Cliente cliente = input.mockCliente(1);
		
		Cliente persistence = cliente;
		persistence.setId(1L);
		
		ClienteVo clienteVo = input.mockClienteVo(1);
		clienteVo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(cliente));
		when(repository.save(cliente)).thenReturn(persistence);
		var result = service.update(clienteVo);
		
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.getKey());
		assertNull(result.getPj());
		assertTrue(result.toString().contains("</api/v1/cliente/1>;rel=\"self\""));
		assertEquals(1,result.getKey());
		assertEquals("gustavo@gmail.com 1", result.getEmail());
		assertEquals("Gustavo Lima 1", result.getNome());
		assertEquals("897456321-59", result.getPf().getCpf());
		assertEquals("765321456-10", result.getPf().getRg());
	}
	
	@Test
	void testListAgenda() throws ParseException {
		
		List<Cliente>clientesList = input.mockListCliente();
		
		when(repository.findAll()).thenReturn(clientesList);
		
		
		var clientes = service.findAll();
		
		assertNotNull(clientes);
		assertEquals(10,clientes.size());
		
		var clienteTwo = clientes.get(2);
		
		assertNotNull(clienteTwo);
		assertNotNull(clienteTwo.getLinks());
		assertNotNull(clienteTwo.getKey());
		assertNull(clienteTwo.getPf());
		assertTrue(clienteTwo.toString().contains("</api/v1/cliente/2>;rel=\"self\""));
		assertEquals(2,clienteTwo.getKey());
		assertEquals("gustavo@gmail.com 2", clienteTwo.getEmail());
		assertEquals("Gustavo Lima 2", clienteTwo.getNome());
		assertEquals("12.345.678/0001-90", clienteTwo.getPj().getCnpj());
		assertEquals("123.456.789.123", clienteTwo.getPj().getInscricaoEstadual());
		
		var clienteFour = clientes.get(4);
		
		assertNotNull(clienteFour);
		assertNotNull(clienteFour.getLinks());
		assertNotNull(clienteFour.getKey());
		assertNull(clienteFour.getPf());
		assertTrue(clienteFour.toString().contains("</api/v1/cliente/4>;rel=\"self\""));
		assertEquals(4,clienteFour.getKey());
		assertEquals("gustavo@gmail.com 4", clienteFour.getEmail());
		assertEquals("Gustavo Lima 4", clienteFour.getNome());
		assertEquals("12.345.678/0001-90", clienteFour.getPj().getCnpj());
		assertEquals("123.456.789.123", clienteFour.getPj().getInscricaoEstadual());
		
		var clienteSeven = clientes.get(7);
		
		assertNotNull(clienteSeven);
		assertNotNull(clienteSeven.getLinks());
		assertNotNull(clienteSeven.getKey());
		assertNull(clienteSeven.getPj());
		assertTrue(clienteSeven.toString().contains("</api/v1/cliente/7>;rel=\"self\""));
		assertEquals(7,clienteSeven.getKey());
		assertEquals("imobile@gmail.com 7", clienteSeven.getEmail());
		assertEquals("Cristine Gomes 7", clienteSeven.getNome());
		assertEquals("897456321-59", clienteSeven.getPf().getCpf());
		assertEquals("765321456-10", clienteSeven.getPf().getRg());
	}
		
	@Test
	void testDeleteAgenda() throws ParseException {
		
		Cliente cliente = input.mockCliente();
		cliente.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(cliente));
		
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
