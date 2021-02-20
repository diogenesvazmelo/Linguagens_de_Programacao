package interpreter.command;

import interpreter.expr.BoolExpr;

public class IfCommand extends Command{
    
    private BoolExpr cond;
    private Command thenCmds;
    private Command elseCmds;

    public IfCommand(int line, BoolExpr cond, Command thenCmds){
        super(line);
        this.thenCmds = thenCmds;
        this.cond = cond;
    }

    public setElseCommands(Command elseCmds){
        this.elseCmds = elseCmds;
    }

    public void execute(){
        if (cond.expr())
            thenCmds.execute();
        else {
            if (elseCmds != null)
                elseCmds.execute();
        }
    }

}