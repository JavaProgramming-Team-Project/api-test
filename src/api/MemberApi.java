package api;

import auth.AuthMember;
import entity.Member;
import ip.IpConfig;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class MemberApi {
    private final static String IP  = IpConfig.getIp();
    private final static String PORT = IpConfig.getPort();

    public static void signUp(Member member) {
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

    public static void login(String id, String password) {
        try{
            String hostUrl = "http://"+IP+":"+PORT+"/user/"+id+"?"+"password="+password;
            System.out.println(hostUrl);
            HttpURLConnection conn = null;

            URL url = new URL(hostUrl);
            conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setRequestProperty("Accept", "application/json; utf-8");

            int responseCode = conn.getResponseCode();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();

            String response = sb.toString();
            System.out.println(response);

            JSONParser jp = new JSONParser();

            Object result = jp.parse(response);

            if (result instanceof JSONObject) {
                JSONObject data = (JSONObject) result;
//                Member member = new Member(data.get("memberId"), data.get("memberPassword"), data.get("memberName"), data.get("memberPhone"), data.get("memberAge"));
//                AuthMember auth = new AuthMember(member);
            }

        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        login("jcw1031", "jcw123");
    }
}
