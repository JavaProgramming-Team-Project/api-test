package api;

import entity.Member;
import ip.IpConfig;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SignUpApi {
    private final String IP  = IpConfig.getIp();
    private final String PORT = IpConfig.getPort();

    public SignUpApi(Member member) {

        JSONObject data = new JSONObject();

        data.put("memberId", member.getId());
        data.put("memberPassword", member.getPassword());
        data.put("memberName", member.getName());
        data.put("memberPhone", member.getPhone());
        data.put("memberAge", member.getAge());

        String jsonType = JSONValue.toJSONString(data);
        System.out.println(jsonType);

        JSONParser jp = new JSONParser();

        try {
            Object result = jp.parse(jsonType);
            if (result instanceof JSONObject) {
                JSONObject test = (JSONObject)result;

                System.out.println("name : " + test.get("memberName"));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        try {
            String hostUrl = "http://"+IP+":"+PORT+"/user/signup";
            HttpURLConnection conn = null;

            URL url = new URL(hostUrl);
            conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("POST");
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("Content-Type", "application/json; utf-8");

            conn.setDoOutput(true);

            OutputStream os = conn.getOutputStream();
            os.write(jsonType.getBytes(StandardCharsets.UTF_8));
            os.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String returnMsg = br.readLine();
            System.out.println("?????? ????????? : " + returnMsg);

            int responseCode = conn.getResponseCode();
            if (responseCode == 400) {
                System.out.println("400 : ?????? ?????? ??????");
            } else if (responseCode == 500) {
                System.out.println("500 : ?????? ??????");
            } else {
                System.out.println(responseCode + " : ?????? ??????");
            }
        } catch (ProtocolException e) {
            System.out.println("ProtocolException");
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("IOException");
            throw new RuntimeException(e);
        }
    }
}
