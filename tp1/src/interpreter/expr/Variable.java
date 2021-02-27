package interpreter.expr;

import interpreter.value.*;

public class Variable extends Expr{
    private String name;
    private Value<?> value;

    public Variable(int line, String name){
        super(line);
        this.name = name;
    }

    @Override
    public Value<?> expr(){
        return new StringValue(this.name);
    }

    public void setValue(Value<?> value){
        this.value = value;
    }

    public Value<?> getValue(){
        return this.value;
    }

    public String getName(){
        return this.name;
    }
}
