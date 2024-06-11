import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RevisorDeArchivo {
    public static void revisarYCorregirArchivo(File inputFile, File outputFile) {
        List<String> transacciones = leerArchivo(inputFile);
        List<String> transaccionesCorregidas = corregirTransacciones(transacciones);
        List<String> transaccionesOrdenadas = ordenarTransacciones(transaccionesCorregidas);
        escribirArchivo(outputFile, transaccionesOrdenadas);
    }

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

    private static List<String> corregirTransacciones(List<String> transacciones) {
        List<String> transaccionesCorregidas = new ArrayList<>();
        for (String transaccion : transacciones) {
            transaccionesCorregidas.add(corregirTransaccion(transaccion));
        }
        return transaccionesCorregidas;
    }

    private static String corregirTransaccion(String transaccion) {
        String[] partes = transaccion.split("\\|\\|");

        if (partes.length < 5) {
            return transaccion;
        }

        if (Integer.parseInt(partes[1]) < 0) {
            partes[1] = String.valueOf(Math.abs(Integer.parseInt(partes[1])));
        }

        return String.join("||", partes);
    }

    private static List<String> ordenarTransacciones(List<String> transacciones) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        transacciones.sort((t1, t2) -> {
            String[] partes1 = t1.split("\\|\\|");
            String[] partes2 = t2.split("\\|\\|");

            LocalDateTime fechaHora1 = LocalDateTime.parse(partes1[2].trim(), formatter);
            LocalDateTime fechaHora2 = LocalDateTime.parse(partes2[2].trim(), formatter);
            return fechaHora1.compareTo(fechaHora2);
        });

        return transacciones;
    }

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
