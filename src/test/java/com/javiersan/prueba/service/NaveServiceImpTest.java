package com.javiersan.prueba.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.response.CategoriaResponseRest;
import com.javiersan.prueba.models.entity.Nave;
import com.javiersan.prueba.models.service.NaveServiceImpl;

public class NaveServiceImpTest {
    
    @InjectMocks
	NaveServiceImpl service;
	
	@Mock
	INaveDao naveDao;
	
	List<Nave> list = new ArrayList<Nave>();
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    public void NaveServiceImplTest(){
		NaveServiceImpl nave = new NaveServiceImpl();

		assertEquals(Page<Nave>, nave.findAll());
		assertEquals(" ", nave.delete(id));
		assertEquals(Nave, nave.findById(id));
		assertEquals(List<Nave>, nave.findByNombreLikeIgnoreCase());
	}


	@Test
	public void findAllTest() {
		
		when(categoriaDao.findAll()).thenReturn(Page<Nave>);
		
		Page<Nave> response = service.findAll();
		
		assertEquals(4, response.getBody().getCategoriaResponse().getCategoria().size());
		
		verify(categoriaDao, times(1)).findAll();
		
		
	}

	@Test
	public void saveTest() {
		
		when(categoriaDao.save()).thenReturn(Nave);
		
		Nave response = service.save(Nave);
		
		assertEquals(4, response.getBody().getCategoriaResponse().getCategoria().size());
		
		verify(categoriaDao, times(1)).save();
		
		
	}

	@Test
	public void findByNombreTest() {
		
		when(categoriaDao.findByNombreLikeIgnoreCase()).thenReturn(list);
		
		List<Nave> response = service.findByNombre(term);
		
		assertEquals(4, response.getBody().getCategoriaResponse().getCategoria().size());
		
		verify(categoriaDao, times(1)).findByNombreLikeIgnoreCase();
		
		
	}

	@Test
	public void findByIdTest() {
		
		when(categoriaDao.findById(id)).thenReturn(Nave);
		
		Nave response = service.findById(id);
		
		assertEquals(4, response.getBody().getCategoriaResponse().getCategoria().size());
		
		verify(categoriaDao, times(1)).findById();
		
		
	}

	@Test
	public void deleteTest() {
		
		when(categoriaDao.deleteById(id)).thenReturn();
		
		ResponseEntity<?> response = service.deleteById(id);
		
		assertEquals(4, response.getBody().getCategoriaResponse().getCategoria().size());
		
		verify(categoriaDao, times(1)).deleteById(id);
		
		
	}









	
	public void cargarCategorias()
	{
		Categoria catUno = new Categoria(Long.valueOf(1), "Abarrotes", "Distintos abarrotes");
		Categoria catdos = new Categoria(Long.valueOf(1), "Lacteos", "variedad de  abarrotes");
		Categoria catTercero = new Categoria(Long.valueOf(1), "Bebidas", "bebidas sin azucar");
		Categoria catcuatro = new Categoria(Long.valueOf(1), "carnes blancas", "distintas carnes");
		
		list.add(catUno);
		list.add(catdos);
		list.add(catTercero);
		list.add(catcuatro);
		
	}
	
}
