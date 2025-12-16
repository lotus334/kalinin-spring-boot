package com.bootRestGzip;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/items", "/items/2"})
public class GzipResponseCompressionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("    INIT GZIP FILTER");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("    DO FILTER GZIP");

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (acceptsGZipEncoding(httpRequest)) {
            System.out.println("    GZIP");
            CustomHttpServletResponseWrapper gzipResponse = new CustomHttpServletResponseWrapper(httpResponse);
            gzipResponse.setHeader("Content-Encoding", "gzip");
            chain.doFilter(request, gzipResponse);
            gzipResponse.close();
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean acceptsGZipEncoding(HttpServletRequest request) {
        String acceptEncoding = request.getHeader("Accept-Encoding");
        return acceptEncoding != null && acceptEncoding.contains("gzip");
    }
}