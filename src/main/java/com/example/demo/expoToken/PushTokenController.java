package com.example.demo.expoToken;//package com.example.demo.expoToken;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/pushToken")
//public class PushTokenController {
//
//    @Autowired
//    private PushTokenServices pushTokenServices;
//
//    @Autowired
//    private NotificationService notificationService;
//
//     @GetMapping("/getAll")
//     public ResponseEntity<List<PushTokenModel>> getAllToken(){
//
//         List<PushTokenModel> tokens = pushTokenServices.getAllToken();
//
//         return new ResponseEntity<>(tokens, HttpStatus.OK);
//     }
//
//
//
//
//    @PostMapping("/send")
//    public String sendNotification(@RequestParam String userId,
//                                   @RequestParam String title,
//                                   @RequestParam String message) {
//        PushTokenModel tokenModel = pushTokenServices.getTokenByUserId(userId);
//
//        if (tokenModel == null || tokenModel.getToken() == null) {
//            return "❌ No token found for userId: " + userId;
//        }
//
//        pushTokenServices.sendNotification(tokenModel.getToken(), title, message);
//
//        return "✅ Notification sent to userId: " + userId;
//    }
//    @PostMapping("/course/{course}")
//    public ResponseEntity<String> notifyCourse(
//            @PathVariable String course,
//            @RequestParam String title,
//            @RequestParam String body) {
//
//        try {
//            pushTokenServices.sendCourseNotification(course, title, body);
//            return ResponseEntity.ok("✅ Notifications sent to course: " + course);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("❌ Failed to send notifications: " + e.getMessage());
//        }
//    }
//
//
//@PostMapping("/add")
//public String saveToken(@RequestBody PushTokenModel tokenModel) {
//    if (tokenModel.getToken() == null || tokenModel.getUserId() == null || tokenModel.getCourse() == null) {
//        return "❌ Token, userId, or course missing";
//    }
//
//    pushTokenServices.saveOrUpdateToken(tokenModel);
//    return "✅ Token saved";
//}
//
//    @PostMapping("/notify-all")
//    public String notifyAllUsers(@RequestBody NotificationRequest request) {
//        pushTokenServices.sendAnnouncementToAll(request.getTitle(), request.getMessage());
//        return "✅ Notifications sent to all users";
//    }
//
//
//    @PostMapping("/schedule")
//    public ResponseEntity<?> scheduleNotification(@RequestBody Notification notification) {
//        Notification savedNotification = notificationService.scheduleNotification(notification);
//        return ResponseEntity.ok(savedNotification);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public void deleteById(@PathVariable String id){
//
//         pushTokenServices.deleteById(id);
//    }
////     get notification
//    @DeleteMapping("/deleteNotification/{id}")
//    public void deleteNotification(@PathVariable String id){
//
//       notificationService.deleteNotificationI(id);
//
//
//    }
//
////    find all notification
//    @GetMapping("/notification/getAll")
//    public ResponseEntity<List<Notification>> getAll (){
//      List<Notification> notifications = notificationService.getAll();
//
//      return new ResponseEntity<>(notifications, HttpStatus.OK);
//    }
//
//}
