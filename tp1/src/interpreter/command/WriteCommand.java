package interpreter.command;

import java.util.ArrayList;
import interpreter.expr.Expr;

public class WriteCommand extends Command{
    private boolean writeln;
    private ArrayList<Expr> exprs;

    public WriteCommand(int line){
        super(line);
        this.writeln = false;
        exprs = new ArrayList<Expr>();
    }

    public WriteCommand(int line, boolean writeln){
        super(line);
        this.writeln = writeln;
    }

    public void addExpr(Expr expr){
        this.exprs.add(expr);
    }

    @Override
    public void execute(){
        while(this.writeln){
            for(Expr expr : this.exprs){
                System.out.print(expr.expr().toString() + " ");
            }
            if(this.writeln)
                System.out.print("\n");
        }
    }

}