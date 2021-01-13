package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.HashMap;

import Operations.DATA;

public class COMMON_UTILITIES {
	
	public static String getProcessName(){
		String processName = ManagementFactory.getRuntimeMXBean().getName();
		return processName;		
	}
	
	public static boolean isKeyLengthValid(String key){
		return key!="" && key.length()<=CONSTANTS.MAX_SIZE;
	}
	
	public static boolean isKeyExists(String key, String filePath){
		boolean keyExists = false;
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		ObjectInputStream objectInputStream = null;
		ObjectOutputStream objectOutputStream = null;
		HashMap<String, DATA> dataMap = new HashMap<String, DATA>();
		
		try{
			File file = new File(filePath);
			if(file.exists()){
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				dataMap = (HashMap<String, DATA>) objectInputStream.readObject();
				
				if(dataMap.containsKey(key))
					keyExists = true;
				
				fileInputStream.close();
				objectInputStream.close();
			}
			
			if(keyExists){
				DATA data = dataMap.get(key);
				long currentTime = new Date().getTime();
				if(data.getLifeSpan()>0 && 
					(currentTime-data.getCreationTime() >= data.getLifeSpan()*CONSTANTS.MILLI_SECONDS)){
					//object expired
					dataMap.remove(key);
					fileOutputStream = new FileOutputStream(file);
					objectOutputStream = new ObjectOutputStream(fileOutputStream);
					objectOutputStream.writeObject(dataMap);
					
					fileOutputStream.close();
					objectOutputStream.close();
					
					keyExists = false;
				}
			}			
		}
		catch(Exception exception){
			exception.printStackTrace();
		}
		finally {
			try{
				if(fileInputStream!=null){
					fileInputStream.close();
				}
			}
			catch(Exception exception){
				exception.printStackTrace();
			}
			
			try{
				if(objectInputStream!=null){
					objectInputStream.close();
				}
			}
			catch(Exception exception){
				exception.printStackTrace();
			}
			
		}
		return keyExists;
	}
	
	public static boolean writeData(DATA data, String filePath) {
		
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		ObjectInputStream objectInputStream = null;
		ObjectOutputStream objectOutputStream = null;
		HashMap<String, DATA> dataMap = null;
		
		try {
			File file = new File(filePath);
			if(file.exists()) {
				
				fileInputStream = new FileInputStream(file);
				objectInputStream = new ObjectInputStream(fileInputStream);
				dataMap = (HashMap<String, DATA>) objectInputStream.readObject();
				
				fileInputStream.close();
				objectInputStream.close();
												
			}
			else {
				dataMap = new HashMap<String, DATA>();				
			}
			
			dataMap.put(data.getKey(), data);
			
			fileOutputStream = new FileOutputStream(file);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(dataMap);
			
			fileOutputStream.close();
			objectOutputStream.close();
			
			return true;
			
		}
		catch(Exception exception){
			return false;
		}
		finally {
			try {
				if(fileInputStream!=null) {
					fileInputStream.close();
				}
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
			
			try {
				if(fileOutputStream!=null) {
					fileOutputStream.close();
				}
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
			
			try {
				if(objectInputStream!=null) {
					objectInputStream.close();
				}
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
			
			try {
				if(objectOutputStream!=null) {
					objectOutputStream.close();
				}
			}
			catch(Exception exception) {
				exception.printStackTrace();
			}
			
		}
		
	}
	
	public static DATA readData(String key, String filePath) throws IOException, ClassNotFoundException {
		
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		HashMap <String, DATA> dataMap;
		File file = new File(filePath);
		
		if(file.exists()) {
			
			fileInputStream = new FileInputStream(file);
			objectInputStream = new ObjectInputStream(fileInputStream);
			dataMap = (HashMap<String, DATA>)objectInputStream.readObject();
			
			fileInputStream.close();
			objectInputStream.close();
			
			return dataMap.get(key);
			
		}
		else {
			
			return null;
		}
		
	}
	
	public static boolean deleteData(String key, String filePath) throws IOException, ClassNotFoundException {
		
		FileInputStream fileInputStream = null;
		ObjectInputStream objectInputStream = null;
		HashMap <String, DATA> dataMap;
		
		File file = new File(filePath);
		
		if(file.exists()) {
			
			
			fileInputStream = new FileInputStream(file);
			objectInputStream = new ObjectInputStream(fileInputStream);
			dataMap = (HashMap<String, DATA>) objectInputStream.readObject();
			
			//System.out.println(dataMap);
			if(dataMap.containsKey(key))
			{
				dataMap.remove(key);
				System.out.println(key+" "+dataMap);
				fileInputStream.close();
				objectInputStream.close();
				return true;
			}
			fileInputStream.close();
			objectInputStream.close();
		}
			
		return false;			
		
	}

}









