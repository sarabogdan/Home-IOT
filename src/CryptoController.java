

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import it.sauronsoftware.cron4j.Scheduler;

public class CryptoController {
	
	public static String lastRippleBidPrice;
	private static PriceDisplay priceDisplay;
	public static void main(String[] args) {
		priceDisplay = new PriceDisplay();
		new CryptoController();
	}
	
	public CryptoController() {
		//api https://www.bitstamp.net/api/v2/ticker/xrpusd
		
		Scheduler s = new Scheduler();
		// Schedule a once-a-minute task.
		s.schedule("* * * * *", new Runnable() {
			public void run() {
				try {
					sendGet();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		// Starts the scheduler.
		s.start();
	}
	
	// HTTP GET request
	private void sendGet() throws Exception {

		String url = "https://www.bitstamp.net/api/v2/ticker/xrpusd";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			CryptoController.lastRippleBidPrice = inputLine.substring(inputLine.indexOf("bid\": ") + 7, inputLine.indexOf("bid\": ") + 14);
			priceDisplay.setPrice(lastRippleBidPrice);
			System.out.println("Current Price: " + lastRippleBidPrice);
		}
		
		in.close();
	}
}