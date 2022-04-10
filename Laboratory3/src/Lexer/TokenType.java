package Lexer;

public enum TokenType {
    // Symbols
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,LEFT_BRACKET, RIGHT_BRACKET,
    COMMA, DOT, SEMICOLON, COLON, D_COTES,

    // Operation
    MINUS, PLUS, DIV, MULT, MOD,

    //Comparison
    BANG, BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,

    //Variable Type
    VARIABLETYPE,
    BOOLEAN, NUMBER, IDENTIFIER, STRING,

    // Keywords.
    AND, CLASS, MAIN, ELSE, FUN, FOR, IF, SWITCH,CASE, OR,
    PRINT, RETURN, SUPER, THIS,  WHILE, BREAK,DO,

    //End of file
    EOF
}
