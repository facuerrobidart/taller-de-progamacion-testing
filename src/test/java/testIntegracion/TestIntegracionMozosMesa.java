package testIntegracion;

import dto.MesaDTO;
import dto.MozoDTO;
import enums.EstadoMozo;
import excepciones.MesaExistenteException;
import excepciones.MozoExistenteException;
import modelo.Empresa;
import modelo.Mesa;
import modelo.Mozo;
import modelo.Operario;
import negocio.GestionDeMesas;
import negocio.GestionDeMozos;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestIntegracionMozosMesa {
    private GestionDeMesas gestionDeMesas;
    private GestionDeMozos gestionDeMozos;
    private Empresa empresa;
    private Mozo mozo;
    private Mesa mesa;

    @Before
    public void setUp() {
        this.empresa = Empresa.getEmpresa();
        this.gestionDeMesas = GestionDeMesas.get();
        this.gestionDeMozos = GestionDeMozos.get();
    }

    @Test
    public void test() {
        empresa.setUsuarioLogueado(new Operario("admin","admin", "admin1234", true));
        try {
            gestionDeMesas.altaMesa(new MesaDTO(1004, 4));
        } catch (MesaExistenteException mesaConMozo) {
        }
        mesa = gestionDeMesas.getMesas().stream().filter(m -> m.getNroMesa() == 1004).findFirst().get();


        try {
            gestionDeMozos.altaMozo(new MozoDTO("[TEST] Mozo", "2022-01-01", 10));
        } catch (MozoExistenteException e) {
        }
        mozo = gestionDeMozos.getMozos().stream().filter(m -> m.getNombreCompleto().equals("[TEST] Mozo")).findFirst().get();


        gestionDeMozos.modEstadoMozo(mozo, EstadoMozo.ACTIVO);

        gestionDeMesas.asignarMozoMesa(new MozoDTO(
                mozo.getNombreCompleto(),
                mozo.getFechaNacimiento(),
                mozo.getCantidadHijos()
        ), new MesaDTO(1000, 4));


        Assert.assertEquals(mesa.getMozoAsignado(), mozo);
    }

    @After
    public void tearDown() {
        gestionDeMesas.bajaMesa(1004);
        gestionDeMozos.bajaMozo(mozo);
        empresa.setUsuarioLogueado(null);
    }
}
