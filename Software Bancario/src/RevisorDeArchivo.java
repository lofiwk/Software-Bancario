import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RevisorDeArchivo {

    /**
     * Revisa y corrige las transacciones de un archivo de entrada y las escribe en un archivo de salida.
     *
     * @param inputFile  Archivo de entrada con las transacciones originales.
     * @param outputFile Archivo de salida donde se escribirán las transacciones corregidas y ordenadas.
     */
    public static void revisarYCorregirArchivo(File inputFile, File outputFile) {
        List<String> transacciones = leerArchivo(inputFile);
        List<String> transaccionesCorregidas = corregirTransacciones(transacciones);
        List<String> transaccionesOrdenadas = ordenarTransacciones(transaccionesCorregidas);
        escribirArchivo(outputFile, transaccionesOrdenadas);
    }

    /**
     * Lee las transacciones de un archivo de entrada.
     *
     * @param inputFile Archivo de entrada a leer.
     * @return Lista de transacciones leídas del archivo.
     */
    private static List<String> leerArchivo(File inputFile) {
        List<String> transacciones = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transacciones.add(line);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo de entrada.");
            e.printStackTrace();
        }
        return transacciones;
    }

    /**
     * Corrige las transacciones leídas, ajustando posibles errores.
     *
     * @param transacciones Lista de transacciones a corregir.
     * @return Lista de transacciones corregidas.
     */
    private static List<String> corregirTransacciones(List<String> transacciones) {
        List<String> transaccionesCorregidas = new ArrayList<>();
        for (String transaccion : transacciones) {
            transaccionesCorregidas.add(corregirTransaccion(transaccion));
        }
        return transaccionesCorregidas;
    }

    /**
     * Corrige una transacción específica.
     *
     * @param transaccion La transacción a corregir.
     * @return La transacción corregida.
     */
    private static String corregirTransaccion(String transaccion) {
        String[] partes = transaccion.split("\\|\\|");

        if (partes.length < 5) {
            return "TransaccionInvalida||Datos faltantes||" + transaccion;
        }

        try {
            int monto = Integer.parseInt(partes[1]);
            if (monto < 0) {
                partes[1] = String.valueOf(Math.abs(monto));
            }
        } catch (NumberFormatException e) {
            return "TransaccionInvalida||Monto inválido||" + transaccion;
        }

        return String.join("||", partes);
    }

    /**
     * Ordena las transacciones por fecha y hora de registro.
     *
     * @param transacciones Lista de transacciones a ordenar.
     * @return Lista de transacciones ordenadas.
     */
    private static List<String> ordenarTransacciones(List<String> transacciones) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        transacciones.sort((t1, t2) -> {
            String[] partes1 = t1.split("\\|\\|");
            String[] partes2 = t2.split("\\|\\|");

            LocalDateTime fechaHora1 = LocalDateTime.parse(partes1[3].trim(), formatter);
            LocalDateTime fechaHora2 = LocalDateTime.parse(partes2[3].trim(), formatter);
            return fechaHora1.compareTo(fechaHora2);
        });

        return transacciones;
    }

    /**
     * Escribe las transacciones en un archivo de salida.
     *
     * @param outputFile Archivo de salida donde se escribirán las transacciones.
     * @param transacciones Lista de transacciones a escribir en el archivo.
     */
    private static void escribirArchivo(File outputFile, List<String> transacciones) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            for (String transaccion : transacciones) {
                writer.println(transaccion);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir el archivo de salida.");
            e.printStackTrace();
        }
    }
}
