import Lexer.Tokenizer;
import Parser.Parser;

public class main {
    public static void main(String[] args) {
        Tokenizer tokenizer = new Tokenizer();
        String string = "function::number[][]  main(number a , number b[][]){" +
                "   number n1 = 0;" +
                "   bool b1 = true;" +
                "   " +
                "    if(b1 == true)" +
                "    {" +
                "        n1 = 5;" +
                "    }"+
                "    else" +
                "    {" +
                "        n1 = 10;" +
                "    }" +
                "return a ;"+
                "}";
         String string2 = "function::number main(number a , number b  ){" +
                 "   number n1 = 0;\n" +
                 "   bool b1[] = true;\n" +
                 "\n" +
                 "    if(b1 == true)\n" +
                 "    {\n" +
                 "     if(true){\n" +
                 "            number a = 2;\n" +
                 "         }else{\n" +
                 "         number a1 = 2;\n" +
                 "         }\n" +
                 "    }\n" +
                 "     \n" +
                 "    else\n" +
                 "    {\n" +
                 "        n1 = 10;\n" +
                 "    }\n" +
                 "return a;"+
                 "}";
        String string3 = "function::number main(number a2 , number a3[]){" +
                "for(int i = 0; i< 5;i++){" +
                "number a = 4;" +
                "}" +
                "return a;"+
                "}";


       // tokenizer.tokenize("function::number[][][] toTest(number a,number a1[][]);");
        //string + " "  + string2 + " " +
        tokenizer.tokenize( string + " "  + string2 + " " + string3);
        tokenizer.printTokens();
        Parser parser = new Parser(tokenizer.getTokens());




    }


}



