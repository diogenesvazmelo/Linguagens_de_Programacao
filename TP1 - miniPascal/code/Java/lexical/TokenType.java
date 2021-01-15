package lexical;

public enum TokenType {
	// Specials
	UNEXPECTED_EOF,
	INVALID_TOKEN,
	END_OF_FILE,

	// Symbols
	SEMICOLON,     // ;
	ASSIGN,        // :=

	// Logic operators
	EQUAL,         // =
	DIFFERENCE,     // <> 
	LOWER,         // <
	LOWER_EQUAL,   // <=
	GREATER,       // >
	GREATER_EQUAL, // >=

	// Arithmetic operators
	ADD,           // +
	SUB,           // -
	MUL,           // *
	DIV,           // /
	MOD,           // %

	// Keywords
	PROGRAM,       // program
	IF,            // if
	ELSE,          // else
	CASE,          // case
	WHILE,         // while
	REPEAT,		   // repeat
	FOR,           // for
	WRITE,         // write
	WRITELN,       // writeln
	READLN,        // readln

	BEGIN,         // begin
	END,           // end

	NOT,           // not

	// Others
	NUMBER,        // number
	VAR            // variable

}
