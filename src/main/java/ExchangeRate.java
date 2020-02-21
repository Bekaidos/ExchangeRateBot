import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Map;

public class ExchangeRate {

    public ExchangeRate(){

    }


    public Response getCurrency(String currency) {

    String output, KZTResponse = "", date_insert  = "";
    StringBuilder response = new StringBuilder();
        try {

            URL url = new URL("http://localhost:8080/exchange/service");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"currency\": \"" + currency + "\" }";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            while ((output = br.readLine()) != null) {
                response.append(output.trim());
            }

            Gson gson= new Gson();
            Map map = gson.fromJson(response.toString(), Map.class);
            KZTResponse = map.get("kztresponse").toString();
            date_insert = map.get("date_insert").toString();

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Response(KZTResponse, date_insert);
    }




}
