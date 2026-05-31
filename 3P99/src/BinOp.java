public enum BinOp { ADD("+",10), MULT("*",20),MINUS("-",10);
private String symbol;
private int precedence;
 BinOp(String symbol, int precedence){
        this.symbol = symbol;
        this.precedence = precedence;
    }
    public String getSymbol(){
     return symbol;
    }
    public int getPrecedence(){
     return precedence;
    }
}
