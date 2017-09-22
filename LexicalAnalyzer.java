import java.util.*;
import java.io.*;

public class LexicalAnalyzer {

    String fileName;
    LinkedList<String> keyWords = new LinkedList<String>();
    LinkedList<String> Fortran = new LinkedList<String>();
    LinkedList<String> JavaCode = new LinkedList<>();

    public LexicalAnalyzer() {
        //this is an array of all keywords through Fortran 95
        final String[] keywordsArray = {"real", "integer", "complex", "assign", "backspace", "block data",
                "call", "close", "common", "continue", "data", "dimension", "do", "else", "else if", "end",
                "endfile", "endif", "entry", "equivalence", "external", "format", "function", "goto", "if",
                "implicit", "inquire", "intrinsic", "open", "parameter", "pause", "print", "program", "read",
                "return", "rewind", "rewrite", "save", "stop", "subroutine", "then", "write", "allocatable",
                "allocate", "case", "contains", "cycle", "deallocate", "elsewhere", "exit", "include",
                "interface", "intent", "module", "namelist", "nullify", "only", "operator", "optional",
                "pointer", "private", "procedure", "public", "recursive", "result", "select", "sequence",
                "target", "use", "while", "where", "elemental", "forall", "pure"};

        //Use a loop to load all keywords into the list at once.
        for (int i = 0; i < keywordsArray.length; i++) {

            keyWords.add(keywordsArray[i]);

        }
        openF95("HelloWorld.f95"); //For IntelliJ, file must be in project root folder
        check();
    }


    //Open .f95 file and fill Fortran list
    public void openF95(String fileName) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Fortran.add(scanner.nextLine());
            }

            scanner.close();
            //Fortran.removeAll(Arrays.asList("", null));
        } catch (Exception fileIO) {
            System.out.println("File IO Error!");
        }
    }


    //This is where we pass to appropriate object
    public void check() {

        for (int i = 0; i < Fortran.size(); i++) {

            String[] thisLine = Fortran.get(i).split(" ");
            ArrayList<String> tokens = new ArrayList<>();
            boolean containsKeyword = false;

            for (String s : thisLine) {
                if (!s.isEmpty()) {
                    tokens.add(s);
                }
            }

            for (int j = 0; j < tokens.size(); ++j) {
                if (keyWords.contains(tokens.get(j))) {
                    containsKeyword = true;
                }
            }
            if (containsKeyword) {

                //Just a check, this is the if statement where we pass to other class.
                for (int j = 0; j < tokens.size(); ++j) {
                    if (keyWords.contains(tokens.get(j))) {

                        if (tokens.get(0).equalsIgnoreCase("program")) {
                            functions fun = new functions();
                            String y = fun.startprogram(tokens.get(j + 1));
                            fileName = tokens.get(j + 1);
                            JavaCode.add(y);
                        } else if (tokens.get(0).equalsIgnoreCase("implicit")) {
                            continue;
                        } else if (tokens.get(j).equalsIgnoreCase("print")) {
                            IOHandler ioHandler = new IOHandler();
                            String y = ioHandler.printFunction(tokens);
                            JavaCode.add(y);
                        } else if (tokens.get(0).equalsIgnoreCase("end")) {
                            JavaCode.add("}\n");
                        }
                    }
                }
            } else {
                String newJavaLine = "";
                for (String s : thisLine) {
                    if (!s.equals(""))
                        newJavaLine += s + " ";
                }
                if (newJavaLine.length() > 0) {
                    newJavaLine += ";";
                } else {
                    newJavaLine = "\n";
                }
                JavaCode.add(newJavaLine);
            }
        }
        try {
            PrintWriter printWriter = new PrintWriter(fileName + ".java");
            for (String s : JavaCode) {
                printWriter.println(s);
            }
            printWriter.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}