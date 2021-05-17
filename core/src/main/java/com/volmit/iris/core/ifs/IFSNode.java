package com.volmit.iris.core.ifs;

import art.arcane.quill.collections.KList;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

public abstract class IFSNode
{
    public static final Iterable<IFSNode> NONE = new KList<>();

    @Getter
    @Setter
    private transient String registryName;

    public IFSNode()
    {

    }

    /**
     * Get any possible nodes that would need to be loaded if this node is fully utilized
     * @return a list / iterable collection of nodes that would need to be loaded to use this node. If there are no dependencies, return IFSNode.NONE.
     */
    public abstract Iterable<? extends IFSNode> getDependencies();
}
