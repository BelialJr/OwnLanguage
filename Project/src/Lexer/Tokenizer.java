package Lexer;


import Exception.CustomException;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {                          //Will get string and parser int into Tokens
    private ArrayList<TokenInfo> tokenInfosList; //Is used to keep  info about default Tokens
    private ArrayList<Token> tokensList;         //Is used to to keep all current tokens


    public Tokenizer() {
        this.tokenInfosList = new ArrayList<>();
        this.tokensList = new ArrayList<>();
        fillOUT();
    }

    private void add(String regex, LexerTYPE type){
        this.tokenInfosList.add(new TokenInfo(Pattern.compile("^("+regex+")"),type)); // regex ^() to match first line regex
    }

    private void fillOUT(){
        this.add("number(\\[\\])+|char(\\[\\])+|bool(\\[\\])+", LexerTYPE.ARR_VAR);
        this.add("number|char|bool", LexerTYPE.VARIABLE);
        this.add("void", LexerTYPE.VOID);
        this.add("true|false", LexerTYPE.TRUE_FALSE);
        this.add("for|while", LexerTYPE.LOOP);
        this.add(">|<|==|>=|<=", LexerTYPE.MORE_LESS_EQUAL);
        this.add("&&|\\|\\|", LexerTYPE.ADN_OR);
        this.add("if|else", LexerTYPE.CONDTION);
        this.add("function::", LexerTYPE.FUNCTION);
        this.add("global", LexerTYPE.VISIB_MODIF);
        this.add("return", LexerTYPE.RETURN);
        this.add("\\,", LexerTYPE.COMMA);
        this.add("\\++|--", LexerTYPE.INCR_DECR);
        this.add("\\+|-", LexerTYPE.SUM_SUB);
        this.add("\\*|/", LexerTYPE.MUL_DIV);
        this.add("\\(", LexerTYPE.OPEN_PAREN);
        this.add("\\)", LexerTYPE.CLOSE_PAREN);
        this.add("\\{", LexerTYPE.OPEN_BRACKET);
        this.add("\\}", LexerTYPE.CLOSE_BRACKET);
        this.add("\\;", LexerTYPE.SEMICOLON);
        this.add("\\=", LexerTYPE.EQUAL);
        this.add("[0-9]+", LexerTYPE.DIGITAL);
        this.add("'.{1}'", LexerTYPE.CHAR);;
        this.add("[a-zA-Z][a-zA-Z0-9_]*(\\[\\d*\\])+", LexerTYPE.ARR_NAME);
        this.add("[a-zA-Z][a-zA-Z0-9_]*\\([^\\(\\)\\{\\}]+\\){1}", LexerTYPE.FUNCT_NAME);
        this.add("[a-zA-Z][a-zA-Z0-9_]*", LexerTYPE.VAR_NAME);
    }
    public void tokenize(String string){
        string = string.trim();
        tokensList.clear();

        while(!string.equals("")){

            boolean matchREsult = false;

            for (TokenInfo tokenInfo : tokenInfosList){
                Matcher matcher = tokenInfo.regex.matcher(string);
                if(matcher.find()){
                    matchREsult = true;
                    String tokenName = matcher.group().trim();
                    string = matcher.replaceFirst("").trim();
                    tokensList.add(new Token(tokenName,tokenInfo.tokenID,tokenInfo.type));
                    break;
                }
            }
            if(!matchREsult){
                throw new CustomException("LEXER EXCEPTION: Unexpected character in input: ' " + string + " '");
        }
        }

    }
    public ArrayList<Token> getTokens()
    {
        return tokensList;
    }

    public void printTokens() {
        int index = 0;
        System.out.println("------------------------[Tokezier]------------------------");
        for(Token token : tokensList){
            String indexStr = String.format("%-3s", String.valueOf(index++));
            String tokeID    =  String.format("%-2s", String.valueOf(token.getTokenID()));
            String tokenType = String.format("%-15s", token.getType().name());
            String tokenSymbol = String.format("%-15s", token.getStr());
            System.out.println("INDEX: ["+indexStr+"] |    "+"ID: ["+ tokeID +"]" + " |    TYPE: " + tokenType + " |  Symbol: " + tokenSymbol);
        }

    }


    private static class TokenInfo{
        private final Pattern regex;
        public final LexerTYPE type;
        public static int tokenCounter = 0;
        public final int  tokenID = ++tokenCounter;

        public TokenInfo(Pattern regex, LexerTYPE type) {
            this.regex = regex;
            this.type = type;
        }
    }
    public static  ArrayList<Token> getFunctionArgumentsTokens(Token funct_name){
        Tokenizer tokenizer = new Tokenizer();
        String arguments = funct_name.getStr().substring(funct_name.getStr().indexOf("(")+1,funct_name.getStr().indexOf(")"));
        tokenizer.tokenize(arguments);
        return tokenizer.getTokens();
    }
}
