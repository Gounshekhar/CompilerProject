public class ExprBooleanConst extends Expression{
    private boolean value;
    public ExprBooleanConst(boolean value){
        this.value = value;
    }
    public String toStringPrec(int val) {
        return String.valueOf(value);
    }
}
