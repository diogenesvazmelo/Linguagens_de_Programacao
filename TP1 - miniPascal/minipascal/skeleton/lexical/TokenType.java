package lexical;

class LexicalComponents {
    public enum TokenType {
        // SPECIALS
        UNEXPECTED_EOF, INVALID_TOKEN, END_OF_FILE,

        // SYMBOLS
        DOT, // .
        COMMA, // ,
        COLON, // :
        SEMICOLON, // ;
        OPEN_PAR, // (
        CLOSE_PAR, // )

        // OPERATORS
        ASSIGN, // :=
        EQUAL, // =
        NOT_EQUAL, // <>
        LOWER, // <
        GREATER, // >
        LOWER_EQ, // <=
        GREATER_EQ, // >=
        ADD, // +
        SUB, // -
        MUL, // *
        DIV, // /
        MOD, // %

        // KEYWORDS
        PROGRAM, // program
        CONST, // const
        VAR, // var
        BEGIN, // begin
        END, // end
        IF, // if
        THEN, // then
        ELSE, // else
        CASE, // case
        OF, // of
        WHILE, // while
        DO, // do
        REPEAT, // repeat
        UNTIL, // until
        FOR, // for
        TO, // to
        WRITE, // write
        WRITELN, // writeln
        READLN, // readln
        NOT, // not
        AND, // and
        OR, // or

        // OTHERS
        ID, // identifier
        INTEGER, // integer
        REAL, // real
        STRING, // string

    };

    public String ttToStr(TokenType tk) {
        switch (tk) {
            // SPECIALS
            case UNEXPECTED_EOF:
                return "UNEXPECTED_EOF";
            case INVALID_TOKEN:
                return "INVALID_TOKEN";
            case END_OF_FILE:
                return "END_OF_FILE";

            // SYMBOLS
            case DOT:
                return ".";
            case COMMA:
                return ",";
            case COLON:
                return ":";
            case SEMICOLON:
                return ";";
            case OPEN_PAR:
                return "(";
            case CLOSE_PAR:
                return ")";

            // OPERATORS
            case ASSIGN:
                return ":=";
            case EQUAL:
                return "=";
            case NOT_EQUAL:
                return "<>";
            case LOWER:
                return "<";
            case GREATER:
                return ">";
            case LOWER_EQ:
                return "<=";
            case GREATER_EQ:
                return ">=";
            case ADD:
                return "+";
            case SUB:
                return "-";
            case MUL:
                return "*";
            case DIV:
                return "/";
            case MOD:
                return "%";

            // KEYWORDS
            case PROGRAM:
                return "program";
            case CONST:
                return "const";
            case VAR:
                return "var";
            case BEGIN:
                return "begin";
            case END:
                return "end";
            case IF:
                return "if";
            case THEN:
                return "then";
            case ELSE:
                return "else";
            case CASE:
                return "case";
            case OF:
                return "of";
            case WHILE:
                return "while";
            case DO:
                return "do";
            case REPEAT:
                return "repeat";
            case UNTIL:
                return "until";
            case FOR:
                return "for";
            case TO:
                return "to";
            case WRITE:
                return "write";
            case WRITELN:
                return "writeln";
            case READLN:
                return "readln";
            case NOT:
                return "not";
            case AND:
                return "and";
            case OR:
                return "or";

            // OTHERS
            case ID:
                return "identifier";
            case INTEGER:
                return "integer";
            case REAL:
                return "real";
            case STRING:
                return "string";
            default:
                return "non-existant";
        }
    }
}