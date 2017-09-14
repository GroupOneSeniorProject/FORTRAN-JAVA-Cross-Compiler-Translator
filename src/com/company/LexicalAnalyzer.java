package com.company;
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
        
        //keyWords.add("print");
        //keyWords.add("integer");
        //keyWords.add("program");
        //keyWords.add("end");
        //keyWords.add("real");
        openF95("test.f95"); //For IntelliJ, file must be in project root folder
        check();
    }


    //Open .f95 file and fill Fortran list
    public void openF95(String fileName)
    {
        try
        {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file).useDelimiter(" ");
            while (scanner.hasNext())
            {
                 Fortran.add(scanner.next());
            }


            scanner.close();
            Fortran.removeAll(Arrays.asList("", null));
            for(int i = 0; i < Fortran.size(); i++)
            {
                Fortran.get(i).replaceAll("\\s", "");
            }
    }
        catch(Exception fileIO)
        {
            System.out.println("File IO Error!");
        }
    }


    //This is where we pass to appropriate object
    public void check()
    {
        /*

        I've been having problems checking if it is a keyword. Even if it is, java is not seeing it. I will look into
        this some more, but if you have a fix, please commit changes to github.
        for(int i = 0; i < keyWords.size(); i++)
        {
            for(int j = 0; j < Fortran.size(); j++)
            {
                System.out.println(Fortran.get(j));
                if(keyWords.get(i).equalsIgnoreCase(Fortran.get(j)))
                {
                    System.out.println("Hi");
                }
            }
        }
        */
    }
}
