package com.architrack.test.mapper;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.architrack.dozermapper.DozerMapper;
import com.architrack.entities.Pagamentos;
import com.architrack.test.mocks.MockPagamento;
import com.architrack.test.vo.PagamentosVo;


@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class TestConverterMapperPagamento {
	
	private MockPagamento input;

	
	@BeforeEach
	void setUp() throws Exception {
		input = new MockPagamento();
		MockitoAnnotations.openMocks(this);
	}

	// validando a conversão entra entidadades através do Dozer
	@Test
	void testConverterPagamentosInVo() throws Throwable{
		PagamentosVo pagamentosVo = DozerMapper.parseObjectForEntity(input.mockPagamentos(1),PagamentosVo.class );
		
		assertNotNull(pagamentosVo);
		assertNotNull(pagamentosVo.getKey());
		assertEquals("Fri Nov 08 00:00:00 BRT 2024",pagamentosVo.getDataPagamento().toString());
		assertEquals(1,pagamentosVo.getKey());
		assertEquals("Aguardando 1",pagamentosVo.getStatus());
		assertEquals(78001.0,pagamentosVo.getValor());	
		
		
	}
	
	@Test
	void testConverterVoInPagamentos() throws Throwable{
		Pagamentos pagamentos = DozerMapper.parseObjectForEntity(input.mockPagamentosVo(1),Pagamentos.class);
		

		assertNotNull(pagamentos);
		assertNotNull(pagamentos.getId());
		assertEquals("Fri Oct 18 00:00:00 BRT 2024",pagamentos.getDataPagamento().toString());
		assertEquals(1,pagamentos.getId());
		assertEquals("Concluido 1",pagamentos.getStatus());
		assertEquals(54001.0,pagamentos.getValor());	
	}
	
	@Test
	void testConverterListPagamentosInVo() throws Throwable{
		List<PagamentosVo> pagamentosVo = DozerMapper.parseListObjectForEntity(input.mockListPagamentos(),PagamentosVo.class);
		
		PagamentosVo voTwo = pagamentosVo.get(2);
		
		assertNotNull(voTwo);
		assertNotNull(voTwo.getKey());
		assertEquals("Fri Nov 08 00:00:00 BRT 2024",voTwo.getDataPagamento().toString());
		assertEquals(2,voTwo.getKey());
		assertEquals("Concluido 2",voTwo.getStatus());
		assertEquals(54002.0,voTwo.getValor());	
		
		PagamentosVo voFive = pagamentosVo.get(5);
		
		assertNotNull(voFive);
		assertNotNull(voFive.getKey());
		assertEquals("Fri Nov 08 00:00:00 BRT 2024",voFive.getDataPagamento().toString());
		assertEquals(5,voFive.getKey());
		assertEquals("Aguardando 5",voFive.getStatus());
		assertEquals(78005.0,voFive.getValor());	
		
		PagamentosVo voEight = pagamentosVo.get(8);
		
		assertNotNull(voEight);
		assertNotNull(voEight.getKey());
		assertEquals("Fri Nov 08 00:00:00 BRT 2024",voFive.getDataPagamento().toString());
		assertEquals(8,voEight.getKey());
		assertEquals("Concluido 8",voEight.getStatus());
		assertEquals(54008.0,voEight.getValor());	
	}
	@Test
	void testConverterListVoInPagamentos() throws Throwable{
		List<Pagamentos> pagamentos = DozerMapper.parseListObjectForEntity(input.mockListPagamentosVo(), Pagamentos.class);
		
		Pagamentos pagamento = pagamentos.get(1);
		
		assertNotNull(pagamento);
		assertNotNull(pagamento.getId());
		assertEquals("Fri Oct 18 00:00:00 BRT 2024",pagamento.getDataPagamento().toString());
		assertEquals(1,pagamento.getId());
		assertEquals("Concluido 1",pagamento.getStatus());
		assertEquals(54001.0,pagamento.getValor());
		
		Pagamentos pagamentoThree = pagamentos.get(3);
		
		assertNotNull(pagamentoThree);
		assertNotNull(pagamentoThree.getId());
		assertEquals("Fri Oct 18 00:00:00 BRT 2024",pagamentoThree.getDataPagamento().toString());
		assertEquals(3,pagamentoThree.getId());
		assertEquals("Concluido 3",pagamentoThree.getStatus());
		assertEquals(54003.0,pagamentoThree.getValor());
		
		Pagamentos pagamentoSix = pagamentos.get(6);
		
		assertNotNull(pagamentoSix);
		assertNotNull(pagamentoSix.getId());
		assertEquals("Fri Oct 18 00:00:00 BRT 2024",pagamentoSix.getDataPagamento().toString());
		assertEquals(6,pagamentoSix.getId());
		assertEquals("Aguardando 6",pagamentoSix.getStatus());
		assertEquals(78006.0,pagamentoSix.getValor());
	}
	
	

}
