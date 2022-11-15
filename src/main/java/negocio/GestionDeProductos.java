package negocio;

import dto.ProductoDTO;
import excepciones.CambioNombreException;
import excepciones.ProductoExistenteException;
import modelo.Empresa;
import modelo.Pedido;
import modelo.Producto;
import persistencia.IPersistencia;
import persistencia.PersistenciaXML;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class GestionDeProductos {

    private final Empresa empresa;
    private static GestionDeProductos gestionDeProductos = null;

    private GestionDeProductos() {
        this.empresa = Empresa.getEmpresa();
    }

    public static GestionDeProductos get() {
        if( gestionDeProductos == null )
            gestionDeProductos = new GestionDeProductos();
        return gestionDeProductos;
    }

    public void persistirProductos(){
        IPersistencia<Set<Producto>> persistencia = new PersistenciaXML();
        try {
            persistencia.abrirOutput("productos.xml");
            persistencia.escribir(this.empresa.getProductos());
            persistencia.cerrarOutput();
        } catch (IOException e) {

        }
    }

    /**
     * precondition: productoDTO!=null
     * @param producto
     * @throws ProductoExistenteException
     */
    public void altaProducto(ProductoDTO producto) throws ProductoExistenteException {
        Set<Producto> productos = this.empresa.getProductos();
        Producto productoNuevo = new Producto(producto.getNombre(), producto.getPrecio(), producto.getCosto(), producto.getStock());
        boolean existeProducto = productos.stream().anyMatch(p -> p.getNombre().equalsIgnoreCase(productoNuevo.getNombre()));

        if(!existeProducto){
            productos.add(productoNuevo);
            this.empresa.setProductos(productos);
            persistirProductos();
        }
        else
            throw new ProductoExistenteException();
    }

    /**
     * precondition:Producto!=null
     * @param producto
     * @throws CambioNombreException
     */
    public void modificaProducto(ProductoDTO producto) throws CambioNombreException {
        Set<Producto> productos = this.empresa.getProductos();
        Iterator<Producto> it = productos.iterator();
        Producto productoMod= new Producto(producto.getNombre(), producto.getPrecio(), producto.getCosto(), producto.getStock());

        boolean encontreProducto = false;
        Producto prod = null;

        while(it.hasNext() && !encontreProducto) {
            prod = it.next();
            if(prod.getNombre().equalsIgnoreCase(productoMod.getNombre())){
                encontreProducto = true;
            }
        }
        if(encontreProducto) {
            productos.remove(prod);
            productos.add(productoMod);
            this.empresa.setProductos(productos);
            persistirProductos();
        }
        else
            throw new CambioNombreException();

    }

    /**
     * precondition: id!=null
     * @param id
     */
    public void bajaProducto(String id) {
        Set<Producto> productos = this.empresa.getProductos();
        Iterator<Producto> it = productos.iterator();
        boolean encontreProducto = false;
        Producto prod = null;

        while(it.hasNext() && !encontreProducto) {
            prod = it.next();
            if(prod.getId().equals(id)){
                encontreProducto = true;
            }
        }
        if(encontreProducto){
            productos.remove(prod);
            this.empresa.setProductos(productos);
            persistirProductos();
        }
    }

    public Set<Producto> getProductos(){
        return this.empresa.getProductos();
    }

    public boolean descontarStock(Pedido pedido) {
        Set<Producto> productos = this.empresa.getProductos();
        Iterator<Producto> it = productos.iterator();

        boolean encontreProducto = false;
        Producto prod = null;

        while (it.hasNext() && !encontreProducto) {
            prod = it.next();
            if (prod.getNombre().equals(pedido.getProducto().getNombre())) {
                encontreProducto = true;
            }
        }
        boolean puedeActualizar = false;
        if (encontreProducto)
            if (prod.getStock() - pedido.getCantidad() >= 0) {
                productos.remove(prod);
                prod.setStock(prod.getStock() - pedido.getCantidad());
                productos.add(prod);
                this.empresa.setProductos(productos);
                puedeActualizar = true;
            }

        return puedeActualizar;
    }
}