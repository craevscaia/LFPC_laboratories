import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Chomsky chomsky = new Chomsky();
        System.out.println("Original");
        chomsky.readInput();
        System.out.println();

        System.out.println("Remove empty");
        while (chomsky.hasEpsilon() != ' ') {
            char eps = chomsky.hasEpsilon(); //C
            chomsky.epsilonProduction(eps); //AB or BA or B
        }
        System.out.println();
        System.out.println("Remove unproductive");
        chomsky.removeNonProductiveStates();
        chomsky.printProductions();
        System.out.println();
        System.out.println("Unit productions");
        chomsky.unitProductions();
        chomsky.printProductions();
        System.out.println();
        System.out.println("Accessible states ");
        chomsky.accessibleStates();
        chomsky.printProductions();
        System.out.println();
        System.out.println("Renaming");
        chomsky.renaming(new ArrayList<>(chomsky.getProductions().keySet()));
        chomsky.printProductions();
    }
}

