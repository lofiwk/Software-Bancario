class TransaccionValida extends Transaccion {
    private final int monto;
    private final Cuenta cuenta;

    public TransaccionValida(String fecha, String hora, String fechaRegistro, String horaRegistro, int monto, Cuenta cuenta) {
        super(fecha, hora, fechaRegistro, horaRegistro);
        this.monto = monto;
        this.cuenta = cuenta;
    }

    public int getMonto() {
        return monto;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    @Override
    public String toString() {
        return "TransaccionValida [fecha=" + fecha + ", hora=" + hora + ", fechaRegistro=" + fechaRegistro + ", horaRegistro=" + horaRegistro + ", monto=" + monto + ", cuenta=" + cuenta.getNumero() + "]";
    }
}
