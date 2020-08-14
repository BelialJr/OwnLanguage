package Nodes.LineNodes;

import Lexer.Token;

public abstract class  AbstractLineNode {
    private AbstractLineNode leftChildren;
    private AbstractLineNode rightChildren;
    private Token root;
    private Token leftToken;
    private Token rightToken;


    public AbstractLineNode(Token token) {
        this.root = token;
    }

    public AbstractLineNode() {

    }

    public AbstractLineNode(AbstractLineNode leftChildren, AbstractLineNode rightChildren) {
        this.leftChildren = leftChildren;
        this.rightChildren = rightChildren;
    }


    public AbstractLineNode(AbstractLineNode leftChildren, AbstractLineNode rightChildren, Token root) {
        this.leftChildren = leftChildren;
        this.rightChildren = rightChildren;
        this.root = root;
    }

    public AbstractLineNode getLeftChildren() {
        return leftChildren;
    }

    public void setLeftChildren(AbstractLineNode leftChildren) {
        this.leftChildren = leftChildren;
    }

    public AbstractLineNode getRightChildren() {
        return rightChildren;
    }

    public void setRightChildren(AbstractLineNode rightChildren) {
        this.rightChildren = rightChildren;
    }

    public Token getRoot() {
        return root;
    }

    public void setRoot(Token root) {
        this.root = root;
    }

    public Token getLeftToken() {
        return leftToken;
    }

    public void setLeftToken(Token leftToken) {
        this.leftToken = leftToken;
    }

    public Token getRightToken() {
        return rightToken;
    }

    public void setRightToken(Token rightToken) {
        this.rightToken = rightToken;
    }
}