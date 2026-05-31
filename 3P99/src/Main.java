public class Main {

    public static void main(String[] args) {

        Expression tree =new ExprBinOp(
                new ExprBinOp(new ExprIntConst(3),new ExprIntConst(4),BinOp.ADD),
                new ExprIntConst(5),
                BinOp.MULT
        );
        System.out.println(tree);
    }
}
