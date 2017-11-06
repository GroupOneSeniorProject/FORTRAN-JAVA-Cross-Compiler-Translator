public class Math_func2
{
    public String func(String[] s, int index)
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
        /*
        for (int i = index + 1; i < s.length; i++)
        {
            build.append(s[i] + " ");
        }
        */
        build.append(";");
        return build.toString();
    }
}