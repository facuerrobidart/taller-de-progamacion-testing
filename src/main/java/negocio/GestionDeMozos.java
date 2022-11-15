package negocio;

import dto.MozoDTO;
import enums.EstadoMozo;
import excepciones.MozoExistenteException;
import excepciones.MozoNoExistenteException;
import excepciones.PermisoDenegadoException;
import modelo.Empresa;
import modelo.Mozo;
import persistencia.IPersistencia;
import persistencia.PersistenciaXML;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public class GestionDeMozos {

    private final Empresa empresa;
    private static GestionDeMozos gestionDeMozos = null;

    private GestionDeMozos() {
        this.empresa = Empresa.getEmpresa();
    }

    public static GestionDeMozos get() {
        if( gestionDeMozos == null )
            gestionDeMozos = new GestionDeMozos();
        return gestionDeMozos;
    }

    public void persistirMozos(){
        IPersistencia<Set<Mozo>> persistencia = new PersistenciaXML();
        try {
            persistencia.abrirOutput("mozos.xml");
            persistencia.escribir(this.getMozos());
            persistencia.cerrarOutput();
        } catch (IOException e) {

        }
    }

    /**
     * Se da de alta un mozo
     * precondition: mozo!=null
     * @param mozo
     * @throws MozoExistenteException
     */
    public void altaMozo(MozoDTO mozo) throws MozoExistenteException {

        if (this.empresa.getUsuarioLogueado().getUsername().equals("admin")){
            Set<Mozo> mozos = this.empresa.getMozos();

            Mozo nuevoMozo = new Mozo(mozo.getNombreCompleto(),mozo.getFechaNacimiento(),mozo.getCantidadHijos());
            boolean existeMozo = mozos.stream().anyMatch(m -> m.getNombreCompleto().equalsIgnoreCase(nuevoMozo.getNombreCompleto()) );
            if( existeMozo )
                throw new MozoExistenteException();
            else{
                mozos.add(nuevoMozo);
                this.empresa.setMozos(mozos);
                persistirMozos();
            }
        }
    }

    public boolean hayMozosActivos(){
        Set<Mozo> mozos = this.getMozos();

        for ( Mozo mozo : mozos ) {
            if( mozo.getEstadoMozo() == EstadoMozo.ACTIVO ){
                return true;
            }
        }
        return false;
    }

    /**
     * Modifica el mozo recibido
     * precondition: mozo!=null
     * @param mozo
     */
    public void modificarMozo(MozoDTO mozo) {

        if (this.empresa.getUsuarioLogueado().getUsername().equals("admin")){
            Set<Mozo> mozos = this.empresa.getMozos();

            Mozo mozoMod = new Mozo(mozo.getNombreCompleto(),mozo.getFechaNacimiento(),mozo.getCantidadHijos());

            boolean existeMozo = mozos.stream().anyMatch(m -> m.getNombreCompleto().equalsIgnoreCase(mozo.getNombreCompleto()) );
            if( existeMozo ){
                mozos.remove(mozoMod);
                mozos.add(mozoMod);
                this.empresa.setMozos(mozos);
                persistirMozos();
            }
        }
    }

    /**
     * Da de baja el mozo enviado
     *  precondition: mozo!=null
     * @param mozo
     */
    public void bajaMozo(Mozo mozo) {

        if (this.empresa.getUsuarioLogueado().getUsername().equals("admin")){
            Set<Mozo> mozos = this.empresa.getMozos();

            boolean existeMozo = mozos.stream().anyMatch(m -> m.getId().equalsIgnoreCase(mozo.getId()) );
            if( existeMozo ){
                mozos.remove(mozo);
                this.empresa.setMozos(mozos);
                persistirMozos();
            }
        }
    }

    /**
     * precondition: mozo!=null && EstadoMozo!=null
     * @param mozo
     * @param nuevoEstado
     */
    public void modEstadoMozo(Mozo mozo, EstadoMozo nuevoEstado) {

        Set<Mozo> mozos = this.empresa.getMozos();
        Optional<Mozo> mozoMod = mozos.stream()
                .filter(m -> m.getId().equals(mozo.getId()))
                .findFirst();

        if (mozoMod.isPresent()) {
            mozoMod.get().setEstadoMozo(nuevoEstado);
            mozos.remove(mozoMod.get());
            mozos.add(mozoMod.get());
            persistirMozos();
        }
    }

    public Set<Mozo> getMozos(){
        return empresa.getMozos();
    }

    public String mozoMaxVentas(){
        return this.empresa.mayorVolumenVentaMozo().getNombreCompleto();
    }

    public String mozoMinVentas(){
        return  this.empresa.menorVolumenVentaMozo().getNombreCompleto();
    }

    public double calculaSueldo(Mozo mozo){
        return empresa.calculaSueldo(mozo.getId());
    }

}
