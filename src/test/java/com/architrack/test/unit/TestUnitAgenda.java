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

import com.architrack.entities.Agenda;
import com.architrack.exception.ResponseBadRequestHandlerException;
import com.architrack.repository.AgendaRepository;
import com.architrack.service.AgendaService;
import com.architrack.test.mocks.MockAgenda;
import com.architrack.vo.v1.AgendaVo;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class TestUnitAgenda {

	private MockAgenda input;
	@InjectMocks
	private AgendaService service;
	@Mock
	private AgendaRepository repository;
	
	@BeforeEach
	void setUp() throws Exception {
		input = new MockAgenda();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindById() throws ParseException {
		
		Agenda agenda = input.mockAgenda(1);
		agenda.setId((long)1);
				
		when(repository.findById((long)1)).thenReturn(Optional.of(agenda));
		
		var result = service.findById(1L);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("</api/v1/agenda/1>;rel=\"self"));
		assertEquals("Tue Oct 15 00:00:00 BRT 2024", result.getDataFim().toString().toString());
		assertEquals("Sat Oct 12 00:00:00 BRT 2024", result.getDataInicio().toString());
		
	}
	
	@Test
	void testCreate() throws ParseException {

		Agenda agenda = input.mockAgenda(1);
		agenda.setId(1L);
		Agenda persistence = agenda;
		persistence.setId(1L);
		
		AgendaVo agendaVo = input.mockAgendaVo();
		agendaVo.setKey(1L);
		
		when(repository.save(any(Agenda.class))).thenReturn(persistence);
		
		var result = service.create(agendaVo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("</api/v1/agenda/1>;rel=\"self"));
		assertEquals("Tue Oct 15 00:00:00 BRT 2024", result.getDataFim().toString());
		assertEquals("Sat Oct 12 00:00:00 BRT 2024", result.getDataInicio().toString());
	}
	
	@Test
	void testUpdate() throws ParseException {
		
		Agenda agenda = input.mockAgenda(1);
		agenda.setId((long)1);
		
		Agenda persistence = agenda;
		persistence.setId((long)1);
		
		AgendaVo agendaVo = input.mockAgendaVo(1);
		agendaVo.setKey((long)1);
		
		when(repository.findById((long)1)).thenReturn(Optional.of(agenda));
		when(repository.save(agenda)).thenReturn(persistence);
		
		var result = service.update(agendaVo);
		
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("</api/v1/agenda/1>;rel=\"self"));
		assertEquals("Fri Oct 25 00:00:00 BRT 2024", result.getDataFim().toString());
		assertEquals("Sun Oct 20 00:00:00 BRT 2024", result.getDataInicio().toString());
	}
	
	@Test
	void testDelete() throws ParseException {
		
		Agenda agenda = input.mockAgenda(1);
		agenda.setId((long)1);
		
		when(repository.findById((long)1)).thenReturn(Optional.of(agenda));
		
		service.delete((long)1);
		
	}
	
	@Test
	void testListAgenda() throws ParseException {
		
		List<Agenda> listAgenda = input.mockListAgenda();
		
		when(repository.findAll()).thenReturn(listAgenda);
		
		var listas = service.findAll();
		
		assertEquals(10,listas.size());
		assertNotNull(listas);
		
		AgendaVo agenda = listas.get(1);
		
		assertNotNull(agenda);
		assertNotNull(agenda.getKey());
		assertNotNull(agenda.getLinks());
		assertTrue(agenda.toString().contains("</api/v1/agenda/1>;rel=\"self"));
		assertEquals("Tue Oct 15 00:00:00 BRT 2024",agenda.getDataFim().toString());
		assertEquals("Sat Oct 12 00:00:00 BRT 2024",agenda.getDataInicio().toString());
		
		AgendaVo agendaFive = listas.get(5);
		
		assertNotNull(agendaFive);
		assertNotNull(agendaFive.getKey());
		assertNotNull(agendaFive.getLinks());
		assertTrue(agendaFive.toString().contains("</api/v1/agenda/5>;rel=\"self"));
		assertEquals("Tue Oct 15 00:00:00 BRT 2024",agenda.getDataFim().toString());
		assertEquals("Sat Oct 12 00:00:00 BRT 2024",agenda.getDataInicio().toString());
		
		AgendaVo agendaEigth = listas.get(8);
		
		assertNotNull(agendaEigth);
		assertNotNull(agendaEigth.getKey());
		assertNotNull(agendaEigth.getLinks());
		assertTrue(agendaEigth.toString().contains("</api/v1/agenda/8>;rel=\"self"));
		assertEquals("Tue Oct 15 00:00:00 BRT 2024", agendaEigth.getDataFim().toString());
		assertEquals("Sat Oct 12 00:00:00 BRT 2024", agendaEigth.getDataInicio().toString());
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
