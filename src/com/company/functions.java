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
}
