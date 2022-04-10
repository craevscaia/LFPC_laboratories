package Lexer;

public class Token {
    final TokenType type;
    final String lexeme;


    public Token(TokenType type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }

    public String getLexeme() {
        return lexeme;
    }

    public TokenType getType() {
        return type;
    }

    public String toString() {
        return type + " " + lexeme + " ";
    }
}
