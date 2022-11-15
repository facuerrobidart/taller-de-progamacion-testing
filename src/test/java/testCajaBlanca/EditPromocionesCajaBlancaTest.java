package testCajaBlanca;

import dto.PromocionProductoDTO;
import enums.Dias;
import modelo.Empresa;
import modelo.promociones.PromocionFija;
import negocio.GestionDePromociones;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class EditPromocionesCajaBlancaTest {
    private EscenariosEditPromociones escenarios = new EscenariosEditPromociones();
    private GestionDePromociones g = GestionDePromociones.get();
    private Empresa empresa = Empresa.getEmpresa();

    @Test
    public void testEditPromocionExito() {
        escenarios.cargarListaPromociones();
        PromocionFija promo = escenarios.getPromo1();
        String nombre = promo.getNombre();

        promo.setDtoPorCantPrecioU(80.0);

        g.modificaPromocionFija(
            new PromocionProductoDTO(
                promo.getNombre(),
                promo.isActivo(),
                promo.getDiasPromo(),
                promo.getProducto(),
                promo.isDosPorUno(),
                promo.isDtoPorCant(),
                promo.getDtoPorCantMin(),
                promo.getDtoPorCantPrecioU()
        ));

        Assert.assertTrue(
                empresa.getPromocionesFijas()
                .stream()
                .anyMatch(p -> p.getNombre().equalsIgnoreCase(nombre) && p.getDtoPorCantPrecioU() == 80.0)
        );
        //me fijo que la promo se haya modificado

        escenarios.removerListaPromociones();
    }

    @Test
    public void testEditPromocionListaVacia() {
        escenarios.cargarListaVacia();

        g.modificaPromocionFija(
                new PromocionProductoDTO(
                        "Promo test",
                        true,
                        List.of(Dias.DOMINGO,Dias.JUEVES),
                        escenarios.getProd1(),
                        true,
                        false,
                        0,
                        0.0
                ));

        Assert.assertTrue(
                empresa.getPromocionesFijas()
                        .stream()
                        .noneMatch(p -> p.getNombre().equalsIgnoreCase("Promo test"))
        ); //me fijo que la promo no se haya agregado

        escenarios.restaurarListaPromociones();
    }

    @Test
    public void testEditPromocionInexistente() {
        escenarios.cargarListaPromociones();

        g.modificaPromocionFija(
            new PromocionProductoDTO(
                "Promo inexistente",
                true,
                List.of(Dias.DOMINGO,Dias.JUEVES),
                escenarios.getProd1(),
                true,
                false,
                0,
                0.0
        ));

        Assert.assertTrue(
                empresa.getPromocionesFijas()
                .stream()
                .noneMatch(p -> p.getNombre().equalsIgnoreCase("Promo inexistente"))
        ); //me fijo que la promo no se haya agregado

        escenarios.removerListaPromociones();
    }
}
