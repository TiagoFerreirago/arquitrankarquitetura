package com.architrack.test.mapper;

import static org.junit.jupiter.api.Assertions.*;

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
import com.architrack.entities.Arquiteto;
import com.architrack.test.mocks.MockArquiteto;
import com.architrack.test.vo.ArquitetoVo;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class TestConverterMapperArquiteto {
	
	private MockArquiteto input;

	
	@BeforeEach
	void setUp() throws Exception {
		input = new MockArquiteto();
		MockitoAnnotations.openMocks(this);
	}

	// validando a conversão entra entidadades através do Dozer
	@Test
	void testConverterArquitetoInVo() throws ParseException{
		ArquitetoVo arquitetoVo = DozerMapper.parseObjectForEntity(input.mockArquiteto(1),ArquitetoVo.class );
		
		assertNotNull(arquitetoVo);
		assertNotNull(arquitetoVo.getKey());
		assertNotNull(arquitetoVo.getEnderecos());
		assertNotNull(arquitetoVo.getProjetos());
		assertNotNull(arquitetoVo.getTipo());
		assertEquals(5,arquitetoVo.getTipo().getId());
		assertEquals("Pessoa Fisica 1",arquitetoVo.getTipo().getTipo());
		assertEquals("987@gmail.com 1",arquitetoVo.getEmail());
		assertEquals(1,arquitetoVo.getKey());
		assertEquals("Harry Kane 1",arquitetoVo.getNome());
		assertEquals("987654321 1",arquitetoVo.getTelefone());
		
	}
	
	@Test
	void testConverterVoInArquiteto() throws ParseException{
		Arquiteto arquiteto = DozerMapper.parseObjectForEntity(input.mockArquitetoVo(1),Arquiteto.class);
		
		assertNotNull(arquiteto);
		assertNotNull(arquiteto.getId());
		assertNotNull(arquiteto.getEnderecos());
		assertNotNull(arquiteto.getProjetos());
		assertNotNull(arquiteto.getTipo());
		assertEquals(5,arquiteto.getTipo().getId());
		assertEquals("Pessoa Juridica 1",arquiteto.getTipo().getTipo());
		assertEquals("123@gmail.com 1",arquiteto.getEmail());
		assertEquals(1,arquiteto.getId());
		assertEquals("Anna Green 1",arquiteto.getNome());
		assertEquals("123456789 1",arquiteto.getTelefone());
	
	}
	
	@Test
	void testConverterListArquitetoInVo() throws ParseException{
		List<ArquitetoVo> arquitetos = DozerMapper.parseListObjectForEntity(input.mockListArquiteto(),ArquitetoVo.class);
		
		ArquitetoVo vo = arquitetos.get(2);
		
		assertNotNull(vo);
		assertNotNull(vo.getKey());
		assertNotNull(vo.getEnderecos());
		assertNotNull(vo.getProjetos());
		assertNotNull(vo.getTipo());
		assertEquals(5,vo.getTipo().getId());
		assertEquals("Pessoa Juridica 2",vo.getTipo().getTipo());
		assertEquals("123@gmail.com 2",vo.getEmail());
		assertEquals(2,vo.getKey());
		assertEquals("Anna Green 2",vo.getNome());
		assertEquals("123456789 2",vo.getTelefone());
		
		
		ArquitetoVo voFive = arquitetos.get(5);
		
		assertNotNull(voFive);
		assertNotNull(voFive.getKey());
		assertNotNull(voFive.getEnderecos());
		assertNotNull(voFive.getProjetos());
		assertNotNull(voFive.getTipo());
		assertEquals(5,voFive.getTipo().getId());
		assertEquals("Pessoa Fisica 5",voFive.getTipo().getTipo());
		assertEquals("987@gmail.com 5",voFive.getEmail());
		assertEquals(5,voFive.getKey());
		assertEquals("Harry Kane 5",voFive.getNome());
		assertEquals("987654321 5",voFive.getTelefone());
		
		ArquitetoVo voEight = arquitetos.get(8);
		
		assertNotNull(voEight);
		assertNotNull(voEight.getKey());
		assertNotNull(voEight.getEnderecos());
		assertNotNull(voEight.getProjetos());
		assertNotNull(voEight.getTipo());
		assertEquals(5,voEight.getTipo().getId());
		assertEquals("Pessoa Juridica 8",voEight.getTipo().getTipo());
		assertEquals("123@gmail.com 8",voEight.getEmail());
		assertEquals(8,voEight.getKey());
		assertEquals("Anna Green 8",voEight.getNome());
		assertEquals("123456789 8",voEight.getTelefone());
	}
	@Test
	void testConverterListVoInArquiteto() throws ParseException{
		List<Arquiteto> arquitetos = DozerMapper.parseListObjectForEntity(input.mockListArquitetoVo(), Arquiteto.class);
		
		Arquiteto arq = arquitetos.get(1);
		
		assertNotNull(arq);
		assertNotNull(arq.getId());
		assertNotNull(arq.getEnderecos());
		assertNotNull(arq.getProjetos());
		assertNotNull(arq.getTipo());
		assertEquals(5,arq.getTipo().getId());
		assertEquals("Pessoa Juridica 1",arq.getTipo().getTipo());
		assertEquals("123@gmail.com 1",arq.getEmail());
		assertEquals(1,arq.getId());
		assertEquals("Anna Green 1",arq.getNome());
		assertEquals("123456789 1",arq.getTelefone());
		
		Arquiteto arqThree = arquitetos.get(3);
		
		assertNotNull(arqThree);
		assertNotNull(arqThree.getId());
		assertNotNull(arqThree.getEnderecos());
		assertNotNull(arqThree.getProjetos());
		assertNotNull(arqThree.getTipo());
		assertEquals(5,arqThree.getTipo().getId());
		assertEquals("Pessoa Juridica 3",arqThree.getTipo().getTipo());
		assertEquals("123@gmail.com 3",arqThree.getEmail());
		assertEquals(3,arqThree.getId());
		assertEquals("Anna Green 3",arqThree.getNome());
		assertEquals("123456789 3",arqThree.getTelefone());
		
		Arquiteto arqSix = arquitetos.get(6);
		
		assertNotNull(arqSix);
		assertNotNull(arqSix.getId());
		assertNotNull(arqSix.getEnderecos());
		assertNotNull(arqSix.getProjetos());
		assertNotNull(arqSix.getTipo());
		assertEquals(5,arqSix.getTipo().getId());
		assertEquals("Pessoa Fisica 6",arqSix.getTipo().getTipo());;
		assertEquals("987@gmail.com 6",arqSix.getEmail());
		assertEquals(6,arqSix.getId());
		assertEquals("Harry Kane 6",arqSix.getNome());
		assertEquals("987654321 6",arqSix.getTelefone());
	}
	
	

}
