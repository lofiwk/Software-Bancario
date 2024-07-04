import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UI_DespliegueTransacciones {
    public JPanel panelPrincipalDT;
    private JPanel panelListadoTransacciones;
    private JPanel panelInformaciones;
    private JPanel panelAtributosCliente;
    private JComboBox<String> comboBox1;
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
    private DefaultTableModel tableModel;

    private Map<String, Cliente> clientes;

    public UI_DespliegueTransacciones() {
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Tipo de Transacción");
        tableModel.addColumn("Nro Cuenta");
        tableModel.addColumn("Monto");
        tableModel.addColumn("Costo");
        tableModel.addColumn("Saldo");
        tableModel.addColumn("Fecha");

        tableTransacciones.setModel(tableModel);

        mostrarTransaccionesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTransacciones();
            }
        });

        cargarClientesDesdeArchivo();
        actualizarComboBoxClientes();
    }

    public JPanel getPanelPrincipalDT() {
        return panelPrincipalDT;
    }

    private void cargarClientesDesdeArchivo() {
        clientes = new HashMap<>();
        String fileName = "archivos/salida.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] partes = line.split("\\|\\|");
                if (partes.length >= 5) {
                    String tipoTransaccion = partes[0];
                    int monto = Integer.parseInt(partes[1]);
                    String fecha = partes[2];
                    String hora = partes[3];
                    String nroCuenta = partes[4];

                    // Crear una transacción válida
                    Cuenta cuenta = new Cuenta(nroCuenta, 0, "Desconocido"); // Asignamos un saldo temporal de 0
                    Transaccion transaccion = new TransaccionValida(fecha, hora, fecha, hora, monto, 0, cuenta);

                    // Agregar la transacción a la cuenta
                    cuenta.agregarTransaccion(transaccion);

                    // Agregar la cuenta al cliente (temporal)
                    Cliente cliente = new Cliente("Cliente " + nroCuenta, nroCuenta, "contact@example.com");
                    cliente.agregarCuenta(cuenta);

                    // Añadir el cliente al mapa
                    clientes.put(nroCuenta, cliente);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void actualizarComboBoxClientes() {
        comboBox1.removeAllItems();
        for (Cliente cliente : clientes.values()) {
            comboBox1.addItem(cliente.getNombre() + " - " + cliente.getDireccion() + " - " + cliente.getContacto());
            System.out.println("Cliente añadido al ComboBox: " + cliente.getNombre() + " - " + cliente.getDireccion() + " - " + cliente.getContacto());
        }
    }

    private void cargarTransacciones() {
        String clienteSeleccionado = (String) comboBox1.getSelectedItem();
        if (clienteSeleccionado == null || clienteSeleccionado.isEmpty()) {
            return;
        }
        System.out.println("Cliente seleccionado: " + clienteSeleccionado);

        String[] partesCliente = clienteSeleccionado.split(" - ");
        String nombreCliente = partesCliente[0];
        String direccionCliente = partesCliente[1];
        String contactoCliente = partesCliente[2];

        Cliente cliente = null;
        for (Cliente c : clientes.values()) {
            if (c.getNombre().equals(nombreCliente) && c.getDireccion().equals(direccionCliente) && c.getContacto().equals(contactoCliente)) {
                cliente = c;
                break;
            }
        }

        if (cliente == null) {
            System.out.println("Cliente no encontrado");
            return;
        }

        NOMBRECLIENTETextField.setText(cliente.getNombre());
        RUTCLIENTETextField.setText(cliente.getDireccion()); // Supongamos que `direccion` contiene el RUT

        tableModel.setRowCount(0); // Limpiar la tabla

        for (Cuenta cuenta : cliente.getCuentas()) {
            System.out.println("Cuenta encontrada: " + cuenta.getNumero() + " - Saldo: " + cuenta.getSaldo());
            boolean mostrarGiros = girosCheckBox.isSelected();
            boolean mostrarDepositos = depósitosCheckBox.isSelected();

            for (Transaccion transaccion : cuenta.getTransacciones()) {
                if (transaccion instanceof TransaccionValida) {
                    TransaccionValida transaccionValida = (TransaccionValida) transaccion;
                    if ((mostrarGiros && transaccionValida.getMonto() < 0) || (mostrarDepositos && transaccionValida.getMonto() > 0)) {
                        tableModel.addRow(new Object[]{
                                transaccionValida.getTipoTransaccion(),
                                transaccionValida.getCuenta().getNumero(),
                                transaccionValida.getMonto(),
                                transaccionValida.getCosto(),
                                transaccionValida.getCuenta().getSaldo(),
                                transaccionValida.getFecha()
                        });
                        System.out.println("Transacción añadida: " + transaccionValida.toString());
                    }
                }
            }
        }
    }


}
