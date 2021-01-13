package Operations;

import java.io.Serializable;

import org.json.simple.JSONObject;

public class DATA implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String key;
	int lifeSpan;
	JSONObject jsonObj;
	long creationTime;
	
	public String getKey(){
		return key;
	}
	
	public void setKey(String key){
		this.key = key;
	}
	
	public int getLifeSpan(){
		return lifeSpan;
	}
	
	public void setLifeSpan(int lifeSpan){
		this.lifeSpan = lifeSpan;
	}
	
	public JSONObject getObject(){
		return jsonObj;
	}
	
	public void setObject(JSONObject obj){
		jsonObj = obj;
	}
	
	public long getCreationTime(){
		return creationTime;
	}
	
	public void setCreationTime(long time){
		creationTime = time;
	}

}
