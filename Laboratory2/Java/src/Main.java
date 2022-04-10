import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Provide your input below. When finished type !!!\"exit\"!!!");

        LinkedHashMap<String, ArrayList<Edge>> adjList = new LinkedHashMap<>();
        LinkedHashMap<String, ArrayList<Edge>> adjListNFA = new LinkedHashMap<>();
        LinkedHashMap<String, ArrayList<Edge>> adjListDFA = new LinkedHashMap<>();
        ArrayList<String> vertices = new ArrayList<>();
        Graph FA = new Graph(adjList, vertices);

        while (true) {
            //Input S aB
            String userInput = sc.nextLine();
            if (userInput.equals("exit") || userInput.equals("EXIT") || userInput.equals("Exit")) {
                break;
            } else {
                FA.addEdge(userInput);
            }
        }

        System.out.println("\n" + "Finite automation: " + "\n");
        FA.printGraph();

        System.out.println("\n" + "NFA: " + "\n");
        NFA nfa = new NFA(FA, adjListNFA);
        nfa.graphNFA();
        nfa.printNFA();

        System.out.println("\n" + "DFA: " + "\n");
        DFA dfa = new DFA(nfa, adjListDFA);
        dfa.NFAtoDFA();
        dfa.printDFA();


    }
}


