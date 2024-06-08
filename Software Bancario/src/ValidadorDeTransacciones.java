class ValidadorDeTransacciones {
    public static Transaccion validarTransaccion(String fecha, String hora, String fechaRegistro, String horaRegistro, int monto, Cuenta cuenta) {
        if (monto == 0) {
            return new TransaccionInvalida(fecha, hora, fechaRegistro, horaRegistro, "Monto no puede ser cero");
        }
        if (monto < 0 && cuenta.getSaldo() + monto < 0) {
            return new TransaccionInvalida(fecha, hora, fechaRegistro, horaRegistro, "Saldo insuficiente");
        }
        if (cuenta == null || fecha == null || hora == null || fechaRegistro == null || horaRegistro == null) {
            return new TransaccionInvalida(fecha, hora, fechaRegistro, horaRegistro, "Datos faltantes");
        }
        return new TransaccionValida(fecha, hora, fechaRegistro, horaRegistro, monto, cuenta);
    }
}