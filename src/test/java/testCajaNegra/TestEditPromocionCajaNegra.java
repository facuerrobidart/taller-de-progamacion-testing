package testCajaNegra;

import dto.PromocionProductoDTO;
import modelo.Empresa;
import modelo.promociones.PromocionFija;
import negocio.GestionDePromociones;
import org.junit.Assert;
import org.junit.Test;
import testCajaBlanca.EscenariosEditPromociones;

public class TestEditPromocionCajaNegra {
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
}
