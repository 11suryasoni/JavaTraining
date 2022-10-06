package RestApi;


import com.github.opendevl.JFlat;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

public class cryptoApi {

    public static void main(String[] args) throws Exception {
        String url = "https://api.coingecko.com/api/v3/exchange_rates";
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        PrintWriter pw = new PrintWriter("/Users/azuga/Desktop/crypto2.json");
        pw.write(response.body());
        pw.close();

        String jsonArrayString= new String(Files.readAllBytes(Paths.get("/Users/azuga/Desktop/crypto2.json")));

        String jsonn=jsonArrayString.replaceAll("}","},{");
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        sb.append(jsonn);
        sb.append("]");

        JFlat jFlatvar= new JFlat(sb.toString());
        jFlatvar.json2Sheet().headerSeparator("_").getJsonAsSheet();
        jFlatvar.write2csv("/Users/azuga/Desktop/cryptocsv2.csv");

    }
}