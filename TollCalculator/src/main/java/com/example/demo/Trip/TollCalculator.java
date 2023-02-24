package com.example.demo.Trip;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TollCalculator {
    
	public static void main(String[] args) {
        String startPoint = "401";
        String endPoint = "404";
        double tollAmount = 0;
        double startKm = 0;
        JSONParser parser = new JSONParser();
        try {
            JSONArray interchanges = (JSONArray) parser.parse(new FileReader("D:\\PracticalSessions\\TripCalculator"));

            for (Object obj : interchanges) {
                JSONObject interchange = (JSONObject) obj;
                String interchangeId = interchange.get("id").toString();
                if (interchangeId.equals(startPoint)) {
                    startKm = Double.parseDouble(interchange.get("km").toString());
                }
                if (interchangeId.equals(endPoint)) {
                    double endKm = Double.parseDouble(interchange.get("km").toString());
                    tollAmount = (endKm - startKm) * 0.25;
                    break;
                }
            }
            System.out.println("Toll amount: $" + tollAmount);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}