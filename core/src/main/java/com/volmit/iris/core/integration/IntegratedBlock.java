package com.volmit.iris.core.integration;

import art.arcane.quill.format.Form;
import com.volmit.iris.core.data.IrisBlock;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IntegratedBlock {
    private String mod;
    private String id;
    private String displayName;

    public boolean match(String search) {
        return Form.match(mod, search) || Form.match(id, search) || Form.match(displayName, search);
    }

    public IrisBlock toBlock()
    {
        return new IrisBlock(mod, id);
    }
}
