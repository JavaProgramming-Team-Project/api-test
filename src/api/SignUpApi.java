package api;

import entity.Member;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class SignUpApi {
    public SignUpApi(Member member) {
//        JSONObject jsonObject = new JSONObject();
//        JSONArray jsonArray = new JSONArray();

        JSONObject data = new JSONObject();
//        Map data = new LinkedHashMap();
//
        data.put("memberId", member.getId());
        data.put("memberPassword", member.getPassword());
        data.put("memberName", member.getName());
        data.put("memberPhone", member.getPhone());
        data.put("memberAge", member.getAge());

        String jsonType = JSONValue.toJSONString(data);

        /*String jsonType = "{\"memberId\" : \"" + member.getId()+"\", "
                + "\"memberPassword\" : \"" + member.getPassword()+"\", "
                + "\"memberName\" : \"" + member.getName()+"\", "
                + "\"memberPhone\" : \"" + member.getPhone()+"\", "
                +"\"memberAge\" : " + member.getAge()+"}";*/

        System.out.println(jsonType);

        try {
            String hostUrl = "http://localhost:8080/member/signup";
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

            /*OutputStream outputStream = conn.getOutputStream();
            byte[] input = jsonType.getBytes("utf-8");
            outputStream.write(input, 0, input.length);
            outputStream.flush();
            outputStream.close();*/

//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            /*bw.write(jsonType);
            bw.flush();
            bw.close();*/
//            outputStream.write(jsonType.getBytes("utf-8"));
//            outputStream.close();

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

    /*public static void main(String[] args) {
        Member member = new Member(1L, "jcwsdfefasdv", "jcw1234", "지찬우", "010-9517-1530", 23);

        new SignUpApi(member);
    }*/
}
