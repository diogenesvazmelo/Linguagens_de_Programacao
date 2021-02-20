package interpreter.expr;

import interpreter.expr.*;
import interpreter.value.*;

public class BinaryExpr extends Expr{
    private Expr left;
    private Expr right;
    private BinaryOp op;

    public BinaryExpr(int line, Expr left, BinaryOp op, Expr right){
        super(line);
        this.left = left;
        this.right = right;
        this.op = op;
    }


    // TODO - see if it's a good idea to do a throw when comparing INT and FLOAT
    public Value<?> expr(){
        switch(op){
            case AddOp:
                if (
                   (this.left.expr().value() instanceof String) &&
                   (this.right.expr().value() instanceof String)
                ) {
                    return this.left.expr().value().toString() + this.right.expr().value().toString();
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){

                }else{
                    
                }
            case SubOp:
                if (
                    (this.left.expr().value() instanceof String) &&
                    (this.right.expr().value() instanceof String)
                ) {
                
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){

                }else{
                    
                }
            case MulOp:
                if (
                    (this.left.expr().value() instanceof String) &&
                    (this.right.expr().value() instanceof String)
                ) {
                
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){

                }else{
                    
                }
            case DivOp:
                if (
                    (this.left.expr().value() instanceof String) &&
                    (this.right.expr().value() instanceof String)
                ) {
                
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){

                }else{
                    
                }
            case ModOp:
                if (
                    (this.left.expr().value() instanceof String) &&
                    (this.right.expr().value() instanceof String)
                ) {
                
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){

                }else{
                    
                }
        }
    }
}
