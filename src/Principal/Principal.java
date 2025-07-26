package Principal;

import Datos.DatosMonedas;
import Modelos.Moneda;
import Modelos.MonedaExchange;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;
import java.util.Scanner;


public class Principal {
    public Principal() {


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create();

        while (true) {
            System.out.println("***********************************************************************");
            System.out.println("Sea bienvenido/a al conversor de monedas");

            DatosMonedas datosMonedas = new DatosMonedas();
            datosMonedas.MuestraPaises();
            System.out.println("0. Salir");

            int opcMoneda = obtenerOpcionUsuario(scanner, datosMonedas.nombresPaises.length);
            if (opcMoneda == 0) {
                System.out.println("Gracias por usar el conversor de monedas. ¡Hasta luego!");
                return;
            }
            

            String m1 = datosMonedas.RegresaMoneda(opcMoneda);

            System.out.println("Introduzca el número de la moneda a la que desea convertir: ");
            datosMonedas.MuestraPaises();
            int opcMoneda2 = obtenerOpcionUsuario(scanner, datosMonedas.nombresPaises.length);
            String m2 = datosMonedas.RegresaMoneda(opcMoneda2);

            System.out.println("Ingrese el número de unidades que desea convertir: ");
            int valorConvertir = obtenerValorConvertir(scanner);

            String direccion = "https://v6.exchangerate-api.com/v6/b73e1f385e818dc3a840001a/latest/" + m1;

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(direccion)).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();

                MonedaExchange monedaExchange = gson.fromJson(json, MonedaExchange.class);
                Moneda moneda = new Moneda(monedaExchange);
                System.out.println("El valor de " + valorConvertir + " [" + m1 + "] a " + m2 + " es: " + moneda.ObtenerMoneda(valorConvertir, m2));
                System.out.println("Presiona ENTER para continuar");
                scanner.nextLine();

            } catch (Exception e) {
                System.out.println("Error genérico: " + e.getMessage());
            }
        }
    }
    

    private static int obtenerOpcionUsuario(Scanner scanner, int maxOpciones) {
        int opcion = -1;
        while (true) {
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion < 0 || opcion > maxOpciones) {
                    System.out.println("Número no válido. Por favor, ingresa un número del 0 al " + maxOpciones);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
            }
        }
        return opcion;
    }
    

    private static int obtenerValorConvertir(Scanner scanner) {
        int valor = -1;
        while (true) {
            try {
                valor = Integer.parseInt(scanner.nextLine());
                if (valor < 0) {
                    System.out.println("Por favor, ingresa un valor positivo.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, ingresa un número.");
            }
        }
        return valor;
    }
}





















