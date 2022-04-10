package Lexer;

import java.util.ArrayList;
import java.util.HashMap;

public class ScannerLexer {
    private static final HashMap<String, TokenType> symbols = new HashMap<>();
    private static final HashMap<String, TokenType> operations = new HashMap<>();
    private static final HashMap<String, TokenType> comparisons = new HashMap<>();

    static {
        symbols.put("{", TokenType.LEFT_BRACE);
        symbols.put("}", TokenType.RIGHT_BRACE);
        symbols.put("(", TokenType.LEFT_PAREN);
        symbols.put(")", TokenType.RIGHT_PAREN);
        symbols.put("[", TokenType.LEFT_BRACKET);
        symbols.put("]", TokenType.RIGHT_BRACKET);
        symbols.put(",", TokenType.COMMA);
        symbols.put(".", TokenType.DOT);
        symbols.put(";", TokenType.SEMICOLON);
        symbols.put(":", TokenType.COLON);
        symbols.put("\"", TokenType.D_COTES);

    }

    static {
        operations.put("-", TokenType.MINUS);
        operations.put("+", TokenType.PLUS);
        operations.put("/", TokenType.DIV);
        operations.put("*", TokenType.MULT);
        operations.put("%", TokenType.MOD);

    }

    static {
        comparisons.put("!", TokenType.BANG);
        comparisons.put("!=", TokenType.BANG_EQUAL);
        comparisons.put("=", TokenType.EQUAL);
        comparisons.put("==", TokenType.EQUAL_EQUAL);
        comparisons.put(">", TokenType.GREATER);
        comparisons.put(">=", TokenType.GREATER_EQUAL);
        comparisons.put("<", TokenType.LESS);
        comparisons.put("<=", TokenType.LESS_EQUAL);
    }

    private String input;
    private final ArrayList<Token> tokens; // int a =20;
    private int current = 0;
    private int length;
    private boolean error = false;

    public ScannerLexer(String input) {
        this.input = input;
        tokens = new ArrayList<>();
    }

    public ArrayList<Token> tokenizer(String input) {
        this.input = input;
        length = input.length();

        while (current < length) {
            //Ignore white space
            if (input.charAt(current) == ' ') {
                current++;
            }
            //Set symbols token:
            else if (symbols.containsKey(Character.toString(input.charAt(current)))) {
                setSymbols(Character.toString(input.charAt(current)));
            }
            //Set operation token:
            else if (operations.containsKey(Character.toString(input.charAt(current)))) {
                setOperation(Character.toString(input.charAt(current)));
            }
            //Set comparison token:
            else if (comparisons.containsKey(Character.toString(input.charAt(current)))) {
                setComparison();
            }
            //Set Identifier(a) + keyWord token://int a = 5;
            else if (Character.isAlphabetic(input.charAt(current))) {
                setKeyWords();
            }
            //Set  Number token:
            else if (Character.isDigit(input.charAt(current))) {
                setNumber();
            } else current++;

        }
        return (tokens);
    }


    public void setSymbols(String symbol) {
        Token token;
        if (symbol.equals("\"")) {
            current++;
            StringBuffer buffer = new StringBuffer();

            while (!(input.charAt(current) == '\"')) {
                buffer.append(input.charAt(current));
                current++;
            }
            token = new Token(TokenType.STRING, buffer.toString());
        } else
            token = new Token(symbols.get(symbol), symbol);

        tokens.add(token);
        current++;
    }

    public void setOperation(String operation) {
        Token token = new Token(operations.get(operation), operation);
        tokens.add(token);
        current++;
    }

    public void setComparison() {
        int position = current;
        String comparison = "";//<=

        while (comparisons.containsKey(String.valueOf(input.charAt(position)))) {
            if (position - current > 2) {
                error = true;
                System.out.println("Too many operators error!");
            }
            position++;
            comparison = input.substring(current, position);
            Token token;
            if (!error) {
                switch (comparison) {
                    case "!":
                        token = new Token(TokenType.BANG, comparison);
                        tokens.add(token);
                        break;
                    case "!=":
                        token = new Token(TokenType.BANG_EQUAL, comparison);
                        tokens.add(token);
                        break;
                    case "=":
                        token = new Token(TokenType.EQUAL, comparison);
                        tokens.add(token);
                        break;
                    case "==":
                        token = new Token(TokenType.EQUAL_EQUAL, comparison);
                        tokens.add(token);
                        break;
                    case "<":
                        token = new Token(TokenType.LESS, comparison);
                        tokens.add(token);
                        break;
                    case "<=":
                        token = new Token(TokenType.LESS_EQUAL, comparison);
                        tokens.add(token);
                        break;
                    case ">=":
                        token = new Token(TokenType.GREATER_EQUAL, comparison);
                        tokens.add(token);
                        break;
                    case ">":
                        token = new Token(TokenType.GREATER, comparison);
                        tokens.add(token);
                        break;


                    default:
                        System.out.println("Operation Error!");
                }
                current = position;
            }

        }

    }

    private void setKeyWords() {
        StringBuffer buffer = new StringBuffer();
        while (current != length) {
            if (!Character.isLetterOrDigit(input.charAt(current)))
                break;
            else
                buffer.append(input.charAt(current));
            current++;
        }
        String word = buffer.toString();
        Token token;
        switch (word) {
            case "class":
                token = new Token(TokenType.CLASS, word);
                tokens.add(token);
                break;
            case "true":
            case "false":
                token = new Token(TokenType.BOOLEAN, word);
                tokens.add(token);
                break;
            case "if":
                token = new Token(TokenType.IF, word);
                tokens.add(token);
                break;
            case "else":
                token = new Token(TokenType.ELSE, word);
                tokens.add(token);
                break;
            case "while":
                token = new Token(TokenType.WHILE, word);
                tokens.add(token);
                break;
            case "main":
                token = new Token(TokenType.MAIN, word);
                tokens.add(token);
                break;
            case "function":
                token = new Token(TokenType.FUN, word);
                tokens.add(token);
                break;
            case "and":
                token = new Token(TokenType.AND, word);
                tokens.add(token);
                break;
            case "or":
                token = new Token(TokenType.OR, word);
                tokens.add(token);
                break;
            case "return":
                token = new Token(TokenType.RETURN, word);
                tokens.add(token);
                break;
            case "for":
                token = new Token(TokenType.FOR, word);
                tokens.add(token);
                break;
            case "switch":
                token = new Token(TokenType.SWITCH, word);
                tokens.add(token);
                break;
            case "case":
                token = new Token(TokenType.CASE, word);
                tokens.add(token);
                break;
            case "break":
                token = new Token(TokenType.BREAK, word);
                tokens.add(token);
                break;
            case "print":
                token = new Token(TokenType.PRINT, word);
                tokens.add(token);
                break;
            case "this":
                token = new Token(TokenType.THIS, word);
                tokens.add(token);
                break;
            case "do":
                token = new Token(TokenType.DO, word);
                tokens.add(token);
                break;
            case "super":
                token = new Token(TokenType.SUPER, word);
                tokens.add(token);
                break;
            case "string":
            case "number":
            case "boolean":
                token = new Token(TokenType.VARIABLETYPE, word);
                tokens.add(token);
                break;

            default:
                token = new Token(TokenType.IDENTIFIER, word);
                tokens.add(token);
                break;
        }
    }

    private void setNumber() {
        //String Buffer has the function to manipulate with the string
        StringBuffer buffer = new StringBuffer();
        while (current != length) {
            if (input.charAt(current) == '.') {
                if (buffer.indexOf(".") != -1)
                    throw new RuntimeException("(Invalid float nr)");
                else
                    buffer.append(".");
            } else if (!Character.isDigit(input.charAt(current)))
                break;
            else
                buffer.append(input.charAt(current));
            current++;
        }
        String number = buffer.toString();
        Token token;
        token = new Token(TokenType.NUMBER, number);
        tokens.add(token);

    }

}
