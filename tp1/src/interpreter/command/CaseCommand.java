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

    public void addOption(Value<?> value, Command cmd){
        this.options.add(new CaseOption(value, cmd));
    }

    public void setOtherwise(Command cmd){
        this.otherwise = cmd;
    }

    @Override
    public void execute() {

        boolean gotInsideCase = false;

        // checks all the cases inside the switch
        for (CaseOption caseOpt : this.options){
            if(this.expr.expr().equals(caseOpt.value)){
                caseOpt.cmd.execute();
                gotInsideCase = true;
                break;
            }
        }

        // default option
        if(!gotInsideCase){
            this.otherwise.execute();
        }
    }
}