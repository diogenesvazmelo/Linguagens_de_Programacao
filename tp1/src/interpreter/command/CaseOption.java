package interpreter.command;

import interpreter.expr.Expr;
import interpreter.value.Value;
import java.util.ArrayList;

public class CaseOption extends CaseCommand{
    private Value value;
    private Command cmd;

    public CaseOption(int line, Expr expr, Value value, Command cmd){
        super(line);
        super(expr);
        this.value = value;
        this.cmd = cmd;
    }
}