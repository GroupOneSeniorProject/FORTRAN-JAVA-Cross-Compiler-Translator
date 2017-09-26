import java.util.*;


public class Arithmetic
{

    public Arithmetic()
    {

    }
    //building the string for addition
    public String add(String[] s, int index)
    {
        StringBuilder build = new StringBuilder();

        for (int i = index; i < s.length; i++)
        {

            build.append(s[i]);
        }
        return build.toString() + ";";
    }
    //building the string for boolean assignment
    public String boolarith(String[] s, int index)
    {
        StringBuilder build = new StringBuilder();
        if(s.length - index > 1) {
            for (int i = index; i < index + 2; i++) {
                build.append(s[i]);

            }
            return build.toString() + " ";
        }
        else
            return "";

    }

    public String checkType(String[] s, int index)
    {
        for (int i = index; i < s.length; i++)
            if (s[i].equalsIgnoreCase("+"))
            {
                String ret = add(s, index);
                i = s.length;
                return ret;

            }
        return "";
    }




}