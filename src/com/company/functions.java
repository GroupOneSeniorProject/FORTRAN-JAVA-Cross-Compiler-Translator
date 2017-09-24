import java.util.ArrayList;
import java.util.LinkedList;

public class functions
{
    public functions()
    {

    }
    String startprogram(String s) {
        String thisProgram = "public class " + s + "\n{\n public static void main(String[] args)\n{\n";
        return thisProgram;
    }
    String endprogram(String s)
    {
        return "}\n";
    }

    String logical(String s)
    {
        String result = "";
        if(s.equalsIgnoreCase(".eqv."))
        {
            return " == ";
        }
        else if(s.equalsIgnoreCase(".true."))
        {
            return " true";
        }
        else if(s.equalsIgnoreCase(".false."))
        {
            return " false";
        }
        else if(s.equalsIgnoreCase(".not."))
        {
            return "!";
        }
        else if(s.equalsIgnoreCase(".and."))
        {
            return " && ";
        }
        else if(s.equalsIgnoreCase(".or."))
        {
            return " || ";
        }
        else if(s.equalsIgnoreCase(".nequv."))
        {
            return " != ";
        }

        return "uncaught error";
    }
    String ifelse(String[] s) {
        StringBuilder builder = new StringBuilder();
        boolean start = false;

        for (int i = 0; i < s.length; i++) {

            if (s[i].equalsIgnoreCase("if")) {
                builder.append("\n" + s[i] + " ");
                start = true;
            } else if (start && !s[i].equalsIgnoreCase("then")) {
                builder.append(s[i] + " ");
            } else if (start) {
                builder.append("\n{");
            }
            if(s[i].equalsIgnoreCase("else"))
            {
                builder.append("}\nelse\n{");
            }
        }
            return builder.toString();



        //return "uncaught error";

    }
    //b s n  for comparison
    String comparison(String s, String b, String n, ArrayList charVariables, LinkedList<String> java)
    {

        if(s.equalsIgnoreCase("<"))
        {
            if(java.getLast().equalsIgnoreCase(b))
            {

                java.add(s);
                java.add(n);

            }
            else
            {

                java.add(b);
                java.add(s);
                java.add(n);
            }
        }
        else if(s.equalsIgnoreCase("<="))
        {
            if(java.getLast().equalsIgnoreCase(b))
            {

                java.add(s);
                java.add(n);

            }
            else
            {

                java.add(b);
                java.add(s);
                java.add(n);
            }
        }
        else if(s.equalsIgnoreCase(">"))
        {
            if(java.getLast().equalsIgnoreCase(b))
            {

                java.add(s);
                java.add(n);

            }
            else
            {

                java.add(b);
                java.add(s);
                java.add(n);
            }
        }
        else if(s.equalsIgnoreCase(">="))
        {
            if(java.getLast().equalsIgnoreCase(b))
            {

                java.add(s);
                java.add(n);

            }
            else
            {

                java.add(b);
                java.add(s);
                java.add(n);
            }
        }
        else if(s.equalsIgnoreCase("=="))
        {

            if(java.getLast().equalsIgnoreCase(b))
            {
                if(charVariables.contains(n))
                {
                    java.add(".equals(" + n + ");");
                }
                else {
                    java.add(" == " + n);
                }
            }
            else {
                if (charVariables.contains(n)) {
                    java.add(b + ".equals(" + n + ");");
                } else {
                    java.add(b + " == " + n);
                }
            }
        }
        else if(s.equalsIgnoreCase("/="))
        {
            if(charVariables.contains(n))
            {
                java.add("!" + b + ".equals(" + n + ") ");
            }
            else {
                java.add(b + " == " + n);
            }
        }


        return "uncaught error";
    }

    String arithmetic(String s, String b, String n, LinkedList<String> java)
    {

        if(s.equalsIgnoreCase("+"))
        {
            if(java.getLast().equalsIgnoreCase(b))
            {

                java.add(s);
                java.add(n);

            }
            else
            {

                java.add(b);
                java.add(s);
                java.add(n);
            }
        }
        if(s.equalsIgnoreCase("-"))
        {
            if(java.getLast().equalsIgnoreCase(b))
            {

                java.add(s);
                java.add(n);

            }
            else
            {

                java.add(b);
                java.add(s);
                java.add(n);
            }
        }
        if(s.equalsIgnoreCase("*"))
        {
            if(java.getLast().equalsIgnoreCase(b))
            {

                java.add(s);
                java.add(n);

            }
            else
            {

                java.add(b);
                java.add(s);
                java.add(n);
            }
        }
        if(s.equalsIgnoreCase("/"))
        {
            if(java.getLast().equalsIgnoreCase(b))
            {

                java.add(s);
                java.add(n);

            }
            else
            {

                java.add(b);
                java.add(s);
                java.add(n);
            }
        }
        if(s.equalsIgnoreCase("="))
        {
            if(java.getLast().equalsIgnoreCase(b))
            {

                java.add(s);
                java.add(n);

            }
            else
            {

                java.add(b);
                java.add(s);
                java.add(n);
            }
        }

        return "Uncaught error";
    }
}
