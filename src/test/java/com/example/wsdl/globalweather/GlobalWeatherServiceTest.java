package com.example.wsdl.globalweather;

import net.webservicex.GlobalWeather;
import net.webservicex.GlobalWeatherSoap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.StringReader;
import java.util.ArrayList;

import static org.testng.Assert.*;

public class GlobalWeatherServiceTest {

    private static GlobalWeatherSoap endpoint;

    @BeforeClass
    public void setUp() {
        endpoint = new GlobalWeather().getGlobalWeatherSoap();
    }

    @DataProvider
    public Object[][] citiesByCountries() {
        return new Object[][]{
                {"Ukraine", "Kyiv"},
                {"Ukraine", "Simferopol"},
                {"France", "Le Mans"},
                {"Austria", "Zeltweg"},
                {"Denmark", "Koebenhavn"},
                {"Germany", "Dresden-Klotzsche"},
                {"Belgium", "Spa"},
                {"Somalia", "Mogadiscio"},
        };
    }

    @Test(dataProvider = "citiesByCountries")
    public void testGetCitiesByCountry(String country, String city) {
        String response = endpoint.getCitiesByCountry(country);

        ArrayList<String> countries = getAllUniqueNodeValuesFromXML(response, "Country");
        assertTrue(countries.contains(country));

        ArrayList<String> cities = getAllUniqueNodeValuesFromXML(response, "City");
        assertTrue(cities.toString().contains(city));
    }

    @Test(dataProvider = "citiesByCountries")
    public void testGetWeather(String country, String city) {
        String response = endpoint.getWeather(city, country);
        assertFalse(response.contains("Data Not Found"));

        ArrayList<String> status = getAllUniqueNodeValuesFromXML(response, "Status");
        assertEquals(status.get(0), "Success");

        ArrayList<String> location = getAllUniqueNodeValuesFromXML(response, "Location");
        assertTrue(location.get(0).contains(city));

        ArrayList<String> temperature = getAllUniqueNodeValuesFromXML(response, "Temperature");
        assertNotEquals(temperature.size(), 0);

    }

    private ArrayList<String> getAllUniqueNodeValuesFromXML(String response, String tagName) {
        ArrayList<String> uniqueNodeValues = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(response)));
            for (int i=0; i < doc.getElementsByTagName(tagName).getLength(); i++) {
                String nodeValue = doc.getElementsByTagName(tagName).item(i).getFirstChild().getNodeValue();
                if (!uniqueNodeValues.contains(nodeValue)) {
                    uniqueNodeValues.add(nodeValue);
                }
            }
            return uniqueNodeValues;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uniqueNodeValues;
    }
}

