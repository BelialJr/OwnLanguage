package Interpreter;

import Nodes.LineNodes.*;
import Checker.ConditionImplementationChecker;
import Checker.FunctionImplementationCheker;
import Checker.InitializationImplChecker;
import Checker.LoopImplementationChecker;

import Lexer.LexerTYPE;
import Lexer.Token;
import Exception.CustomException;
import Nodes.SegmentNodes.FunctionSegmnentNode;
import Nodes.SegmentNodes.AbstractSegmentNode;

import java.util.ArrayList;

public class Interpreter {
    private AbstractSegmentNode root;
    private ArrayList<ArrayList<Token>> allTokens;
    private ArrayList<Token> tokensLine;
    private ArrayList<AbstractLineNode> allNodes ;

    public Interpreter(ArrayList<ArrayList<Token>> tokens) {
        allTokens = tokens;
        root = getSegmentNodeType() ;
        allNodes = getAllNodes();
        root.setNodesList(allNodes);
    }

    private ArrayList<AbstractLineNode> getAllNodes() {
        ArrayList<AbstractLineNode> allNodes = new ArrayList<>();
            while (!allTokens.isEmpty()) {
                AbstractLineNode node = buildNode();
                allNodes.add(node);
            }
        return allNodes;
    }

    private AbstractLineNode buildNode() {
        this.tokensLine = allTokens.get(0); // remove
        AbstractLineNode newNode = getLineNodeType();
        newNode = buildTree(newNode);
        return newNode;

    }

    private AbstractLineNode buildTree(AbstractLineNode node) {

        if (node instanceof InitializationNode)
        {
            InitializationImplChecker.checkImplementation(this.tokensLine);
            int caseInex = InitializationImplChecker.getCaseindex();

            switch(caseInex)
            {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default : Exception("");
            }
        }
        if (node instanceof FunctionLineNode)
        {
            FunctionImplementationCheker.checkImplementation(0,this.tokensLine.size()-1,this.tokensLine);
         //   this.allTokens.remove()
            return new FunctionLineNode();
        }
        if (node instanceof ConditionNode)
        {
            ConditionImplementationChecker.checkImplementation();
            ConditionNode conditionNode =  new ConditionNode();

            return conditionNode;
        }
        if (node instanceof LoopNode)
        {
            LoopImplementationChecker.checkImplementation();
            LoopNode loopNode = new LoopNode();

            return loopNode;
        }
        if (node instanceof ReturnNode)
        {
            LoopImplementationChecker.checkImplementation();
            LoopNode loopNode = new LoopNode();

            return loopNode;
        }
        throw  new CustomException("PARSER EXCEPTION: Unrecognizable input: " + tokensLine.get(0).getStr());
    }

    private AbstractLineNode getLineNodeType(){
        if(tokensLine.get(0).getType() == LexerTYPE.VARIABLE && ( tokensLine.get(1).getType() == LexerTYPE.VAR_NAME || tokensLine.get(1).getType() == LexerTYPE.ARR_NAME))
            return new InitializationNode();

        if(tokensLine.get(0).getType() == LexerTYPE.VAR_NAME || tokensLine.get(0).getType() == LexerTYPE.ARR_NAME)
            return new InitializationNode();

        if(tokensLine.get(0).getType() == LexerTYPE.FUNCTION)
            return new FunctionLineNode();

        if(tokensLine.get(0).getType() == LexerTYPE.LOOP)
            return new LoopNode();

        if(tokensLine.get(0).getType() == LexerTYPE.CONDTION)
            return new ConditionNode();

        if(tokensLine.get(0).getType() == LexerTYPE.RETURN)
            return new ReturnNode();

        throw  new CustomException("PARSER EXCEPTION: Unrecognizable input: " + tokensLine.get(0).getStr());
    }



    private AbstractSegmentNode getSegmentNodeType()
    {
        if(allTokens.get(0).get(0).getType() == LexerTYPE.FUNCTION)
        {
            return new FunctionSegmnentNode();
        }
        return null;
    }


    public static Interpreter getInterpreter(ArrayList<ArrayList<Token>> tokens){
        return new Interpreter(tokens);
    }

    public AbstractSegmentNode getRootNode() {
        return root;
    }

    private void Exception(String str){
        throw new CustomException("INTERPRETER EXCEPTION: Unrecognizable input: " + str);
    }
    public static void printInterpreter(){
        System.out.println("---------------------[Interpreter]---------------------");
    }
}
