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
    String ifelse(String s)
    {
        if(s.equalsIgnoreCase("if"))
        {
            return "if(";
        }
        else if(s.equalsIgnoreCase("then"))
        {
            return ")\n{";
        }
        else if(s.equalsIgnoreCase("else"))
        {
            return "}\nelse\n{";
        }

        return "uncaught error";
    }
    String comparison(String s)
    {

        if(s.equalsIgnoreCase("<"))
        {
            return " < ";
        }
        else if(s.equalsIgnoreCase("<="))
        {
            return " <= ";
        }
        else if(s.equalsIgnoreCase(">"))
        {
            return " > ";
        }
        else if(s.equalsIgnoreCase(">="))
        {
            return " >= ";
        }
        else if(s.equalsIgnoreCase("=="))
        {
            return " == ";
        }
        else if(s.equalsIgnoreCase("/="))
        {
            return " != ";
        }
        

        return "uncaught error";
    }
}
