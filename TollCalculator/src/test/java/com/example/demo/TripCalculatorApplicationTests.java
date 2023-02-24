package com.example.demo;
import static org.junit.Assert.*;

import java.io.FileReader;

import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TripCalculatorApplicationTests {
	
	@Test
    public void testCalculateToll() {
        String startPoint = "401";
        String endPoint = "404";
        double expectedToll = 0.75;
        double actualToll = calculateToll(startPoint, endPoint);
        assertEquals(expectedToll, actualToll, 0.01);
        System.out.println("Expected toll: $" + expectedToll + ", Actual toll: $" + actualToll);
    }

    public double calculateToll(String startPoint, String endPoint) {
        double tollAmount = 0;
        double startKm = 0;
        JSONParser parser = new JSONParser();
        try {
            JSONArray interchanges = (JSONArray) parser.parse(new FileReader("interchanges.json"));
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
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return tollAmount;
    }
}