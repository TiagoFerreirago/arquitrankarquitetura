package com.architrack.test.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.architrack.dozermapper.DozerMapper;
import com.architrack.entities.Projeto;
import com.architrack.test.mocks.MockProjeto;
import com.architrack.test.vo.ProjetoVo;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class TestConverterMapperProjeto {
	
	private MockProjeto input;

	
	@BeforeEach
	void setUp() throws Exception {
		input = new MockProjeto();
		MockitoAnnotations.openMocks(this);
	}

	// validando a conversão entra entidadades através do Dozer
	@Test
	void testConverterProjetosInVo() throws ParseException{
		ProjetoVo projetosVo = DozerMapper.parseObjectForEntity(input.mockProjeto(1), ProjetoVo.class);
		
		assertNotNull(projetosVo.getKey());
		assertEquals(1, projetosVo.getKey());
		assertEquals("Arquitetura Comercial 1", projetosVo.getAreaEmpreendimento());
			
	}
	
	@Test
	void testConverterVoInProjetos() throws ParseException{
		Projeto projetos = DozerMapper.parseObjectForEntity(input.mockProjetoVo(), Projeto.class);
		
		assertNotNull(projetos.getId());
		assertEquals(1, projetos.getId());
		assertEquals("Construção civil 1", projetos.getAreaEmpreendimento());
	}
	
	@Test
	void testConverterListProjetosInVo() throws ParseException{
		List<ProjetoVo> projetos = DozerMapper.parseListObjectForEntity(input.mockListProjeto(),ProjetoVo.class);
		
		ProjetoVo projetoTwo = projetos.get(2);
		
		assertNotNull(projetoTwo.getKey());
		
		assertEquals(2, projetoTwo.getKey());
		assertEquals("Construção civil 2", projetoTwo.getAreaEmpreendimento());
		
		ProjetoVo projetoFive = projetos.get(5);
		
		assertNotNull(projetoFive.getKey());
		assertEquals(5, projetoFive.getKey());
		assertEquals("Arquitetura Comercial 5", projetoFive.getAreaEmpreendimento());
		
		ProjetoVo projetoNine = projetos.get(9);
		
		assertNotNull(projetoNine.getKey());
		assertEquals(9, projetoNine.getKey());
		assertEquals("Arquitetura Comercial 9", projetoNine.getAreaEmpreendimento());
	}
	@Test
	void testConverterListVoInProjetos() throws ParseException{
		List<Projeto> projetos = DozerMapper.parseListObjectForEntity(input.mockListProjetoVo(),Projeto.class);
		
		Projeto projetoThree = projetos.get(3);

		assertNotNull(projetoThree.getId());
		assertEquals(3,projetoThree.getId());
		assertEquals("Construção civil 3", projetoThree.getAreaEmpreendimento());
		
		Projeto projetoFour = projetos.get(4);
		
		assertNotNull(projetoFour.getId());
		assertEquals(4, projetoFour.getId());
		assertEquals("Arquitetura Comercial 4", projetoFour.getAreaEmpreendimento());
		
		Projeto projetoSeven = projetos.get(7);
		
		assertNotNull(projetoSeven.getId());
		assertEquals(7, projetoSeven.getId());
		assertEquals("Construção civil 7", projetoSeven.getAreaEmpreendimento());
	}
	
	

}
