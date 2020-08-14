package Nodes.SegmentNodes;


import Nodes.LineNodes.AbstractLineNode;


import java.util.ArrayList;


public abstract class AbstractSegmentNode {
    private ArrayList<AbstractLineNode> nodesList ;

    public AbstractSegmentNode(){
        nodesList =   new ArrayList<AbstractLineNode>();
    }

    public ArrayList<AbstractLineNode> getNodesList() {
        return nodesList;
    }

    public void setNodesList(ArrayList<AbstractLineNode> nodesList) {
        this.nodesList = nodesList;
    }
}
