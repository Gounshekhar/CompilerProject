public class ExprBinOp extends Expression{
    private Expression left;
    private Expression right;
    private BinOp binop;

    public ExprBinOp(Expression left, Expression right, BinOp binop){
        this.left = left;
        this.right = right;
        this.binop = binop;
    }
    public String toStringPrec(int val) {
        String result = left.toStringPrec(binop.getPrecedence())+ binop.getSymbol()+right.toStringPrec(binop.getPrecedence());
        if (val > binop.getPrecedence()){
            return "("+result+")";
        }
        return result;
    }
}
