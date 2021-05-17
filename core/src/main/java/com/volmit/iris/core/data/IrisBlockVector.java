package com.volmit.iris.core.data;

import art.arcane.quill.cache.Cache;
import com.volmit.iris.core.ifs.Binary;
import lombok.Data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@Data
public class IrisBlockVector implements Binary
{
    private short x;
    private short y;
    private short z;

    public IrisBlockVector(DataInputStream din) throws IOException {
        read(din);
    }

    public IrisBlockVector(int x, int y, int z)
    {
        this.x = (short) x;
        this.y = (short) y;
        this.z = (short) z;
    }

    public void add(int x, int y, int z)
    {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    public void subtract(int x, int y, int z)
    {
        add(-x, -y, -z);
    }

    public void add(IrisBlockVector other)
    {
        add(other.getX(), other.getY(), other.getZ());
    }

    public void subtract(IrisBlockVector other)
    {
        subtract(other.getX(), other.getY(), other.getZ());
    }

    @Override
    public void write(DataOutputStream dos) throws IOException {
        dos.writeShort(x);
        dos.writeShort(y);
        dos.writeShort(z);
    }

    @Override
    public void read(DataInputStream din) throws IOException {
        x = din.readShort();
        y = din.readShort();
        z = din.readShort();
    }
}
