import java.util.LinkedList;
import java.util.StringJoiner;
import java.util.*;

public class AssignStatement
{
    public AssignStatement()
    {

    }

    public String integer(String[] s, int index)
    {
        StringBuilder build = new StringBuilder();
        build.ensureCapacity(100);
        build.append("int ");
        for (int i = index; i < s.length; i++)
        {
            if (!(s[i].equalsIgnoreCase("::") || s[i].equalsIgnoreCase("integer")))
            {
                build.append(s[i]+" ");
            }
        }


        return build.toString() ;
    }
}
