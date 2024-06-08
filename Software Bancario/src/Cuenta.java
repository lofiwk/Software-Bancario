class Cuenta {
    private final String numero;
    private int saldo;
    private final String tipo;

    public Cuenta(String numero, int saldo, String tipo) {
        this.numero = numero;
        this.saldo = Math.abs(saldo); // Los saldos iniciales negativos se convierten a positivos
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public int getSaldo() {
        return saldo;
    }

    public void actualizarSaldo(int monto) {
        saldo += monto;
    }

    @Override
    public String toString() {
        return "Cuenta [numero=" + numero + ", saldo=" + saldo + ", tipo=" + tipo + "]";
    }
}