package com.notes.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadJson {
	private static String dataFilePath = new File(ReadProperties.getProperty("jsonFilePath")).getAbsolutePath()+File.separator;
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> getJsonData(String jsonFileName, String jsonKey) {
        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader(dataFilePath + jsonFileName)) {
            // Parse the JSON file into a JSONObject
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            // Extract the data associated with the provided key
            if (jsonObject.containsKey(jsonKey)) {
                return (Map<String,String>) jsonObject.get(jsonKey);
            } else {
                throw new RuntimeException("Key not found in JSON file: " + jsonKey);
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Failed to read or parse JSON file: " + jsonFileName, e);
        }
}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


