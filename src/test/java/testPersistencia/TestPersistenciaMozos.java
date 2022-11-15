package testPersistencia;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import modelo.Empresa;
import modelo.Mozo;

import negocio.GestionDeMozos;
import persistencia.IPersistencia;
import persistencia.PersistenciaXML;


public class TestPersistenciaMozos {
	
	private GestionDeMozos g;
	private Set<Mozo> listaMozosAnt;
	private Set<Mozo> listaMozosExp;
	
	
	@Before
	public void setUp() {
		
		this.g= GestionDeMozos.get();
		this.listaMozosAnt = Empresa.getEmpresa().getMozos();
		Empresa.getEmpresa().setMozos(new HashSet<>());
		this.listaMozosExp=new HashSet<>();
		File archivo= new File("mozos.xml");
		if(archivo.exists())
			archivo.delete();
	
	}
	
	@After
	public void tearDown() {
		Empresa.getEmpresa().setMozos(this.listaMozosAnt);
		
	}
	
	@Test
	public void testCrearArchivo() {
		
		g.persistirMozos();
		File archivo=new File("mozos.xml");
		assertTrue("Deberia existir el archivo mozos.xml",archivo.exists());
		 
	}
	
	
	@Test
	public void despersistenciaTest() {
		
		g.persistirMozos();
		Empresa.getEmpresa().cargarMozos();
		Set<Mozo> mozosDespersistidos=Empresa.getEmpresa().getMozos();	
		assertEquals("Error en la despersistencia", mozosDespersistidos.size(),listaMozosExp.size());
	}
	
	
	
	
	

}
