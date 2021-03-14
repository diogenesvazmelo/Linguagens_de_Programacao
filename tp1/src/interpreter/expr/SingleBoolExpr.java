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
    
    
    @Override
    public boolean expr(){
        switch (op) {
            case Equal:
                if (
                    (this.left.expr().value() instanceof String) &&
                    (this.right.expr().value() instanceof String)
                ) {                    
                    return this.left.expr().value().toString().length() == this.right.expr().value().toString().length();
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){
                    return (int)this.left.expr().value() == (int)this.right.expr().value();
                }else if (
                    (this.left.expr().value() instanceof Double) &&
                    (this.right.expr().value() instanceof Double)
                ){
                    return (double)this.left.expr().value() == (double)this.right.expr().value();
                }else{
                    return false; // throw here - comparison between diff types should give a throw or something
                }    
            case NotEqual:
                if (
                    (this.left.expr().value() instanceof String) &&
                    (this.right.expr().value() instanceof String)
                ) {
                    return this.left.expr().value().toString().length() != this.right.expr().value().toString().length();
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){
                    return (int)this.left.expr().value() != (int)this.right.expr().value();
                }else if (
                    (this.left.expr().value() instanceof Double) &&
                    (this.right.expr().value() instanceof Double)
                ){
                    return (double)this.left.expr().value() != (double)this.right.expr().value();
                }else{
                    return false;
                }    
            case LowerThan:
                if (
                   (this.left.expr().value() instanceof String) &&
                   (this.right.expr().value() instanceof String)
                ) {
                    return this.left.expr().value().toString().length() < this.right.expr().value().toString().length();
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){
                    return (int)this.left.expr().value() < (int)this.right.expr().value();
                }else if (
                    (this.left.expr().value() instanceof Double) &&
                    (this.right.expr().value() instanceof Double)
                ){
                    return (double)this.left.expr().value() < (double)this.right.expr().value();
                }else{
                    return false;
                }                
            case LowerEqual:
                if (
                   (this.left.expr().value() instanceof String) &&
                   (this.right.expr().value() instanceof String)
                ) {
                    return this.left.expr().value().toString().length() <= this.right.expr().value().toString().length();
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){
                    return (int)this.left.expr().value() <= (int)this.right.expr().value();
                }else if (
                    (this.left.expr().value() instanceof Double) &&
                    (this.right.expr().value() instanceof Double)
                ){
                    return (double)this.left.expr().value() <= (double)this.right.expr().value();
                }else{
                    return false; // throw here - comparison between diff types should give a throw or something
                }                
            case GreaterThan:
                if (
                   (this.left.expr().value() instanceof String) &&
                   (this.right.expr().value() instanceof String)
                ) {
                    return this.left.expr().value().toString().length() > this.right.expr().value().toString().length();
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){
                    return (int)this.left.expr().value() > (int)this.right.expr().value();
                }else if (
                    (this.left.expr().value() instanceof Double) &&
                    (this.right.expr().value() instanceof Double)
                ){
                    return (double)this.left.expr().value() > (double)this.right.expr().value();
                }else{
                    return false; // throw here - comparison between diff types should give a throw or something
                }                
            case GreateEqual:
                if (
                   (this.left.expr().value() instanceof String) &&
                   (this.right.expr().value() instanceof String)
                ) {
                    return this.left.expr().value().toString().length() >= this.right.expr().value().toString().length();
                }else if (
                    (this.left.expr().value() instanceof Integer) &&
                    (this.right.expr().value() instanceof Integer) 
                ){
                    return (int)this.left.expr().value() >= (int)this.right.expr().value();
                }else if (
                    (this.left.expr().value() instanceof Double) &&
                    (this.right.expr().value() instanceof Double)
                ){
                    return (double)this.left.expr().value() >= (double)this.right.expr().value();
                }else{
                    return false;
                }                
            default:
                return false;
        }        
    }
}
