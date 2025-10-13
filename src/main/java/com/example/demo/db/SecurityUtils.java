package com.example.demo.db;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security helper for checking if a request is from the correct owner (student)
 */
public class SecurityUtils {

    /**
     * Get the currently authenticated student's number from the JWT.
     */
    public static String getCurrentUserStudentNumber() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getName() != null) {
            return auth.getName(); // JWT subject = studentNumber
        }
        return null;
    }

    /**
     * Check if the current logged-in user is the owner of the requested data.
     */
    public static boolean isOwner(String targetStudentNumber) {
        String current = getCurrentUserStudentNumber();
        return current != null && current.equals(targetStudentNumber);
    }

    /**
     * Check ownership when you only have DB id but can fetch studentNumber from DB.
     */
    public static boolean isOwnerById(String targetId, String ownerStudentNumberFromDb) {
        String current = getCurrentUserStudentNumber();
        return current != null && current.equals(ownerStudentNumberFromDb);
    }
}
