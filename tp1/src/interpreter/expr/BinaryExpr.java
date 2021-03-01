package interpreter.expr;

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

    @Override
    public Value<?> expr(){        
        switch(op){
            case AddOp:
                if (
                   (this.left.expr().value() instanceof String) &&
                   (this.right.expr().value() instanceof String)
                ) {
                    return new StringValue(this.left.expr().value().toString() + this.right.expr().value().toString());
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){
                    return new IntegerValue((int)this.left.expr().value() + (int)this.right.expr().value());
                }else if (
                    (this.left.expr().value() instanceof Double) &&
                    (this.right.expr().value() instanceof Double) 
                ){
                    return new RealValue((double)this.left.expr().value() + (double)this.right.expr().value());
                }else{
                    // do nothing
                }
            case SubOp:
                if (
                    (this.left.expr().value() instanceof String) &&
                    (this.right.expr().value() instanceof String)
                ) {
                    // do nothing
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){
                    return new IntegerValue((int)this.left.expr().value() - (int)this.right.expr().value());
                }else if (
                    (this.left.expr().value() instanceof Double) &&
                    (this.right.expr().value() instanceof Double) 
                ){
                    return new RealValue((double)this.left.expr().value() - (double)this.right.expr().value());
                }else{
                    // do nothing
                }
            case MulOp:
                if (
                    (this.left.expr().value() instanceof String) &&
                    (this.right.expr().value() instanceof String)
                ) {
                    // do nothing
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){
                    return new IntegerValue((int)this.left.expr().value() * (int)this.right.expr().value());
                }else if (
                    (this.left.expr().value() instanceof Double) &&
                    (this.right.expr().value() instanceof Double) 
                ){
                    return new RealValue((double)this.left.expr().value() * (double)this.right.expr().value());
                }else{
                    // do nothing
                }
            case DivOp:
                if (
                    (this.left.expr().value() instanceof String) &&
                    (this.right.expr().value() instanceof String)
                ) {
                    // do nothing
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){
                    return new IntegerValue((int)this.left.expr().value() / (int)this.right.expr().value());
                }else if (
                    (this.left.expr().value() instanceof Double) &&
                    (this.right.expr().value() instanceof Double) 
                ){
                    return new RealValue((double)this.left.expr().value() / (double)this.right.expr().value());
                }else{
                    // do nothing
                }
            case ModOp:
                if (
                    (this.left.expr().value() instanceof String) &&
                    (this.right.expr().value() instanceof String)
                ) {
                    // do nothing
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){
                    return new IntegerValue((int)this.left.expr().value() % (int)this.right.expr().value());
                }else if (
                    (this.left.expr().value() instanceof Double) &&
                    (this.right.expr().value() instanceof Double) 
                ){
                    return new RealValue((double)this.left.expr().value() % (double)this.right.expr().value());
                }else{
                    // do nothing
                }

            default:                
                return this.left.expr();
        }
    }
}
