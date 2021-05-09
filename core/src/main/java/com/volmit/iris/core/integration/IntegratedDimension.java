package com.volmit.iris.core.integration;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IntegratedDimension {
    private int maxHeight;
    private String displayName;
    private String identifier;
    private int dimensionId;
}
