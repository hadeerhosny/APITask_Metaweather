import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class main{
	
	private static String spec;

	public main(String[] args) {
		
		try {
			
			URL url = new URL(spec: "https://www.metaweather.com/api/location/search/?query=london");
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			int responseCode = conn.getResponseCode();
			
			if (responseCode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responseCode);
				
			} else {
				StringBuilder informationString = new StringBuilder();
				Scanner scanner = new Scanner(url.openStream());
				
				while(scanner.hasNext()) {
					informationString.append(scanner.nextLine());
				}
				scanner.close();
				
				System.out.println(informationString);
				
				JSONParser parse = new JSONParser();
				JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));
				
				System.out.println(dataObject.get(0));
				
				JSONObject countryData = (JSONObject) dataObject.get(0);
				
				System.out.println(countryData.get("location_type"));
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}