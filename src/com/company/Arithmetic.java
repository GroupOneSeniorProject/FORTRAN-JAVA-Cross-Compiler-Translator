import java.util.*;


public class Arithmetic
{

    public Arithmetic()
    {

    }

    public String add(String[] s, int index)
    {
        StringBuilder build = new StringBuilder();

        for (int i = index; i < s.length; i++)
        {

            build.append(s[i]);
        }
        return build.toString() + ";";
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
