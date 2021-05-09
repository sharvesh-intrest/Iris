package com.volmit.iris.core.util;

public interface IrisConverter<A,B> {
    B to(A b);

    A from(B b);
}
