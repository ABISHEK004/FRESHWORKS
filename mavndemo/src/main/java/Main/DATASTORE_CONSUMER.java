package Main;

import org.json.simple.JSONObject;

import Operations.DATA;

public class DATASTORE_CONSUMER {

	public static void main(String[] args) {
		
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("First~Name ", "Abishek");
		jsonObj.put("Last~Name ", "Srinivasan");
		jsonObj.put("Address ", "Karur");
		
		System.out.println("--------------CREATE---------------");
		DATASTORE dataStore = new DATASTORE("C:\\Users\\Admin\\workspace\\mavndemo\\JSONFolder");
		System.out.println(dataStore.create("1", jsonObj, 2));
		System.out.println(dataStore.create("1", jsonObj, 2));
		System.out.println(dataStore.create("2", jsonObj, 2));
		System.out.println(dataStore.create("0123456789012345678901234567890123456789", jsonObj));
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("--------------AFTER WAIT---------------");
		jsonObj.put("Age ", 21);
		System.out.println(dataStore.create("1", jsonObj, 1));
		System.out.println(dataStore.create("2", jsonObj, 1));
		
		System.out.println("--------------READ---------------");
		System.out.println(dataStore.read("1"));
		System.out.println(dataStore.read("2"));
		System.out.println(dataStore.read("3"));
		
		
		System.out.println("--------------DELETE---------------");
		//jsonObj.re
		System.out.println(dataStore.delete("1"));
		System.out.println(dataStore.delete("1"));
		System.out.println(dataStore.delete("2"));
		System.out.println(dataStore.delete("0"));
		
		
		
	}

}
