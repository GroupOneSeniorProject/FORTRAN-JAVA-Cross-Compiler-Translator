import java.util.*;
import java.io.*;
import java.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer
{
    LinkedList<String> keyWords = new LinkedList<String>();
    LinkedList<String> Fortran = new LinkedList<String>();
    public static LinkedList<String> Java = new LinkedList<String>();
    //variable names that have been initialized to these types
    ArrayList<String> integerVariables = new ArrayList<>();
    ArrayList<String> realVariables = new ArrayList<>();
    ArrayList<String> complexVariables = new ArrayList<>();
    ArrayList<String> logicalVariables = new ArrayList<>();
    ArrayList<String> charVariables = new ArrayList<>();
    ArrayList<String> globalVariables = new ArrayList<>();

    ArrayList<String> functionNames = new ArrayList<>();
    ArrayList<Integer> functionParameterLocations = new ArrayList<>();
    Queue<Integer> numberOfParameters = new LinkedList<>();
    Queue<String> parameterVariables = new LinkedList<>();

    String fileName = "";


    public LexicalAnalyzer(File file)
    {
        //this is an array of all keywords through Fortran 95
        final String[] keywordsArray = {"real", "integer", "complex", "character", "logical", "assign",
                "backspace", "block data", "call", "close", "common", "continue", "data", "dimension", "do",
                "else", "else if", "end","endfile","endif", "entry", "equivalence", "external", "format", "function",
                "goto", "if", "implicit", "inquire", "intrinsic", "open", "parameter", "pause", "print", "program",
                "read", "return", "rewind", "rewrite", "save", "stop", "subroutine", "then", "write", "allocatable",
                "allocate", "case", "contains", "cycle", "deallocate", "elsewhere", "exit", "include", "interface",
                "intent", "module", "namelist", "nullify", "only", "operator", "optional", "pointer", "private",
                "procedure", "public", "recursive", "result", "select", "sequence", "target", "use", "while", "where",
                "elemental", "forall", "pure", "real"};

        //Java.add(0, "import java.util.*;\n");
        //Use a loop to load all keywords into the list at once.
        for(int i = 0; i < keywordsArray.length; i++)
        {

            keyWords.add(keywordsArray[i]);

        }
        openF95(file); //For IntelliJ, file must be in project root folder
        check();
    }


    //Open .f95 file and fill Fortran list
    public void openF95(File file)
    {
        try
        {
            //File file = new File(file);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext())
            {
                Fortran.add(scanner.nextLine());
            }

            scanner.close();
            //Fortran.removeAll(Arrays.asList("", null));
        }
        catch(Exception fileIO)
        {
            System.out.println(fileIO);
        }
    }


    //This is where we pass to appropriate object
    public void check()
    {
        //Declare objects here
        functions fun = new functions();
        AssignStatement assign = new AssignStatement();
        Arithmetic arith = new Arithmetic();

        fun.identifyGlobalVariables(globalVariables, Fortran);

        //testing
        /*
        for(int i = 0; i < globalVariables.size(); i++) {
            System.out.println(globalVariables.get(i));
        }
        */
        //end testing


        for(int i = 0; i < Fortran.size(); i++) {

            String[] thisLine = Fortran.get(i).split(" ");
            ArrayList<String> tokens = new ArrayList<>();

            //System.out.println(Fortran.get(i));

   /*         if(keyWords.contains(Fortran.get(i)))
            {
                //Just a check, this is the if statement where we pass to other class.
               System.out.println(Fortran.get(i));
            }
            
            
*/

            //Loop to add any non-whitespace elements of thisLine to new ArrayList
            for (String s : thisLine) {
                if (!s.isEmpty()) {
                    tokens.add(s);
                }
            }
            //Check to convert Fortran character declaration with length included to keyword 'character'
            if (!tokens.isEmpty()) {
                Pattern charPattern = Pattern.compile("character.*");
                Matcher matcher = charPattern.matcher(tokens.get(0));
                if (matcher.matches())
                    tokens.set(0, "character");
            }
            //Check for 'print' or 'read' keywords and execute translation by evaluating first item in tokens
            //This is done to ensure that if keywords are part of a String in print or read that they are treated as part
            //of the String and not as a keyword to be processed by the LexicalAnalyzer

            if (!tokens.isEmpty() && tokens.get(0).equalsIgnoreCase("print")) {
                IOHandler ioHandler = new IOHandler();
                String y = ioHandler.printFunction(tokens);
                Java.add(y);
            } else if (!tokens.isEmpty() && tokens.get(0).equalsIgnoreCase("read")) {
                if(!Java.getFirst().equalsIgnoreCase("import java.util.Scanner;")) {
                    Java.addFirst("import java.util.Scanner;");
                    for(int incrementCounter = 0; incrementCounter < functionParameterLocations.size(); incrementCounter++){
                        int thisLocation = functionParameterLocations.get(incrementCounter);
                        thisLocation ++;
                        functionParameterLocations.remove(incrementCounter);
                        functionParameterLocations.add(incrementCounter, thisLocation);
                    }
                }
                IOHandler ioHandler = new IOHandler();
                ioHandler.readFunction(integerVariables, realVariables, charVariables, logicalVariables, complexVariables, tokens, Java);
            } else if (tokens.size() > 2 && tokens.get(1).contains("intent")){
                continue;
            }
            //Check to see if thisLine is a function declaration
            else if(tokens.size() > 2 && tokens.get(1).equalsIgnoreCase("function") && !tokens.get(0).equalsIgnoreCase("end")){
                //Add static because calling from static setting
                String functionBuilder = "static ";
                //Determine return type
                if(tokens.get(0).equalsIgnoreCase("real"))
                    functionBuilder += "double ";
                else if(tokens.get(0).equalsIgnoreCase("integer"))
                    functionBuilder += "int ";
                else
                    functionBuilder += "boolean ";
                //Determine function name
                int parenthIndex1 = tokens.get(2).indexOf('(');
                String functionName = tokens.get(2).substring(0, parenthIndex1);
                //Isolate parameters and add to parameterVariables
                int parenthIndex2 = Fortran.get(i).indexOf('(');
                int parenthIndex3 = Fortran.get(i).indexOf(')');
                int thisNumberParams = 0;
                String paramString = Fortran.get(i).substring(parenthIndex2 + 1, parenthIndex3);
                String [] params = paramString.split(",");
                for(String s : params){
                    if(!s.isEmpty()){
                        parameterVariables.add(s.trim());
                        thisNumberParams++;
                    }
                }
                //Determine number of parameters for this function
                numberOfParameters.add(thisNumberParams);
                //Add first part of declaration to Java. Parameters will be added as final step before writing
                functionBuilder += functionName;
                functionNames.add(functionName);
                functionParameterLocations.add(Java.size());
                Java.add(functionBuilder);
                Java.add("");
                //Declare variable to be returned
                if(tokens.get(0).equalsIgnoreCase("real"))
                    Java.add("double returnVariable");
                else if(tokens.get(0).equalsIgnoreCase("integer"))
                    Java.add("int returnVariable");
                else
                    Java.add("boolean returnVariable");
                Java.add("");
            }
            //Replace Fortran function name with returnVariable in statements
           /* else if(!tokens.isEmpty() && functionNames.contains(tokens.get(0))){
                String functionValue = "returnVariable ";
                for(int tokenReader = 1; tokenReader < tokens.size(); ++tokenReader){
                    functionValue += tokens.get(tokenReader) + " ";
                }
                functionValue += ";";
                Java.add(functionValue);
            }*/
            //Add return statement for returnVariable
            else if(tokens.size() == 1 && tokens.get(0).equalsIgnoreCase("return")){
                Java.add("return returnVariable");
            }
            else
             {
                //Loop to process keywords
                for (int j = 0; j < thisLine.length; j++) {
                    System.out.println(thisLine[j]);

                    if(functionNames.contains(thisLine[j]))
                        thisLine[j] = "returnVariable";
                    if (keyWords.contains(thisLine[j]) || thisLine[j].startsWith("character")) {

                        if (thisLine[j].equalsIgnoreCase("program") && !thisLine[0].equalsIgnoreCase("end")) {
                            //Pass to classes

                            //add Java code to arraylist
                            Java.add(fun.startprogram(thisLine[j + 1]));
                            fileName = thisLine[j + 1];

                            System.out.println();

                        }
                        else if (thisLine[j].equalsIgnoreCase("end")) {
                            Java.add(fun.endprogram(thisLine[j + 1]));
                        }

                        if (thisLine[j].equalsIgnoreCase("integer"))//equalsIgnoreCase("integer"))
                        {
                            Java.add(assign.integer(thisLine, j, integerVariables));
                        }

                        if (thisLine[j].contains("real"))
                        {

                            Java.add(assign.real(thisLine, j, realVariables));
                        }

                        if (thisLine[j].equalsIgnoreCase(("logical"))) {
                            Java.add(assign.bool(thisLine, j, logicalVariables));
                        }
                        //Unsure what this line serves other than testing
                        //System.out.println(thisLine[j]);
                        if (thisLine[j].contains("character")) {
                            Java.add(assign.character(thisLine, j, charVariables));
                        }
                        //pass in for if/else logic
                        if ((thisLine[j].equalsIgnoreCase("if") || thisLine[j].equalsIgnoreCase("else") || thisLine[j].equalsIgnoreCase("then")) && !thisLine[j - 1].equalsIgnoreCase("end")) {
                            Java.add(fun.ifelse(thisLine));
                            j = thisLine.length - 1;
                        }
                        //pass in for logical comparisons
                        if (thisLine[j].equalsIgnoreCase(".eqv.") || thisLine[j].equalsIgnoreCase(".true.") ||
                                thisLine[j].equalsIgnoreCase(".false.") || thisLine[j].equalsIgnoreCase(".not.") ||
                                thisLine[j].equalsIgnoreCase(".and.") || thisLine[j].equalsIgnoreCase(".or.") ||
                                thisLine[j].equalsIgnoreCase(".nequv.")) {
                            Java.add(fun.logical(thisLine[j]));
                        }
                        //pass in for numerical comparisons
                        if (thisLine[j].equalsIgnoreCase("<") || thisLine[j].equalsIgnoreCase("<=") ||
                                thisLine[j].equalsIgnoreCase(">") || thisLine[j].equalsIgnoreCase(">=") ||
                                thisLine[j].equalsIgnoreCase("==") || thisLine[j].equalsIgnoreCase("/=")) {
                            fun.comparison(thisLine[j], thisLine[j - 1], thisLine[j + 1], charVariables, Java);
                        }

                    }
                    //comparison operations
                    if (thisLine[j].equalsIgnoreCase("<") || thisLine[j].equalsIgnoreCase("<=") ||
                            thisLine[j].equalsIgnoreCase(">") || thisLine[j].equalsIgnoreCase(">=") ||
                            thisLine[j].equalsIgnoreCase("==") || thisLine[j].equalsIgnoreCase("/=")) {
                        fun.comparison(thisLine[j], thisLine[j - 1], thisLine[j + 1], charVariables, Java);
                    }


                    if (logicalVariables.contains(thisLine[j])) {
                        //Java.add(thisLine[j]);
                    }


                    //duplicated code, commenting out until we are sure we do not need it
                /*
                if(thisLine[j].equalsIgnoreCase("="))
                {
                    fun.arithmetic(thisLine[j], thisLine[j - 1], thisLine[j + 1], Java);
                }
                */

                    //if an integer
                    if (integerVariables.contains(thisLine[j])) {
                        int temp = j;
                        for (int k = j; k < thisLine.length; k++) {
                            if (thisLine[k].equalsIgnoreCase("+")) {
                                Java.add(arith.add(thisLine, j));
                                j += (k - temp); // Avoid looping back through to do + again
                            }
                        }

                        //Java.add(arith.add(thisLine, j));
                    }
                    //if bool
                    if (logicalVariables.contains(thisLine[j])) {
                        Java.add(arith.boolarith(thisLine, j));
                        j += 2;

                    }
                    if(j < thisLine.length && thisLine[j].equalsIgnoreCase("=")){
                        fun.arithmetic(thisLine[j], thisLine[j - 1], thisLine[j + 1], Java);
                    }

                }
                //   if ( i == Fortran.size()-1 )
                //       Java.add("}");


            }
        }

        //Loop to add type declarations to all function parameters
        while(!functionParameterLocations.isEmpty()){
            int index = functionParameterLocations.get(0);
            functionParameterLocations.remove(0);
            String func = Java.get(index);
            func += "(";
            int numParams = numberOfParameters.poll();
            for(int counter = 0; counter < numParams; ++counter){
                String param = parameterVariables.poll();
                if(realVariables.contains(param))
                    func += "double " + param;
                else if(integerVariables.contains(param))
                    func += "int " + param;
                else
                    func += "boolean " + param;
                if(counter != numParams - 1){
                    func +=", ";
                }
            }
            func += "){";
            Java.remove(index);
            Java.add(index, func);
        }
        //write to file
        try {
            PrintWriter printWriter = new PrintWriter(fileName + ".java");
            for (String s : Java) {
                printWriter.println(s);
            }
            printWriter.println("}");
            printWriter.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public int ignoreWhitespace(int start, int size, String[] s)
    {
        for (int i = 0; i < s.length; i++)
        {
            if (!s[i].equals(" "))
            {
                return i;
            }
        }
        return 0;
    }
}