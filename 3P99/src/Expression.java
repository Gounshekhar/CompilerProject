import java.util.List;
import org.jparsec.OperatorTable;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.Scanners;
import org.jparsec.Terminals;

public abstract class Expression{
    public String toString(){
        return toStringPrec(0);
    }
    public abstract String toStringPrec(int val);
}