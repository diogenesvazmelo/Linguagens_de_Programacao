package interpreter.command;

import java.util.Scanner;
import java.util.ArrayList;
import interpreter.expr.Variable;
import interpreter.value.*;

public class ReadCommand extends Command{    
    private ArrayList<Variable> vars;

    public ReadCommand(int line){
        super(line);
        vars = new ArrayList<Variable>();
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
        for(Variable var : vars){
            String s = sc.nextLine();
            if(isNumber(s)) {
                if(hasDot(s)){
                    double d = Double.parseDouble(s);
                    var.setValue(new RealValue(d));
                }else{
                    int i = Integer.parseInt(s);
                    var.setValue(new IntegerValue(i));
                }
            }else{
                var.setValue(new StringValue(s));
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