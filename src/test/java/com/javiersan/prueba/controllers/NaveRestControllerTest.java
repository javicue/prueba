package com.javiersan.prueba.controllers;

import com.javiersan.prueba.models.entity.Nave;
import com.javiersan.prueba.models.service.INaveService;

public class NaveRestControllerTest {

    @InjectMocks
	NaveRestController naveController;
	
	@Mock
	private INaveService service;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void crearTest() {
		
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Nave nave = new Nave();
		
		when(service.crear(any(Nave.class))).thenReturn(new ResponseEntity<?>(HttpStatus.OK));
		
		ResponseEntity<?> respuesta = naveController.crear(nave);
		
		assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);
		
	}

	@Test
	public void deleteTest() {
		
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		when(service.delete(any(id).thenReturn(new ResponseEntity<?>(HttpStatus.OK))));
		
		ResponseEntity<?> respuesta = naveController.delete(id);
		
		assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);
		
	}

	@Test
	public void showTest() {
		
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		when(service.findById(any(id).thenReturn(new ResponseEntity<?>(HttpStatus.OK))));
		
		ResponseEntity<?> respuesta = naveController.show(id);
		
		assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);
		
	}

	@Test
	public void listarTest() {
		
		
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		
		Nave nave = new Nave();
		
		when(service.findAll().thenReturn(new ResponseEntity<?>(HttpStatus.OK)));
		
		ResponseEntity<?> respuesta = naveController.listar();
		
		assertThat(respuesta.getStatusCodeValue()).isEqualTo(200);
		
	}

	

    
}
