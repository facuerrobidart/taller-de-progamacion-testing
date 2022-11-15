package vistas;
import javax.swing.*;

import java.awt.*;

public class MiOptionPane implements InterfazOptionPanel {
    public MiOptionPane() {
        super();
    }


    @Override
    public void ShowMessage(Component parent, String mensaje) {
        JOptionPane.showMessageDialog(parent, mensaje, null, JOptionPane.ERROR_MESSAGE);
    }
}