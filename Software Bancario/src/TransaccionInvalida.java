class TransaccionInvalida extends Transaccion {
    private final String error;

    public TransaccionInvalida(String fecha, String hora, String fechaRegistro, String horaRegistro, String error) {
        super(fecha, hora, fechaRegistro, horaRegistro);
        this.error = error;
    }

    @Override
    public int getMonto() {
        return 0;
    }

    @Override
    public int getCosto() {
        return 0;
    }

    @Override
    public Cuenta getCuenta() {
        return null;
    }

    @Override
    public String getTipoTransaccion() {
        return "Invalida";
    }

    @Override
    public String getFecha() {
        return fechaRegistro;
    }

    @Override
    public String toString() {
        return String.format("TransaccionInvalida||%s||%s %s||%s %s", error, fecha, hora, fechaRegistro, horaRegistro);
    }
}
