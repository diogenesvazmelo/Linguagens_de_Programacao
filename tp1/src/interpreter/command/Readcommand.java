package interpreter.command;

import java.util.ArrayList;
import interpreter.expr.Variable;

import java.util.Scanner;

import org.graalvm.compiler.lir.Variable;
public class ReadCommand extends Command{
    
    private ArrayList<Variable> vars;

    public ReadCommand(int line){
        super(line);
    }

    public addVariable(Variable var){
        this.vars.add(var);
    }

    public void execute(){
        Scanner sc1 = new Scanner(System.in);
        
        float numF = sc.nextFloat();
        int num1 = sc.nextInt();
        byte byte1 = sc.nextByte();
        long lg1 = sc.nextLong();
        boolean b1 = sc.nextBoolean();
        double num2 = sc.nextDouble();
        String nome = sc.nextLine();
    }

}