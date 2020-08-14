package Nodes.LineNodes;



import java.util.ArrayList;
import java.util.List;

public class ConditionNode extends AbstractLineNode  {
    private List<AbstractLineNode> insideNodes;

    public ConditionNode() {
        this.insideNodes = new ArrayList<>();
    }

}
