package com.medhead.api.filter;

import org.springframework.stereotype.Component;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ApiKeyFilter implements javax.servlet.Filter {

    private static final String API_KEY_HEADER = "X-API-KEY";
    private static final String API_KEY = "203f16bd-d289-452c-9371-b0e042a64ef4";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization
    }

    @Override
    // Filter requests based on the API key
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        String apiKey = httpRequest.getHeader(API_KEY_HEADER);
        System.err.println("Received API Key: " + apiKey);

        // Exclude specific patterns
        if (requestURI.startsWith("/swagger-ui")) {
            chain.doFilter(request, response);
            return;
        }
        // Check if the API key is valid
        if (API_KEY != null && API_KEY.equals(apiKey)) {
            chain.doFilter(request, response);
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.getWriter().write("Unauthorized");
        }
    }

    @Override
    public void destroy() {
        // Cleanup
    }
}