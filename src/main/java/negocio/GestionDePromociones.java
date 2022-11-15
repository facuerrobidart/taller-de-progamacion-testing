package negocio;

import dto.PromocionProductoDTO;
import dto.PromocionTemporalDTO;
import enums.Dias;
import excepciones.PromocionExistenteException;
import modelo.Empresa;
import modelo.promociones.Promocion;
import modelo.promociones.PromocionFija;
import modelo.promociones.PromocionTemporal;
import persistencia.IPersistencia;
import persistencia.PersistenciaXML;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GestionDePromociones {

    private final Empresa empresa;
    private static GestionDePromociones gestionDePromociones = null;

    private GestionDePromociones() {
        this.empresa = Empresa.getEmpresa();
    }

    public static GestionDePromociones get() {
        if( gestionDePromociones == null )
            gestionDePromociones = new GestionDePromociones();
        return gestionDePromociones;
    }

    public Set<PromocionFija> getPromocionesFijas(){
        return this.empresa.getPromocionesFijas();
    }

    public Set<PromocionTemporal> getPromocionesTemporales(){
        return this.empresa.getPromocionesTemporales();
    }

    public void persistirPromocionesTemporales() {
        IPersistencia<Set<PromocionTemporal>> persistencia = new PersistenciaXML();
        try {
            persistencia.abrirOutput("promociones-temporales.xml");
            persistencia.escribir(this.getPromocionesTemporales());
            persistencia.cerrarOutput();
        } catch (IOException e) {

        }
    }

    public void persistirPromocionesFijas(){
        IPersistencia<Set<PromocionFija>> persistencia1 = new PersistenciaXML();
        try {
            persistencia1.abrirOutput("promociones-fijas.xml");
            persistencia1.escribir(this.getPromocionesFijas());
            persistencia1.cerrarOutput();
        } catch (IOException e) {

        }

    }

    /**
     * precondition: promocion!=null
     * @param promocion
     * @throws PromocionExistenteException
     */
    public void altaPromocionFija(PromocionProductoDTO promocion) throws PromocionExistenteException {
        PromocionFija promoFija;
        Set<PromocionFija> promociones = this.getPromocionesFijas();

        boolean existePromo = promociones.stream().anyMatch(p -> p.getNombre().equalsIgnoreCase(promocion.getNombre()));

        if(!existePromo){

            promoFija = new PromocionFija(
                    promocion.getNombre(),
                    promocion.getDiasPromo(),
                    promocion.getProducto(),
                    promocion.isDosPorUno(),
                    promocion.isDtoPorCant(),
                    promocion.getDtoPorCantMin(),
                    promocion.getDtoPorCantPrecioU()
            );

            promociones.add(promoFija);
            this.empresa.setPromocionesFijas(promociones);
            persistirPromocionesFijas();

        }else
            throw new PromocionExistenteException("Ya existe una promocion fija con el nombre " + promocion.getNombre());
    }

    /**
     * precondition: promocion!=null
     * @param promocion
     * @throws PromocionExistenteException
     */
    public void altaPromocionTemporal(PromocionTemporalDTO promocion) throws PromocionExistenteException {
        PromocionTemporal promoTemporal;
        Set<PromocionTemporal> promociones = this.getPromocionesTemporales();

        boolean existePromo = promociones.stream().anyMatch(p -> p.getNombre().equalsIgnoreCase(promocion.getNombre()));

        if(!existePromo){

            promoTemporal = new PromocionTemporal(promocion.getNombre(),
                    promocion.getDiasPromo(),
                    promocion.getFormaPago(),
                    promocion.getPorcentajeDescuento(),
                    promocion.isAcumulable()
            );

            promociones.add(promoTemporal);
            this.empresa.setPromocionesTemporales(promociones);
            persistirPromocionesTemporales();

        } else
            throw new PromocionExistenteException("Ya existe una promocion temporal con el nombre " + promocion.getNombre());
    }

        /**
         * precondition promocion!=null
         * @param promocion
         */
    public void modificaPromocionFija(PromocionProductoDTO promocion) {

        Set<PromocionFija> promociones = this.getPromocionesFijas();

        Iterator<PromocionFija> it = promociones.iterator();
        boolean encontrePromo = false;
        Promocion p = null;

        while(it.hasNext() && !encontrePromo) {
            p = it.next();
            if( p.getNombre().equals(promocion.getNombre()) )
                encontrePromo = true;
        }

        if(encontrePromo){

            promociones.remove(p);

            PromocionFija promoFija = new PromocionFija(promocion.getNombre(),
                                                        promocion.getDiasPromo(),
                                                        promocion.getProducto(),
                                                        promocion.isDosPorUno(),
                                                        promocion.isDtoPorCant(),
                                                        promocion.getDtoPorCantMin(),
                                                        promocion.getDtoPorCantPrecioU()
           );

            promociones.add(promoFija);
            this.empresa.setPromocionesFijas(promociones);
            persistirPromocionesFijas();

        }

    }


    /**
     * precondition promocion!=null
     * @param promocion
     */
    public void modificaPromocionTemporal(PromocionTemporalDTO promocion) {
            Set<PromocionTemporal> promociones = this.getPromocionesTemporales();

            Iterator<PromocionTemporal> it = promociones.iterator();
            boolean encontrePromo = false;
            Promocion p = null;

            while(it.hasNext() && !encontrePromo) {
                p = it.next();
                if( p.getNombre().equals(promocion.getNombre()) )
                    encontrePromo = true;
            }

            if(encontrePromo){

                promociones.remove(p);

                PromocionTemporal promoTemporal = new PromocionTemporal(promocion.getNombre(),
                                                                        promocion.getDiasPromo(),
                                                                        promocion.getFormaPago(),
                                                                        promocion.getPorcentajeDescuento(),
                                                                        promocion.isAcumulable()
                );

                promociones.add(promoTemporal);
                this.empresa.setPromocionesTemporales(promociones);
                persistirPromocionesTemporales();
            }
    }


    public void bajaPromocionFija( String id ) {

        this.empresa.getPromocionesFijas().removeIf( p -> p.getId().equalsIgnoreCase(id) );

        persistirPromocionesFijas();

    }

    public void bajaPromocionTemporal( String id ) {

        this.empresa.getPromocionesTemporales().removeIf( p -> p.getId().equalsIgnoreCase(id) );

        persistirPromocionesTemporales();

    }

    /**
     * Devuelve si el dia pasado por parametro se encuentra en la promocion pasada por parametro
     * precondition: promo!=null && dia!=null && dia!=""
     * @param promocion
     * @param dia
     * @return
     */
    public boolean isDiaIncluido(Promocion promocion,String dia){

        List<Dias> dias = promocion.getDiasPromo();
        Iterator<Dias> it = dias.iterator();
        boolean res = false;

        while (it.hasNext() && res==false){
            String diaLista = it.next().toString();
            if(dia.startsWith("s") && diaLista.startsWith("S"))
                res = true;
            else
                res = diaLista.equalsIgnoreCase(dia);
        }

        return res;

    }

    /**
     * Devuelve si hay suficientes promociones por prodcuto para abrir el local
     * preconditions: listaDePromociones!=null
     * @return
     */
    public boolean hayPromocionesHoy(){
        DateFormat dateFormat = new SimpleDateFormat("EEEEE");
        String dia = dateFormat.format(Calendar.getInstance().getTime());//Dia de hoy en letras

        Set<PromocionFija> promocionesFijas = empresa.getPromocionesFijas();
        Iterator<PromocionFija> itPF = promocionesFijas.iterator();
        PromocionFija promoFija;
        int cont=0;
        while (itPF.hasNext() && cont<2) {
            promoFija = itPF.next();
            if (promoFija.isActivo() && gestionDePromociones.isDiaIncluido(promoFija, dia)) {
                cont++;
            }
        }
        return cont==2;
    }


}
