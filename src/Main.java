import com.alura.conversor.Monedas;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public static int menu( String selection) {

    Scanner scan = new Scanner(System.in);
    int option;

    System.out.println("\n\nBienvenido al conversor de monedas");
    System.out.println("-------------------------------------");

    if (selection != null) {
        System.out.println("1 - Cambiar la moneda a consultar");
        System.out.println("2 - Comparar el valor de la moneda");
    } else {
        System.out.println("1 - Ingresar moneda a consultar");
        System.out.println( "\u001B[31m"+"2 - Todavía no se ha ingresado una moneda"+"\u001B[0m");
    }
    System.out.println("3 - Quit");
    option = scan.nextInt();
    return option;
}

public static void main() {

    Scanner scan = new Scanner(System.in);
    int userChoice;
    String selection = null;

    do {

            userChoice = menu(selection);

            if (userChoice == 2 && selection == null) {
                System.out.println("Eleccion no valida");
            }
            else {
        switch (userChoice) {
            case 1:
                System.out.println("Ingresa la moneda a consultar");
                selection = scan.nextLine();
                break;
            case 2:
                Conversor(selection);
                break;
            case 3:
                System.out.println("Adios");
                break;
            default:
                System.out.println("Opción no válida");
        }
    }
    } while (userChoice != 3);
}

private static void Conversor(String eleccion) {
    double monto;
    int seleccion , exit;
    String Moneda;
    String url = "https://v6.exchangerate-api.com/v6/edf34053b6bb467b3ff5c91f/latest/" + eleccion;

    String json = "";
    try (HttpClient client = HttpClient.newHttpClient()) {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        json = client.send(request, HttpResponse.BodyHandlers.ofString()).body();


    } catch (Exception e) {
        e.printStackTrace();
    }

    Gson gson = new Gson();
    Monedas monedas = gson.fromJson(json, Monedas.class);

    do {
        System.out.println(" Moneda a convertir: " + monedas.getMoneda());
        System.out.println("-------------------------\n");
        System.out.println("Escriba el monto a convertir");
        Scanner scan = new Scanner(System.in);
        monto = scan.nextDouble();
        System.out.println("Escriba la moneda a la que desea convertir");
        System.out.println(monedas.getConversion());
        System.out.println("-------------------------\n");
        seleccion = scan.nextInt();
        switch (seleccion) {
            case 1:
                Moneda = "USD";
                System.out.println("El valor de la moneda en " + Moneda + " es: " + monedas.converto(monto,seleccion));
                break;
            case 2:
                Moneda = "ARS";
                System.out.println("El valor de la moneda en " + Moneda + " es: " + monedas.converto(monto,seleccion));
                break;
            case 3:
                Moneda = "BRL";
                System.out.println("El valor de la moneda en " + Moneda + " es: " + monedas.converto(monto,seleccion));
                break;
            case 4:
                Moneda = "COP";
                System.out.println("El valor de la moneda en " + Moneda + " es: " + monedas.converto(monto,seleccion));
                break;
            case 5:
                Moneda = "CLP";
                System.out.println("El valor de la moneda en " + Moneda + " es: " + monedas.converto(monto,seleccion));
                break;
            default:
                System.out.println("Opción no válida");
        }
        System.out.println("Desea realizar otra conversión? (1 = si / 2 = no)");
        exit = scan.nextInt();
    } while (exit == 1);
}
