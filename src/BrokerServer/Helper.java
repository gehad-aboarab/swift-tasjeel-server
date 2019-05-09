package BrokerServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Helper {
	private static MongoClient mongoClient;
	private static MongoDatabase database;
	
	private static String REGISTERED_ENTITY_TABLE = "broker-registered-entity";
	private static String ENTITY_PATHS_TABLE = "broker-entity-paths";
	
	public Helper(String databaseName) {
		mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://Gehad:Aboarab97@cloud-computing-zqxty.mongodb.net/test?retryWrites=true"));
		database = mongoClient.getDatabase(databaseName);
	}
	
	public String callRest(String urlString){
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			
			if (connection.getResponseCode() != 200){
				return "{}";
			}
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			
			String output;
			output = bufferedReader.readLine();
			
			connection.disconnect();
			
			return output;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "{}";
		} catch (IOException e) {
			e.printStackTrace();
			return "{}";
		}
	}
	
	public static String getPath(String entityType) {
		MongoCollection<Document> collection = database.getCollection(ENTITY_PATHS_TABLE);
		FindIterable<Document> documents = collection.find();
		
		for(Document d:documents) {
			if(d.get("type").equals(entityType)) {
				return d.getString("path");
			}
		}
		
		return "";
	}
	
	public static String getServicePath(String entityType, String service) {
		MongoCollection<Document> collection = database.getCollection(ENTITY_PATHS_TABLE);
		FindIterable<Document> documents = collection.find();
		
		for(Document d:documents) {
			if(d.get("type").equals(entityType) && d.get("service").equals(service)) {
				return d.getString("service-path");
			}
		}
		
		return "";
	}
	
	public static String registerEntity(String type, String name, String path) {
		MongoCollection<Document> collection = database.getCollection(REGISTERED_ENTITY_TABLE);
		FindIterable<Document> documents = collection.find();

		boolean exists = false;
		
		// Loop through the documents to check if the entity is already registered
		for(Document document : documents) {
			if(document.get("name").equals(name) || document.get("path").equals(path)) {
				exists = true;
				break;
			}
		}
		
		// Insert new entity to table
		if(!exists) {
			Document entity = new Document();
			entity.put("name", name);
			entity.put("path", path);
			entity.put("type", type);
			collection.insertOne(entity);
			return new JSONObject(entity).toString();
		}
		  
		return "{}";
		
	}
	
	public static void main(String[] args) {
		mongoClient = new MongoClient(new MongoClientURI("mongodb+srv://Gehad:Aboarab97@cloud-computing-zqxty.mongodb.net/test?retryWrites=true"));
		database = mongoClient.getDatabase("cloud-computing");
		
//		System.out.println(getPath("insurance"));
//		System.out.println(getPath("testing-center"));
//		System.out.println(getPath("dubai-police"));
//		System.out.println(getPath("rta"));
//		
//		System.out.println(getServicePath("insurance","get-packages"));
//		System.out.println(getServicePath("insurance","get-plan"));
//		System.out.println(getServicePath("insurance","renew-plan"));
//		System.out.println(getServicePath("insurance","register-package"));
//		System.out.println(getServicePath("testing-center","get-timings"));
//		System.out.println(getServicePath("testing-center","book-timing"));
//		System.out.println(getServicePath("testing-center","booked-timing"));
//		System.out.println(getServicePath("dubai-police","get-fines"));
//		System.out.println(getServicePath("dubai-police","payment"));
//		System.out.println(getServicePath("rta","renewal-fees"));
//		System.out.println(getServicePath("rta","renew-registration"));
	}

}
