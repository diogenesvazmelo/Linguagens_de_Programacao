package interpreter.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import interpreter.value.*;

public class Memory {        
    private static final Map<String, Value<?>> memory = new HashMap<String, Value<?>>();
    private static final Set<String> consts = new HashSet<String>();    

    public Memory(){}
        
    public static void registryVariable(String name, Value<?> value) {
        if (consts.contains(name)){
            // throw new LexicalException("const already exists with that name");            
            System.out.println("const already exists with that name");
        }else{
            memory.put(name, value);
        }        
    }
    
    public static void registryConstant(String name, Value<?> value) {
        if (consts.contains(name)){
            // throw new LexicalException("const already exists with that name");
            System.out.println("const already exists with that name");
        }else{
            consts.add(name); 
            memory.put(name, value);
        }
    }

    public static Value<?> read(String name){
        return memory.get(name);
    }

    public static boolean contains(String name){
        return memory.containsKey(name);
    }

    public static void write(String name, Value<?> value) {
        memory.put(name, value);
    }

    public static void writeAllVariables(){
        var x = memory.values();
        x.forEach (e -> System.out.println(e) );
    }
}
