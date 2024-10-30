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
import com.architrack.entities.Cliente;
import com.architrack.test.mocks.MockCliente;
import com.architrack.test.vo.ClienteVo;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class TestConverterMapperCliente {
	
	private MockCliente input;

	
	@BeforeEach
	void setUp() throws Exception {
		input = new MockCliente();
		MockitoAnnotations.openMocks(this);
	}

	// validando a conversão entra entidadades através do Dozer
	@Test
	void testConverterClienteInVo() throws ParseException{
		ClienteVo clienteVo = DozerMapper.parseObjectForEntity(input.mockCliente(1),ClienteVo.class );
		
		assertNotNull(clienteVo);
		assertNotNull(clienteVo.getKey());
		assertNotNull(clienteVo.getPf());
		assertNull(clienteVo.getPj());
		assertEquals("imobile@gmail.com 1",clienteVo.getEmail());
		//assertEquals(3,clienteVo.getProjeto().getId());
		assertEquals(1,clienteVo.getKey());
		assertEquals("Cristine Gomes 1",clienteVo.getNome());
		assertEquals("18-987654321 1",clienteVo.getTelefone());
		assertEquals("765321456-10",clienteVo.getPf().getRg());
		assertEquals("897456321-59",clienteVo.getPf().getCpf());
		
	}
	
	@Test
	void testConverterVoInCliente() throws ParseException{
		Cliente cliente = DozerMapper.parseObjectForEntity(input.mockClienteVo(1),Cliente.class);
		
		assertNotNull(cliente);
		assertNotNull(cliente.getId());
		assertNull(cliente.getPj());
		assertNotNull(cliente.getPf());
		assertEquals("gustavo@gmail.com 1",cliente.getEmail());
		assertEquals(1,cliente.getId());
		assertEquals("Gustavo Lima 1",cliente.getNome());
		assertEquals("81-123456789 1",cliente.getTelefone());
		assertEquals("897456321-59",cliente.getPf().getCpf());
		assertEquals("765321456-10",cliente.getPf().getRg());
	}
	
	@Test
	void testConverterListClienteInVo() throws ParseException{
		List<ClienteVo> clientesVo = DozerMapper.parseListObjectForEntity(input.mockListCliente(),ClienteVo.class);
		
		ClienteVo vo = clientesVo.get(2);
		
		assertNotNull(vo);
		assertNotNull(vo.getKey());
		assertNotNull(vo.getPj());
		assertNull(vo.getPf());
		assertEquals("gustavo@gmail.com 2",vo.getEmail());
		assertEquals(2,vo.getKey());
		assertEquals("Gustavo Lima 2",vo.getNome());
		assertEquals("81-123456789 2",vo.getTelefone());
		assertEquals("12.345.678/0001-90",vo.getPj().getCnpj());
		assertEquals("123.456.789.123",vo.getPj().getInscricaoEstadual());
		
		ClienteVo voFive = clientesVo.get(5);
		
		assertNotNull(voFive);
		assertNotNull(voFive.getKey());
		assertNull(voFive.getPj());
		assertEquals("imobile@gmail.com 5",voFive.getEmail());
		assertEquals(5,voFive.getKey());
		assertEquals("Cristine Gomes 5",voFive.getNome());
		assertEquals("18-987654321 5",voFive.getTelefone());
		assertEquals("765321456-10",voFive.getPf().getRg());
		assertEquals("897456321-59",voFive.getPf().getCpf());
		
		ClienteVo voEight = clientesVo.get(8);
		
		assertNotNull(voEight);
		assertNotNull(voEight.getKey());
		assertNotNull(voEight.getPj());
		assertNull(voEight.getPf());
		assertEquals("gustavo@gmail.com 8",voEight.getEmail());
		assertEquals(8,voEight.getKey());
		assertEquals("Gustavo Lima 8",voEight.getNome());
		assertEquals("81-123456789 8",voEight.getTelefone());
		assertEquals("12.345.678/0001-90",voEight.getPj().getCnpj());
		assertEquals("123.456.789.123",voEight.getPj().getInscricaoEstadual());
	}
	@Test
	void testConverterListVoInCliente() throws ParseException{
		List<Cliente> clientes = DozerMapper.parseListObjectForEntity(input.mockListClienteVo(), Cliente.class);
		
		Cliente cliente = clientes.get(1);
		
		assertNotNull(cliente);
		assertNotNull(cliente.getId());
		assertNotNull(cliente.getPf());
		assertNull(cliente.getPj());
		assertEquals("gustavo@gmail.com 1",cliente.getEmail());
		assertEquals(1,cliente.getId());
		assertEquals("Gustavo Lima 1",cliente.getNome());
		assertEquals("81-123456789 1",cliente.getTelefone());
		assertEquals("897456321-59",cliente.getPf().getCpf());
		assertEquals("765321456-10",cliente.getPf().getRg());
		
		Cliente clienteThree = clientes.get(3);
		
		assertNotNull(clienteThree);
		assertNotNull(clienteThree.getId());
		assertNotNull(clienteThree.getPf());
		assertNull(clienteThree.getPj());
		assertEquals("gustavo@gmail.com 3",clienteThree.getEmail());
		assertEquals(3,clienteThree.getId());
		assertEquals("Gustavo Lima 3",clienteThree.getNome());
		assertEquals("81-123456789 3",clienteThree.getTelefone());
		assertEquals("897456321-59",clienteThree.getPf().getCpf());
		assertEquals("765321456-10",clienteThree.getPf().getRg());
		
		Cliente clienteSix = clientes.get(6);
		
		assertNotNull(clienteSix);
		assertNotNull(clienteSix.getId());
		assertNull(clienteSix.getPf());
		assertEquals("imobile@gmail.com 6",clienteSix.getEmail());
		assertEquals(6,clienteSix.getId());
		assertEquals("Cristine Gomes 6",clienteSix.getNome());
		assertEquals("18-987654321 6",clienteSix.getTelefone());
		assertEquals("12.345.678/0001-90",clienteSix.getPj().getCnpj());
		assertEquals("123.456.789.123",clienteSix.getPj().getInscricaoEstadual());
	}
	
	

}
