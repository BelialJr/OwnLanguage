package Parser;



import Nodes.SegmentNodes.AbstractSegmentNode;
import java.util.LinkedList;

public class ParseTree {
    public LinkedList<AbstractSegmentNode> listNodes;

    public ParseTree() {
        this.listNodes = new LinkedList<>();
    }
    public void add(AbstractSegmentNode node){
        listNodes.add(node);
    }
    public int size(){
        return listNodes.size();
    }

    public LinkedList<AbstractSegmentNode> getListNodes() {
        return listNodes;
    }

    public void setListNodes(LinkedList<AbstractSegmentNode> listNodes) {
        this.listNodes = listNodes;
    }

    public AbstractSegmentNode get(int i){
        return listNodes.get(i);
    }

}