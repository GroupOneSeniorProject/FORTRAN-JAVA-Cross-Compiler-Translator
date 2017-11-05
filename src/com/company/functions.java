import java.util.ArrayList;
import java.util.LinkedList;

public class functions
{
  public functions()
  {

  }
  //begin program
  String startprogram(String s) {
    String thisProgram = "public class " + s + "\n{\n\tpublic static void main(String[] args)\n\t{\n\t\tScanner scanner = new Scanner(System.in);\n";
    return thisProgram;
  }
  String endprogram(String s)
  {
    return "}\n";
  }

  //handle instances of logical comparison
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
  //handle if/else logic
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
  //comparison operators, b = before, s = current, n = next
  //b s n  for comparison
  String comparison(String s, String b, String n, ArrayList charVariables, LinkedList<String> java, ArrayList<String> integerArrays, ArrayList<String> realArrays, ArrayList<String> logicalArrays)
  {

    if(s.equalsIgnoreCase("<"))
    {
      if(java.getLast().equalsIgnoreCase(b))
      {


        java.add(s);
        if(integerArrays.contains(n) || realArrays.contains(n) || logicalArrays.contains(n)) {
          java.add(n + ".getValue()");
        }
        else
        {
          java.add(n);
        }
      }
      else
      {

        if(integerArrays.contains(b) || realArrays.contains(b) || logicalArrays.contains(b)) {
          java.add(b + ".getValue()");
        }
        else
        {
          java.add(n);
        }
        java.add(s);
        if(integerArrays.contains(n) || realArrays.contains(n) || logicalArrays.contains(n)) {
          java.add(n + ".getValue()");
        }
        else
        {
          java.add(n);
        }
      }
    }
    else if(s.equalsIgnoreCase("<="))
    {
      if(java.getLast().equalsIgnoreCase(b))
      {

        java.add(s);
        if(integerArrays.contains(n) || realArrays.contains(n) || logicalArrays.contains(n)) {
          java.add(n + ".getValue()");
        }
        else
        {
          java.add(n);
        }

      }
      else
      {

        if(integerArrays.contains(b) || realArrays.contains(b) || logicalArrays.contains(b)) {
          java.add(b + ".getValue()");
        }
        else
        {
          java.add(b);
        }
        java.add(s);
        if(integerArrays.contains(n) || realArrays.contains(n) || logicalArrays.contains(n)) {
          java.add(n + ".getValue()");
        }
        else
        {
          java.add(n);
        }
      }
    }
    else if(s.equalsIgnoreCase(">"))
    {
      if(java.getLast().equalsIgnoreCase(b))
      {

        java.add(s);
        if(integerArrays.contains(n) || realArrays.contains(n) || logicalArrays.contains(n)) {
          java.add(n + ".getValue()");
        }
        else
        {
          java.add(n);
        }

      }
      else
      {

        if(integerArrays.contains(b) || realArrays.contains(b) || logicalArrays.contains(b)) {
          java.add(b + ".getValue()");
        }
        else
        {
          java.add(b);
        }
        java.add(s);
        if(integerArrays.contains(n) || realArrays.contains(n) || logicalArrays.contains(n)) {
          java.add(n + ".getValue()");
        }
        else
        {
          if(integerArrays.contains(n) || realArrays.contains(n) || logicalArrays.contains(n)) {
            java.add(n + ".getValue()");
          }
          else
          {
            java.add(n);
          }
        }
      }
    }
    else if(s.equalsIgnoreCase(">="))
    {
      if(java.getLast().equalsIgnoreCase(b))
      {

        java.add(s);
        if(integerArrays.contains(n) || realArrays.contains(n) || logicalArrays.contains(n)) {
          java.add(n + ".getValue()");
        }
        else
        {
          java.add(n);
        }

      }
      else
      {

        if(integerArrays.contains(b) || realArrays.contains(b) || logicalArrays.contains(b)) {
          java.add(b + ".getValue()");
        }
        else
        {
          java.add(b);
        }
        java.add(s);
        if(integerArrays.contains(n) || realArrays.contains(n) || logicalArrays.contains(n)) {
          java.add(n + ".getValue()");
        }
        else
        {
          java.add(n);
        }
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
        java.add(b + " != " + n);
      }
    }


    return "uncaught error";
  }

  //comparison operators, b = before, s = current, n = next
  //b s n  for comparison
  //checking to ensure that the previous item is not already in the list

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
        //java.add(n);

      }
      else
      {

        java.add(b);
        java.add(s);
        //java.add(n);
      }
    }

    return "Uncaught error";
  }

  void identifyGlobalVariables(ArrayList<String> globalVariables, LinkedList<String> Fortran)
  {

    int index1 = 0;

    int index2 = 0;

    boolean globalsPresent = false;

    boolean inParenth = false;

    String global = "";

    for(int i = 0; i < Fortran.size(); i++)
    {

      String[] line = Fortran.get(i).split(" ");


      for(int j = 0; j < line.length; j++)
      {
        if(!line[j].equalsIgnoreCase(""))
        {
          //want the form "subroutine(....)" not "end subroutine"
          if(line[j].equalsIgnoreCase("subroutine") && !line[j - 1].equalsIgnoreCase("end"))
          {
            globalsPresent = true;
            j++;
          }
          //if we are inside the parentheses
          if(globalsPresent) {
            //walk along the characters in the string
            for (int k = 0; k < line.length; k++) {
              //if open paren
              if (line[j].length() > k && line[j].charAt(k) == '(') {

                inParenth = true;
                index1 = k + 1;
                //if open paren followed by comma (muliple variables
                if (line[j].length() >= k && line[j].charAt(line[j].length() - 1) == ',') {
                  globalVariables.add(line[j].substring(index1, line[j].length() - 1));
                }
                //if open paren followed by close paren (single variable)
                if (line[j].length() > k && line[j].charAt(line[j].length() - 1) == ')' && line[j].length() > 2) {

                  globalVariables.add(line[j].substring(index1, line[j].length() - 1));
                  globalsPresent = false;

                }

              }
              //if close paren (end of variables)
              else if (line[j].length() > k && line[j].charAt(line[j].length() - 1) == ')') {

                if (line[j].length() > 1) {

                  globalVariables.add(line[j].substring(0, line[j].length() - 1));

                }

                globalsPresent = false;
                break;

              }
              //if not open or close (more than two variables)
              else {

                if (line[j].length() > k && line[j].charAt(line[j].length() - 1) == ',') {

                  index2 = k;

                  for(int l = 0; l < line[j].length(); l++)
                  {

                    if(line[j].charAt(l) == '(')
                    {
                      index2 = l;
                      k = l;
                    }

                  }

                  globalVariables.add(line[j].substring(index2 + 1, line[j].length() - 1));

                } else if (line[j].length() >= k && line[j].charAt(0) == ',') {

                  globalVariables.add(line[j].substring(1, line[j].length()));

                } else {

                  globalVariables.add(line[j]);

                }

              }


            }

          }

        }

      }


    }

  }
}    
