package com.volmit.iris.core.object;

import com.volmit.iris.core.ifs.IFSNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class IrisBiome extends IFSNode
{
    @Override
    public Iterable<? extends IFSNode> getDependencies() {
        return IFSNode.NONE;
    }
}
