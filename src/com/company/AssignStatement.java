import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.*;

public class AssignStatement {
  public AssignStatement() {

  }
  //building integer initialization and declaration
  public String integer(String[] s, int index, ArrayList<String> integer, ArrayList<String> globals) {

    StringBuilder build = new StringBuilder();
    build.ensureCapacity(100);
    //globals.add("a");
    //globals.add("b");
    //globals.add("z");
    //globals.add("bool");
    //globals.add("string1");

    //build.append("int ");
    for (int i = index; i < s.length; i++) {
      if (!(globals.contains(s[i]) || s[i].equalsIgnoreCase("::") || s[i].equalsIgnoreCase("integer") ||
              s[i].equalsIgnoreCase(",") || s[i].equalsIgnoreCase("=")
              || s[i].equalsIgnoreCase("0"))) {
        build.append("int "+s[i].replace(",","") + " = 0;\n");
        integer.add(s[i].replace(",",""));
      }
    }
    for (int i = 0; i < globals.size(); i++)
    {
      for (int j = 0; j < s.length; j++)
      {
        if (s[j].contains(globals.get(i)) && integer.contains(s[j]))
        {
          build.append("intWrapper " + globals.get(i) + " = new intWrapper();\n");
          int temp = build.indexOf("int " +globals.get(i)+ " = 0;");
          build.replace(temp, temp+j+1, "");
        }
      }
    }
    //build.append(";");


    return build.toString();
  }
  //building double initialization and declaration
  public String real(String[] s, int index,  ArrayList<String> real, ArrayList<String> globals) {
    System.out.println("IMADEIT");
    StringBuilder build = new StringBuilder();
    build.ensureCapacity(100);
    System.out.println(real.toString());
    //build.append("double ");
    for (int i = index; i < s.length; i++) {
      if (!(s[i].equalsIgnoreCase("::") || s[i].equalsIgnoreCase("real") ||
              s[i].equalsIgnoreCase(",") || s[i].equalsIgnoreCase("=")
              || s[i].equalsIgnoreCase("0"))) {
        build.append("double " +s[i].replaceAll(",", "") + " = 0;\n" );
        real.add(s[i].replace(",",""));
      }
    }
    for (int i = 0; i < globals.size(); i++)
    {
      for (int j = 0; j < s.length; j++)
      {
        if (globals.get(i).contains(s[j]) && real.contains(s[j]))
        {
          build.append("doubleWrapper " + globals.get(i) + " = new doubleWrapper();\n");
          int temp = build.indexOf("double " +globals.get(i)+ " = 0;");
          if(temp >= 0)
          build.replace(temp, temp+j+1, "");
        }
      }
    }
    //build.append(";");

    return build.toString();
  }
  //building boolean initilazation and declaration
  public String bool(String[] s, int index,  ArrayList<String> logical, ArrayList<String> globals) {
    StringBuilder build = new StringBuilder();
    build.ensureCapacity(100);
    build.append("boolean ");
    for (int i = index; i < s.length; i++) {
      if (!(s[i].equalsIgnoreCase("::") || s[i].equalsIgnoreCase("logical"))) {
        build.append(s[i] + " ");

        logical.add(s[i].replace(",",""));
      }

    }
    for (int i = 0; i < globals.size(); i++)
    {
      System.out.println(globals.get(i));
      for (int j = 0; j < s.length; j++)
      {
        if (s[j].contains(globals.get(i)) && logical.contains(s[j]))
        {
          build.append("booleanWrapper " + globals.get(i) + " = new booleanWrapper();\n");

          int temp = build.indexOf("boolean "+globals.get(i));
          if (temp <= 0)
            break;
          build.replace(temp, temp+j, "");
        }
      }
    }


    build.append(";");
    return build.toString();
  }
  //string init and declare
  public String character(String[] s, int index,  ArrayList<String> character, ArrayList<String> globals) {
    StringBuilder build = new StringBuilder();
    build.ensureCapacity(100);
    build.append("String ");
    int i = index;
    for (int k = 0; k < character.size(); k++)
      System.out.println(character.get(k));
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
    for (int l = 0; l < globals.size(); l++)
    {
      for (int j = 0; j < s.length; j++)
      {
        if (s[j].contains(globals.get(l)) && character.contains(s[j]))
        {
          build.append("\nstringWrapper " + globals.get(l) + " = new stringWrapper();\n");
          int temp = build.indexOf("String " + globals.get(l));
          build.replace(temp, temp+j, "");
        }
      }
    }
    return build.toString();
  }

  public String intArray(String[] s, int index,  ArrayList<String> integer)
  {
    StringBuilder builder = new StringBuilder();
    boolean isArray = false;

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
        isArray = true;
        int first = s[i].indexOf('(');
        int second = s[i].indexOf(')');
        arrLen = s[i].substring(first + 1, second);
        //builder.append("[" + arrLen + "]");
      }
      if (!s[i].equalsIgnoreCase(" ") && !s[i].contains("dimension") && !s[i].equalsIgnoreCase("::") &&
              !s[i].contains("integer") && !s[i].equalsIgnoreCase(""))
      {

        integer.add(s[i].replaceAll("\t" , ""));

        builder.append(" " + s[i].replaceAll(",", ""));

      }

    }

    builder.append(" = new int[" + arrLen + "];");
    if(isArray)
      return builder.toString();
    else
      return "";
  }

  //real arrays

  public String realArray(String[] s, int index,  ArrayList<String> real)
  {
    StringBuilder builder = new StringBuilder();

    boolean isAarray = false;

    builder.ensureCapacity(100);
    String arrLen = "";
    for(int i = 0; i < s.length; i++)
    {
      if(s[i].equalsIgnoreCase("real,"))
      {
        builder.append("double[]");
      }
      if(s[i].contains("dimension"))
      {
        isAarray = true;
        int first = s[i].indexOf('(');
        int second = s[i].indexOf(')');
        arrLen = s[i].substring(first + 1, second);
        //builder.append("[" + arrLen + "]");
      }
      if (!s[i].equalsIgnoreCase(" ") && !s[i].contains("dimension") && !s[i].equalsIgnoreCase("::") &&
              !s[i].contains("real") && !s[i].equalsIgnoreCase(""))
      {

        real.add(s[i].replaceAll("\t" , ""));

        builder.append(" " + s[i].replaceAll(",", ""));

      }

    }

    builder.append(" = new double[" + arrLen + "];");

    if(isAarray)
      return builder.toString();
    else
      return "";
  }

  //logical arrays

  public String boolArray(String[] s, int index,  ArrayList<String> bool)
  {
    StringBuilder builder = new StringBuilder();

    builder.ensureCapacity(100);
    String arrLen = "";
    for(int i = 0; i < s.length; i++)
    {
      if(s[i].equalsIgnoreCase("logical,"))
      {
        builder.append("boolean[]");
      }
      if(s[i].contains("dimension"))
      {
        int first = s[i].indexOf('(');
        int second = s[i].indexOf(')');
        arrLen = s[i].substring(first + 1, second);
        //builder.append("[" + arrLen + "]");
      }
      if (!s[i].equalsIgnoreCase(" ") && !s[i].contains("dimension") && !s[i].equalsIgnoreCase("::") &&
              !s[i].contains("logical") && !s[i].equalsIgnoreCase(""))
      {

        bool.add(s[i].replaceAll("\t" , ""));

        builder.append(" " + s[i].replaceAll(",", ""));

      }

    }

    builder.append(" = new boolean[" + arrLen + "];");

    return builder.toString();

  }

}
