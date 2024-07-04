class TransaccionValida extends Transaccion {
    private final int monto;
    private final int costo;
    private final Cuenta cuenta;

    public TransaccionValida(String fecha, String hora, String fechaRegistro, String horaRegistro, int monto, int costo, Cuenta cuenta) {
        super(fecha, hora, fechaRegistro, horaRegistro);
        this.monto = monto;
        this.costo = costo;
        this.cuenta = cuenta;
    }

    @Override
    public int getMonto() {
        return monto;
    }

    @Override
    public int getCosto() {
        return costo;
    }

    @Override
    public Cuenta getCuenta() {
        return cuenta;
    }

    @Override
    public String getTipoTransaccion() {
        return monto < 0 ? "Giro" : "Deposito";
    }

    @Override
    public String getFecha() {
        return fechaRegistro;
    }

    @Override
    public String toString() {
        return String.format("TransaccionValida||%d||%s %s||%s %s||%s", monto, fecha, hora, fechaRegistro, horaRegistro, cuenta.getNumero());
    }
}
