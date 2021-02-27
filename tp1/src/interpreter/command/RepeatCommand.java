package interpreter.command;

import interpreter.expr.BoolExpr;
import interpreter.util.Memory;

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
        System.out.println(Memory.read("valid").value().toString());
        while(this.cond.expr()){
            System.out.println("Repeat");
            for(Command cmd : this.cmds){
                cmd.execute();
            }
        }        
    }

}
