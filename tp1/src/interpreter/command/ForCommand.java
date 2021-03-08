package interpreter.command;

import interpreter.expr.Expr;
import interpreter.expr.Variable;

public class ForCommand extends Command {
    private Variable var;
    private Expr src;
    private Expr dst;
    private Command cmd;

    public ForCommand(int line, Variable var, Expr src, Expr dst, Command cmd) {
        super(line);
        this.var = var;
        this.src = src;
        this.dst = dst;
        this.cmd = cmd;
    }

    @Override
    public void execute() {        
        // initial value
        this.var.setValue(this.src.expr()); 

        // // while var != dst
        // while( this.var.expr().value().equals(this.dst.expr().value()) == false) {
        //     // executes command
        //     this.cmd.execute();            
        // }

        while(this.var.getValue().value() != this.dst.expr().value()){
            this.cmd.execute();
        }
    }
}