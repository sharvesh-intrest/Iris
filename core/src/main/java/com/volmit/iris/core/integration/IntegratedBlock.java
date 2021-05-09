package com.volmit.iris.core.integration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntegratedBlock {
    private String mod;
    private String id;
    private String displayName;
}
