package interpreter.expr;

import interpreter.util.Memory;
import interpreter.value.*;

public class Variable extends Expr{
    private String name;    

    public Variable(int line, String name){
        super(line);
        this.name = name;
    }

    @Override
    public Value<?> expr(){
        return Memory.read(this.name);
    }

    public void setValue(Value<?> value){        
        Memory.registryVariable(this.name, value);
    }

    public Value<?> getValue(){
        return Memory.read(this.name);
    }

    public String getName(){
        return this.name;
    }
}
