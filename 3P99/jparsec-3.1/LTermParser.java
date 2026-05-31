package fcompiler.program;

import static fcompiler.program.ArithOperations.*;
import java.util.List;
import org.jparsec.OperatorTable;
import org.jparsec.Parser;
import org.jparsec.Parsers;
import org.jparsec.Scanners;
import org.jparsec.Terminals;

/**
 *
 * @author Michael Winter
 */
public class LTermParser {
    
    private static final String[] SYMBOLS = { "(", ")", ".", "\u03BB", "*", "+" };

    private static final Terminals progOperators = Terminals.operators(SYMBOLS);
	 
    private static LTermParser parser;

    public static LTermParser getLTermParser() {
        if (parser == null) {
            parser = new LTermParser();
        }
        return parser;
    }
	
    private LTermParser() {
    }

    public Parser<LTerm> getParser(Terminals operators) {
        Parser.Reference<LTerm> ref = org.jparsec.Parser.newReference();
        Parser<LTerm> term = 
                ref.lazy().between(operators.token("("), operators.token(")"))
                .or(Terminals.Identifier.PARSER.map(LTermVariable::new))
                .or(Terminals.IntegerLiteral.PARSER.map(s -> new LTermIntConstant(Integer.parseInt(s))))
                .or(Parsers.sequence(operators.token("\u03BB"),Terminals.Identifier.PARSER,operators.token("."),ref.lazy(),(s1,s2,s3,t) -> new LTermAbstraction(s2,t)))
                .or(Parsers.sequence(operators.token("("),operators.token("+").or(operators.token("*")),operators.token(")"),(s1,op,s2) -> new LTermArithOperation(ArithOperations.fromSymbol(op.toString()))))
                .or(Parsers.sequence(operators.token("("),ref.lazy(),operators.token("+").or(operators.token("*")),operators.token(")"),(s1,t,op,s2) -> new LTermApplication(new LTermArithOperation(ArithOperations.fromSymbol(op.toString())),t)))
                .or(Parsers.sequence(operators.token("("),operators.token("+").or(operators.token("*")),ref.lazy(),operators.token(")"),(s1,op,t,s2) -> new LTermApplication(new LTermArithOperation(ArithOperations.fromSymbol(op.toString())),t)));
        Parser<LTerm> typeTerm = term.many1().map(l -> makeApplications(l));
        Parser<LTerm> opParser = new OperatorTable<LTerm>()
                .infixl(operators.token("+").retn((l,r) -> new LTermApplication(new LTermApplication(new LTermArithOperation(ADD),l),r)),ADD.getPrecedence())
                .infixl(operators.token("*").retn((l,r) -> new LTermApplication(new LTermApplication(new LTermArithOperation(MULT),l),r)),MULT.getPrecedence())
            .build(typeTerm);
        ref.set(opParser);
        return opParser;
    }

    public LTerm parse(CharSequence source) {
        return getParser(progOperators)
        		.from(progOperators.tokenizer().cast().or(
                        Terminals.Identifier.TOKENIZER)
                        .or(Terminals.IntegerLiteral.TOKENIZER),
                         Scanners.WHITESPACES.skipMany())
                        .parse(source);
    }
    
    private LTerm makeApplications(List<LTerm> l) {
        LTerm result = l.get(0);
        for(int i=1; i<l.size(); i++) {
            result = new LTermApplication (result,l.get(i));
        }
        return result;
    }
    
}
