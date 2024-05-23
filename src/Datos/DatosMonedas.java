package Datos;

public class DatosMonedas {
    public String[] nombresPaises = new String[]{
            "Peso argentino (ARS)",
            "Boliviano boliviano (BOB)",
            "Real brasileño (BRL)",
            "Peso chileno (CLP)",
            "Peso colombiano (COP)",
            "Dólar estadounidense (USD)",
            "Peso mexicano (MXN)"
    };
    String[] codigosMonedas = new String[]{"ARS", "BOB", "BRL", "CLP", "COP", "USD", "MXN"};

    public DatosMonedas() {
    }

    public void MuestraPaises() {
        System.out.println("Selecciona un país: ");

        for (int i = 0; i < this.nombresPaises.length; i++) {
            System.out.println((i + 1) + ". " + this.nombresPaises[i]);
        }
    }

    public String RegresaMoneda(int opc) {
        String aux;
        switch (opc) {
            case 1:
                aux = codigosMonedas[0];
                break;
            case 2:
                aux = codigosMonedas[1];
                break;
            case 3:
                aux = codigosMonedas[2];
                break;
            case 4:
                aux = codigosMonedas[3];
                break;
            case 5:
                aux = codigosMonedas[4];
                break;
            case 6:
                aux = codigosMonedas[5];
                break;
            case 7:
                aux = codigosMonedas[6];
                break;
            default:
                aux = "Opción inválida";
                break;
        }
        return aux;
    }
}


