import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.*;

public class AssignStatement {
    public AssignStatement() {

    }

    public String integer(String[] s, int index) {
        StringBuilder build = new StringBuilder();
        build.ensureCapacity(100);
       // build.append("int ");
        for (int i = index; i < s.length; i++) {
            if (!(s[i].equalsIgnoreCase("::") || s[i].equalsIgnoreCase("integer") || s[i].equalsIgnoreCase(",") || s[i].equalsIgnoreCase("=") || s[i].equalsIgnoreCase("0"))) {
                build.append("int "+s[i].replace(",","") + " = 0;\n");
            }
        }
        //build.append(";");


        return build.toString();
    }

    public String real(String[] s, int index) {
        StringBuilder build = new StringBuilder();
        build.ensureCapacity(100);
        build.append("double ");
        for (int i = index; i < s.length; i++) {
            if (!(s[i].equalsIgnoreCase("::") || s[i].equalsIgnoreCase("double"))) {
                build.append(s[i] + " ");
            }
        }
        build.append(";");

        return build.toString();
    }
    public String bool(String[] s, int index) {
        StringBuilder build = new StringBuilder();
        build.ensureCapacity(100);
        build.append("boolean ");
        for (int i = index; i < s.length; i++) {
            if (!(s[i].equalsIgnoreCase("::") || s[i].equalsIgnoreCase("logical"))) {
                build.append(s[i] + " ");
            }
        }
        build.append(";");



        return build.toString();
    }

    public String character(String[] s, int index) {
        StringBuilder build = new StringBuilder();
        System.out.println("Hi");
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
                }
            }
        }
        build.append(";");
        return build.toString();
    }


}
