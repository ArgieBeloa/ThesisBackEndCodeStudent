package com.example.demo.db;

import com.example.demo.studentPackage.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

/**
 * Global JWT filter for all secured endpoints.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ApplicationContext context;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        String method = request.getMethod();

        System.out.println("🔎 Incoming request: " + method + " " + path);

        // ✅ Skip JWT filtering for public endpoints
        if (path.contains("/api/students/login") ||
                path.contains("/api/students/register") ||
                path.contains("/api/admin/check-student")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String studentNumber = null;

        try {
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                studentNumber = jwtService.extractUserName(token);
            }

            if (studentNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = context.getBean(MyUserDetailsService.class)
                        .loadUserByUsername(studentNumber);

                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            // 🚫 Prevent cross-student access attempts
            if (path.startsWith("/api/students/")) {
                String[] parts = path.split("/");
                if (parts.length > 3) {
                    String targetParam = parts[3]; // /api/students/{id or studentNumber}/...
                    String currentUser = jwtService.extractUserName(token);

                    // If the logged-in student tries to access another student's data
                    if (currentUser != null && !currentUser.equals(targetParam)) {
                        System.out.println("❌ Blocked cross access: " + currentUser + " → " + targetParam);
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        response.getWriter().write("Access denied: You cannot access another student's data");
                        return;
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("⚠️ JWT filter error: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid or missing token");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
