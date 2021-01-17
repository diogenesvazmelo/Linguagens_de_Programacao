package lexical;

public enum TokenType {
	// Specials
	UNEXPECTED_EOF, INVALID_TOKEN, END_OF_FILE,

	// Symbols
	SEMICOLON, // ;
	ASSIGN, // :=

	// Logic operators
	EQUAL, // =
	DIFFERENCE, // <>
	LOWER, // <
	LOWER_EQUAL, // <=
	GREATER, // >
	GREATER_EQUAL, // >=
	NOT, // not
	AND, // and
	OR, // or

	// Arithmetic operators
	ADD, // +
	SUB, // -
	MUL, // *
	DIV, // /
	MOD, // %

	// Keywords
	PROGRAM, // program
	BEGIN, // begin
	END, // end
	REPEAT, // repeat
	UNTIL, // until
	WRITE, // write
	WRITELN, // writeln
	READLN, // readln
	IF, // if
	THEN, // then
	ELSE, // else
	CASE, // case

	// Constant Types
	INTEGER, // integer
	FLOAT, // floating number
	STRING, // string, cant' concatenate

	// var declarations
	VAR, // variable
	CONST, // constants

	// Others
}
