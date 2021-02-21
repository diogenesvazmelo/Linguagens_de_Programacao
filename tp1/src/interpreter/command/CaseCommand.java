package interpreter.command;

import interpreter.expr.Expr;
import interpreter.value.Value;
import java.util.ArrayList;

public class CaseCommand extends Command{
    private Expr expr;
    private ArrayList<CaseOption> options;
    private Command otherwise;

    public CaseCommand(int line, Expr expr){
        super(line);
        this.expr = expr;
    }

    public void addOption(Value value, Command cmd){
        CaseOption c1 = new CaseOption(value, cmd);
        this.options.add(c1);
    }

    public void setOtherwise(Command cmd){
        this.otherwise = cmd;
    }

}