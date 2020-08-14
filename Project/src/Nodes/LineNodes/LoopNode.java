package Nodes.LineNodes;

import java.util.ArrayList;
import java.util.List;

public class LoopNode extends AbstractLineNode  {
    private List<AbstractLineNode> insideNodes;

    public LoopNode() {
        this.insideNodes = new ArrayList<>();
    }
}
