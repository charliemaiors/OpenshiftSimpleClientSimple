import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenshiftRouteConfig {

    private final static String consoleIpAddress = "https://openshift:8443/oapi/v1/namespaces/nubomedia/routes";
    private final static String authToken = "0lCfgEQQRrPShWFyrSd48lnkAbUYhNwJg_qTalq6E0k";

    public static String mapRoute() throws IOException{
        String serviceStatusString = readRoute("resources/demo-app-route.json");
        System.out.println(serviceStatusString);
        HttpClient client = HttpClients.createDefault();
        StringEntity se = new StringEntity(serviceStatusString);
        se.setContentType("application/json");
        se.setContentEncoding("UTF-8");
        HttpPost post = new HttpPost(consoleIpAddress);
        post.setEntity(se);
        post.setHeader("Authorization", "Bearer " + authToken);
        post.setHeader("Content-type", "application/json");

        HttpResponse res = client.execute(post);
        String responseString = EntityUtils.toString(res.getEntity());
        post.releaseConnection();

        return responseString;
    }

    private static String readRoute(String filename){

        String res = "";
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));
            String line;

            while ((line = bf.readLine()) != null){
                res += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;

    }

}
