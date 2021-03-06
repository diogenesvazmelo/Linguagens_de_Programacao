package interpreter.command;

import interpreter.expr.Variable;
import interpreter.expr.Expr;

public class AssignCommand extends Command{
    private Variable left;
    private Expr right;

    public AssignCommand(int line, Variable left, Expr right){
        super(line);
        this.left = left;
        this.right = right;
    }

    @Override
    public void execute(){        
        this.left.setValue(this.right.expr());        
    }
}