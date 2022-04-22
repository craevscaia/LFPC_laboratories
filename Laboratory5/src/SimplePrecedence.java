import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SimplePrecedence {
    LinkedHashMap<String, ArrayList<String>> productionsMap = new LinkedHashMap<>();
    LinkedHashMap<String, ArrayList<String>> firstHashMap = new LinkedHashMap<>();
    LinkedHashMap<String, ArrayList<String>> lastHashMap = new LinkedHashMap<>();
    String everyLetter;
    protected String[][] precedence;


    //Read the given grammar from the file
    public void readInput() throws IOException {
        File file = new File("U:\\lfpc_lab\\Laboratory5\\src\\Input.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            if (!productionsMap.containsKey(String.valueOf(line.charAt(0)))) {
                ArrayList<String> keyProduction = new ArrayList<>();
                keyProduction.add(line.substring(2));
                productionsMap.put(String.valueOf(line.charAt(0)), keyProduction);
            } else {
                productionsMap.get(String.valueOf(line.charAt(0))).add(line.substring(2));
            }
        }
    }

    //HasMap for created first and last HashMaps
    public void createHashMap() {
        for (String key : productionsMap.keySet()) {
            firstHashMap.put(key, new ArrayList<>());
            lastHashMap.put(key, new ArrayList<>());
        }
    }


    public void first(String key, String intermediateKey) { //S Ab- valueAtKey
        for (String valueAtKey : productionsMap.get(key)) {
            String firstLetter = String.valueOf(valueAtKey.charAt(0));
            boolean isLowerCase = firstLetter.equals(firstLetter.toLowerCase());
            boolean letterExist = !firstHashMap.get(intermediateKey).contains(firstLetter);
            if (isLowerCase && letterExist) {
                firstHashMap.get(intermediateKey).add(firstLetter);
            } else if (!isLowerCase && letterExist) { //A TcA
                firstHashMap.get(intermediateKey).add(firstLetter);
                first(firstLetter, intermediateKey);
            }
        }
    }

    public void last(String key, String intermediateKey) {//S aB
        for (String valueAtKey : productionsMap.get(key)) {
            String lastLetter = String.valueOf(valueAtKey.charAt(valueAtKey.length() - 1)); // /o : do -1
            boolean isLowerCase = lastLetter.equals(lastLetter.toLowerCase());
            boolean letterExist = !lastHashMap.get(intermediateKey).contains(lastLetter);
            if (isLowerCase && letterExist) {
                lastHashMap.get(intermediateKey).add(lastLetter);
            }else if (!isLowerCase && letterExist) { //A TcA
                lastHashMap.get(intermediateKey).add(lastLetter);
                last(lastLetter, intermediateKey);
            }
        }

    }

    public void createIndex(){
        StringBuilder everyLetter = new StringBuilder();
        for (String key : productionsMap.keySet()) {
            for (String value : productionsMap.get(key)) {
                for (Character character : value.toCharArray()) {
                    if(everyLetter.indexOf(String.valueOf(character)) == -1){
                        everyLetter.append(character);
                    }
                }
            }
        }
        everyLetter.append("$");
        int size = everyLetter.length();
        precedence = new String[size][size];
        this.everyLetter = String.valueOf(everyLetter);
        System.out.println(everyLetter);
    }

    public void establishingRelations() {
        for (String key : productionsMap.keySet()) {
            for (String value : productionsMap.get(key)) {
                //FunctionCAll
                equalPrecedence(value);
                smallerPrecedence(value);
                biggerPrecedence(value);
                dollarRelation();
            }
        }
    }

    public void equalPrecedence(String production) { //bA        ABCab
        if (production.length() > 1) {
            for (int i = 0; i < production.length() - 1; i++) {
                int index = everyLetter.indexOf(production.charAt(i)); //4
                int index2 = everyLetter.indexOf(production.charAt(i + 1)); //4
                precedence[index][index2] = "=";
            }
        }
    }

    public void smallerPrecedence(String production) {
        for (int i = 1; i < production.length(); i++) {
            if (Character.isUpperCase(production.charAt(i))) {
                int index = everyLetter.indexOf(production.charAt(i - 1)); //4
                for (String value : firstHashMap.get(String.valueOf(production.charAt(i)))) {
                    int index2 = everyLetter.indexOf(value.charAt(0));
                    precedence[index][index2] = "<";
                }
                break;
            }
        }
    }

    public void biggerPrecedence(String production) {
        for (int i = 0; i < production.length() - 1; i++) {
            if (Character.isUpperCase(production.charAt(i))) { //BV
                if (Character.isLowerCase(production.charAt(i + 1))) {
                    int index = everyLetter.indexOf(production.charAt(i + 1)); //4
                    for (String value : lastHashMap.get(String.valueOf(production.charAt(i)))) {
                        int index2 = everyLetter.indexOf(value.charAt(0));
                        precedence[index2][index] = ">";
                    }
                } else {
                    for (String value : lastHashMap.get(String.valueOf(production.charAt(i)))) {
                        int index = everyLetter.indexOf(value.charAt(0)); //4
                        for (String first : firstHashMap.get(String.valueOf(production.charAt(i + 1)))) {
                            if (first.equals(first.toLowerCase())) {
                                int index2 = everyLetter.indexOf(first.charAt(0)); //4
                                precedence[index][index2] = ">";
                            }
                        }
                    }
                }
                break;
            }
        }
    }


    public void dollarRelation() {
        ArrayList<String> firstKeys = new ArrayList<>();
        for (String key : firstHashMap.keySet()) {
            for (String value : firstHashMap.get(key)) {
                if (!firstKeys.contains(value)) {
                    firstKeys.add(value);
                }
            }
        }
        for (String key : firstKeys) {
            int index = everyLetter.indexOf(key.charAt(0));
            precedence[everyLetter.indexOf("$")][index] = "<";
        }

        ArrayList<String> lastKeys = new ArrayList<>();
        for (String key : lastHashMap.keySet()) {
            for (String value : lastHashMap.get(key)) {
                if (!lastKeys.contains(value)) {
                    lastKeys.add(value);
                }
            }
        }
        for (String key : lastKeys) {
            int index = everyLetter.indexOf(key.charAt(0));
            precedence[index][everyLetter.indexOf("$")] = ">";
        }
    }


    public void printArray() {
        System.out.print(" ");
        for (int i = 0; i < everyLetter.length(); i++) {
            System.out.print(" " + everyLetter.charAt(i));
        }
        System.out.println();
        for (int i = 0; i < everyLetter.length(); i++) {
            System.out.print(everyLetter.charAt(i));
            for (int j = 0; j < everyLetter.length(); j++) {
                if (precedence[i][j] == null) {
                    System.out.print(" -");
                } else
                    System.out.print(" " + precedence[i][j]);
            }
            System.out.println();
        }
    }

    public void initializeRelationsWord(String string) { //$dabacbaa$
        StringBuilder resultString = new StringBuilder();
        int firstPosition, secPosition;
        for (int i = 0; i < string.length() - 1; i++) {
            firstPosition = everyLetter.indexOf(string.charAt(i));
            secPosition = everyLetter.indexOf(string.charAt(i + 1));
            resultString.append(string.charAt(i));
            resultString.append(precedence[firstPosition][secPosition]);
        }
        resultString.append("$");
        System.out.println(resultString);
    }


    public void letterToReplace(String string) {
        StringBuilder stringBuilder = new StringBuilder(string);
        for (int i = 1; i < string.length() - 1; i += 2) {
            if (string.charAt(i) == '<' && string.charAt(i + 2) == '>') {
                String letter = String.valueOf(string.charAt(i + 1));
                String replaceKey = replaceLetter(letter);
                stringBuilder.replace(i + 1, i + 2, replaceKey);
                char charBefore = stringBuilder.charAt(i - 1);
                char charAfter = stringBuilder.charAt(i + 3);
                String sign = precedence[everyLetter.indexOf(charBefore)][everyLetter.indexOf(replaceKey)];
                stringBuilder.replace(i, i + 1, sign);
                sign = precedence[everyLetter.indexOf(replaceKey)][everyLetter.indexOf(charAfter)];
                stringBuilder.replace(i + 2, i + 3, sign);
                string = findNewString(stringBuilder);
                System.out.println(string);
                letterToReplace(string);
                break;
            }
        }
//        return "";
    }

    public void replaceFurther(String string) {
        while (!string.isEmpty()) {
            letterToReplace(string);
        }
    }


    //this function finds the quality and replaces it ex: b=c  is replaced with D
    public String findNewString(StringBuilder stringBuilder) {
        String substring;
        for (int i = 0; i < stringBuilder.length() - 1; i++) {
            if (stringBuilder.charAt(i) == '=') {
                substring = stringBuilder.substring(i - 1, i + 2);
                String substringParameter = substring.replace("=", "");
                String string = stringBuilder.toString();
                string = string.replace(substring, newKey(substringParameter));
                stringBuilder = new StringBuilder(string);
                return String.valueOf(stringBuilder);
            }
        }
        return String.valueOf(stringBuilder);
    }


    public String newKey(String substring) {
        String newKey = "";
        for (String key : productionsMap.keySet()) {
            for (String value : productionsMap.get(key)) {
                if (value.contains(substring)) {
                    newKey = key;
                    break;
                }
            }
        }
        return newKey;
    }


    public String replaceLetter(String string) {
        for (String key : productionsMap.keySet()) {
            if (productionsMap.get(key).contains(string)) {
                return key;
            }
        }
        return " ";
    }



    public void printFirstHashmap() {
        for (String key : productionsMap.keySet()) {
            first(key, key);
            System.out.print("First (" + key + ") => ");
            for (String value : firstHashMap.get(key)) {
                System.out.print("[" + value + "]");
            }
            System.out.println();
        }
    }

    public void printLastHashmap() {
        for (String key : productionsMap.keySet()) {
            last(key, key);
            System.out.print("Last (" + key + ") => ");
            for (String value : lastHashMap.get(key)) {
                System.out.print("[" + value + "]");
            }
            System.out.println();
        }
    }

    public void main() throws IOException {
        readInput();
        createHashMap();
        System.out.println(productionsMap);
        printFirstHashmap();
        printLastHashmap();
        createIndex();
        establishingRelations();

        printArray();
        initializeRelationsWord("$adbbdb$");
        replaceFurther("$<a<d<b>b<d<b>$");
    }

}
