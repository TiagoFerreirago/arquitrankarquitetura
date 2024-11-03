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

import com.architrack.entities.Pagamentos;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.repository.PagamentoRepository;
import com.architrack.service.PagamentoService;
import com.architrack.test.mocks.MockPagamento;
import com.architrack.vo.v1.PagamentosVo;


@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class TestUnitPagamento {

	private MockPagamento input;
	@InjectMocks
	private PagamentoService service;
	@Mock
	private PagamentoRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		
		input = new MockPagamento();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() throws ParseException {
		
		Pagamentos pagamento = input.mockPagamentos(1);
		pagamento.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(pagamento));
		
		var result = service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("</api/v1/pagamento/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Fri Nov 08 00:00:00 BRT 2024", result.getDataPagamento().toString());
		assertEquals("Aguardando 1", result.getStatus());
		assertEquals(78001D, result.getValor());
		
	}
	
	@Test
	void testCreate() throws ParseException {
		
		Pagamentos pagamento = input.mockPagamentos(1);
		pagamento.setId(1L);
		Pagamentos persistence = pagamento;
		persistence.setId(1L);
		
		PagamentosVo vo = input.mockPagamentosVo(1);
		vo.setKey(1L);
		
		when(repository.save(any(Pagamentos.class))).thenReturn(persistence);
		
		var result = service.create(vo);

		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("</api/v1/pagamento/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Fri Nov 08 00:00:00 BRT 2024", result.getDataPagamento().toString());
		assertEquals("Aguardando 1", result.getStatus());
		assertEquals(78001D, result.getValor());
		
		
	}
	
	@Test
	void testUpdate() throws ParseException {
		
		Pagamentos pagamento = input.mockPagamentos(1);
		
		Pagamentos persistence = pagamento;
		persistence.setId(1L);
		
		PagamentosVo vo = input.mockPagamentosVo(1);
		vo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(pagamento));
		when(repository.save(pagamento)).thenReturn(persistence);
		
		var result = service.update(vo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("</api/v1/pagamento/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Fri Oct 18 00:00:00 BRT 2024", result.getDataPagamento().toString());
		assertEquals("Concluido 1", result.getStatus());
		assertEquals(54001D, result.getValor());
	}
	
	@Test
	void testListAgenda() throws ParseException {
		
		List<Pagamentos>pagamentosList = input.mockListPagamentos();
		
		when(repository.findAll()).thenReturn(pagamentosList);
		
		var pagamentos = service.findAll();
		
		assertNotNull(pagamentos);
		assertEquals(10,pagamentos.size());
		
		var pagamentoTwo = pagamentos.get(2);
		
		assertNotNull(pagamentoTwo);
		assertNotNull(pagamentoTwo.getKey());
		assertNotNull(pagamentoTwo.getLinks());
		assertTrue(pagamentoTwo.toString().contains("</api/v1/pagamento/2>;rel=\"self\""));
		assertEquals(2, pagamentoTwo.getKey());
		assertEquals("Fri Nov 08 00:00:00 BRT 2024", pagamentoTwo.getDataPagamento().toString());
		assertEquals("Concluido 2", pagamentoTwo.getStatus());
		assertEquals(54002D, pagamentoTwo.getValor());
		
		var pagamentoFour = pagamentos.get(4);
		
		assertNotNull(pagamentoFour);
		assertNotNull(pagamentoFour.getKey());
		assertNotNull(pagamentoFour.getLinks());
		assertTrue(pagamentoFour.toString().contains("</api/v1/pagamento/4>;rel=\"self\""));
		assertEquals(4, pagamentoFour.getKey());
		assertEquals("Fri Nov 08 00:00:00 BRT 2024", pagamentoFour.getDataPagamento().toString());
		assertEquals("Concluido 4", pagamentoFour.getStatus());
		assertEquals(54004D, pagamentoFour.getValor());
		
		var pagamentoNine = pagamentos.get(9);
		
		assertNotNull(pagamentoNine);
		assertNotNull(pagamentoNine.getKey());
		assertNotNull(pagamentoNine.getLinks());
		assertTrue(pagamentoNine.toString().contains("</api/v1/pagamento/9>;rel=\"self\""));
		assertEquals(9, pagamentoNine.getKey());
		assertEquals("Fri Nov 08 00:00:00 BRT 2024", pagamentoNine.getDataPagamento().toString());
		assertEquals("Aguardando 9", pagamentoNine.getStatus());
		assertEquals(78009D, pagamentoNine.getValor());
		
	}
		
	@Test
	void testDeleteAgenda() throws ParseException {
		
		Pagamentos pagamento = input.mockPagamentos(1);
		pagamento.setId(1L);
		
		when(repository.findById(1l)).thenReturn(Optional.of(pagamento));
		
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
