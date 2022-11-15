package testGUI;

import controladores.ControladorLogin;
import org.junit.After;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;

public class GuiTestIngresoDatos {

    Robot robot;
    ControladorLogin controlador;
    FalsoOptionPane op = new FalsoOptionPane();

    public GuiTestIngresoDatos()
    {
        try
        {
            robot = new Robot();
        } catch (AWTException e)
        {
        }
    }

    @Before
    public void setUp() throws Exception
    {
        controlador = new ControladorLogin();
        controlador.setOptionPane(op);
    }

    @After
    public void tearDown() throws Exception
    {
        controlador.getVistaLogin().esconder();
    }

    @Test
    public void testLoginOk()
    {
        robot.delay(TestUtils.getDelay());
        //obtengo las referencias a los componentes necesarios
        JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVistaLogin(), "textFieldUsername");
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVistaLogin(), "textFieldContrasena");

        JButton btnEntrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVistaLogin(), "btnEntrar");

        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("admin", robot);

        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("admin1234", robot);

        TestUtils.clickComponent(btnEntrar, robot);

        assertEquals("Deberia coincidir el nombre de usuario con el nombre ingresado", "admin", controlador.getLogueado().getUsername());
    }

    @Test
    public void testLoginFail()
    {
        robot.delay(TestUtils.getDelay());

        JTextField nombre = (JTextField) TestUtils.getComponentForName((Component) controlador.getVistaLogin(), "textFieldUsername");
        JTextField password = (JTextField) TestUtils.getComponentForName((Component) controlador.getVistaLogin(), "textFieldContrasena");

        JButton btnEntrar = (JButton) TestUtils.getComponentForName((Component) controlador.getVistaLogin(), "btnEntrar");

        TestUtils.clickComponent(nombre, robot);
        TestUtils.tipeaTexto("admin", robot);

        TestUtils.clickComponent(password, robot);
        TestUtils.tipeaTexto("1234", robot);

        TestUtils.clickComponent(btnEntrar, robot);

        assertEquals("Mensaje incorrecto, deberia decir: Usuario o contraseña incorrectos" ,"Usuario o contraseña incorrectos" ,op.getMensaje());
    }




}