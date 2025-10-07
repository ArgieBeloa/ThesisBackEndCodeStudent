package com.example.demo.expoToken;//package com.example.demo.expoToken;
//
//import org.json.JSONObject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.http.*;
//
//import java.io.IOException;
//import java.util.List;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.MediaType;
//import okhttp3.Response;
//
//import static okhttp3.MediaType.*;
//
//
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//
//
//
//@Service
//public class PushTokenServices {
//
//    @Autowired
//    private PushTokenRepo pushTokenRepo;
//
//    public List<PushTokenModel> getAllToken(){
//
//        return  pushTokenRepo.findAll();
//    }
//    public PushTokenModel getTokenByUserId(String userId) {
//        return pushTokenRepo.findByUserId(userId);
//    }
//    public void saveOrUpdateToken(PushTokenModel tokenModel) {
//        PushTokenModel existingToken = pushTokenRepo.findByUserId(tokenModel.getUserId());
//
//        if (existingToken != null) {
//            existingToken.setToken(tokenModel.getToken());
//            existingToken.setCourse(tokenModel.getCourse());
//             pushTokenRepo.save(existingToken);
//        } else {
//            pushTokenRepo.save(tokenModel);
//        }
//    }
//
//    public void sendCourseNotification(String course, String title, String body) throws IOException {
//        List<PushTokenModel> tokens = pushTokenRepo.findByCourse(course);
//
//        for (PushTokenModel token : tokens) {
//            sendPushNotification(token.getToken(), title, body);
//        }
//    }
//    private void sendPushNotification(String token, String title, String body) throws IOException {
//        OkHttpClient client = new OkHttpClient();
//
//        // Create the JSON payload
//        JSONObject message = new JSONObject();
//        message.put("to", token);                  // Expo push token
//        message.put("sound", "default");           // Optional: notification sound
//        message.put("title", title);               // Notification title
//        message.put("body", body);                 // Notification body
//
//        // Request body setup
//        RequestBody requestBody = RequestBody.create(
//                message.toString(), parse("application/json"));
//
//        // HTTP request to Expo push endpoint
//        Request request = new Request.Builder()
//                .url("https://exp.host/--/api/v2/push/send")
//                .post(requestBody)
//                .addHeader("Content-Type", "application/json")
//                .build();
//
//        // Execute the request and print the response
//        try (Response response = client.newCall(request).execute()) {
//            if (response.isSuccessful()) {
//                System.out.println("✅ Expo response: " + response.body().string());
//            } else {
//                System.err.println("❌ Expo error: " + response.code() + " - " + response.body().string());
//            }
//        }
//    }
//
//
//
//
//
//
//    public void sendNotification(String to, String title, String body) {
//        RestTemplate restTemplate = new RestTemplate();
//
//        JSONObject payload = new JSONObject();
//        payload.put("to", to);
//        payload.put("title", title);
//        payload.put("body", body);
//        payload.put("sound", "default");
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Content-Type", "application/json");  // <-- Use raw string here
//
//        HttpEntity<String> request = new HttpEntity<>(payload.toString(), headers);
//
//        try {
//            String EXPO_URL = "https://exp.host/--/api/v2/push/send";
//            restTemplate.postForEntity(EXPO_URL, request, String.class);
//            System.out.println("✅ Notification sent successfully");
//        } catch (Exception e) {
//            System.err.println("❌ Failed to send push: " + e.getMessage());
//        }
//    }
//
//
//
//
//    public void sendAnnouncementToAll(String title, String message) {
//        List<PushTokenModel> tokens = pushTokenRepo.findAll();
//        for (PushTokenModel token : tokens) {
//            sendNotification(token.getToken(), title, message);
//        }
//    }
//
//
//    public void deleteById(String id){
//
//        pushTokenRepo.deleteById(id);
//
//    }
//}
