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
import com.architrack.entities.Endereco;
import com.architrack.test.mocks.MockEndereco;
import com.architrack.test.vo.EnderecoVo;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class TestConverterMapperEndereco {
	
	private MockEndereco input;

	
	@BeforeEach
	void setUp() throws Exception {
		input = new MockEndereco();
		MockitoAnnotations.openMocks(this);
	}

	// validando a conversão entra entidadades através do Dozer
	@Test
	void testConverterEnderecoInVo() throws ParseException{
		EnderecoVo enderecoVo = DozerMapper.parseObjectForEntity(input.mockEndereco(1),EnderecoVo.class );
		
		assertNotNull(enderecoVo);
		assertNotNull(enderecoVo.getKey());
		assertEquals("Pituba 1",enderecoVo.getBairro());
		assertEquals(1,enderecoVo.getKey());
		assertEquals("41830-480 1",enderecoVo.getCep());
		assertEquals("Salvador 1",enderecoVo.getCidade());	
		assertEquals("BA",enderecoVo.getEstado());	
		assertEquals("Rua Ceará 1",enderecoVo.getRua());	
		
	}
	
	@Test
	void testConverterVoInEndereco(){
		Endereco endereco = DozerMapper.parseObjectForEntity(input.mockEnderecoVo(1),Endereco.class);
		
		assertNotNull(endereco);
		assertNotNull(endereco.getId());
		assertEquals("Marilia 1",endereco.getBairro());
		assertEquals(1,endereco.getId());
		assertEquals("41235-415 1",endereco.getCep());
		assertEquals("São Bernado 1",endereco.getCidade());	
		assertEquals("SP",endereco.getEstado());	
		assertEquals("Rua das Neves 1",endereco.getRua());	
	}
	
	@Test
	void testConverterListEnderecoInVo(){
		List<EnderecoVo> enderecosVo = DozerMapper.parseListObjectForEntity(input.mockListEndereco(),EnderecoVo.class);
		
		EnderecoVo voTwo = enderecosVo.get(2);
		
		assertNotNull(voTwo);
		assertNotNull(voTwo.getKey());
		assertEquals("Marilia 2",voTwo.getBairro());
		assertEquals(2,voTwo.getKey());
		assertEquals("41235-415 2",voTwo.getCep());
		assertEquals("São Bernado 2",voTwo.getCidade());	
		assertEquals("SP",voTwo.getEstado());	
		assertEquals("Rua das Neves 2",voTwo.getRua());	
		
		EnderecoVo voFive = enderecosVo.get(5);
		
		assertNotNull(voFive);
		assertNotNull(voFive.getKey());
		assertEquals("Pituba 5",voFive.getBairro());
		assertEquals(5,voFive.getKey());
		assertEquals("41830-480 5",voFive.getCep());
		assertEquals("Salvador 5",voFive.getCidade());	
		assertEquals("BA",voFive.getEstado());	
		assertEquals("Rua Ceará 5",voFive.getRua());
		
		EnderecoVo voEight = enderecosVo.get(8);
		
		assertNotNull(voEight);
		assertNotNull(voEight.getKey());
		assertEquals("Marilia 8",voEight .getBairro());
		assertEquals(8,voEight .getKey());
		assertEquals("41235-415 8",voEight .getCep());
		assertEquals("São Bernado 8",voEight .getCidade());	
		assertEquals("SP",voEight .getEstado());	
		assertEquals("Rua das Neves 8",voEight.getRua());
	}
	@Test
	void testConverterListVoInEndereco(){
		List<Endereco> enderecos = DozerMapper.parseListObjectForEntity(input.mockListEnderecoVo(), Endereco.class);
		
		Endereco endereco = enderecos.get(1);
		
		assertNotNull(endereco);
		assertNotNull(endereco.getId());
		assertEquals("Marilia 1",endereco.getBairro());
		assertEquals(1,endereco.getId());
		assertEquals("41235-415 1",endereco.getCep());
		assertEquals("São Bernado 1",endereco.getCidade());	
		assertEquals("SP",endereco.getEstado());	
		assertEquals("Rua das Neves 1",endereco.getRua());
		
		Endereco enderecoThree = enderecos.get(3);
		
		assertNotNull(enderecoThree);
		assertNotNull(enderecoThree.getId());
		assertEquals("Marilia 3",enderecoThree.getBairro());
		assertEquals(3,enderecoThree.getId());
		assertEquals("41235-415 3",enderecoThree.getCep());
		assertEquals("São Bernado 3",enderecoThree.getCidade());	
		assertEquals("SP",enderecoThree.getEstado());	
		assertEquals("Rua das Neves 3",enderecoThree.getRua());
		
		Endereco enderecoSix = enderecos.get(6);
		
		assertNotNull(enderecoSix);
		assertNotNull(enderecoSix.getId());
		assertEquals("Pituba 6",enderecoSix.getBairro());
		assertEquals(6,enderecoSix.getId());
		assertEquals("41830-480 6",enderecoSix.getCep());
		assertEquals("Salvador 6",enderecoSix.getCidade());	
		assertEquals("BA",enderecoSix.getEstado());	
		assertEquals("Rua Ceará 6",enderecoSix.getRua());
	}
	
	

}
