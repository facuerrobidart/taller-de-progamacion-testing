package testA;


import excepciones.PermisoDenegadoException;
import modelo.Mozo;
import modelo.Operario;
import negocio.GestionDeOperarios;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistencia.IPersistencia;
import persistencia.PersistenciaXML;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class GestionDeOperariosTest {

    private EscenarioConAdminLogueado escenario;
    private GestionDeOperarios gestionDeOperarios;

    @Before
    public void setUp() throws Exception {
        this.gestionDeOperarios = GestionDeOperarios.get();
        escenario = new EscenarioConAdminLogueado();
        escenario.aplicaEscenarioConAdminLogueado();
    }

    @After
    public void tearDown() throws Exception {
        escenario.borrarEscenario();
    }

    @Test
    public void bajaOperario() {

        Operario operarioEscenario = this.escenario.getOperario();

        try {
            gestionDeOperarios.bajaOperario(operarioEscenario.getNombreCompleto());
        } catch (PermisoDenegadoException e) {
            fail("No deberia lanzar excepcion" + e.getMessage());
        }

        Optional<Operario> operarioEncontrado = gestionDeOperarios.getOperarios().stream().filter(op -> op.getNombreCompleto().equals(operarioEscenario.getNombreCompleto())).findFirst();

        assertEquals("El operario no ha sido eliminado", operarioEncontrado.isPresent(), false );

    }

    @Test
    public void persistirOperarios() {

        IPersistencia<Set<Operario>> persistencia = new PersistenciaXML();
        try {
            persistencia.abrirOutput("operariosTest.xml");
            persistencia.escribir(this.gestionDeOperarios.getOperarios());
            persistencia.cerrarOutput();
            File archivo = new File("operariosTest.xml");
            assertTrue("Deberia existir el archivo operariosTest.xml",  archivo.exists());
        } catch (IOException e) {
            fail("No deberia lanzar excepcion" + e.getMessage());
        }

    }
}