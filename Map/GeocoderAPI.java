package Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GeocoderAPI {
	
	public String geocoder(String addr){
		String lat = ""; // 위도
		String lon = ""; // 경도
		String mapxml = ""; // 받아온 xml		
		
		try {
			if(addr.contains("우송대"))
				addr = "자양동 155-3번지";
			
			StringBuilder sb = new StringBuilder("https://api.vworld.kr/req/address");
			sb.append("?service=address");
			sb.append("&request=getCoord");
			sb.append("&format=xml");
			sb.append("&crs=epsg:4326");
			sb.append("&key=072F6218-9430-3BCD-BC3E-323D41BCFFA5");
			sb.append("&type=parcel");
			String murl = sb.toString()+"&address=" + URLEncoder.encode(addr, "UTF-8");
			
			URL mapXmlUrl = new URL(murl);
			HttpURLConnection urlConn = (HttpURLConnection) mapXmlUrl
					.openConnection();
			urlConn.setDoOutput(true);
			urlConn.setRequestMethod("POST");
			int len = urlConn.getContentLength(); // 받아오는 xml의 길이
			if (len > 0) {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						urlConn.getInputStream(), "UTF-8"));
				String inputLine = "";
				while ((inputLine = br.readLine()) != null) {
					mapxml += inputLine; 
				}
				if (mapxml != null) {
					if (mapxml.indexOf("</point>") > -1) {

						lon = mapxml.substring(mapxml.indexOf("<x>") + 12,
								mapxml.indexOf("]]></x>")); // 경도 잘라오기
						lat = mapxml.substring(mapxml.indexOf("<y>") + 12,
								mapxml.indexOf("]]></y>")); // 위도 잘라오기
					}
				}
				br.close();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return lat + "," + lon;
		
	}
}