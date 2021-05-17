package com.volmit.iris.core.ifs;

import art.arcane.quill.io.IO;
import com.google.gson.Gson;
import com.volmit.iris.core.Iris;

import java.io.File;

public class IFSJsonLoader<T extends IFSNode> extends IFSLoader<T> {
    private final Gson gson;
    private final Class<? extends T> clazz;

    public IFSJsonLoader(File root, String extension, String singular, String plural, Class<? extends T> clazz, Gson gson) {
        super(root, extension, singular, plural);
        this.gson = gson;
        this.clazz = clazz;
    }

    public IFSJsonLoader(File root, String extension, String singular, String plural, Class<? extends T> clazz) {
        this(root, extension, singular, plural, clazz, new Gson());
    }

    @Override
    public T onLoad(File file) {
        try {
            return gson.fromJson(IO.readAll(file), clazz);
        } catch (Throwable e) {
            Iris.f("(IFS) Error loading JSON for " + file.getAbsolutePath() + " into " + clazz.getCanonicalName());
            Iris.ex(e);
        }

        return null;
    }
}
