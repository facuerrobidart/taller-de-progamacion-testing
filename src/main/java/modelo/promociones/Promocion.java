package modelo.promociones;

import enums.Dias;
import java.util.List;
import java.util.UUID;

public class Promocion {

    protected String nombre;
    protected String id;
    protected boolean activo;
    protected List<Dias> diasPromo;

    public Promocion() {
    }

    public Promocion(String nombre, List<Dias> diasPromo) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.diasPromo = diasPromo;
        this.activo = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDiasPromo(List<Dias> diasPromo) {
        this.diasPromo = diasPromo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List<Dias> getDiasPromo() {
        return diasPromo;
    }

    public void addDiaPromo(Dias diaPromo) {
        this.diasPromo.add(diaPromo);
    }

    public void removeDiaPromo(Dias diaPromo) {
        if(this.diasPromo.contains(diaPromo))
            this.diasPromo.remove(diaPromo);
    }








}
