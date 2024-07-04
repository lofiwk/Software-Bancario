import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Banco {
    private final List<Cliente> clientes;
    private final List<Transaccion> transacciones;

    public Banco() {
        this.clientes = new ArrayList<>();
        this.transacciones = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Genera transacciones simuladas para los clientes del banco.
     * Se crean dos clientes con sus respectivas cuentas y transacciones.
     */
    public void generarTransacciones() {
        Random random = new Random();

        Cliente cliente1 = new Cliente("Jorge Soto", "1234 Agua Santa", "contact@example.com");
        Cliente cliente2 = new Cliente("Juan Perez", "5678 Ferrari Street", "contact2@example.com");

        Cuenta cuenta1 = new Cuenta("1234567890", 1000, "Vista");
        Cuenta cuenta2 = new Cuenta("0987654321", 5000, "Corriente");

        cliente1.agregarCuenta(cuenta1);
        cliente2.agregarCuenta(cuenta2);

        this.agregarCliente(cliente1);
        this.agregarCliente(cliente2);

        transacciones.add(ValidadorDeTransacciones.validarTransaccion("2023-06-01", "10:00:00", "2023-06-01", "10:05:00", 500, cuenta1));
        transacciones.add(ValidadorDeTransacciones.validarTransaccion("2023-06-02", "15:30:00", "2023-06-02", "15:35:00", -200, cuenta1));
        transacciones.add(ValidadorDeTransacciones.validarTransaccion("2023-06-03", "09:15:00", "2023-06-03", "09:20:00", 1000, cuenta2));

        for (int i = 0; i < 2; i++) {
            int monto = random.nextInt(2000) - 1000;
            String fecha = "2023-06-" + String.format("%02d", (4 + i));
            String hora = "12:00:00";
            String horaRegistro = "12:05:00";

            Transaccion transaccion = ValidadorDeTransacciones.validarTransaccion(fecha, hora, fecha, horaRegistro, monto, random.nextBoolean() ? cuenta1 : cuenta2);
            transacciones.add(transaccion);
        }
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }
}
