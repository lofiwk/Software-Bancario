import java.util.ArrayList;
import java.util.List;

class Cliente {
    private final String nombre;
    private final String direccion;
    private final String contacto;
    private final List<Cuenta> cuentas;

    public Cliente(String nombre, String direccion, String contacto) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
        this.cuentas = new ArrayList<>();
    }

    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    @Override
    public String toString() {
        return "Cliente [nombre=" + nombre + ", direccion=" + direccion + ", contacto=" + contacto + "]";
    }
}
