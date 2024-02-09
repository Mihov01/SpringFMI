// package com.flight.manager.flightmanager.config;

// import com.flight.manager.flightmanager.model.User;
// import com.flight.manager.flightmanager.service.UserService;
// import io.jsonwebtoken.ExpiredJwtException;
// import io.jsonwebtoken.JwtException;
// import io.jsonwebtoken.MalformedJwtException;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.core.annotation.Order;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;

// @Component
// @Slf4j
// @Order
// public class JwtAthFilter extends OncePerRequestFilter {

//     @Autowired
//     private UserService userService;

//     @Autowired
//     private JwtUtils jwtUtils;

//     @Override
//     protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//             throws ServletException, IOException {

//         final String authorizationHeader = request.getHeader("Authorization");
//         String username = null;
//         String jwtToken = null;

//         if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//             jwtToken = authorizationHeader.substring(7);
//             try {
//                 username = jwtUtils.getUsernameFromToken(jwtToken);
//             } catch (IllegalArgumentException ex) {
//                 log.error("Unable to get JWT token.");
//                 throw new BadCredentialsException("Unable to get JWT token.");
//             } catch (ExpiredJwtException ex) {
//                 log.error("JWT token has expired.");
//                 throw new BadCredentialsException("JWT token has expired.");
//             } catch (JwtException  ex) {
//                 log.error("Invalid JWT token.");
//                 throw new BadCredentialsException("Invalid JWT token.");
//             }
//         } else {
//             log.error("JWT token is missing or does not begin with 'Bearer ' prefix.");
//         }

//         if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//             User user = userService.getUserByUsername(username);
//             if (jwtUtils.validateToken(jwtToken, user)) {
//                 UsernamePasswordAuthenticationToken authenticationToken =
//                         new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//                 authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//             } else {
//                 log.error("JWT token validation failed.");
//                 throw new BadCredentialsException("JWT token validation failed.");
//             }
//         }

//         filterChain.doFilter(request, response);
//     }
// }
