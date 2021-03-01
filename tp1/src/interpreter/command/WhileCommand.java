package interpreter.command;

import interpreter.expr.BoolExpr;

public class WhileCommand extends Command {

    private BoolExpr cond;
    private Command cmd;

    public WhileCommand(int line, BoolExpr cond, Command cmd) {
        super(line);
        this.cond = cond;
        this.cmd = cmd;
    }

    @Override
    public void execute() {
        while (this.cond.expr())
            this.cmd.execute();
    }

}