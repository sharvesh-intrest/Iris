package com.volmit.iris.core.ifs;

import com.volmit.iris.core.Iris;
import com.volmit.iris.core.util.IrisMaps;
import lombok.Data;

import java.io.File;
import java.util.Map;

@Data
public abstract class IFSLoader<T extends IFSNode>
{
    private final String pluralTypeName;
    private final String singularTypeName;
    private final String nodeNameExtension;
    private final File rootDirectory;
    private final Map<String, T> loadCache;

    public IFSLoader(File root, String extension, String singular, String plural)
    {
        loadCache = IrisMaps.newFootprintMap();
        this.rootDirectory = root;
        this.nodeNameExtension = extension;
        this.pluralTypeName = plural;
        this.singularTypeName = singular;
    }

    public File getPathToKey(String key)
    {
        return new File(getRootDirectory(), key + "." + getNodeNameExtension());
    }

    public T load(String key)
    {
        T t = getLoadCache().get(key);

        if(t != null)
        {
            return t;
        }

        File ptk = getPathToKey(key);
        t = onLoad(ptk);

        if(t != null)
        {
            getLoadCache().put(key, t);
            Iris.v("(IFS) Loaded " + getSingularTypeName() + " " + key);
        }

        else if(ptk.exists())
        {
            Iris.f("(IFS) Failed to load " + getSingularTypeName() + " " + key + " (" + ptk.getAbsolutePath() + " may be invalid or corrupt. See above error.)");
        }

        else
        {
            Iris.f("(IFS) Failed to load " + getSingularTypeName() + " " + key + " (" + ptk.getAbsolutePath() + " does not exist)");
        }

        return t;
    }

    public abstract T onLoad(File file);
}
