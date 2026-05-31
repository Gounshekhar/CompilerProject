import java.util.function.BinaryOperator;

public enum BinOp2 {
    ADD("+",10){
        public BinaryOperator<Value> operation(){
            return (v1, v2) -> {
                Value result = null;
                if (v1 instanceof ValueInt v1Int && v2 instanceof ValueInt v2Int){
                    result = new ValueInt(v1Int.getValue() + v2Int.getValue());
                }
                return result;
            };

        }
    }
}
