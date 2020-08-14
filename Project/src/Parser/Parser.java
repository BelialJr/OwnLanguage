package Parser;


import Checker.FunctionImplementationCheker;
import Exception.CustomException;
import Interpreter.Interpreter;
import Lexer.LexerTYPE;
import Lexer.Token;
import Nodes.SegmentNodes.AbstractSegmentNode;


import java.util.ArrayList;
import java.util.List;


public class Parser {                                                 //          funct                 funct
    private ArrayList<ArrayList<ArrayList<Token>>> separatedTokens3D; // [ [[tokens],[tokens]], [[tokens],[tokens]],  ]
    private ParseTree parseTree;

    public Parser(ArrayList<Token> tokens) {
       this.separatedTokens3D = getSeparatedTokens3D(tokens);
    //   this.parseTree =  generateParseTreeFrom3DList();
       printSeparatedTokens3D();
    }

    private ParseTree generateParseTreeFrom3DList() {
        ParseTree parseTree = new ParseTree();
        Interpreter interpreter;

        for(ArrayList<ArrayList<Token>> functTokens : separatedTokens3D)
        {
            interpreter = Interpreter.getInterpreter(functTokens);
            AbstractSegmentNode node = interpreter.getRootNode();
            parseTree.add(node);
        }
        return parseTree;
    }

    private ArrayList<ArrayList<ArrayList<Token>>> getSeparatedTokens3D(ArrayList<Token> tokens) {
        ArrayList<ArrayList<ArrayList<Token>>> result = new ArrayList<>();

        try{
         for (int i = 0; i < tokens.size() ; i++) {
            ArrayList<ArrayList<Token>> function = new ArrayList<>();       // [ [] , [] , [] ] Functions with lines


            if (tokens.get(i).getType() == LexerTYPE.FUNCTION){
                    int startIndex = i;
                    int lastIndex = FunctionImplementationCheker.getTheLastIndex(startIndex, tokens);         // get last index of funct impl ')'
                    FunctionImplementationCheker.checkImplementation(startIndex, lastIndex, tokens);         // start from 'funct' and end in ')'
                    function.add(new ArrayList<Token>(tokens.subList(startIndex, lastIndex + 1)));         // add fucntionImpelemantation (  function::void toTest()   )
                    i = lastIndex + 1;                                                                     // index = ) +1 pos

                    if (tokens.get(i).getType() == LexerTYPE.SEMICOLON)
                    {
                        function.get(function.size()-1).add(tokens.get(i));          // funct();
                    }
                    else if (tokens.get(i).getType() == LexerTYPE.OPEN_BRACKET)
                    {
                        ArrayList<ArrayList<Token>> lines = parseLines(tokens,i);    // funct() { . .. . . }
                        int  indexShift = 0;

                        for(int wrt = 0;wrt<lines.size();wrt++)
                        {
                           indexShift+=lines.get(wrt).size();
                        }

                        i += indexShift-1;
                        function.addAll(lines);
                        FunctionImplementationCheker.checkReturnStatement(function);
                    }
                    else
                    {
                        throw new CustomException("PARSER EXCEPTION: WRONG Function implemenatation ");
                    }

                     result.add(function);
                     continue;
                }
                if ((tokens.get(i).getType() == LexerTYPE.VISIB_MODIF && tokens.get(i).getStr().equals("global"))) { // should be posibility to create or reInitialized global variable
                    continue;
                }
                else
                {
                     throw new CustomException("PARSER EXCEPTION: Unrecognizable input: Can not  parse : " + tokens.get(i).getStr() + " : Can not exist outside the function");
                }
            }
        }catch (IndexOutOfBoundsException e){}
        return result;
    }


    private ArrayList< ArrayList<Token>> parseLines(ArrayList<Token> leftLines, int startIndex) { // parse all lines in funct : starts from {
        ArrayList<ArrayList<Token>> result  = new ArrayList<>();
        result.add(new ArrayList<>(List.of(leftLines.get(startIndex))));                        //add { to list
        int closeBracketExpected = 1;

            try {
                ArrayList<Token> line ;

                for (int i = startIndex+1; closeBracketExpected > 0; )
                {
                    line = new ArrayList<>();

                    if (leftLines.get(i).getType() == LexerTYPE.OPEN_BRACKET) {
                        closeBracketExpected++;
                        line.add(leftLines.get(i));
                        i++;
                    } else

                    if (leftLines.get(i).getType() == LexerTYPE.CLOSE_BRACKET) {
                        closeBracketExpected--;
                        line.add(leftLines.get(i));
                        i++;
                    } else

                    if (leftLines.get(i).getType() == LexerTYPE.CONDTION || leftLines.get(i).getType() == LexerTYPE.LOOP) {
                        while(leftLines.get(i).getType() != LexerTYPE.OPEN_BRACKET){
                            line.add(leftLines.get(i));
                            i++;
                        }

                    }else {
                        while(leftLines.get(i).getType() != LexerTYPE.SEMICOLON){
                            line.add(leftLines.get(i));
                            i++;
                        }
                        line.add(leftLines.get(i));
                        i++;
                    }
                    result.add(line);
                }
            }catch (IndexOutOfBoundsException ignore){}

        return result;
    }


    public void printSeparatedTokens3D(){
        String topic = "";
        System.out.println("\n---------------------[Parser]---------------------\n");
        for (int i = 0; i < this.separatedTokens3D.size() ; i++) {
            topic = this.separatedTokens3D.get(0).get(0).get(0).getStr();
            System.out.println("------------------------- ["+topic.toUpperCase().replace("::","")+"] -------------------------");
            for (int j = 0; j < this.separatedTokens3D.get(i).size(); j++) {
                if(j!=0) System.out.println("---------------------");
                for (int k = 0; k <this.separatedTokens3D.get(i).get(j).size() ; k++) {
                    String tokeID = String.format("%-2s", String.valueOf(this.separatedTokens3D.get(i).get(j).get(k).getTokenID()));
                    String tokenType = String.format("%-15s", this.separatedTokens3D.get(i).get(j).get(k).getType());
                    String tokenSymbol = String.format("%-15s", this.separatedTokens3D.get(i).get(j).get(k).getStr());
                    System.out.println("ID: [" + tokeID + "]" + " |    TYPE: " + tokenType + " |  Symbol: " + tokenSymbol);
                }
            }
            System.out.println();
        }
    }

}

