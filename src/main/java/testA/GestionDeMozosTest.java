package testA;

import dto.MesaDTO;
import dto.MozoDTO;
import enums.EstadoMesa;
import enums.EstadoMozo;
import excepciones.MesaExistenteException;
import excepciones.MozoExistenteException;
import modelo.Mesa;
import modelo.Mozo;
import negocio.GestionDeMozos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class GestionDeMozosTest {

    private EscenarioConAdminLogueado escenario;
    private GestionDeMozos gestionDeMozos;

    @Before
    public void setUp() throws Exception {
        this.gestionDeMozos = GestionDeMozos.get();
        escenario = new EscenarioConAdminLogueado();
        escenario.aplicaEscenarioConAdminLogueado();
    }

    @After
    public void tearDown() throws Exception {
        escenario.borrarEscenario();
    }

    @Test
    public void altaMozoOk() {

        MozoDTO mozoDTO = new MozoDTO( "Jorge" , "31/12/1999" , 1);
        try {
            this.gestionDeMozos.altaMozo(mozoDTO);
        } catch (MozoExistenteException e) {
            fail("No deberia lanzar excepcion" + e.getMessage());
        }

        Boolean seAgrego = this.gestionDeMozos.getMozos().stream().anyMatch( m -> m.getNombreCompleto().equals(mozoDTO.getNombreCompleto())  );
        this.gestionDeMozos.bajaMozo(new Mozo( mozoDTO.getNombreCompleto(),mozoDTO.getFechaNacimiento(),mozoDTO.getCantidadHijos() ));
        assertEquals( "La mesa no se ha agregado", seAgrego, true );

    }

    @Test
    public void altaMozoFail() {

        Mozo mozoEscenario = this.escenario.getMozo();

        MozoDTO mozoDTO = new MozoDTO(mozoEscenario.getNombreCompleto(), mozoEscenario.getFechaNacimiento(), mozoEscenario.getCantidadHijos());

        assertThrows( "No ha lanzado correctamente la excepcion", MozoExistenteException.class, () -> this.gestionDeMozos.altaMozo(mozoDTO));
        this.gestionDeMozos.bajaMozo(mozoEscenario);

    }

    @Test
    public void modEstadoMozo() {

        Mozo mozoEscenario = this.escenario.getMozo(); //Estado mozo = activo
        EstadoMozo estadoMod = EstadoMozo.FRANCO;

        gestionDeMozos.modEstadoMozo(mozoEscenario, estadoMod);

        Optional<Mozo> mozoModificado = gestionDeMozos.getMozos().stream().filter(m -> m.getNombreCompleto().equals(mozoEscenario.getNombreCompleto())).findFirst();

        if(mozoModificado.isPresent())
            assertEquals("No se modifico el estado correctamente",mozoEscenario.getEstadoMozo(), estadoMod);

    }
}