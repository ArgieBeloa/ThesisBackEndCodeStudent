package com.example.demo.expoToken;//package com.example.demo.expoToken;
//
//import okhttp3.*;
//import org.json.JSONObject;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class PushNotificationSender {
//
//    private final OkHttpClient client = new OkHttpClient();
//
//    public void sendNotification(String token, String title, String body) throws IOException {
//        JSONObject message = new JSONObject();
//        message.put("to", token);
//        message.put("sound", "default");
//        message.put("title", title);
//        message.put("body", body);
//
//        RequestBody requestBody = RequestBody.create(
//                message.toString(),
//                MediaType.parse("application/json")
//        );
//
//        Request request = new Request.Builder()
//                .url("https://exp.host/--/api/v2/push/send")
//                .post(requestBody)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (!response.isSuccessful()) {
//                throw new IOException("Unexpected code " + response);
//            }
//            System.out.println("Expo response: " + response.body().string());
//        }
//    }
//}
