import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Wes Groover on 9/21/2017.
 */
public class IOHandler {
    IOHandler() {
    }

    public String printFunction(ArrayList<String> s) {

        //Add beginning of Java pring statment to a string for further manipulation

        String javaLine = "System.out.println(";

        //Loop to run through passed tokenized ArrayList. Starts at index 2 because *, needs to be ignored
        for (int i = 2; i < s.size(); ++i) {
            //Checks to see if a '+' symbol needs to be added for cases where full line is not a string literal
            if (s.get(i).charAt(s.get(i).length() - 1) == ',') {
                int endIndex = s.get(i).length() - 1;
                String thisSubstring = s.get(i).substring(0, endIndex);
                javaLine += thisSubstring + " + ";
            } else
                //Add token follwed by a whitespace
                javaLine += s.get(i) + " ";
        }
        //Close Java print statement
        javaLine += ");";
        //Return translated string
        return javaLine;
    }

    //Method to add Java code for reading input from console.
    void readFunction(ArrayList<String> integerVariables, ArrayList<String> doubleVariables, ArrayList<String> stringVariables,
                      ArrayList<String> booleanVariables, ArrayList<String> complexVariables, ArrayList<String> tokens, LinkedList<String> javaCode) {

        //thisVariable will determine the type of scan that needs to be made from console by checking for it's inclusion
        //in a set of ArrayLists containing all declared variables.
        String thisVariable = tokens.get(2);
        boolean variableFound = false;

        for (int i = 0; i < integerVariables.size(); ++i) {
            if (integerVariables.get(i).equalsIgnoreCase(thisVariable)) {
                javaCode.add(thisVariable + " = scanner.nextInt();");
                javaCode.add("scanner.nextLine();");
                variableFound = true;
                break;
            }
        }
        if (!variableFound) {
            for (int i = 0; i < doubleVariables.size(); ++i) {
                if (doubleVariables.get(i).equalsIgnoreCase(thisVariable)) {
                    javaCode.add(thisVariable + " = scanner.nextDouble();");
                    javaCode.add("scanner.nextLine();");
                    variableFound = true;
                    break;
                }
            }
        }
        if (!variableFound) {
            for (int i = 0; i < stringVariables.size(); ++i) {
                if (stringVariables.get(i).equalsIgnoreCase(thisVariable)) {
                    javaCode.add(thisVariable + " = scanner.nextLine();");
                    variableFound = true;
                    break;
                }
            }
        }
        if (!variableFound) {
            for (int i = 0; i < booleanVariables.size(); ++i) {
                if (booleanVariables.get(i).equalsIgnoreCase(thisVariable)) {
                    javaCode.add(thisVariable + " = scanner.nextBoolean();");
                    javaCode.add("scanner.nextLine();");
                    variableFound = true;
                    break;
                }
            }
        }
    }
}
