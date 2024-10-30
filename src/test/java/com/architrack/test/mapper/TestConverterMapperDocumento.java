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
import com.architrack.entities.Documento;
import com.architrack.test.mocks.MockDocumento;
import com.architrack.test.vo.DocumentoVo;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class TestConverterMapperDocumento {
	
	private MockDocumento input;

	
	@BeforeEach
	void setUp() throws Exception {
		input = new MockDocumento();
		MockitoAnnotations.openMocks(this);
	}

	// validando a conversão entra entidadades através do Dozer
	@Test
	void testConverterDocumentoInVo() throws ParseException{
		DocumentoVo documentoVo = DozerMapper.parseObjectForEntity(input.mockDocumento(1),DocumentoVo.class );
		
		assertNotNull(documentoVo);
		assertNotNull(documentoVo.getKey());
		assertEquals("Wed Dec 25 00:00:00 BRT 2024",documentoVo.getDataEnvio().toString());
		assertEquals(1,documentoVo.getKey());
		assertEquals("Licença Para Construção 1",documentoVo.getNomeDocumento());
		assertEquals("Licenças 1",documentoVo.getTipoDocumento());	
		
	}
	
	@Test
	void testConverterVoInDocumento() throws ParseException{
		Documento documento = DozerMapper.parseObjectForEntity(input.mockDocumentoVo(1),Documento.class);
		
		assertNotNull(documento);
		assertNotNull(documento.getId());
		assertEquals("Wed Dec 11 00:00:00 BRT 2024",documento.getDataEnvio().toString());
		assertEquals(1,documento.getId());
		assertEquals("Projeto Estrutural 1",documento.getNomeDocumento());
		assertEquals("Técnico 1",documento.getTipoDocumento());	
	}
	
	@Test
	void testConverterListDocumentoInVo() throws ParseException{
		List<DocumentoVo> documentosVo = DozerMapper.parseListObjectForEntity(input.mockListDocumento(),DocumentoVo.class);
		
		DocumentoVo voTwo = documentosVo.get(2);
		
		assertNotNull(voTwo);
		assertNotNull(voTwo.getKey());
		assertEquals("Wed Dec 25 00:00:00 BRT 2024",voTwo.getDataEnvio().toString());
		assertEquals(2,voTwo.getKey());
		assertEquals("Projeto Estrutural 2",voTwo.getNomeDocumento());
		assertEquals("Técnico 2",voTwo.getTipoDocumento());	
		
		DocumentoVo voFive = documentosVo.get(5);
		
		assertNotNull(voFive);
		assertNotNull(voFive.getKey());
		assertEquals("Wed Dec 25 00:00:00 BRT 2024",voFive.getDataEnvio().toString());
		assertEquals(5,voFive.getKey());
		assertEquals("Licença Para Construção 5",voFive.getNomeDocumento());
		assertEquals("Licenças 5",voFive.getTipoDocumento());	
		
		DocumentoVo voEight = documentosVo.get(8);
		
		assertNotNull(voEight);
		assertNotNull(voEight.getKey());
		assertEquals("Wed Dec 25 00:00:00 BRT 2024",voEight.getDataEnvio().toString());
		assertEquals(8,voEight.getKey());
		assertEquals("Projeto Estrutural 8",voEight.getNomeDocumento());
		assertEquals("Técnico 8",voEight.getTipoDocumento());	
	}
	@Test
	void testConverterListVoInDocumento() throws ParseException{
		List<Documento> documentos = DozerMapper.parseListObjectForEntity(input.mockListDocumentoVo(), Documento.class);
		
		Documento documento = documentos.get(1);
		
		assertNotNull(documento);
		assertNotNull(documento.getId());
		assertEquals("Wed Dec 11 00:00:00 BRT 2024",documento.getDataEnvio().toString());
		assertEquals(1,documento.getId());
		assertEquals("Projeto Estrutural 1",documento.getNomeDocumento());
		assertEquals("Técnico 1",documento.getTipoDocumento());	
		
		Documento documentoThree = documentos.get(3);
		
		assertNotNull(documentoThree);
		assertNotNull(documentoThree.getId());
		assertEquals("Wed Dec 11 00:00:00 BRT 2024",documentoThree.getDataEnvio().toString());
		assertEquals(3,documentoThree.getId());
		assertEquals("Projeto Estrutural 3",documentoThree.getNomeDocumento());
		assertEquals("Técnico 3",documentoThree.getTipoDocumento());	
		
		Documento documentoSix = documentos.get(6);
		
		assertNotNull(documentoSix);
		assertNotNull(documentoSix.getId());
		assertEquals("Wed Dec 11 00:00:00 BRT 2024",documentoSix.getDataEnvio().toString());
		assertEquals(6,documentoSix.getId());
		assertEquals("Licença Para Construção 6",documentoSix.getNomeDocumento());
		assertEquals("Licenças 6",documentoSix.getTipoDocumento());	
	}
	
	

}
