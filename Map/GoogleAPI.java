package Map;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import javax.swing.ImageIcon;


public class GoogleAPI {
	GeocoderAPI geo = new GeocoderAPI();
	public void downloadMap(String start, String end){
		try{
			
			
			String startpoint = geo.geocoder(start);//위도, 경도			
			String endpoint = geo.geocoder(end);
			
			
			String imageURL = "https://maps.googleapis.com/maps/api/staticmap?"
					//+ URLEncoder.encode(addr, "UTF-8")+", "+ URLEncoder.encode(start, "UTF-8")
							//+ "center=127.446757,36.3378566"
							+ "&size=800x800"
							+ "&markers=size:mid%7Ccolor:blue%7Clabel:S%7C"
							+ URLEncoder.encode(startpoint, "UTF-8")
							+ "&markers=size:mid%7Ccolor:red%7Clabel:E%7C"
							+ URLEncoder.encode(endpoint, "UTF-8")
							+ "&maptype=roadmap"
							+ "&path=weight:5%7Ccolor:red%7C"
							+ URLEncoder.encode(startpoint, "UTF-8") + "|"
							+ URLEncoder.encode(endpoint, "UTF-8")
							+ "&key=AIzaSyDjV4VZtoXc5Uxalf5l7cdFhFagfpDb51I";
//							+ "&scale=2";
			
			
			URL url = new URL(imageURL);
			url.openConnection();
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(end);
			byte[] b = new byte[2048];
			int length;
			while((length = is.read(b))!=-1){
				os.write(b, 0, length);
			}
			is.close();
			os.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public ImageIcon getMap(String location){
		return new ImageIcon((new ImageIcon(location)).getImage().getScaledInstance(800, 800, java.awt.Image.SCALE_SMOOTH));
	}
	
	public void fileDelete(String fileName){
		File f = new File(fileName);
		f.delete();
	}
}