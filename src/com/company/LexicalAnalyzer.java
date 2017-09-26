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
                "elemental", "forall", "pure"};
        
        Java.add(0, "import java.util.*;\n");
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
                IOHandler ioHandler = new IOHandler();
                ioHandler.readFunction(integerVariables, realVariables, charVariables, logicalVariables, complexVariables, tokens, Java);
            } else {
                //Loop to process keywords
                for (int j = 0; j < thisLine.length; ++j) {


                    if (keyWords.contains(thisLine[j]) || thisLine[j].startsWith("character")) {

                        if (thisLine[j].equalsIgnoreCase("program") && !thisLine[0].equalsIgnoreCase("end")) {
                            //Pass to classes

                            //add Java code to arraylist
                            Java.add(fun.startprogram(thisLine[j + 1]));
                            fileName = thisLine[j + 1];

                            System.out.println();

                        } else if (thisLine[j].equalsIgnoreCase("end")) {
                            Java.add(fun.endprogram(thisLine[j + 1]));
                        }

                        if (thisLine[j].equalsIgnoreCase("integer"))//equalsIgnoreCase("integer"))
                        {
                            Java.add(assign.integer(thisLine, j, integerVariables));
                        }

                        if (thisLine[j].equalsIgnoreCase("real")) {
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


                }
                //   if ( i == Fortran.size()-1 )
                //       Java.add("}");


            }
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