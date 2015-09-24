import java.io.IOException;

/**
 * Created by Carlo on 18/09/2015.
 */
public class Launcher {

    public static void main(String[] args){

        System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\Carlo\\Documents\\openshift-keystore");

        try {
            String response = OpenshiftImageStream.createStream();
            System.out.println(response);

            Thread.sleep(5000);

            response = OpenshiftBuildConfig.doRequest();
            System.out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
