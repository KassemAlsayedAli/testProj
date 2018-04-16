package com.kass.dao;
/**
 * Created by kass on 2017-02-26.
 */

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;


public class JsonProcess {
    private static JsonParser parser = new JsonParser();
    private static String fileName = "/Users/kass/gdrive/kass/projects/testProj/src/main/resources/address.txt";
    private static JsonElement jsonElement;

    static {
        try {
            //jsonElement = parser.parse(new FileReader(fileName));

            jsonElement = parser.parse("{\"address\":{\"streetAddress\":\"21 Street\",\"city\":\"Ottawa\"},\"phoneNumber\":[{\"type\":\"home\",\"number\":\"613 123-4567\"}]}");

            JsonObject jo = jsonElement.getAsJsonObject();

            System.out.println(jo.get("phoneNumber"));

            System.out.println(jsonElement);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
    }

    public static JsonElement getIppProofingRequirementsJson() {
        return jsonElement;
    }
}