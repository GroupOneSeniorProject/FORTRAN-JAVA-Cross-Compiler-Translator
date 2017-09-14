import java.util.*;
import java.io.*;

public class LexicalAnalyzer
{
    LinkedList<String> keyWords = new LinkedList<String>();
    LinkedList<String> Fortran = new LinkedList<String>();

    public LexicalAnalyzer()
    {
        //this is an array of all keywords through Fortran 95
        final String[] keywordsArray = {"real", "integer", "complex", "assign", "backspace", "block data", "call", "close", "common", "continue", "data", "dimension", "do", "else", "else if", "end","endfile","endif", "entry", "equivalence", "external", "format", "function", "goto", "if", "implicit", "inquire", "intrinsic", "open", "parameter", "pause", "print", "program", "read", "return", "rewind", "rewrite", "save", "stop", "subroutine", "then", "write", "allocatable", "allocate", "case", "contains", "cycle", "deallocate", "elsewhere", "exit", "include", "interface", "intent", "module", "namelist", "nullify", "only", "operator", "optional", "pointer", "private", "procedure", "public", "recursive", "result", "select", "sequence", "target", "use", "while", "where", "elemental", "forall", "pure"};
        
        //Use a loop to load all keywords into the list at once.
        for(int i = 0; i < keywordsArray.length; i++)
        {
            
            keyWords.add(keywordsArray[i]);
            
        }
        openF95("test.f95"); //For IntelliJ, file must be in project root folder
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
                 Fortran.add(scanner.next());
            }

            scanner.close();
            Fortran.removeAll(Arrays.asList("", null));
    }
        catch(Exception fileIO)
        {
            System.out.println("File IO Error!");
        }
    }


    //This is where we pass to appropriate object
    public void check()
    {
        for(int i = 0; i < Fortran.size(); i++)
        {
            if(keyWords.contains(Fortran.get(i)))
            {
                //Just a check, this is the if statement where we pass to other class.
                System.out.println(Fortran.get(i));

            }
        }
    }
}
