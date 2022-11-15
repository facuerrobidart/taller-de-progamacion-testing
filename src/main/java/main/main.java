package main;

import controladores.ControladorAltaOperario;
import controladores.ControladorLogin;
import modelo.Empresa;

public class main {

    public static void main(String[] args) {

        Empresa empresa = Empresa.getEmpresa();
        ControladorLogin controladorLogin = ControladorLogin.getControladorLogin();
    }

}
