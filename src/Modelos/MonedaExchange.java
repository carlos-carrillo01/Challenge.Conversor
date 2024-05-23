package Modelos;

import com.google.gson.JsonObject;

public record MonedaExchange(String baseCode, JsonObject conversionRates) {
    public  MonedaExchange(String baseCode, JsonObject conversionRates){
        this.baseCode = baseCode;
        this.conversionRates = conversionRates;
    }

    public  String baseCode(){
        return  this.baseCode;
    }

    public  JsonObject conversionRates(){
        return  this.conversionRates;
    }
}
