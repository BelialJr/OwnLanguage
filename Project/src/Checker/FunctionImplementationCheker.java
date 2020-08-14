package Checker;

import Exception.CustomException;
import Lexer.LexerTYPE;
import Lexer.Token;
import Lexer.Tokenizer;

import java.util.ArrayList;
import java.util.List;

public class FunctionImplementationCheker {

    public static  int getTheLastIndex(int startIndex, ArrayList<Token> tokens) {
        //   return ) index and check arguments
        int lastIndex  =  startIndex ;
        try {
            while (tokens.get(lastIndex).getType() != LexerTYPE.FUNCT_NAME){
                lastIndex++;
            }
        }catch (Exception e){
            throw new CustomException("PARSER EXCEPTION: Wrong function initialization ");
        }
        return lastIndex;
    }

    public static  void checkImplementation(int startIndex,int lastIndex, ArrayList<Token> tokens) {
        StringBuilder log = new StringBuilder();
        for (int i = startIndex; i < lastIndex + 1; i++) {
            log.append(tokens.get(i).getStr() + " ");
        }
        try {
            int start = startIndex;
            if (    tokens.get(start + 0).getType() == LexerTYPE.FUNCTION &&
                   (tokens.get(start + 1).getType() == LexerTYPE.ARR_VAR || tokens.get(start + 1).getType() == LexerTYPE.VARIABLE || tokens.get(start + 1).getType() == LexerTYPE.VOID) &&
                    tokens.get(start + 2).getType() == LexerTYPE.FUNCT_NAME )
            {
                    checkArgsImplementation(tokens.get(start + 2));

            }
            else
            {
                throw new CustomException("PARSER EXCEPTION: Wrong function pattern.Should be function::variable fucntName(var varName).Recieved " + log.toString());
            }
        } catch(IndexOutOfBoundsException e){
            throw new CustomException("PARSER EXCEPTION: Wrong function pattern.Should be function::variable fucntName(var varName).Recieved " + log.toString());
        }
    }

    public static  void checkArgsImplementation(Token funct_name) {
        ArrayList<Token> arguments = Tokenizer.getFunctionArgumentsTokens(funct_name);
        StringBuilder logs = new StringBuilder();

        for (Token token : arguments) {
            logs.append(token.getStr() + " ");
        }

        List<List<Token>> separetedArgumets = new ArrayList<>();
        int commaCount = 0;
        int index = 0;
        try {
            for (int i = 0; i < arguments.size(); ) {

            separetedArgumets.add(new ArrayList<>());

                if (arguments.get(i).getType() == LexerTYPE.COMMA) {
                    i++;
                    commaCount++;
                }

                for (int f = 0 ; f < 2 ; f++) {
                    separetedArgumets.get(index).add( arguments.get(i++) );
                }
                index++;
            }

            if(separetedArgumets.size()-1 != commaCount)
                throw new IndexOutOfBoundsException();

            for (List<Token> argumets : separetedArgumets) {
                if (!(argumets.get(0).getType() == LexerTYPE.VARIABLE && (argumets.get(1).getType() == LexerTYPE.VAR_NAME || argumets.get(1).getType() == LexerTYPE.ARR_NAME)))
                    throw new IndexOutOfBoundsException();
            }
        }catch (IndexOutOfBoundsException e )
        {
            throw new CustomException("PARSER EXCEPTION: Can not parse function arguments : " + logs.toString());
        }
    }


   /* public static  void checkArgsImplementation(Token funct_name) {
        ArrayList<Token> arguments = Tokenizer.getFunctionArgumentsTokens(funct_name);
        StringBuilder logs = new StringBuilder();

        for(Token token : arguments) {
            logs.append(token.getStr()+ " ");
        }


        //if type that cant be in function arguments
        if(!(arguments.stream().anyMatch(token-> (token.getType()!= LexerTYPE.ARR_NAME && token.getType()!= LexerTYPE.VARIABLE &&  token.getType()!= LexerTYPE.VAR_NAME && token.getType()!= LexerTYPE.COMMA)))) {

            List<List<Token>> separetedArgumets = new ArrayList<>();
            int commaCount = 0;
            int index = -1;
            try {
                for (int i = 0; i < arguments.size(); i++) {
                    index++;
                    separetedArgumets.add(new ArrayList<>());
                    while (true) {
                        if (arguments.get(i).getType() != LexerTYPE.COMMA) {
                            separetedArgumets.get(index).add(arguments.get(i));
                            i++;
                        } else {
                            commaCount++;
                            break;
                        }
                    }
                }
            }catch (IndexOutOfBoundsException ignore) {}


            if( (!(commaCount == separetedArgumets.size()-1))){
                if( (!(commaCount==0 && separetedArgumets.size()== 0))) {
                    throw new CustomException("PARSER EXCEPTION: Can not parse function arguments : " + logs.toString());
                }
            }


            //check arguments pattern
            for (List<Token> argumets : separetedArgumets) {
                if (!(argumets.get(0).getType() == LexerTYPE.VARIABLE && (argumets.get(1).getType() == LexerTYPE.VAR_NAME || argumets.get(1).getType() == LexerTYPE.ARR_NAME)))
                    throw new CustomException("PARSER EXCEPTION: Can not parse function arguments : " + logs.toString());
            }

        }
        else
        {
            throw new CustomException("PARSER EXCEPTION: Can not parse function arguments : "+logs.toString());
        }
    }*/

    public static void checkReturnStatement(ArrayList<ArrayList<Token>> function){
            if (function.get(0).get(1).getType() != LexerTYPE.VOID) {
                    if(function.get(function.size()-2).get(0).getType() != LexerTYPE.RETURN){
                        throw new CustomException("PARSER EXCEPTION: Wrong function pattern.Should contains return statement");
                    }
            }
    }
}
