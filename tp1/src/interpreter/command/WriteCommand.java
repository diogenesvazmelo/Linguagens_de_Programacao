package interpreter.command;

import java.util.ArrayList;
import interpreter.expr.Expr;

import java.util.Scanner;

import org.graalvm.compiler.lir.Variable;
public class WriteCommand extends Command{
    
    private ArrayList<Expr> exprs;
    private bool writeln;

    public WriteCommand(int line){
        super(line);
        this.writeln = false;
    }

    public WriteCommand(int line, bool writeln){
        super(line);
        this.writeln = writeln;
    }

    public addExpr(Expr expr){
        this.exprs.add(expr);
    }

    public void execute(){
        while(this.writeln){
            for(Expr expr : this.exprs){
                System.out.println(expr);
            }
        }
    }

}