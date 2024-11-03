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

import com.architrack.entities.Historico;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.repository.HistoricoRepository;
import com.architrack.service.HistoricoService;
import com.architrack.test.mocks.MockHistorico;
import com.architrack.vo.v1.HistoricoVo;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class TestUnitHistorico {

	
	private MockHistorico input;
	@InjectMocks
	private HistoricoService service;
	@Mock
	private HistoricoRepository repository;
	@BeforeEach
	void setUp() throws Exception {
		input = new MockHistorico();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() throws ParseException {
		Historico historico = input.mockHistorico(1);
		historico.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(historico));
		
		var result =service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.getKey());
		assertTrue(result.toString().contains("</api/v1/historico/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Sun Dec 15 00:00:00 BRT 2024", result.getDataMudanca().toString());
		assertEquals("Licença 1", result.getDescricaoMudanca());
		
	}
	
	@Test
	void testCreate() throws ParseException {

		Historico historico = input.mockHistorico(1);
		historico.setId(1L);
		Historico persistence = historico;
		persistence.setId(1L);
		
		HistoricoVo historicoVo = input.mockHistoricoVo(1);
		historicoVo.setKey(1L);
		when(repository.save(any(Historico.class))).thenReturn(persistence);
		
		var result = service.create(historicoVo);
		
		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.getKey());
		assertTrue(result.toString().contains("</api/v1/historico/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Sun Dec 15 00:00:00 BRT 2024", result.getDataMudanca().toString());
		assertEquals("Licença 1", result.getDescricaoMudanca());
	}
	
	@Test
	void testUpdate() throws ParseException {
		
		Historico historico = input.mockHistorico(1);
		
		Historico persistence = historico;
		persistence.setId(1L);
		
		HistoricoVo historicoVo = input.mockHistoricoVo(1);
		historicoVo.setKey(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(historico));
		when(repository.save(historico)).thenReturn(persistence);
		var result = service.update(historicoVo);
		

		assertNotNull(result);
		assertNotNull(result.getLinks());
		assertNotNull(result.getKey());
		assertTrue(result.toString().contains("</api/v1/historico/1>;rel=\"self\""));
		assertEquals(1, result.getKey());
		assertEquals("Wed Dec 25 00:00:00 BRT 2024", result.getDataMudanca().toString());
		assertEquals("Atraso de Matérial 1", result.getDescricaoMudanca());
	}
	
	@Test
	void testListAgenda() throws ParseException {
		
		List<Historico>historicosList = input.mockListHistorico();
		
		when(repository.findAll()).thenReturn(historicosList);
		
		
		var historicos = service.findAll();
		
		assertNotNull(historicos);
		assertEquals(10,historicos.size());
		
		var historicoOne = historicos.get(1);
		

		assertNotNull(historicoOne);
		assertNotNull(historicoOne.getLinks());
		assertNotNull(historicoOne.getKey());
		assertTrue(historicoOne.toString().contains("</api/v1/historico/1>;rel=\"self\""));
		assertEquals(1, historicoOne.getKey());
		assertEquals("Sun Dec 15 00:00:00 BRT 2024", historicoOne.getDataMudanca().toString());
		assertEquals("Licença 1", historicoOne.getDescricaoMudanca());
		
		var historicoFive= historicos.get(5);
		

		assertNotNull(historicoFive);
		assertNotNull(historicoFive.getLinks());
		assertNotNull(historicoFive.getKey());
		assertTrue(historicoFive.toString().contains("</api/v1/historico/5>;rel=\"self\""));
		assertEquals(5, historicoFive.getKey());
		assertEquals("Sun Dec 15 00:00:00 BRT 2024", historicoFive.getDataMudanca().toString());
		assertEquals("Licença 5", historicoFive.getDescricaoMudanca());
		
		var historicoEigth= historicos.get(8);
		

		assertNotNull(historicoEigth);
		assertNotNull(historicoEigth.getLinks());
		assertNotNull(historicoEigth.getKey());
		assertTrue(historicoEigth.toString().contains("</api/v1/historico/8>;rel=\"self\""));
		assertEquals(8, historicoEigth.getKey());
		assertEquals("Sun Dec 15 00:00:00 BRT 2024", historicoEigth.getDataMudanca().toString());
		assertEquals("Atraso de Matérial 8", historicoEigth.getDescricaoMudanca());

	}
		
	@Test
	void testDeleteAgenda() throws ParseException {
		
		Historico historico = input.mockHistorico(1);
		historico.setId(1L);
		
		when(repository.findById(1L)).thenReturn(Optional.of(historico));
		
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
