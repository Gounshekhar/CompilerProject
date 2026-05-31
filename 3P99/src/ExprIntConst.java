public class ExprIntConst extends Expression{
    private int value;
    public ExprIntConst(int value){
        this.value = value;
    }
    public String toStringPrec(int val) {
        return String.valueOf(value);
    }
}
