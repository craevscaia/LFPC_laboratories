package Lexer;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)throws FileNotFoundException {
        ArrayList<Token> tokens = new ArrayList<>();


        Scanner scanner = new Scanner(new File("U:\\lfpc_lab\\Laboratory3\\src\\Lexer\\Program.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            ScannerLexer sc = new ScannerLexer(line);
            tokens.addAll(sc.tokenizer(line));
        }
        for (Token token : tokens)
            System.out.println(token.toString());
    }

}
