package lexical;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.PushbackInputStream;

import java.util.*;

public class LexicalAnalysis implements AutoCloseable {

    private int line;
    private SymbolTable st;
    private PushbackInputStream input;

    public LexicalAnalysis(String filename) throws LexicalException {
        try {
            input = new PushbackInputStream(new FileInputStream(filename));
        } catch (Exception e) {
            throw new LexicalException("Unable to open file");
        }

        st = new SymbolTable();
        line = 1;
    }

    public void close() throws IOException {
        input.close();
    }

    public int getLine() {
        return this.line;
    }

    public Lexeme nextToken() throws LexicalException, IOException {
        Lexeme lex = new Lexeme("", TokenType.END_OF_FILE);

        int state = 1;        
        while (state != 12 && state != 13) {            
            int c = getc();
            // System.out.printf("[%s, (%s)]\n", state, (char)c);
            switch (state) {
                case 1:
                    if (c == '\n'){
                        line++;
                        state = 1;
                    }else if (c == '\t' || c == '\r' || c == ' ')
                        state = 1;
                    else if(c == '('){
                        lex.token += (char)c;
                        state = 2;
                    }else if(c == '<'){
                        lex.token += (char)c;
                        state = 5;
                    }else if(c == '>'){
                        lex.token += (char)c;
                        state = 6;
                    }else if(c == ':'){
                        lex.token += (char)c;
                        state = 7;
                    }else if(c == '.'){
                        lex.token += (char)c;
                        lex.type = TokenType.DOT;
                        state = 12;
                    }else if(c == ','){
                        lex.token += (char)c;
                        lex.type = TokenType.COMMA;
                        state = 12;
                    }else if(c == ';'){
                        lex.token += (char)c;
                        lex.type = TokenType.SEMICOLON;
                        state = 12;
                    }else if(c == '='){
                        lex.token += (char)c;
                        lex.type = TokenType.EQUAL;
                        state = 12;
                    }else if(c == '+'){
                        lex.token += (char)c;
                        lex.type = TokenType.ADD;
                        state = 12;
                    }else if(c == '-'){
                        lex.token += (char)c;
                        lex.type = TokenType.SUB;
                        state = 12;
                    }else if(c == '*'){
                        lex.token += (char)c;
                        lex.type = TokenType.MUL;
                        state = 12;
                    }else if(c == '/'){
                        lex.token += (char)c;
                        lex.type = TokenType.DIV;
                        state = 12;
                    }else if(c == '%'){
                        lex.token += (char)c;
                        lex.type = TokenType.MOD;
                        state = 12;
                    }else if(c == ')'){
                        lex.token += (char)c;
                        lex.type = TokenType.CLOSE_PAR;
                        state = 12;
                    }else if(c == '_' || (c >='a' && c<='z') || (c >= 'A' && c<='Z')){
                        lex.token += (char)c;                        
                        state = 8;
                    }else if(c >='0' && c<='9'){
                        lex.token += (char)c;                        
                        state = 9;
                    }else if(c == 39){
                        // is '
                        lex.token += (char)c;
                        state = 11;
                    }else if(c == -1){
                        lex.type = TokenType.END_OF_FILE;
                        state = 13;
                    }else{
                        lex.type = TokenType.INVALID_TOKEN;
                        state = 13;
                    }                  
                    break;
                case 2:
                    if (c == '*') {
                        lex.token = "";
                        state = 3;
                    } else {                        
                        ungetc(c);
                        lex.type = TokenType.OPEN_PAR;                                               
                        state = 12;
                    }
                    break;
                case 3:
                    if (c == '*') {                        
                        state = 4;
                    } else if (c != -1) {                        
                        state = 3;
                    } else {
                        lex.type = TokenType.UNEXPECTED_EOF;
                        state = 13;
                    }
                    break;
                case 4:
                    if (c == '*') {                        
                        state = 4;
                    } else if (c == ')') {                        
                        state = 1;
                    } else if (c != -1) {                        
                        state = 3;
                    } else {
                        lex.type = TokenType.UNEXPECTED_EOF;
                        state = 13;
                    }
                    break;
                case 5:
                    if (c == '=') {
                        lex.token += (char) c;
                        lex.type = TokenType.LOWER_EQ;
                    } else if (c == '>') {
                        lex.token += (char) c;
                        lex.type = TokenType.NOT_EQUAL;
                    } else {
                        ungetc(c);
                        lex.type = TokenType.LOWER;
                    }
                    state = 12;
                    break;
                case 6:
                    if (c == '=') {
                        lex.token += (char) c;
                        lex.type = TokenType.GREATER_EQ;
                    } else {
                        ungetc(c);
                        lex.type = TokenType.GREATER;
                    }
                    state = 12;
                    break;
                case 7:
                    if (c == '=') {
                        lex.token += (char) c;
                        lex.type = TokenType.ASSIGN;
                        state = 12;
                    } else {
                        ungetc(c);
                        state = 12;
                    }
                    break;
                case 8:
                    // _ letter digit
                    if (c == '_' || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '0')) {
                        lex.token += (char) c;
                        state = 8;
                    } else {
                        ungetc(c);                        
                        lex.type = TokenType.ID;
                        state = 12;
                    }
                    break;
                case 9:
                    // is digit
                    if (c >= '0' && c <= '9') {
                        lex.token += (char) c;
                        state = 9;
                    } else {
                        if (c == '.') {
                            lex.token += (char) c;
                            state = 10;
                        } else {
                            ungetc(c);
                            lex.type = TokenType.INTEGER;
                            state = 13;
                        }
                    }
                    break;
                case 10:
                    // is digit
                    if (c >= '0' && c <= '9') {
                        lex.token += (char) c;
                        state = 10;
                    } else {
                        ungetc(c);
                        lex.type = TokenType.REAL;
                        state = 13;
                    }
                    break;
                case 11:
                    // 39 => int value of '
                    if (c != 39 && c != -1) {
                        lex.token += (char) c;
                        state = 11;
                    } else {
                        if (c == -1) {
                            lex.type = TokenType.UNEXPECTED_EOF;
                        } else {                            
                            lex.token += (char) c;
                            lex.type = TokenType.STRING;
                        }
                        state = 13;
                    }
                    break;
                default:
                    throw new LexicalException("Unreachable");
            }
        }

        if (state == 12)
            lex.type = st.find(lex.token);

        return lex;
    }

    private int getc() throws LexicalException {
        try {
            return input.read();
        } catch (Exception e) {
            throw new LexicalException("Unable to read file");
        }
    }

    private void ungetc(int c) throws LexicalException {
        if (c != -1) {
            try {
                input.unread(c);
            } catch (Exception e) {
                throw new LexicalException("Unable to ungetc");
            }
        }
    }
}
