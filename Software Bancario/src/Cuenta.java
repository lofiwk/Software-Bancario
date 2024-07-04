import java.util.ArrayList;
import java.util.List;

class Cuenta {
    private final String numero;
    private int saldo;
    private final String tipo;
    private final List<Transaccion> transacciones;

    public Cuenta(String numero, int saldo, String tipo) {
        this.numero = numero;
        this.saldo = Math.abs(saldo); // Los saldos iniciales negativos se convierten a positivos
        this.tipo = tipo;
        this.transacciones = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void agregarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
        actualizarSaldo(transaccion.getMonto());
    }

    public void actualizarSaldo(int monto) {
        saldo += monto;
    }

    @Override
    public String toString() {
        return "Cuenta [numero=" + numero + ", saldo=" + saldo + ", tipo=" + tipo + "]";
    }
}
