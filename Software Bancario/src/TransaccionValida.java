class TransaccionValida extends Transaccion {
    private final int monto;
    private final Cuenta cuenta;

    public TransaccionValida(String fecha, String hora, String fechaRegistro, String horaRegistro, int monto, Cuenta cuenta) {
        super(fecha, hora, fechaRegistro, horaRegistro);
        this.monto = monto;
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return String.format("TransaccionValida||%d||%s %s||%s %s||%s", monto, fecha, hora, fechaRegistro, horaRegistro, cuenta.getNumero());
    }
}
