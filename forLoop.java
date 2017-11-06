import java.util.LinkedList;

public class forLoop {

    String whileLoop(String[] s, int index)
    {
        functions fun = new functions();
        StringBuilder builder = new StringBuilder();
        String whileParse = s[index].substring(0, 5);
        builder.append("while(");
        builder.append(s[index].substring(6, s[index].length()));

        builder.append(fun.logical(s[index + 1]));
        builder.append(s[index + 2]);
        builder.append("{");

        return builder.toString();
    }

    /*
    * integer  :: i
              do  i=0,20
                  print  *,i
              end  do
    */
    String forloop(String[] s){
        StringBuilder builder = new StringBuilder();
        if (s[1].equalsIgnoreCase("DO")) // interprets the lines for DO call in function
        {
            for (int i = 0; i < s.length; i++) {


                String next = s[i];
                String equals = s[i];
                String num = s[i];
                num = num.replace(",", ""); // removes the commas
                builder.append("\t\tfor (" + next + equals + num + "; " + next + " < " +
                        s[i] + "; " + next + "++)\n\t\t{");
            }

        }
        return builder.toString();
    } // END OF DO
}
