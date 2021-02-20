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

    // TODO should it throw?
    public void registryConstant(String name, Value<?> value) throws Exception {
        // newInsertion == true -> no 'name' element in const sets
        boolean newInsertion = consts.add(name); 
        if (!newInsertion){
            throw new Exception("const already exists");
        }

        memory.put(name, value);
    }

    public Value<?> read(String name){
        return memory.get(name);
    }

    public void write(String name, Value<?> value) {
        memory.put(name, value);
    }
}
