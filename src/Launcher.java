import java.io.IOException;

public class Launcher {

    public static void main(String[] args){

        System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\Carlo\\Documents\\openshift-keystore");
        String response;
        try {
            response = OpenshiftImageStream.createStream();
            System.out.println(response + "\n");

            Thread.sleep(5000);

            response = OpenshiftBuildConfig.doRequest();
            System.out.println(response + "\n");

            Thread.sleep(5000);

            response = OpenshiftDeploymentConfig.writeDeployment();
            System.out.println(response);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
