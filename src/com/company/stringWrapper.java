public class stringWrapper {

    String value;

    stringWrapper()
    {
        value = "";
    }

    stringWrapper(String input)
    {
        value = input;
    }

    String getString()
    {
        return this.value;
    }

    void setString(String input)
    {
        this.value = input;
    }

    int getStringLength()
    {
        return this.value.length();
    }

    char getCharAt(int index)
    {
        return this.value.charAt(index);
    }

}