package syntatic;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import lexical.Lexeme;
import lexical.TokenType;
import lexical.LexicalAnalysis;
import lexical.LexicalException;

import interpreter.command.Command;

public class SyntaticAnalysis {

    private LexicalAnalysis lex;
    private Lexeme current;

    public SyntaticAnalysis(LexicalAnalysis lex) throws LexicalException, IOException {
        this.lex = lex;
        this.current = lex.nextToken();
    }

    public Command start() throws LexicalException, IOException {
        procProgram();
        return null;
        // matchToken(TokenType.END_OF_FILE);
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
    private void procProgram() throws LexicalException, IOException {
        eat(TokenType.PROGRAM);
        procId();
        eat(TokenType.SEMICOLON);
        
        if ( this.current.type == TokenType.CONST ){
            advance();
            procConst();

            while( this.current.type == TokenType.CONST ){
                advance();
                procConst();
            }
        }
        
        if ( this.current.type == TokenType.VAR ) {
            eat(TokenType.VAR);
            procConst();
            while( this.current.type == TokenType.VAR ) {
                advance();
                procConst();
            }
        }

        procBlock();
    }

    // <const>    ::= <id> = <value> ';'
    private void procConst() throws LexicalException, IOException {
        procId();
        eat(TokenType.EQUAL);
        procValue();
        eat(TokenType.SEMICOLON);
    }

    // <var>      ::= <id> { ',' <id> } [ = <value> ] ';'
    private void procVar() throws LexicalException, IOException {
        procId();

        while( this.current.type == TokenType.COMMA ) {
            advance();
            procId();
        }

        if (this.current.type == TokenType.EQUAL){
            advance();
            procValue();
        }

        eat(TokenType.SEMICOLON);
    }

    // <body>     ::= <block> | <cmd>
    private void procBody() throws LexicalException, IOException {
        // eat(TokenType.BEGIN);
        // procBlock();

    }

    // <block>    ::= begin [ <cmd> { ';' <cmd> } ] end
    private void procBlock() throws LexicalException, IOException {
        eat(TokenType.BEGIN);
        
        if (this.current.type != TokenType.END){
            procCmd();
            while(this.current.type == TokenType.SEMICOLON){
                advance();
                procCmd();
            }
        }
        
        eat(TokenType.END);
    }

    // <cmd>      ::= <assign> | <if> | <case> | <while> | <for> | <repeat> | <write> | <read>
    private void procCmd() throws LexicalException, IOException {
        if (current.type == TokenType.ASSIGN) {
            procAssign();
        } else if (current.type == TokenType.IF) {
            procIf();
        } else if (current.type == TokenType.CASE) {
            procCase();
        } else if (current.type == TokenType.WHILE) {
            procWhile();
        } else if (current.type == TokenType.FOR) {
            procFor();
        } else if (current.type == TokenType.REPEAT) {
            procRepeat();
        } else if (current.type == TokenType.WRITE) {
            procWrite();
        } else {
            procRead();
        }
    }

    // <assign>   ::= <id> := <expr>
    private void procAssign() throws LexicalException, IOException {
        procId();
        eat(TokenType.ASSIGN);
        procExpr();
    }

    // <if> ::= if <boolexpr> then <body> [else <body>]
    private void procIf() throws LexicalException, IOException {
        eat(TokenType.IF);
        procBoolExpr();
        eat(TokenType.THEN);
        procBody();
        if (current.type == TokenType.ELSE) {
            advance();
            procBody();
        }
    }

    // <case>     ::= case <expr> of { <value> : <body> ';' } [ else <body> ';' ] end
    private void procCase() throws LexicalException, IOException {
        eat(TokenType.CASE);
        procExpr();
        eat(TokenType.OF);        
        do {
            procValue();
            procBody();
        }while(this.current.type == TokenType.SEMICOLON);

        if (this.current.type == TokenType.ELSE){
            advance();
            procBody();
            eat(TokenType.SEMICOLON);
        }   
    }

    // <while>    ::= while <boolexpr> do <body>
    private void procWhile() throws LexicalException, IOException {
        eat(TokenType.WHILE);
        procBoolExpr();
        eat(TokenType.DO);
        procBody();
    }

    // <repeat>   ::= repeat [ <cmd> { ';' <cmd> } ] until <boolexpr>
    private void procRepeat() throws LexicalException, IOException {
        eat(TokenType.REPEAT);
        
        procCmd();
        while(this.current.type == TokenType.SEMICOLON){
            advance();
            procCmd();
        }

        eat(TokenType.UNTIL);
        procBoolExpr();
    }

    // <for>      ::= for <id> := <expr> to <expr> do <body>
    private void procFor() throws LexicalException, IOException {
        eat(TokenType.FOR);
        procId();
        eat(TokenType.ASSIGN);
        procExpr();
        eat(TokenType.TO);
        procExpr();
        eat(TokenType.DO);
        procBody();
    }

    // <write>    ::= (write | writeln) '(' [ <expr> { ',' <expr> } ] ')'
    private void procWrite() throws LexicalException, IOException {
        if (current.type == TokenType.WRITE || current.type == TokenType.WRITELN){
            advance();
        }
        eat(TokenType.OPEN_PAR);
        if (procExpr()){
            while (current.type == TokenType.COMMA){
                advance();
                procExpr()
            }
        }
        eat(TokenType.CLOSE_PAR);
    }

    // <read>     ::= readln '(' <id> { ',' <id> } ')'
    private void procRead() throws LexicalException, IOException {
        eat(TokenType.READLN);
        eat(TokenType.OPEN_PAR);
        procId();
        while(current.type == TokenType.COMMA){
            advance();
            procId();
        }
    }
    
    // <boolexpr> ::= [ not ] <cmpexpr> [ (and | or) <boolexpr> ]
    private void procBoolExpr() throws LexicalException, IOException {
        if (current.type == TokenType.NOT) {
            advance();
        }
        procCmpExpr();
        if (current.type == TokenType.AND || current.type == TokenType.OR) {
            advance();
            procCmpExpr();
        }
    }

    // <cmpexpr>  ::= <expr> ('=' | '<>' | '<' | '>' | '<=' | '>=') <expr>
    private void procCmpExpr() throws LexicalException, IOException {
        procExpr();
        if (current.type == TokenType.EQUAL) {
            advance();
        } else if (current.type == TokenType.NOT_EQUAL) {
            advance();
        } else if (current.type == TokenType.LOWER) {
            advance();
        } else if (current.type == TokenType.GREATER) {
            advance();
        } else if (current.type == TokenType.LOWER_EQ) {
            advance();
        } else {
            advance();
        }
        procExpr();
    }

    // <expr>     ::= <term> { ('+' | '-') <term> }
    private void procExpr() throws LexicalException, IOException {
        procTerm();
        while (current.type == TokenType.ADD || current.type == TokenType.SUB) {
            advance();
            procTerm();
        }
    }

    // <term>     ::= <factor> { ('*' | '/' | '%') <factor> }
    private void procTerm() throws LexicalException, IOException {
        procFactor();
        while(current.type == TokenType.MUL || current.type == TokenType.DIV || current.type == TokenType.MOD){
            advance();
            procFactor();
        }
    }

    // <factor>   ::= <value> | <id> | '(' <expr> ')'
    private void procFactor() throws LexicalException, IOException {
        if (current.type == TokenType.VALUE) {
            procValue();
        } else if (current.type == TokenType.ID) {
            procId();
        } else {
            eat(TokenType.OPEN_PAR);
            procExpr();
            eat(TokenType.CLOSE_PAR);
        }
    }

    // <value>    ::= <integer> | <real> | <string>
    private void procValue() throws LexicalException, IOException {
        if (current.type == TokenType.INTEGER) {
            procInteger();
        } else if (current.type == TokenType.REAL) {
            procReal();
        } else {
            procString();
        }
    }

    private void procId() throws LexicalException, IOException {
        eat(TokenType.ID);
    }

    private void procInteger() throws LexicalException, IOException {
        eat(TokenType.INTEGER);
    }

    private void procReal() throws LexicalException, IOException {
        eat(TokenType.REAL);
    }

    private void procString() throws LexicalException, IOException {
        eat(TokenType.STRING);
    }

}