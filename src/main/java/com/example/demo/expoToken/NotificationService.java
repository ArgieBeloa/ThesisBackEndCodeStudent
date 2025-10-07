package com.example.demo.expoToken;//package com.example.demo.expoToken;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.ZonedDateTime;
//import java.util.List;
//
//@Service
//public class NotificationService {
//
//    @Autowired
//    private NotificationRepository notificationRepository;
//
//    @Autowired
//    private PushTokenRepo pushTokenRepository;  // Your existing push token repo
//
//    @Autowired
//    private PushNotificationSender pushNotificationSender;  // A component you write to send push notifications (see below)
//
////    @Scheduled(cron = "0 * * * * ?") // runs every minute
////    public void sendScheduledNotifications() {
////        ZonedDateTime nowPHT = ZonedDateTime.now(ZoneId.of("Asia/Manila"));
////
////        List<Notification> dueNotifications = notificationRepository.findByScheduledTimeBeforeAndSentFalse(nowPHT.toOffsetDateTime());
////
////        for (Notification notification : dueNotifications) {
////            List<PushTokenModel> tokens = pushTokenRepository.findByCourse(notification.getCourse());
////
////            for (PushTokenModel token : tokens) {
////                try {
////                    pushNotificationSender.sendNotification(token.getToken(), notification.getTitle(), notification.getBody());
////                } catch (IOException e) {
////                    System.err.println("Failed to send notification to token: " + token.getToken());
////                    e.printStackTrace();
////                }
////            }
////
////            notification.setSent(true);
////            notificationRepository.save(notification);
////        }
////    }
//
//
//    // Optional: Add method to create new notification
//    public Notification scheduleNotification(Notification notification) {
//        notification.setSent(false);
//        return notificationRepository.save(notification);
//    }
//
//    public  void deleteNotificationI(String id){
//        notificationRepository.deleteById(id);
//    }
//
//    public List<Notification> getAll(){
//      return  notificationRepository.findAll();
//    }
//}
