import java.util.ArrayList;

public class Math_func2
{
    public String func(String[] s, int index, ArrayList<String> globals)
    {
        StringBuilder build = new StringBuilder();
        build.ensureCapacity(100);
        String type = s[index].substring(0, 3);

        if(s[index].charAt(s[index].length() - 1) == ')')
        {
            switch (type){
                case "abs":
                    build.append("Math.");
                    build.append(s[index]);
                    break;
                case "sin":
                    build.append("Math.");
                    build.append(s[index]);
                    break;
                case "cos":
                    build.append("Math.");
                    build.append(s[index]);
                    break;
                case "tan":
                    build.append("Math.");
                    build.append(s[index]);
                    break;
                case "log":
                    build.append("Math.");
                    build.append(s[index]);
                    break;
                case "sqr":
                    build.append("Math.");
                    build.append(s[index]);
                    break;
            }

        } else if(s[index].contains(globals.get(index))){
            switch (type){
                case "abs":
                    build.append(globals.get(index) + ".setValue(Math.");
                    build.append(s[index]);
                    build.append(")");
                    break;
                case "sin":
                    build.append(globals.get(index) + ".setValue(Math.");
                    build.append(s[index]);
                    build.append(")");
                    break;
                case "cos":
                    build.append(globals.get(index) + ".setValue(Math.");
                    build.append(s[index]);
                    build.append(")");
                    break;
                case "tan":
                    build.append(globals.get(index) + ".setValue(Math.");
                    build.append(s[index]);
                    build.append(")");
                    break;
                case "log":
                    build.append(globals.get(index) + ".setValue(Math.");
                    build.append(s[index]);
                    build.append(")");
                    break;
                case "sqr":
                    build.append(globals.get(index) + ".setValue(Math.");
                    build.append(s[index]);
                    build.append(")");
                    break;
            }
        }
        else {
            switch (type) {
                case "abs":
                    build.append("Math.abs(");
                    break;
                case "sin":
                    build.append("Math.sin(");
                    break;
                case "cos":
                    build.append("Math.cos(");
                    break;
                case "tan":
                    build.append("Math.tan(");
                    break;
                case "log":
                    build.append("Math.log(");
                    break;
                case "sqr":
                    build.append("Math.sqrt(");
                    break;
            }
        }



        build.append(";");
        return build.toString();
    }
}