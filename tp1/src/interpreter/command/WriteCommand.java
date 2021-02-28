package interpreter.command;

import java.util.ArrayList;
import interpreter.expr.Expr;
import interpreter.util.Memory;

public class WriteCommand extends Command{
    private boolean writeln;
    private ArrayList<Expr> exprs;

    public WriteCommand(int line){
        super(line);
        this.writeln = false;
        this.exprs = new ArrayList<Expr>();
    }

    public WriteCommand(int line, boolean writeln){
        super(line);
        this.writeln = writeln;
        this.exprs = new ArrayList<Expr>();
    }

    public void addExpr(Expr expr){
        this.exprs.add(expr);
    }

    @Override
    public void execute(){           
        // Memory.writeAllVariables();
        for(Expr expr : this.exprs){               
            System.out.print(expr.expr().value());
        }
        if(this.writeln)
            System.out.print("\n");
        
    }
}