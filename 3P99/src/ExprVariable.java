public class ExprVariable extends Expression{
    private String name;
    public ExprVariable (String name) {
        this.name = name;
    }
    public String toStringPrec(int val) {
        return name;
    }
}
