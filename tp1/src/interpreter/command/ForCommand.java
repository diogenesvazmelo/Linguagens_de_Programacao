package interpreter.command;

import interpreter.expr.Expr;
import org.graalvm.compiler.lir.Variable;

public class ForCommand extends Command {

    private Variable var;
    private Expr src;
    private Expr dst;
    private Command cmd;

    public ForCommand(int line, Variable var, Expr src, Expr dst, Command cmd) {
        super(line);
        this.var = var;
        this.src = src;
        this.cmd = cmd;
    }

    public void execute() {
        for (int i = this.src; i < this.dst; i++){
            cmd.execute();
        }
    }

}