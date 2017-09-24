import java.util.*;
import java.io.*;
import java.*;
//import ~/IdeaProjects/SeniorProject/src/functions.java;

public class LexicalAnalyzer
{
    LinkedList<String> keyWords = new LinkedList<String>();
    LinkedList<String> Fortran = new LinkedList<String>();
    LinkedList<String> Java = new LinkedList<String>();

    ArrayList<String> integerVariables = new ArrayList<>();
    ArrayList<String> realVariables = new ArrayList<>();
    ArrayList<String> complexVariables = new ArrayList<>();
    ArrayList<String> logicalVariables = new ArrayList<>();
    ArrayList<String> charVariables = new ArrayList<>();

    public LexicalAnalyzer()
    {
        //this is an array of all keywords through Fortran 95
        final String[] keywordsArray = {"real", "integer", "complex", "character", "logical", "assign", "backspace", "block data", "call", "close", "common", "continue", "data", "dimension", "do", "else", "else if", "end","endfile","endif", "entry", "equivalence", "external", "format", "function", "goto", "if", "implicit", "inquire", "intrinsic", "open", "parameter", "pause", "print", "program", "read", "return", "rewind", "rewrite", "save", "stop", "subroutine", "then", "write", "allocatable", "allocate", "case", "contains", "cycle", "deallocate", "elsewhere", "exit", "include", "interface", "intent", "module", "namelist", "nullify", "only", "operator", "optional", "pointer", "private", "procedure", "public", "recursive", "result", "select", "sequence", "target", "use", "while", "where", "elemental", "forall", "pure"};
        Java.add(0, "import java.*;\n");
        //Use a loop to load all keywords into the list at once.
        for(int i = 0; i < keywordsArray.length; i++)
        {

            keyWords.add(keywordsArray[i]);

        }
        openF95("C:\\Users\\home\\Desktop\\SP\\src\\firstDeliverable.f95"); //For IntelliJ, file must be in project root folder
        check();
    }


    //Open .f95 file and fill Fortran list
    public void openF95(String fileName)
    {
        try
        {
            File file = new File(fileName);
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
            System.out.println("File IO Error!");
        }
    }


    //This is where we pass to appropriate object
    public void check()
    {
        //Declare objects here
        functions fun = new functions();
        AssignStatement assign = new AssignStatement();
        for(int i = 0; i < Fortran.size(); i++)
        {
            String[] thisLine = Fortran.get(i).split(" ");
            //System.out.println(Fortran.get(i));

   /*         if(keyWords.contains(Fortran.get(i)))
            {
                //Just a check, this is the if statement where we pass to other class.
               System.out.println(Fortran.get(i));
            }
*/
            for(int j = 0; j < thisLine.length; ++j)
            {


                if(keyWords.contains(thisLine[j]) || thisLine[j].startsWith("character"))
                {

                    if (thisLine[j].equalsIgnoreCase("program") && !thisLine[0].equalsIgnoreCase("end"))
                    {
                        //Pass to classes

                        //add Java code to arraylist
                        Java.add(fun.startprogram(thisLine[j + 1]));

                        System.out.println();

                    }
                    else if (thisLine[j].equalsIgnoreCase("end" ))
                    {
                        Java.add(fun.endprogram(thisLine[j+1]));
                    }

                    if (thisLine[j].equalsIgnoreCase("integer"))//equalsIgnoreCase("integer"))
                    {
                        Java.add(assign.integer(thisLine,j, integerVariables));
                    }

                    if (thisLine[j].equalsIgnoreCase("real"))
                    {
                        Java.add(assign.real(thisLine,j, realVariables));
                    }

                    if (thisLine[j].equalsIgnoreCase(("logical")))
                    {
                        Java.add(assign.bool(thisLine,j, logicalVariables));
                    }
                    //Unsure what this line serves other than testing
                    //System.out.println(thisLine[j]);
                    if (thisLine[j].contains("character"))
                    {
                        Java.add(assign.character(thisLine,j, charVariables));
                    }
                    //pass in for if/else logic
                    if((thisLine[j].equalsIgnoreCase("if") || thisLine[j].equalsIgnoreCase("else") || thisLine[j].equalsIgnoreCase("then")) && !thisLine[j - 1].equalsIgnoreCase("end"))
                    {
                        Java.add(fun.ifelse(thisLine[j]));
                    }
                    //pass in for logical comparisons
                    if(thisLine[j].equalsIgnoreCase(".eqv.") || thisLine[j].equalsIgnoreCase(".true.") ||
                            thisLine[j].equalsIgnoreCase(".false.") || thisLine[j].equalsIgnoreCase(".not.") ||
                            thisLine[j].equalsIgnoreCase(".and.") || thisLine[j].equalsIgnoreCase(".or.") ||
                            thisLine[j].equalsIgnoreCase(".nequv."))
                    {
                        Java.add(fun.logical(thisLine[j]));
                    }
                    //pass in for numerical comparisons
                    if(thisLine[j].equalsIgnoreCase("<") || thisLine[j].equalsIgnoreCase("<=") ||
                            thisLine[j].equalsIgnoreCase(">") || thisLine[j].equalsIgnoreCase(">=") ||
                            thisLine[j].equalsIgnoreCase("==") || thisLine[j].equalsIgnoreCase("/="))
                    {
                        fun.comparison(thisLine[j], thisLine[j - 1], thisLine[j + 1], charVariables, Java);
                    }

                }

                if(thisLine[j].equalsIgnoreCase("<") || thisLine[j].equalsIgnoreCase("<=") ||
                        thisLine[j].equalsIgnoreCase(">") || thisLine[j].equalsIgnoreCase(">=") ||
                        thisLine[j].equalsIgnoreCase("==") || thisLine[j].equalsIgnoreCase("/="))
                {
                    fun.comparison(thisLine[j], thisLine[j - 1], thisLine[j + 1], charVariables, Java);
                }

                if(thisLine[j].equalsIgnoreCase("=") || thisLine[j].equalsIgnoreCase("+") ||
                        thisLine[j].equalsIgnoreCase("-") || thisLine[j].equalsIgnoreCase("*")||
                        thisLine[j].equalsIgnoreCase("/"))
                {


                    fun.arithmetic(thisLine[j], thisLine[j - 1], thisLine[j + 1], Java);
                }


            }
            //   if ( i == Fortran.size()-1 )
            //       Java.add("}");


        }
        for (int i = 0 ; i < Java.size(); i++)
        {
            System.out.println(Java.get(i));
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
