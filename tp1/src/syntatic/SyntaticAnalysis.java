package syntatic;

import java.io.IOException;
import java.util.ArrayList;

import interpreter.command.*;

import interpreter.value.*;
import interpreter.expr.*;
import interpreter.util.Memory;
import interpreter.value.StringValue;


import lexical.Lexeme;
import lexical.TokenType;
import lexical.LexicalAnalysis;
import lexical.LexicalException;


public class SyntaticAnalysis {    
    private LexicalAnalysis lex;
    private Lexeme current;    

    public SyntaticAnalysis(LexicalAnalysis lex) throws LexicalException, IOException {
        this.lex = lex;
        this.current = lex.nextToken();
    }

    public Command start() throws LexicalException, IOException {
        Command cmd = procProgram();
        eat(TokenType.END_OF_FILE);        
        return cmd;
    }

    private void advance() throws LexicalException, IOException {
        // System.out.println("Advanced (\"" + current.token + "\", " +
        //     current.type + ")");
        current = lex.nextToken();
    }

    private void eat(TokenType type) throws LexicalException, IOException {
        // System.out.println("Expected (..., " + type + "), found (\"" + 
        //     current.token + "\", " + current.type + ")");
        if (type == current.type) {
            current = lex.nextToken();
        } else {
            showError();
        }
    }

    private void showError() {
        System.out.printf("%02d: ", lex.getLine());

        switch (current.type) {
            case INVALID_TOKEN:
                System.out.printf("Lexema inválido [%s]\n", current.token);
                break;
            case UNEXPECTED_EOF:
            case END_OF_FILE:
                System.out.printf("Fim de arquivo inesperado\n");
                break;
            default:
                System.out.printf("Lexema não esperado [%s]\n", current.token);
                break;
        }

        System.exit(1);
    }

    // <program>  ::= program <id> ';'
    //                [ const <const> { <const> } ]
    //                [ var <var> { <var> } ]
    //                <block> '.'
    private Command procProgram() throws LexicalException, IOException {
        eat(TokenType.PROGRAM);
        procId();
        eat(TokenType.SEMICOLON);
        
        // const
        if ( this.current.type == TokenType.CONST ){
            advance();
            procConst();

            while( this.current.type == TokenType.ID ){
                procConst();
            }
        }
        
        // var
        if ( this.current.type == TokenType.VAR ) {
            advance();
            procVar();
            while( this.current.type == TokenType.ID ){
                procVar();
            }
        }

        Command cmd = procBody();
        
        eat(TokenType.DOT);
        
        return cmd;
    }

    // <const>    ::= <id> = <value> ';'
    private void procConst() throws LexicalException, IOException {         
        
        String name = procId();           
        
        eat(TokenType.EQUAL);

        Value<?> value = procValue();
        eat(TokenType.SEMICOLON);


        Memory.registryConstant(name, value);
    }

    // <var> ::= <id> { ',' <id> } [ = <value> ] ';'
    private void procVar() throws LexicalException, IOException {
        ArrayList<String> IDs = new ArrayList<String>();

        // adds IDs into the
        IDs.add(procId());
        while( this.current.type == TokenType.COMMA ) {
            advance();
            IDs.add( procId() );
        }
        

        Value<?> value = null;
        if (this.current.type == TokenType.EQUAL){
            advance();            
            value = procValue();
        }

        for(String ID : IDs) {
            Memory.registryVariable(ID, value);
        }

        eat(TokenType.SEMICOLON);                
    }

    // <body>     ::= <block> | <cmd>
    private Command procBody() throws LexicalException, IOException {
        if (this.current.type == TokenType.BEGIN){
            return procBlock();
        } else { 
            return procCmd();
        }
    }

    // <block>    ::= begin [ <cmd> { ';' <cmd> } ] end
    private BlocksCommand procBlock() throws LexicalException, IOException {
        int line = this.lex.getLine();
        BlocksCommand blockCmd = new BlocksCommand(line);

        eat(TokenType.BEGIN);
        
        if (
            this.current.type == TokenType.ID ||
            this.current.type == TokenType.IF ||
            this.current.type == TokenType.CASE ||
            this.current.type == TokenType.WHILE ||
            this.current.type == TokenType.REPEAT ||
            this.current.type == TokenType.FOR ||
            this.current.type == TokenType.WRITE ||
            this.current.type == TokenType.WRITELN ||
            this.current.type == TokenType.READLN
        ){
            blockCmd.addCommand(procCmd());  
            while(
                this.current.type == TokenType.ID ||
                this.current.type == TokenType.IF ||
                this.current.type == TokenType.CASE ||
                this.current.type == TokenType.WHILE ||
                this.current.type == TokenType.REPEAT ||
                this.current.type == TokenType.FOR ||
                this.current.type == TokenType.WRITE ||
                this.current.type == TokenType.WRITELN ||
                this.current.type == TokenType.READLN
            ){                
                blockCmd.addCommand(procCmd());  
            }
        }

        eat(TokenType.END);

        return blockCmd;
    }

    // <cmd>      ::= <assign> | <if> | <case> | <while> | <for> | <repeat> | <write> | <read>
    private Command procCmd() throws LexicalException, IOException {
        
    if (this.current.type == TokenType.IF) {    
        return procIf();
    } else if (this.current.type == TokenType.CASE) {
        return procCase();
    } else if (this.current.type == TokenType.WHILE) {
        return procWhile();
    } else if (this.current.type == TokenType.FOR) {
        return procFor();
    } else if (this.current.type == TokenType.REPEAT) {
        return procRepeat();
    } else if (
        this.current.type == TokenType.WRITE ||
        this.current.type == TokenType.WRITELN
    ) {
        return procWrite();
    } else if ( this.current.type == TokenType.READLN ){
        return procRead();
    }else if ( this.current.type == TokenType.ID ){     
        return procAssign();
    }
    
    // TODO review this
    throw new LexicalException("weird token was read");
}

// <assign>   ::= <id> := <expr>
    private AssignCommand procAssign() throws LexicalException, IOException {
        int line = this.lex.getLine();
        String id = procId();
        
        eat(TokenType.ASSIGN);
        
        Expr expr = procExpr();
        Variable var = new Variable(line, id);

        Memory.registryVariable(id, expr.expr()); // TODO should it be done here or in the exec?
        return new AssignCommand(line, var, expr);
    }

    // <if> ::= if <boolexpr> then <body> [else <body>]
    private IfCommand procIf() throws LexicalException, IOException {
        int line = this.lex.getLine();
        eat(TokenType.IF);
        
        BoolExpr cond = procBoolExpr();
        eat(TokenType.THEN);

        IfCommand cmdIf = new IfCommand(line, cond, procBody());
        if (this.current.type == TokenType.ELSE) {
            advance();
            cmdIf.setElseCommands(procBody());
        }
        return cmdIf;
    }

    // <case>     ::= case <expr> of { <value> : <body> ';' } [ else <body> ';' ] end
    private CaseCommand procCase() throws LexicalException, IOException {
        int line = this.lex.getLine();
        eat(TokenType.CASE);

        Expr expr = procExpr();
        eat(TokenType.OF);

        CaseCommand caseCmd = new CaseCommand(line, expr);
            
        while(                
            this.current.type == TokenType.REAL ||
            this.current.type == TokenType.INTEGER ||
            this.current.type == TokenType.STRING
        ) {
            Value<?> v = procValue();
            eat(TokenType.COLON);
            
            Command cmd = procBody();
            eat(TokenType.SEMICOLON);

            caseCmd.addOption(v, cmd);
        }
            
        if (this.current.type == TokenType.ELSE){
            advance();
            Command cmd = procBody();
            eat(TokenType.SEMICOLON);

            caseCmd.setOtherwise(cmd);
        }   
        eat(TokenType.END);


        return caseCmd;
    }

    // <while>    ::= while <boolexpr> do <body>
    private WhileCommand procWhile() throws LexicalException, IOException {
        int line = this.lex.getLine();
        eat(TokenType.WHILE);

        BoolExpr cond = procBoolExpr();
        eat(TokenType.DO);

        Command cmd = procBody();

        return new WhileCommand(line, cond, cmd);
    }

    // <repeat>   ::= repeat [ <cmd> { ';' <cmd> } ] until <boolexpr>
    private RepeatCommand procRepeat() throws LexicalException, IOException {
        int line = this.lex.getLine();
        eat(TokenType.REPEAT);

        ArrayList<Command> cmds = new ArrayList<Command>();
        
        if (            
            this.current.type == TokenType.ID ||
            this.current.type == TokenType.IF ||
            this.current.type == TokenType.CASE ||
            this.current.type == TokenType.WHILE ||
            this.current.type == TokenType.REPEAT ||
            this.current.type == TokenType.FOR ||
            this.current.type == TokenType.WRITE ||
            this.current.type == TokenType.WRITELN ||
            this.current.type == TokenType.READLN
        ){
            cmds.add(procCmd());
            while(this.current.type == TokenType.SEMICOLON){
                advance();
                cmds.add(procCmd());
            }
        }

        eat(TokenType.UNTIL);
        BoolExpr cond = procBoolExpr();

        return new RepeatCommand(line, cmds, cond);
    }

    // <for>      ::= for <id> := <expr> to <expr> do <body>
    private ForCommand procFor() throws LexicalException, IOException {
        int line = this.lex.getLine();
        
        eat(TokenType.FOR);        
        Variable var = new Variable(line, procId());

        eat(TokenType.ASSIGN);
        Expr src = procExpr();

        eat(TokenType.TO);
        Expr dst = procExpr();

        eat(TokenType.DO);
        Command cmd = procBody();

        return new ForCommand(line, var, src, dst, cmd);
    }

    // <write>    ::= (write | writeln) '(' [ <expr> { ',' <expr> } ] ')'
    private WriteCommand procWrite() throws LexicalException, IOException {        
        int line = this.lex.getLine();        

        boolean ln = false;
        if (this.current.type == TokenType.WRITE){
            eat(TokenType.WRITE);
        }else{
            eat(TokenType.WRITELN);
            ln = true;
        }

        WriteCommand writeCmd = new WriteCommand(line, ln);

        eat(TokenType.OPEN_PAR);        
                
        if (                
            this.current.type == TokenType.REAL ||
            this.current.type == TokenType.INTEGER ||
            this.current.type == TokenType.STRING ||
            this.current.type == TokenType.ID
        ) {          
            Expr x = procExpr();
            writeCmd.addExpr(x);
            
            while (current.type == TokenType.COMMA){                  
                advance();
                x = procExpr();
                writeCmd.addExpr(x);
            }
        }
                
        eat(TokenType.CLOSE_PAR);                
        return writeCmd;
    }

    // <read>     ::= readln '(' <id> { ',' <id> } ')'
    private ReadCommand procRead() throws LexicalException, IOException {
        ReadCommand readCommand = new ReadCommand(this.lex.getLine());        
        
        eat(TokenType.READLN);
        eat(TokenType.OPEN_PAR);
        
        int line = this.lex.getLine();
        readCommand.addVariable( new Variable(line, procId()) );

        while(current.type == TokenType.COMMA){            
            advance();
            line = this.lex.getLine();
            readCommand.addVariable( new Variable(line, procId()) );
        }

        eat(TokenType.CLOSE_PAR);

        return readCommand;
    }
    
    // <boolexpr> ::= [ not ] <cmpexpr> { (and | or) <boolexpr> }
    private BoolExpr procBoolExpr() throws LexicalException, IOException {
        int line = this.lex.getLine();    

        boolean isNot = false;
        if (current.type == TokenType.NOT) {
            advance();
            isNot = true;
        }
        
        BoolExpr boolExpr =  procCmpExpr();        

        while (current.type == TokenType.AND || current.type == TokenType.OR) {
            BoolOp op = BoolOp.NoOp;
            if (current.type == TokenType.AND) 
                op = BoolOp.And;
            else
                op = BoolOp.Or;
            advance();

            BoolExpr right =  procCmpExpr();
            boolExpr = new CompositeBoolExpr(line, boolExpr, op, right);
        }

        if (isNot)
            return new NotBoolExpr(line, boolExpr);
        else
            return boolExpr;
    }

    // <cmpexpr>  ::= <expr> ('=' | '<>' | '<' | '>' | '<=' | '>=') <expr>
    private SingleBoolExpr procCmpExpr() throws LexicalException, IOException {
        int line = this.lex.getLine();
        RelOp op = null;
        
        Expr left = procExpr();

        if (current.type == TokenType.EQUAL) {
            advance();
            op = RelOp.Equal;
        } else if (current.type == TokenType.NOT_EQUAL) {
            advance();
            op = RelOp.NotEqual;
        } else if (current.type == TokenType.LOWER) {
            advance();
            op = RelOp.LowerThan;
        } else if (current.type == TokenType.GREATER) {
            advance();
            op = RelOp.GreaterThan;
        } else if (current.type == TokenType.LOWER_EQ) {
            advance();
            op = RelOp.LowerEqual;
        } else if (current.type == TokenType.GREATER_EQ) {
            advance();
            op = RelOp.GreateEqual;
        }
        
        Expr right = procExpr();

        return new SingleBoolExpr(line, left, op, right);
    }

    // <expr>     ::= <term> { ('+' | '-') <term> }
    private Expr procExpr() throws LexicalException, IOException {         
        int line = this.lex.getLine();
        Expr expr = procTerm();        
    
        
        while (current.type == TokenType.ADD || current.type == TokenType.SUB) {
            BinaryOp op = BinaryOp.NoOp; 
            
            switch (this.current.token){
                case "+":
                    op = BinaryOp.AddOp;
                break;
                case "-":
                    op = BinaryOp.SubOp;
                break;
                default:
                break;
            }
            advance();
            Expr right = procTerm();
            expr = new BinaryExpr(line, expr, op, right);
        }
        
        return expr;       
    }

    // <term>     ::= <factor> { ('*' | '/' | '%') <factor> }
    private Expr procTerm() throws LexicalException, IOException {          
        int line = this.lex.getLine();        
        Expr expr = procFactor();                

        while(current.type == TokenType.MUL || current.type == TokenType.DIV || current.type == TokenType.MOD){            
            BinaryOp op = BinaryOp.NoOp;
            switch (this.current.token){
                case "*":
                    op = BinaryOp.MulOp;
                break;
                case "/":
                    op = BinaryOp.DivOp;
                break;
                case "%":
                    op = BinaryOp.ModOp;
                break;
                default:
                break;
            }
            advance();
            Expr right = procFactor();
            expr = new BinaryExpr(line, expr, op, right);
        }   

        return expr;   
    }

    // <factor>   ::= <value> | <id> | '(' <expr> ')'
    private Expr procFactor() throws LexicalException, IOException {        
        int line = this.lex.getLine();
        if (
            current.type == TokenType.INTEGER ||
            current.type == TokenType.REAL ||
            current.type == TokenType.STRING 
        ) {            
            return new ConstExpr(line, procValue());
        
        } else if (current.type == TokenType.ID) {            
            String id = procId();   
            return new Variable(line, id);
            
        } else {

            eat(TokenType.OPEN_PAR);
            Expr expr = procExpr();
            eat(TokenType.CLOSE_PAR);
            return expr;
        }        
    }

    // <value>    ::= <integer> | <real> | <string>
    private Value<?> procValue() throws LexicalException, IOException {
        if (current.type == TokenType.INTEGER) {
            return procInteger();
        } else if (current.type == TokenType.REAL) {
            return procReal();
        } else {
            return procString();
        }
    }

    private String procId() throws LexicalException, IOException {
        String id = this.current.token;
        eat(TokenType.ID);
        return id;
    }

    private IntegerValue procInteger() throws LexicalException, IOException {
        String token = this.current.token;
        eat(TokenType.INTEGER);

        int value = Integer.parseInt(token);
        return new IntegerValue(value);
    }

    private RealValue procReal() throws LexicalException, IOException {
        String token = this.current.token;
        eat(TokenType.REAL);

        Double value = Double.parseDouble(token);
        return new RealValue(value);
    }

    private StringValue procString() throws LexicalException, IOException {        
        String value = this.current.token;
        eat(TokenType.STRING);
        return new StringValue(value);
    }

}