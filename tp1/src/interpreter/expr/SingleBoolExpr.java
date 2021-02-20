package interpreter.expr;


public class SingleBoolExpr extends BoolExpr {
    private Expr left;
    private RelOp op;
    private Expr right;


    public SingleBoolExpr(int line, Expr left, RelOp op, Expr right){
        super(line);
        this.left = left;
        this.right = right;
        this.op = op;
    }
    
    // TODO - see if it's a good idea to do a throw when comparing INT and FLOAT
    public boolean expr(){
        switch (op) {
            case Equal:
                return this.left.expr() == this.right.expr();
            case NotEqual:
                return this.left.expr() != this.right.expr();
            case LowerThan:
                if (
                   (this.left.expr().value() instanceof String) &&
                   (this.right.expr().value() instanceof String)
                ) {
                
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){

                }else if (
                    (this.left.expr().value() instanceof Float) &&
                    (this.right.expr().value() instanceof Float)
                ){
                
                }else{
                    return false;
                }                
            case LowerEqual:
                if (
                   (this.left.expr().value() instanceof String) &&
                   (this.right.expr().value() instanceof String)
                ) {
                
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){

                }else if (
                    (this.left.expr().value() instanceof Float) &&
                    (this.right.expr().value() instanceof Float)
                ){
                
                }else{
                    return false;
                }                
            case GreaterThan:
                if (
                   (this.left.expr().value() instanceof String) &&
                   (this.right.expr().value() instanceof String)
                ) {
                
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){

                }else if (
                    (this.left.expr().value() instanceof Float) &&
                    (this.right.expr().value() instanceof Float)
                ){
                
                }else{
                    return false;
                }                
            case GreateEqual:
                if (
                   (this.left.expr().value() instanceof String) &&
                   (this.right.expr().value() instanceof String)
                ) {
                
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){

                }else if (
                    (this.left.expr().value() instanceof Float) &&
                    (this.right.expr().value() instanceof Float)
                ){
                
                }else{
                    return false;
                }                
            default:
                return false;
        }        
    }
}
