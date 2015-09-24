import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;

/**
 * Created by Carlo on 23/09/2015.
 */
public class OpenshiftImageStream {

    private final static String consoleIpAddress = "https://194.95.174.111:8443/oapi/v1/namespaces/ciao-sti-build/imagestreams";
    private final static String authToken = "4F7tVwyuaB4i8pBVc8gxfhGq60shwPyNBYsl2lWmfME";

    public static String createStream() throws IOException {
        String buildConfigString = readBuildConfig("resources/demo-app-imagestream-docker.json");
        System.out.println(buildConfigString);
        HttpClient client = HttpClients.createDefault();
        StringEntity se = new StringEntity(buildConfigString);
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

    private static String readBuildConfig (String filename){

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
