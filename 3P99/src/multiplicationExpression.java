public class multiplicationExpression extends Expression{
    private Expression left;
    private Expression right;
    public multiplicationExpression(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }
    @Override
    public String toStringPrec(int val) {
        return "(" + left.toString() + "*" + right.toString() + ")";
    }
}
