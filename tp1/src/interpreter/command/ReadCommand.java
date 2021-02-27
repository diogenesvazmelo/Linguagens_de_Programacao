package interpreter.command;

import java.util.Scanner;
import java.util.ArrayList;
import interpreter.expr.Variable;
import interpreter.value.*;
import lexical.LexicalException;
import interpreter.util.Memory;

public class ReadCommand extends Command{    
    private ArrayList<Variable> vars;    

    public ReadCommand(int line){
        super(line);
        this.vars = new ArrayList<Variable>();
    }
    

    public void addVariable(Variable var){
        this.vars.add(var);
    }


    // TODO: DOESN'T exists string with only number in our language
    // maybe pass these checks to the math operations and 
    // leave everything as string
    @Override
    public void execute(){        
        Scanner sc = new Scanner(System.in);
        
        for(int i=0;i< this.vars.size(); i++){            

            String s = sc.next(); // TODO should it be next or next line?
            Variable tmp = this.vars.get(i);
            if(isNumber(s)) {
                if(hasDot(s)){
                    double value = Double.parseDouble(s);                    
                    tmp.setValue(new RealValue(value));
                    
                }else{
                    int value = Integer.parseInt(s);
                    tmp.setValue(new IntegerValue(value));
                }
            }else{
                tmp.setValue(new StringValue(s));
            }
            this.vars.set(i, tmp);


            // TODO REMOVE
            // System.out.println(tmp.getName() + " -> " + tmp.getValue().value());
            // registers in memory
            Memory.registryVariable(tmp.getName(), tmp.getValue());
        }
        sc.close();
    }

    private boolean isNumber(String s){        
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(
                (ch < '0' || ch > '9') &&                
                ch != '.')
                return false;            
        }
        return true;
    }
    
    private boolean hasDot(String s){
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch == '.')
                return true;
        }
        return false;
    }
}