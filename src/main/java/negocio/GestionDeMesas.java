package negocio;

import dto.MesaDTO;
import dto.MozoDTO;
import enums.EstadoMesa;
import excepciones.MesaExistenteException;
import excepciones.MesaNoExistenteException;
import modelo.*;
import modelo.promociones.Promocion;
import modelo.promociones.PromocionFija;
import modelo.promociones.PromocionTemporal;
import persistencia.IPersistencia;
import persistencia.PersistenciaXML;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class GestionDeMesas {

    private final Empresa empresa;
    private static GestionDeMesas gestionDeMesas = null;

    private static GestionDePromociones gestionDePromociones = null;
    private Set<Mozo> mozos;

    private GestionDeMesas() {
        this.empresa = Empresa.getEmpresa();
        this.mozos = Empresa.getEmpresa().getMozos();
        this.gestionDePromociones = GestionDePromociones.get();
    }

    public static GestionDeMesas get() {
        if( gestionDeMesas == null )
            gestionDeMesas = new GestionDeMesas();
        return gestionDeMesas;
    }

    /**
     * Persistencia de la coleccion de mesas
     */
    public void persistirMesas(){
        IPersistencia<Set<Mesa>> persistencia = new PersistenciaXML();
        try {
            persistencia.abrirOutput("mesas.xml");
            persistencia.escribir(this.empresa.getMesas());
            persistencia.cerrarOutput();
        } catch (IOException e) {

        }
    }

    /**
     * Carga la mesa ala coleccion
     * precondition: MesaDTO != null && mesas != null
     * @param mesa
     * @throws MesaExistenteException
     */
    public void altaMesa(MesaDTO mesa) throws MesaExistenteException {

        Set<Mesa> mesas = this.empresa.getMesas();
        boolean existeMesa = mesas.stream().anyMatch(m -> m.getNroMesa() == mesa.getNroMesa() );

        Mesa mesaNueva = new Mesa(mesa.getNroMesa(),mesa.getCantSillas());

        if(!existeMesa){
            mesas.add(mesaNueva);
            persistirMesas();
        }
        else
            throw new MesaExistenteException();
    }

    /**
     * Elimina una mesa recibida y la vuelve a agregar
     * precondition: MesaDTO!=null
     * @param mesa
     */
    public double cerrarMesa(MesaDTO mesa,String medioDePago){

        double total = 0;
        Set<Mesa> mesas = this.empresa.getMesas();
        Iterator<Mesa> it = mesas.iterator();

        Mesa mesaMod = new Mesa(mesa.getNroMesa(),mesa.getCantSillas());

        boolean encontreMesa = false;
        Mesa m = null;

        while(it.hasNext() && !encontreMesa) {
            m = it.next();
            if(m.getNroMesa() == mesaMod.getNroMesa()){
                encontreMesa = true;
            }
        }
        if(encontreMesa) {
            total = this.totalMesa(m, medioDePago);
            mesas.remove(m);
            m.setEstadoMesa(EstadoMesa.LIBRE);
            m.setMozoAsignado(mesa.getMozoAsignado());
            m.setVentas(mesa.getVentas() + total);
            m.setCantCuentasCerradas(mesa.getCantCuentasCerradas() + 1);
            m.setComanda(null);
            mesas.add(m);
            this.empresa.setMesas(mesas);

            //Actualizo las ventas del mozo

            Set<Mozo> mozos = empresa.getMozos();
            Iterator<Mozo> itMozos = mozos.iterator();
            boolean encontre = false;
            Mozo mGenerico;
            while (itMozos.hasNext() && !encontre) {
                mGenerico = itMozos.next();
                if (mGenerico.getNombreCompleto().equalsIgnoreCase(mesa.getMozoAsignado().getNombreCompleto())) {
                    mozos.remove(mGenerico);
                    mGenerico.setVentas(mGenerico.getVentas() + total);
                    encontre = true;
                    mozos.add(mGenerico);
                }
            }
            empresa.setMozos(mozos);
        }
        return total;
    }

    public void modificaMesa(MesaDTO mesa)  {
        Set<Mesa> mesas = this.empresa.getMesas();
        Iterator<Mesa> it = mesas.iterator();

        Mesa mesaMod = new Mesa(mesa.getNroMesa(),mesa.getCantSillas());

        boolean encontreMesa = false;
        Mesa m = null;

        while(it.hasNext() && !encontreMesa) {
            m = it.next();
            if(m.getNroMesa() == mesaMod.getNroMesa()){
                encontreMesa = true;
            }
        }
        if(encontreMesa) {
            mesaMod.setEstadoMesa( mesa.getEstadoMesa() );
            mesaMod.setCantCuentasCerradas( mesa.getCantCuentasCerradas() );
            mesaMod.setComanda(mesa.getComanda());
            mesaMod.setMozoAsignado(mesa.getMozoAsignado());
            mesaMod.setVentas(mesa.getVentas());
            mesas.remove(m);
            mesas.add(mesaMod);
            this.empresa.setMesas(mesas);
            persistirMesas();
        }
    }

    /**
     * Elimina la mesa seleccionada
     * @param nroMesa
     */
    public void bajaMesa(int nroMesa) {
        Set<Mesa> mesas = this.empresa.getMesas();
        Optional<Mesa> mesa = mesas.stream().filter(m -> m.getNroMesa() == nroMesa).findFirst();

        if(mesa.isPresent()){
            mesas.remove(mesa.get());
            this.empresa.setMesas(mesas);
            persistirMesas();
        }
    }

    /**
     * Asigna el mozo enviado a la mesa enviada
     * precondition: MesaDTO!=null && MozoDTO!=null
     * @param mozo
     * @param mesa
     */

    public void asignarMozoMesa(MozoDTO mozo, MesaDTO mesa) {

        boolean existeMozo = this.empresa.getMozos().stream().anyMatch(m -> m.getNombreCompleto().equalsIgnoreCase(mozo.getNombreCompleto()) );
        Set<Mesa> mesas = this.empresa.getMesas();
        boolean existeMesa = mesas.stream().anyMatch(m -> m.getNroMesa() == mesa.getNroMesa());

            if( existeMesa ){
                Mesa mesaM = new Mesa(mesa.getNroMesa(), mesa.getCantSillas());
                Mozo mozoA = new Mozo(mozo.getNombreCompleto(),mozo.getFechaNacimiento(),mozo.getCantidadHijos());
                mesas.removeIf(m -> m.getNroMesa() == mesaM.getNroMesa());
                mesaM.setMozoAsignado(mozoA);
                mesas.add(mesaM);
                this.empresa.setMesas(mesas);
            }
    }

    /**
     * Cierra la mesa enviada
     * precondition: mesa!=null
     * @param mesa
     * @return
     */
    public double totalMesa(Mesa mesa,String medioDePago){
        DateFormat dateFormat = new SimpleDateFormat("EEEEE");
        String dia = dateFormat.format(Calendar.getInstance().getTime());//Dia de hoy en letras
        boolean seAplicoPromo = false; //Uso global para saber si ya se aplico alguna promocion y si se estan acumulando o no promociones
        boolean aplique; //Uso local del while para aniadir el costo si no se aplico ninguna promo

        double total=0,bruto=0;
        List<Pedido> pedidosMesa = mesa.getComanda().getPedidos();
        Iterator<Pedido> itPedidos = pedidosMesa.iterator();
        Pedido pedido;

        while(itPedidos.hasNext()) {
            pedido = itPedidos.next();
            Set<PromocionFija> promocionesFijas = empresa.getPromocionesFijas();
            Iterator<PromocionFija> itPF = promocionesFijas.iterator();
            PromocionFija promoFija;
            aplique = false;
            while (itPF.hasNext()) {
                promoFija = itPF.next();
                if(promoFija.isActivo() && gestionDePromociones.isDiaIncluido(promoFija,dia) && promoFija.getProducto()==pedido.getProducto()) {
                    if (promoFija.isDosPorUno()) {
                        if (pedido.getCantidad() % 2 == 0) {
                            bruto += pedido.getProducto().getPrecio() * pedido.getCantidad() * 0.5; //2x1 de una cantidad par = %50 de descuento
                            seAplicoPromo = true;
                            aplique = true;
                        }
                        else {
                            bruto += (pedido.getProducto().getPrecio() * (pedido.getCantidad() - 1) * 0.5) + pedido.getProducto().getPrecio(); //Le sumo el impar
                            seAplicoPromo = true;
                            aplique = true;
                        }
                        } else if (pedido.getCantidad() > promoFija.getDtoPorCantMin()) { //Si supero la cant minima
                            bruto += pedido.getCantidad() * promoFija.getDtoPorCantPrecioU();
                             seAplicoPromo = true;
                             aplique = true;
                        }
                        else
                            bruto += pedido.getCantidad() * pedido.getProducto().getPrecio();
                }
            }
            if(!aplique)
                bruto += pedido.getCantidad() * pedido.getProducto().getPrecio();
        }

        //Calculo final de promociones temporales
        Set<PromocionTemporal> promocionesTemporales = empresa.getPromocionesTemporales();
        Iterator<PromocionTemporal> itPT = promocionesTemporales.iterator();
        PromocionTemporal promo;
        aplique=false;
        while (itPT.hasNext() && aplique==false) {
            promo = itPT.next();
            if (promo.isActivo() && gestionDePromociones.isDiaIncluido(promo, dia) && promo.getFormaPago().equalsIgnoreCase(medioDePago)) {
                    if (promo.isEsAcumulable() || seAplicoPromo==false) {
                        total = bruto * (1 - (promo.getPorcentajeDescuento() / 100));
                        aplique=true;
                    }
            }
            else
                total = bruto;
        }

        return total;
    }

    public Set<Mesa> getMesas(){
        return this.empresa.getMesas();
    }

    /**
     * trae el consumo promedio de una mesa
     * @param nroMesa
     * @return consumo promedio de la mesa enviada
     * @throws MesaNoExistenteException
     */
    public float calculaConsumoPromedio(int nroMesa) throws MesaNoExistenteException {
        return (float) empresa.consumoPromedioMesa(nroMesa);
    }

    public Set<Mozo> getMozos() {
        return mozos;
    }

    public void setMozos(Set<Mozo> mozos) {
        this.mozos = mozos;
    }
}
