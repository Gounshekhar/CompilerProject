public class minusExpression extends Expression{
    private Expression left;
    private Expression right;
    public minusExpression(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }
    public String toStringPrec(int val) {
        return "(" + left.toString() + "-" + right.toString() + ")";
    }
}
