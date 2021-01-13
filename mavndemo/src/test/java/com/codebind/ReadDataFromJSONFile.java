/**
 * 
 */
package com.codebind;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Admin
 *
 */
public class ReadDataFromJSONFile {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		
		FileReader reader = new FileReader(".\\JSONFolder\\JSONFile.json");
		
		Object obj = parser.parse(reader);
		
		JSONObject jsonObj = (JSONObject)obj;
		
		String fname = (String) jsonObj.get("firstname");
		String lname = (String) jsonObj.get("lastname");
		
		System.out.println(fname+"~"+lname);
		
		JSONArray array = (JSONArray)jsonObj.get("address");
		
		for(int i=0;i<array.size();i++)
		{
			JSONObject add = (JSONObject)array.get(i);
			System.out.println(add.get("street")+"-"+add.get("city")+"-"+add.get("state"));
		}

	}

}
