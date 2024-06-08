import java.util.Comparator;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class SistemaBancario {
    public static void main(String[] args) {
        List<Transaccion> transacciones = new ArrayList<>();
        Random random = new Random();

        Cliente cliente1 = new Cliente("Jorge Soto", "1234 Main St", "contact@example.com");
        Cliente cliente2 = new Cliente("Juan Perez", "5678 Main St", "contact2@example.com");

        Cuenta cuenta1 = new Cuenta("1234567890", 1000, "Vista");
        Cuenta cuenta2 = new Cuenta("0987654321", 5000, "Corriente");

        cliente1.agregarCuenta(cuenta1);
        cliente2.agregarCuenta(cuenta2);

        // Validar y agregar transacciones a la lista
        transacciones.add(ValidadorDeTransacciones.validarTransaccion("2023-06-01", "10:00:00", "2023-06-01", "10:05:00", 500, cuenta1));
        transacciones.add(ValidadorDeTransacciones.validarTransaccion("2023-06-02", "15:30:00", "2023-06-02", "15:35:00", -200, cuenta1));
        transacciones.add(ValidadorDeTransacciones.validarTransaccion("2023-06-03", "09:15:00", "2023-06-03", "09:20:00", 1000, cuenta2));

        // Agregar errores aleatorios
        for (int i = 0; i < 2; i++) {
            int monto = random.nextInt(2000) - 1000; // Genera montos aleatorios entre -1000 y 1000
            String fecha = "2023-06-" + (4 + i);
            String hora = "12:00:00";
            String horaRegistro = "12:05:00";

            Transaccion transaccion = ValidadorDeTransacciones.validarTransaccion(fecha, hora, fecha, horaRegistro, monto, random.nextBoolean() ? cuenta1 : cuenta2);
            transacciones.add(transaccion);
        }

        // Reordenar las transacciones por fecha de registro y hora de registro
        transacciones.sort(Comparator.comparing(Transaccion::getFechaRegistro).thenComparing(Transaccion::getHoraRegistro));

        // Procesar las transacciones
        for (Transaccion transaccion : transacciones) {
            if (transaccion instanceof TransaccionValida transaccionValida) {
                Cuenta cuenta = transaccionValida.getCuenta();
                int monto = transaccionValida.getMonto();
                cuenta.actualizarSaldo(monto);
                System.out.println("Transacción válida procesada: " + transaccionValida);
            } else if (transaccion instanceof TransaccionInvalida transaccionInvalida) {
                System.out.println("Transacción inválida: " + transaccionInvalida);
            }
        }

        // Mostrar los saldos actualizados de las cuentas
        System.out.println("Saldo de la cuenta " + cuenta1.getNumero() + ": " + cuenta1.getSaldo());
        System.out.println("Saldo de la cuenta " + cuenta2.getNumero() + ": " + cuenta2.getSaldo());
    }
}
