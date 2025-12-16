package com.bootRestGzip;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.WriteListener;

import java.io.IOException;
import java.util.zip.GZIPOutputStream;

class CustomServletOutputStream extends ServletOutputStream {

    private final GZIPOutputStream gzipOutputStream;

    public CustomServletOutputStream(GZIPOutputStream gzipOutputStream) {
        this.gzipOutputStream = gzipOutputStream;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
    }

    @Override
    public void write(int b) throws IOException {
        this.gzipOutputStream.write(b);
    }
}
