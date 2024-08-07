import javax.swing.*;
import java.io.File;
import java.util.Comparator;
import java.util.List;

public class SistemaBancario {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Por favor, siga los pasos de los comentarios");
            // Si está utilizando IntelliJ para ejecutar este programa, es necesario seguir unos pasos adicionales para proporcionar los argumentos necesarios:
            // 1. Haga clic en 'Run' en el menú superior.
            // 2. Seleccione 'Edit Configurations'.
            // 3. Seleccione 'Application' y luego la clase 'SistemaBancario'.
            // 4. En el campo 'Program Arguments', escriba las rutas de los archivos de entrada y salida:
            //    ejemplo: archivos/entrada.txt archivos/salida.txt
            // Este uso de argumentos de línea de comandos es una solución temporal (placeholder) hasta que se implemente una interfaz gráfica de usuario (GUI) en una entrega futura.
            // Con la GUI, los usuarios podrán seleccionar archivos y ejecutar el programa de manera más interactiva.
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        Banco banco = new Banco();
        banco.generarTransacciones();

        List<Transaccion> transacciones = banco.getTransacciones();
        transacciones.sort(Comparator.comparing(Transaccion::getFechaRegistro).thenComparing(Transaccion::getHoraRegistro));

        GestorDeArchivos.escribirTransaccionesEnArchivo(inputFileName, transacciones);
        GestorDeArchivos.leerYMostrarContenidoDelArchivo(inputFileName);

        File outputFile = new File(outputFileName);
        RevisorDeArchivo.revisarYCorregirArchivo(new File(inputFileName), outputFile);

        // Abrir la interfaz gráfica de login
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Login");
                UI_login uiLogin = new UI_login();
                frame.setContentPane(uiLogin.getPanelPrincipalLogin());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

                // Si el login es exitoso, abrir la interfaz de despliegue de transacciones
                uiLogin.setLoginListener(new UI_login.LoginListener() {
                    @Override
                    public void onLoginSuccess() {
                        frame.dispose(); // Cerrar la ventana de login
                        JFrame transaccionesFrame = new JFrame("Despliegue de Transacciones");
                        UI_DespliegueTransacciones uiDespliegueTransacciones = new UI_DespliegueTransacciones();
                        transaccionesFrame.setContentPane(uiDespliegueTransacciones.getPanelPrincipalDT());
                        transaccionesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        transaccionesFrame.pack();
                        transaccionesFrame.setVisible(true);
                    }
                });
            }
        });
    }
}
