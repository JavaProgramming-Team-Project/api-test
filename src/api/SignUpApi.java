package api;

import entity.Member;
import ip.IpConfig;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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

        try {
            String hostUrl = "http://"+IP+":"+PORT+"/member/signup";
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
            System.out.println("응답 메시지 : " + returnMsg);

            int responseCode = conn.getResponseCode();
            if (responseCode == 400) {
                System.out.println("400 : 명령 실행 오류");
            } else if (responseCode == 500) {
                System.out.println("500 : 서버 에러");
            } else {
                System.out.println(responseCode + " : 응답 코드");
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
