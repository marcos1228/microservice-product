//package com.product.config.security;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.product.constant.JWTConstants;
//import com.product.service.JwtUserDetailsService;
//import com.product.util.JwTokenUtil;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import java.util.Arrays;
//
//
//@Component
//public class JwtAutenticationFilter extends OncePerRequestFilter{
//	
//	@Autowired
//	private JwtUserDetailsService jwtUserDetailsService;
//	
//	@Autowired
//	private JwTokenUtil jwTokenUtil;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//	
//		String header = request.getHeader(JWTConstants.HEADER_STRING);
//        String username = null;
//        String authToken = null;
//
//        if (header != null && header.startsWith(JWTConstants.TOKEN_PREFIX)) {
//            authToken = header.replace(JWTConstants.TOKEN_PREFIX, "");
//            username = jwTokenUtil.getUsernameFromToken(authToken);
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
//
//            if (jwTokenUtil.validateToken(authToken, userDetails)) {
//                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//                        userDetails, null,
//                        Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
//
//                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//
//        filterChain.doFilter(request, response);
//    }
//	}
//	