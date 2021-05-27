package com.craftmanship;

public class Calculator {

    Database theDb;

    public Calculator() {
        this.theDb = Database.connect("jdbc:mysql://localhost:3306/myShop", "store", "123456");
    }

    public double calc(double p, String type) {
        double result = p;

        // apply food discount
        if (type == "Food") {
            result *= 0.9;
        }
        if (p > 1000) {
            result *= 0.2;
        }

        var v = theDb.getVat(type);
        result += result * v;
        double roundOff = Math.round(result * 100) / 100;
        return roundOff;
    }

}
