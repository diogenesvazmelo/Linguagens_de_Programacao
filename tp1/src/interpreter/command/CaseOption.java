package interpreter.command;

import interpreter.value.Value;

public class CaseOption{
    public Value<?> value;
    public Command cmd;

    public CaseOption(Value<?> value, Command cmd){
        this.value = value;
        this.cmd = cmd;
    }
}