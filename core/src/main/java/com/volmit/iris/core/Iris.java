package com.volmit.iris.core;

import art.arcane.quill.execution.Looper;
import com.volmit.iris.core.data.IrisDimension;
import com.volmit.iris.core.integration.IrisIntegration;
import com.volmit.iris.core.util.ThreadBiased;

public class Iris {
    private static Looper looper;
    private static IrisIntegration integration;

    public static void integrate(IrisIntegration i)
    {
        integration = i;

        if(looper == null)
        {
            looper = new Looper() {
                @Override
                protected long loop() {
                    try
                    {
                        ThreadBiased.tick();
                        integration.tick();
                    }

                    catch(Throwable e)
                    {
                        f("Failed to tick the Iris Maintenance Thread!");
                        ex(e);
                    }
                    return 1000;
                }
            };
            looper.setPriority(0);
            looper.setName("Iris Maintenance");
            looper.start();
        }
    }

    public static IrisIntegration integration(){
        return integration;
    }

    public static void i(String msg)
    {
        integration().i(msg);
    }

    public static void v(String msg)
    {
        integration().v(msg);
    }

    public static void w(String msg)
    {
        integration().w(msg);
    }

    public static void f(String msg)
    {
        integration().f(msg);
    }

    public static void ex(Throwable msg)
    {
        integration().ex(msg);
    }

    public static void fex(String msg)
    {
        try
        {
            throw new RuntimeException(msg);
        }

        catch(Throwable e)
        {
            ex(e);
        }
    }
}
