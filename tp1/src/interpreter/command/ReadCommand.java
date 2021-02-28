package interpreter.command;

import java.util.Scanner;
import java.util.ArrayList;
import interpreter.expr.Variable;
import interpreter.value.*;


public class ReadCommand extends Command{    
    private ArrayList<Variable> vars;    
    private Scanner sc = new Scanner(System.in);  

    public ReadCommand(int line){
        super(line);
        this.vars = new ArrayList<Variable>();
    }
    

    public void addVariable(Variable var){
        this.vars.add(var);
    }

    
    // maybe pass these checks to the math operations and 
    // leave everything as string
    @Override
    public void execute(){                      
        for(Variable v : this.vars){
            String s = sc.next(); // TODO should it be next or next line?
            if(isNumber(s)) {
                if(hasDot(s)){
                    double value = Double.parseDouble(s);                    
                    v.setValue(new RealValue(value));
                    
                }else{
                    int value = Integer.parseInt(s);
                    v.setValue(new IntegerValue(value));
                }
            }else{
                v.setValue(new StringValue(s));
            }
        }     
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