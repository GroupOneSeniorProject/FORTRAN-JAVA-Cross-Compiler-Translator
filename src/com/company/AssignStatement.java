import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.*;

public class AssignStatement {
    public AssignStatement() {

    }
    //building integer initialization and declaration
    public String integer(String[] s, int index, ArrayList<String> integer) {
        StringBuilder build = new StringBuilder();
        build.ensureCapacity(100);
        // build.append("int ");
        for (int i = index; i < s.length; i++) {
            if (!(s[i].equalsIgnoreCase("::") || s[i].equalsIgnoreCase("integer") ||
                    s[i].equalsIgnoreCase(",") || s[i].equalsIgnoreCase("=")
                    || s[i].equalsIgnoreCase("0"))) {
                build.append("int "+s[i].replace(",","") + " = 0;\n");
                integer.add(s[i].replace(",",""));
            }
        }
        //build.append(";");


        return build.toString();
    }
    //building double initialization and declaration
    public String real(String[] s, int index,  ArrayList<String> real) {
        StringBuilder build = new StringBuilder();
        build.ensureCapacity(100);
        //build.append("double ");
        for (int i = index; i < s.length; i++) {
            if (!(s[i].equalsIgnoreCase("::") || s[i].equalsIgnoreCase("real") ||
            		s[i].equalsIgnoreCase(",") || s[i].equalsIgnoreCase("=")
            		|| s[i].equalsIgnoreCase("0"))) {
                build.append("double " +s[i].replaceAll(",", "") + " = 0;\n" );
                real.add(s[i].replace(",",""));
            }
        }
        //build.append(";");

        return build.toString();
    }
    //building boolean initilazation and declaration
    public String bool(String[] s, int index,  ArrayList<String> logical) {
        StringBuilder build = new StringBuilder();
        build.ensureCapacity(100);
        build.append("boolean ");
        for (int i = index; i < s.length; i++) {
            if (!(s[i].equalsIgnoreCase("::") || s[i].equalsIgnoreCase("logical"))) {
                build.append(s[i] + " ");

                logical.add(s[i].replace(",",""));
            }
        }
        build.append(";");



        return build.toString();
    }
    //string init and declare
    public String character(String[] s, int index,  ArrayList<String> character) {
        StringBuilder build = new StringBuilder();
        //System.out.println("Hi");
        build.ensureCapacity(100);
        build.append("String ");
        int i = index;
        while (!s[i].equalsIgnoreCase("::"))
        {
            i++;
        }
        for (; i < s.length; i++) {
            {
                if (!(s[i].equalsIgnoreCase("::") || s[i].equalsIgnoreCase("character"))) {
                    build.append(s[i] + " ");
                    character.add(s[i].replace(",",""));

                }
            }
        }
        build.append(";");
        return build.toString();
    }
    
      public String intArray(String[] s, int index,  ArrayList<String> character)
  {
    StringBuilder builder = new StringBuilder();

    builder.ensureCapacity(100);
    String arrLen = "";
    for(int i = 0; i < s.length; i++)
    {
      if(s[i].equalsIgnoreCase("integer,"))
      {
        builder.append("int[]");
      }
      if(s[i].contains("dimension"))
      {
        int first = s[i].indexOf('(');
        int second = s[i].indexOf(')');
        arrLen = s[i].substring(first + 1, second);
        //builder.append("[" + arrLen + "]");
      }
      if (!s[i].equalsIgnoreCase(" ") && !s[i].contains("dimension") && !s[i].equalsIgnoreCase("::") &&
              !s[i].contains("integer"))
      {

        builder.append(" " + s[i].replaceAll(",", ""));

      }

    }

    builder.append(" = new int[" + arrLen + "];");

    return builder.toString();

  }


}
