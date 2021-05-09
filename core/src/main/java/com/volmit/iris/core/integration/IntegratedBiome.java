package com.volmit.iris.core.integration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntegratedBiome {
    private int biomeId;
    private String displayName;
    private String identifier;

    @Builder.Default
    private float temperature = 0.75f;
    @Builder.Default
    private float rainfall= 0.5f;
    @Builder.Default
    private float heightMultiplier = 1;
}
