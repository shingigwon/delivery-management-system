package Map;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GeocoderAPI {
	
	public String geocoder(String addr){
		String lat = ""; // ����
		String lon = ""; // �浵
		String mapxml = ""; // �޾ƿ� xml		
		
		try {
			if(addr.contains("��۴�"))
				addr = "�ھ絿 155-3����";
			
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
			int len = urlConn.getContentLength(); // �޾ƿ��� xml�� ����
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
								mapxml.indexOf("]]></x>")); // �浵 �߶����
						lat = mapxml.substring(mapxml.indexOf("<y>") + 12,
								mapxml.indexOf("]]></y>")); // ���� �߶����
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