package Lexer;

public  class Token{
    private final String str;
    private   final int  tokenID;
    private final LexerTYPE type;


    public Token(String str, int tokenID, LexerTYPE type) {
        this.str = str;
        this.tokenID = tokenID;
        this.type = type;
    }

    public String getStr() {
        return str;
    }

    public int getTokenID() {
        return tokenID;
    }

    public LexerTYPE getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Token{" +
                "str='" + str + '\'' +
                ", tokenID=" + tokenID +
                ", type=" + type +
                '}';
    }
}