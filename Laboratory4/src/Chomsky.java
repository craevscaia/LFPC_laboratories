import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Chomsky {
    private final LinkedHashMap<String, ArrayList<String>> productions = new LinkedHashMap<>();

    public void readInput() throws IOException {
        File file = new File("U:\\lfpc_lab\\Laboratory4\\src\\Input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String string;
        while ((string = br.readLine()) != null) { //A aB
            if (!productions.containsKey(string.substring(0, 1))) {
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(string.substring(2));
                productions.put(String.valueOf(string.charAt(0)), arrayList);
            } else {
                productions.get(String.valueOf(string.charAt(0))).add(string.substring(2));
            }
        }
        System.out.println(productions);
    }

    //////////////////////////Finds the key with has an epsilon production: C-> # //////////////////////////
    public char hasEpsilon() {
        for (String nonTerminal : productions.keySet()) {
            if (productions.get(nonTerminal).contains("#")) {
                return nonTerminal.charAt(0);
            }
        }
        return ' ';
    }

    // loops through the productions and creates null states with the already found epsilon state

    //Searches through the productions and finds the states with epsilon production and calls the function createEpsilonProductions
    // which creates the combinations out of a string  Ex: A -> #,  S->ABc then the function will return S -> Bc

    public void epsilonProduction(char epsilon) {
        for (String key : productions.keySet()) {
            for (int values = 0; values < productions.get(key).size(); values++) {
                if (productions.get(key).get(values).contains(String.valueOf(epsilon))) {
                    findIndexesEpsilon(productions.get(key).get(values), epsilon, key);
                }
            }
        }
        productions.get(String.valueOf(epsilon)).remove("#");
        System.out.println(productions);
    }

    //creates the combinations based on indexes of each epsilon char in the string

    // S - > AbCsCaC , C -> eps
    // the function will find the positions of eps in the string
    // In this case, on position 2, 4, 6 we have the char that goes in empty


    public void findIndexesEpsilon(String production, char epsilon, String key) {
        char[] set = production.toCharArray();
        ArrayList<Integer> index = new ArrayList<>(); //Store indexes pn which empty states can be found

        for (int i = 0; i < production.length(); i++) {  //AbAbA  -> A = eps
            if (set[i] == epsilon) {
                index.add(i); //0, 2, 4
            }
        }
        String indexString = index.toString();
        String digits = indexString.replaceAll("[^0-9]", "");
        char[] combinations = digits.toCharArray();

        //Gets all the combinations out of given indexes we found empty state in
        // printPowerSet will receive the positions of epsilon and output all the unique combinations:
        // For 0, 2, 4 we will have 0,2,4, 02, 04, 24, 024


        makeCombinationIndexes(combinations, index.size(), production, key); //Gets all the combinations out of given indexes we found empty state in
    }

    //Function to print all the combinations of empty symbol: ABA -> AB, BA, B

    // Having the above positions of eps, will create the new string
    // AbAbA having eps on 0, 2, 4 : bAbA, AbbA, AbAb, bbA, AbAA, bAb, bb


    public void makeCombinationIndexes(char[] set, int set_size, String production, String key) {
        long pow_set_size = (long) Math.pow(2, set_size);
        int counter, j;
        for (counter = 0; counter < pow_set_size; counter++) {
            List<Character> indexList = new ArrayList<>();
            for (j = 0; j < set_size; j++) {
                if ((counter & (1 << j)) > 0) {
                    indexList.add(set[j]);
                }
            }
            if (!indexList.isEmpty())
                deleteCharAtIndex(production, indexList, key);
        }
    }

    //having all the possible combinations of eliminating chars in a string we eliminate the chars at those indexes and store the result
    public void deleteCharAtIndex(String production, List<Character> indexList, String key) {
        StringBuilder copy = new StringBuilder(production);
        for (int i = 0; i < indexList.size(); i++) {
            copy.deleteCharAt(Character.getNumericValue(indexList.get(i)) - i); //ACA
            if (!productions.get(key).contains(copy.toString()) && copy.length() != 0) // BaC -> Ba
                productions.get(key).add(copy.toString());
        }
    }


    ///////////////////// Removes states that have no productions like C->___  , this happens if C->epsilon , at the start    ////////////////

    //function to remove the key that does not have any result, remove productions which conatin this key
    //If the only production on a key is empty, the key has no more production so is unproductive
    // C -> #   S -> ABCd
    // after removing eps, C -> nothing   so S -> ABd

    //function to remove the key that does not have any result, remove productions which conatin this key

    public void findProductionsWithDeadStates(String deadState) {
        for (String key : productions.keySet()) {
            for (int i = 0; i < productions.get(key).size(); i++) {
                if (productions.get(key).get(i).contains(deadState)) {
                    productions.get(key).remove(i);
                    findProductionsWithDeadStates(deadState); //we call one more time the function
                    // because there are changes in the size of productions, and to rewrite all productions
                    break;
                }

            }

        }

    }


    // function to remove States with no productions

    //Finds all the states that have dead states: C -> # then AC and CA will be removed


    public void removeNonProductiveStates() {
        for (String key : productions.keySet()) {
            if (productions.get(key).size() == 0) {
                findProductionsWithDeadStates(key); //first remove productions than
                // the key which do no has productions
                productions.remove(key);
                removeNonProductiveStates();
                break;
            }

        }


    }

    public void printProductions() {
        System.out.println(productions);

    }


    /////////////////////// Unit transitions ///////////////////////////////////////////////////////

    // if S-> A  then S will get all the values A goes into


    //unit transitions function
    public void unitProductions() {
        String prod;
        for (String key : productions.keySet()) {
            for (int i = 0; i < productions.get(key).size(); i++) {
                if (productions.get(key).get(i).length() == 1) {
                    prod = productions.get(key).get(i);
                    if (prod.equals(prod.toUpperCase())) {
                        //c.equals(c.toUpperCase())
                        //c.equals(C)
                        rewriteProductions(key, prod);// A -> B
                        productions.get(key).remove(prod);
                        unitProductions();
                        break;
                    }

                }
            }
        }
    }

    // A -> B rewrite all productions of B to A
    public void rewriteProductions(String key, String prod) {
        for (String value : productions.get(prod)) { //A -> B.....B-prod, value - the values of B
            if (!productions.get(key).contains(value)) {
                productions.get(key).add(value);
            }
        }
    }

    //function for accessible states

    // A key is unreachable if it is in none left part of other keys
    // S -> ABD,  D -> S
    // Because using D we can get S (S being in the right part), S is accessible key


    public void accessibleStates() {
        ArrayList<String> reachableStates = new ArrayList<>();
        reachableStates.add("S");
        for (int key = 0; key < reachableStates.size(); key++) {
            for (int value = 0; value < productions.get(reachableStates.get(key)).size(); value++) {
                for (char letter : productions.get(reachableStates.get(key)).get(value).toCharArray()) {
                    //S -> ba...'b' , 'A '
                    if (Character.isUpperCase(letter) && !reachableStates.contains(String.valueOf(letter))) {
                        reachableStates.add(String.valueOf(letter));
                    }
                }
            }
        }
        System.out.println(reachableStates);
        for (String key : productions.keySet()) {
            if (!reachableStates.contains(key)) {
                productions.remove(key);
            }
        }


    }


    //////////////////////////////////////Renaming step//////////////////////////////////////////
    //Function that loop through each state and calls other functions based on the type

    public LinkedHashMap<String, ArrayList<String>> getProductions() {
        return productions;
    }

    public void renaming(ArrayList<String> keys) {// list of keys   S -> aB //S A B
        if (!keys.isEmpty()) {
            for (String key : keys) { //because keys is an array list
                for (int value = 0; value < productions.get(key).size(); value++) {
                    String currentValue = productions.get(key).get(value);

                    //if it's not a valid production A->a or A->BC
                    //Simple case of length 2 like Aa ->  AX where X->a

                    if (!validStates(currentValue)) {
                        String newProd;

                        if (currentValue.length() == 2) {
                            newProd = productionsLengthTwo(currentValue);
                        }
                        //bCaCb returns FG for example so we replace bCaCb with FG
                        else {
                            newProd = splitProduction(currentValue);
                        }
                        //add new transition and key to hashmap
                        ArrayList<String> newList = productions.get(key);  //copy of values at keys
                        newList.remove(value);
                        newList.add(newProd);
                        productions.replace(key, newList);
                        //call function again since the array is now resized
                        renaming(keys);
                        break;
                    }
                }
                //printHashmap();
                //remove the key from passed array, so we don't loop through it again
                keys.remove(key);
                renaming(keys);
                break;
            }

        }
    }

    public boolean validStates(String currentValue) {
        if (currentValue.length() == 2 && currentValue.equals(currentValue.toUpperCase()))
            return true;
        if (currentValue.length() == 1 && currentValue.equals(currentValue.toLowerCase()))
            return true;
        return false;
    }


    public String productionsLengthTwo(String currentValue) {
        if (currentValue.equals(currentValue.toLowerCase())) {
            return lowerCaseProduction(currentValue);
        } else if (currentValue.equals(currentValue.toUpperCase())) {
            return currentValue;
        } else {

            //get the small letter from a double production like Aa


            String smallLetter = "";
            for (int i = 0; i < currentValue.length(); i++) {
                if (Character.isLowerCase(currentValue.charAt(i)))
                    smallLetter = String.valueOf(currentValue.charAt(i));
            }
            //check if there exists a transition that goes into a
            String newKey = "";
            for (String key : productions.keySet())
                if (productions.get(key).size() == 1 && productions.get(key).contains(smallLetter))
                    newKey = key;
            //if it doesn't exist we create a new tranistion
            if (newKey.equals(""))
                newKey = addTransitions(smallLetter);
            // we replace the obtained production with the small letter
            return currentValue.replace(smallLetter, newKey);
        }

    }


    // deal with productions of the form aa |  aba

    //String that changes its length
    //We check for existing characters and if there doesn't exist we
    // create a new transition and continue to the next letter

    public String lowerCaseProduction(String currentValue) {
        //String that changes its length

        StringBuilder newProduction = new StringBuilder();
        for (char letter : currentValue.toCharArray()) {
            boolean existKey = false;
            for (String key : productions.keySet()) {
                if (productions.get(key).size() == 1 && productions.get(key).contains(String.valueOf(letter))) {
                    newProduction.append(key);
                    existKey = true;
                    break;
                }
            }

            //create a new transition for the lower case letter


            if (!existKey) {
                String newKey = addTransitions(String.valueOf(letter));
                newProduction.append(newKey);
            }
        }
        return newProduction.toString();

    }


    // add a new transition to the hashmap


    public String addTransitions(String currentValue) {
        ArrayList<String> single = new ArrayList<>();
        single.add(String.valueOf(currentValue));
        String newKey = getValidKey();
        productions.put(newKey, single);
        return newKey;
    }


    // get a valid new key


    public String getValidKey() {
        String keys = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
        for (char key : keys.toCharArray()) {
            //returns a key which is not present in map
            if (!productions.containsKey(String.valueOf(key))) {
                return String.valueOf(key);
            }
        }
        return "";
    }

    //recursive function that takes a production as input for example bCab and
    public String splitProduction(String production) { // Aa | aAa
        //create a single production
        if (production.length() == 1) {
            return createFinalProduction(production);
        }
        //create a double production of the form: AA aa Aa
        else if (production.length() == 2) {
            String newProd = productionsLengthTwo(production);
            return createFinalProduction(newProd);
        } else {
            int half = production.length() / 2;
            String firstHalf = splitProduction(production.substring(0, half));
            String secondHalf = splitProduction(production.substring(half));

            if (firstHalf.length() == 2) {
                firstHalf = createFinalProduction(firstHalf);
            }
            if (secondHalf.length() == 2) {
                secondHalf = createFinalProduction(secondHalf);
            }
            return firstHalf + secondHalf;
        }
    }

    //returns an existing production or creates a new one if it doesn't exist
    public String createFinalProduction(String production) {
        for (String key : productions.keySet()) {
            if (productions.get(key).size() == 1) {
                if (productions.get(key).contains(production)) {
                    return key;
                }
            }
        }
        return addTransitions(production);
    }
}
