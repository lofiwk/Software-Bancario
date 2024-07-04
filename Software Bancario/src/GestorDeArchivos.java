import java.io.*;
import java.util.List;

public class GestorDeArchivos {

    /**
     * Escribe una lista de transacciones en un archivo.
     *
     * @param fileName      El nombre del archivo donde se escribirán las transacciones.
     * @param transacciones La lista de transacciones a escribir en el archivo.
     */
    public static void escribirTransaccionesEnArchivo(String fileName, List<Transaccion> transacciones) {
        try (PrintStream fileOut = new PrintStream(new FileOutputStream(fileName))) {
            for (Transaccion transaccion : transacciones) {
                fileOut.println(transaccion);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ocurrió un error al crear el archivo de salida.");
            e.printStackTrace();
        }
    }

    /**
     * Lee y muestra el contenido de un archivo en la consola.
     *
     * @param fileName El nombre del archivo a leer.
     */
    public static void leerYMostrarContenidoDelArchivo(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("Contenido del archivo:");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo.");
            e.printStackTrace();
        }
    }
}
