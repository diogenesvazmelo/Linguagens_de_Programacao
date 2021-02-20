package interpreter.util;

import java.util.Map;
import java.util.Set;
import interpreter.value.*;

public class Memory {
    private Map<String, Value<?>> memory;
    private Set<String> consts;

    public void registryVariable(String name, Value<?> value){
        memory.put(name, value);
    }

    public void registryConstant(String name, Value<?> value){
        // TODO REVIEW
        consts.add(name);
    }

    public Value<?> read(String name){
        return memory.get(name);
    }

    public void write(String name, Value<?> value) {
        memory.put(name, value);
    }
}
