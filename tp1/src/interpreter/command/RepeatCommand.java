package interpreter.command;

import interpreter.expr.BoolExpr;

import java.util.*;

public class RepeatCommand extends Command{
    private BoolExpr cond;
    private ArrayList<Command> cmds;

    public RepeatCommand(int line, ArrayList<Command> cmds, BoolExpr cond ){
        super(line);
        this.cond = cond;
        this.cmds = cmds;
    }

    @Override
    public void execute(){
        while(cond.expr()){
            for(Command cmd : this.cmds){
                cmd.execute();
            }
        }        
    }

}
