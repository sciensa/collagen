package com.sciensa.collagen.process;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author Douglas Pimentel Rodrigues
 * Class that maps the input for processing
 */
public class Input {

	/**
	 * 
	 * @param payload
	 * @return Map<String, Object> transformed by input
	 */
	public Map<String, Object> process(String payload) {
		Map<String, Object> map = this.processJSON(payload);
		return map;
	}

	/**
	 * 
	 * @param payload
	 * @return Map<String, Object> transformed by json
	 */
	private Map<String, Object> processJSON(String payload) {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser parser = new JsonParser();
		JsonObject parentObject = null;
		Map<String, Object> map = null;
		
		try{
			parentObject = parser.parse(payload).getAsJsonObject();
		}catch(Exception ex){
			return map = new HashMap<String, Object>();
		}

		map = gson.fromJson(parentObject, new TypeToken<Map<String, Object>>() {
		}.getType());

		return map;
	}

}
