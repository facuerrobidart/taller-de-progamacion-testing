package modelo;

import excepciones.*;
import modelo.promociones.Promocion;
import modelo.promociones.PromocionFija;
import modelo.promociones.PromocionTemporal;
import persistencia.IPersistencia;
import persistencia.PersistenciaXML;

import java.io.IOException;
import java.util.*;

public class Empresa {

    private static Empresa empresa = null;

    private Operario admin;

    private String nombre;
    private Set<Mozo> mozos;
    private Set<Mesa> mesas;
    private Set<Producto> productos;
    private Set<Operario> operarios;
    private Set<PromocionFija> promocionesFijas;
    private Set<PromocionTemporal> promocionesTemporales;
    private double sueldoBasico=1000;

    private Operario usuarioLogueado;

    public static Empresa getEmpresa() {
        if (empresa == null) {
            empresa = new Empresa();
        }
        return empresa;
    }

    private Empresa() {
        cargarMesas();
        cargarMozos();
        cargarProductos();
        cargarOperarios();
        cargarPromocionesFijas();
        cargarPromocionesTemporales();
    }


    public void logout() {
        usuarioLogueado = null;
    }

    private void cargarOperarios() {
        IPersistencia<Set<Operario>> persistencia = new PersistenciaXML();

        try { // Archivo XML "Operarios" -> Empresa
            persistencia.abrirInput("operarios.xml");
            this.operarios = persistencia.leer();
            persistencia.cerrarInput();
        } catch (Exception err) {
            Operario admin = Operario.admin();
            this.operarios = new HashSet<>();
            this.operarios.add(admin);
        }
    }

    private void cargarProductos() {
        IPersistencia<Set<Producto>> persistencia = new PersistenciaXML();

        try { // Archivo XML "Productos" -> Empresa
            persistencia.abrirInput("productos.xml");
            this.productos = persistencia.leer();
            if (productos == null) {
                productos = new HashSet<>();
            }
            persistencia.cerrarInput();
        } catch (Exception err) {
            this.productos = new HashSet<>();
        }
    }

    private void cargarMesas() {
        IPersistencia<Set<Mesa>> persistencia = new PersistenciaXML();

        try { // Archivo XML "Mesas" -> Empresa
            persistencia.abrirInput("mesas.xml");
            this.mesas = persistencia.leer();
            if (mesas == null) {
                mesas = new HashSet<>();
            }
            persistencia.cerrarInput();
        } catch (Exception err) {
            this.mesas = new HashSet<>();
        }
    }

    public void cargarMozos() {
        IPersistencia<Set<Mozo>> persistencia = new PersistenciaXML();

        try { // Archivo XML "Mozos" -> Empresa
            persistencia.abrirInput("mozos.xml");
            this.mozos = persistencia.leer();
            if (mozos == null) {
                mozos = new HashSet<>();
            }
            persistencia.cerrarInput();
        } catch (Exception err) {
            this.mozos = new HashSet<>();
        }
    }

    private void cargarPromocionesFijas() {
        IPersistencia<Set<PromocionFija>> persistencia = new PersistenciaXML();

        try { // Archivo XML "Promociones" -> Empresa
            persistencia.abrirInput("promociones-fijas.xml");
            promocionesFijas = persistencia.leer();
            if (promocionesFijas == null) {
                promocionesFijas = new HashSet<>();
            }
            persistencia.cerrarInput();
        } catch (Exception err) {
            this.promocionesFijas = new HashSet<>();
        }
    }

    private void cargarPromocionesTemporales() {
        IPersistencia<Set<PromocionTemporal>> persistencia = new PersistenciaXML();

        try { // Archivo XML "Promociones" -> Empresa
            persistencia.abrirInput("promociones-temporales.xml");
            promocionesTemporales = persistencia.leer();
            if (promocionesTemporales == null) {
                promocionesTemporales = new HashSet<>();
            }
            persistencia.cerrarInput();
        } catch (Exception err) {
            this.promocionesTemporales = new HashSet<>();
        }
    }

    /**
     *
     * @return: Mozo con mayor cantidad de ventas
     */
    public Mozo mayorVolumenVentaMozo(){
        Iterator<Mozo> it = mozos.iterator();
        Mozo mozo, mayor = null;
        double max = -1;

        while(it.hasNext()){
            mozo = it.next();
            if(mozo.getVentas() > max){
                mayor = mozo;
                max = mozo.getVentas();
            }
        }
        return mayor;
    }

    /**
     *
     * @return: Mozo con menor cantidad de ventas
     */
    public Mozo menorVolumenVentaMozo(){
        Iterator<Mozo> it = mozos.iterator();
        Mozo mozo, menor = null;
        double min = 9999999;

        while(it.hasNext()){
            mozo = it.next();
            if(mozo.getVentas() < min){
                menor = mozo;
                min = mozo.getVentas();
            }
        }
        return menor;
    }

    /**
     *
     * @param nroMesa
     * @return: Devuelve el consumo promedio del numero de mesa ingresada
     * @throws MesaNoExistenteException
     */
    public double consumoPromedioMesa(int nroMesa) {
        Iterator<Mesa> it = mesas.iterator();
        boolean encontreMesa = false;
        double consumo = 0.;
        Mesa mesa = null;

        while (it.hasNext() && !encontreMesa) {
            mesa = it.next();
            if (mesa.getNroMesa() == nroMesa) {
                encontreMesa = true;
            }
        }

        if (encontreMesa)
            if( mesa.getCantCuentasCerradas() != 0 )
                return mesa.getVentas() / mesa.getCantCuentasCerradas();
            else
                return 0;
        else
            return 0;

    }

    /**
     * precondition: id!=null
     * @param id
     * @return: sueldo del mozo referenciado por el id
     * @throws MozoNoExistenteException
     */
    public double calculaSueldo(String id){

        Iterator<Mozo> it = mozos.iterator();
        boolean sale = false;
        Mozo mozo = null;

        while(it.hasNext() && !sale) {
            mozo = it.next();
            if(mozo.getId().equals(id)){
                sale = true;
            }
        }
        return this.sueldoBasico * (1 + 0.05 * mozo.getCantidadHijos());
    }

    public static void setEmpresa(Empresa empresa) {
        Empresa.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Mozo> getMozos() {
        return mozos;
    }

    public void setMozos(Set<Mozo> mozos) {
        this.mozos = mozos;
    }

    public Set<Mesa> getMesas() {
        return mesas;
    }

    public void setMesas(Set<Mesa> mesas) {
        this.mesas = mesas;
    }

    public Set<Producto> getProductos() {
        return productos;
    }

    public void setProductos(Set<Producto> productos) {
        this.productos = productos;
    }

    public Set<Operario> getOperarios() {
        return operarios;
    }

    public void setOperarios(Set<Operario> operarios) {
        this.operarios = operarios;
    }

    public Set<PromocionFija> getPromocionesFijas() {
        return promocionesFijas;
    }

    public void setPromocionesFijas(Set<PromocionFija> promocionesFijas) {
        this.promocionesFijas = promocionesFijas;
    }

    public Set<PromocionTemporal> getPromocionesTemporales() {
        return promocionesTemporales;
    }

    public void setPromocionesTemporales(Set<PromocionTemporal> promocionesTemporales) {
        this.promocionesTemporales = promocionesTemporales;
    }

    public double getSueldoBasico() {
        return sueldoBasico;
    }

    public void setSueldoBasico(double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    public Operario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    public void setUsuarioLogueado(Operario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;
    }

    public Operario login(String username, String password) throws UsuarioIncorrectoException, ContrasenaIncorrectaException {

        Optional<Operario> candidato = operarios.stream()
                .filter(x -> x.getUsername().equalsIgnoreCase(username)).findAny();

        if (candidato.isPresent()) {
            candidato.get().validaContrasena(password);
            this.usuarioLogueado = candidato.get();
            return candidato.get();
        }

        if(this.admin!=null) {
            if (this.admin.getUsername().equalsIgnoreCase(username)) {
                this.admin.validaContrasena(password);
                this.usuarioLogueado = this.admin;
                return admin;
            }
        }

        throw new UsuarioIncorrectoException("Usuario no encontrado", username);
    }

    public Operario getAdmin() {
        return admin;
    }

    public void setAdmin(Operario admin) {
        this.admin = admin;
    }




}
