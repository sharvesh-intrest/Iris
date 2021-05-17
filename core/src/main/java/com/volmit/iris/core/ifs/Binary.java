package com.volmit.iris.core.ifs;

import art.arcane.quill.io.IO;

import java.io.*;
import java.util.Base64;

public interface Binary {
    void write(DataOutputStream dos) throws IOException;

    void read(DataInputStream din) throws IOException;

    default String writeBase64() throws IOException
    {
        return Base64.getEncoder().encodeToString(writeBytes());
    }

    default void read(String base64) throws IOException
    {
        read(Base64.getDecoder().decode(base64));
    }

    default byte[] writeBytes() throws IOException {
        ByteArrayOutputStream bin = new ByteArrayOutputStream();
        write(bin);
        bin.close();
        return bin.toByteArray();
    }

    default void read(byte[] bytes) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        read(in);
        in.close();
    }

    default void write(OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        write(dos);
        dos.flush();
    }

    default void write(File file) throws IOException
    {
        file.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(file);
        write(fos);
        fos.close();
    }

    default void read(InputStream in) throws IOException
    {
        read(new DataInputStream(in));
    }

    default void read(File file) throws IOException
    {
        FileInputStream fin = new FileInputStream(file);
        read(fin);
        fin.close();
    }
}
