import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI_DespliegueTransacciones {
    private JPanel panelPrincipalDT;
    private JPanel panelListadoTransacciones;
    private JPanel panelInformaciones;
    private JPanel panelAtributosCliente;
    private JComboBox comboBox1;
    private JCheckBox a1CtaCteCheckBox;
    private JCheckBox a2CtaAhorroCheckBox;
    private JCheckBox a3CtaVistaCheckBox;
    private JCheckBox girosCheckBox;
    private JCheckBox depósitosCheckBox;
    private JButton mostrarTransaccionesButton;
    private JTextField NOMBRECLIENTETextField;
    private JTextField RUTCLIENTETextField;
    private JTable tableTransacciones;
    private JTextField NroCuentaCtaCte;
    private JTextField SaldoCtaCte;
    private JTextField NroCtaAhorro;
    private JTextField SaldoCtaAhorro;
    private JTextField NroCtaVista;
    private JTextField SaldoCtaVista;
    private JScrollPane JScrollPaneListaTransacciones;

    public UI_DespliegueTransacciones() {
        mostrarTransaccionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
