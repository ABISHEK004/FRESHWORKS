package Main;

import java.util.Date;

import org.json.simple.JSONObject;

import Operations.DATA;
import Utilities.COMMON_UTILITIES;
import Utilities.CONSTANTS;

public class DATASTORE {
	
	String dataStoreLoc = "";
	String dataStoreName = "";
	
	public DATASTORE() {
		
		dataStoreLoc = CONSTANTS.defaultDataStoreLoc;
		dataStoreName = "DataStore - " + COMMON_UTILITIES.getProcessName();
	}
	
	public DATASTORE(String filePath) {
		
		dataStoreLoc = filePath;
		dataStoreName = "DataStore - " + COMMON_UTILITIES.getProcessName();
	}
	
	public synchronized String create(String key, JSONObject jobj) {
		
		return create(key, jobj, -1);
	}
	
	public synchronized String create(String key, JSONObject jobj, int lifeSpan) {
		
		String filePath = dataStoreLoc + "/" + dataStoreName;
		
		if(COMMON_UTILITIES.isKeyLengthValid(key)==false)
			return CONSTANTS.FAILURE_KEY_LENGTH_EXCEEDED;
		
		if(COMMON_UTILITIES.isKeyExists(key, filePath))
			return CONSTANTS.FAILURE_KEY_ALREADY_AVAILABLE;
		
		DATA data = new DATA();
		
		if(lifeSpan>0)
			data.setLifeSpan(lifeSpan);
		else
			data.setLifeSpan(-1);
		
		data.setKey(key);
		data.setObject(jobj);
		data.setCreationTime(new Date().getTime());
		
		return COMMON_UTILITIES.writeData(data, filePath) ? CONSTANTS.SUCCESS_CREATE : CONSTANTS.FAILURE_CREATE;
					
	}
	
	public synchronized Object read(String key) {
		
		String filePath = dataStoreLoc + "/" + dataStoreName;
		
		if(COMMON_UTILITIES.isKeyLengthValid(key)==false)
			return CONSTANTS.FAILURE_KEY_LENGTH_EXCEEDED;
		
		if(COMMON_UTILITIES.isKeyExists(key, filePath)==false)
			return CONSTANTS.FAILURE_KEY_ABSENT;
		
		DATA data = new DATA();
		try {
			data = COMMON_UTILITIES.readData(key, filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (data!=null) ? data.getObject() : CONSTANTS.FAILURE_READ;
	}
	
	public synchronized String delete(String key) {
		
		String filePath = dataStoreLoc + "/" + dataStoreName;
		
		if(COMMON_UTILITIES.isKeyLengthValid(key)==false)
			return CONSTANTS.FAILURE_KEY_LENGTH_EXCEEDED;
		
		if(COMMON_UTILITIES.isKeyExists(key, filePath)==false)
			return CONSTANTS.FAILURE_KEY_ABSENT;
		
		try {
			return COMMON_UTILITIES.deleteData(key, filePath) ? CONSTANTS.SUCCESS_DELETE : CONSTANTS.FAILURE_DELETE;
		} catch (Exception e) {
			e.printStackTrace();
			return CONSTANTS.FAILURE_DELETE;
		}
		
	}
}











