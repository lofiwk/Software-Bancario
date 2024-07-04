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

    // Método para agregar una cuenta a la lista de cuentas del cliente
    public void agregarCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
    }

    // Método para obtener la lista de cuentas del cliente
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    // Método sobrescrito para devolver una representación en cadena del cliente
    @Override
    public String toString() {
        return "Cliente [nombre=" + nombre + ", direccion=" + direccion + ", contacto=" + contacto + "]";
    }
}