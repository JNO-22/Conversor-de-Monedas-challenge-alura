package com.alura.conversor;

import com.google.gson.annotations.SerializedName;

public class Monedas {

    @SerializedName("conversion_rates")
    private Conversion conversion;
    @SerializedName("base_code")
    private String moneda;

    public Conversion getConversion() {
        return conversion;
    }

    public String getMoneda() {
        return moneda;
    }
    public double converto (double monto, int seleccion) {
        return switch (seleccion) {
            case 1 -> conversion.getUSD() * monto;
            case 2 -> conversion.getARS() * monto;
            case 3 -> conversion.getBRL() * monto;
            case 4 -> conversion.getCOP() * monto;
            case 5 -> conversion.getCLP() * monto;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return "Monedas{" +
                "conversion=" + conversion +
                ", moneda='" + moneda + '\'' +
                '}';
    }
}

class Conversion {
    private double USD;
    private double ARS;
    private double BRL;
    private double COP;
    private double CLP;

    @Override
    public String toString() {
        return  "1- USD=" + USD +
                ",\n2- ARS=" + ARS +
                ",\n3- BRL=" + BRL +
                ",\n4- COP=" + COP +
                ",\n5- CLP=" + CLP ;
    }

    public double getUSD() {
        return USD;
    }

    public double getARS() {
        return ARS;
    }

    public double getBRL() {
        return BRL;
    }

    public double getCOP() {
        return COP;
    }

    public double getCLP() {
        return CLP;
    }
}