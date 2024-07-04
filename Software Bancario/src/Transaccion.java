abstract class Transaccion {
    protected String fecha;
    protected String hora;
    protected String fechaRegistro;
    protected String horaRegistro;

    public Transaccion(String fecha, String hora, String fechaRegistro, String horaRegistro) {
        this.fecha = fecha;
        this.hora = hora;
        this.fechaRegistro = fechaRegistro;
        this.horaRegistro = horaRegistro;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public String getHoraRegistro() {
        return horaRegistro;
    }

    public abstract int getMonto();

    public abstract int getCosto();

    public abstract String getTipoTransaccion();

    public abstract String getFecha();

    public abstract Cuenta getCuenta();

    @Override
    public abstract String toString();
}
