package com.bootRestGzip;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.zip.GZIPOutputStream;

class CustomHttpServletResponseWrapper extends HttpServletResponseWrapper {

    private final GZIPOutputStream gzipOutputStream;
    private ServletOutputStream outputStream;
    private PrintWriter writer;

    public CustomHttpServletResponseWrapper(HttpServletResponse response) throws IOException {
        super(response);
        this.gzipOutputStream = new GZIPOutputStream(response.getOutputStream());
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (this.outputStream == null) {
            this.outputStream = new CustomServletOutputStream(this.gzipOutputStream);
        }
        return this.outputStream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (this.writer == null) {
            this.writer = new PrintWriter(new OutputStreamWriter(this.gzipOutputStream, getCharacterEncoding()));
        }
        return this.writer;
    }

    @Override
    public void flushBuffer() throws IOException {
        if (this.writer != null) {
            this.writer.flush();
        }
        if (this.outputStream != null) {
            this.outputStream.flush();
        }
        this.gzipOutputStream.flush();
    }

    public void close() throws IOException {
        this.gzipOutputStream.close();
    }
}
