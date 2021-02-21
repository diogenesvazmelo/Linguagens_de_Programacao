package interpreter.expr;

import interpreter.value.*;

public class Variable extends Expr{
    private String name;
    private Value<?> value;

    public Variable(int line, String name){
        super(line);
        this.name = name;
    }

    public Value<?> expr(){
        return new StringValue(this.name);
    }

    public void setValue(Value value){
        this.value = value;
    }

    public String getName(){
        return this.name;
    }
}
