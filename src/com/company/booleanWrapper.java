public class booleanWrapper {

    boolean value;

    booleanWrapper()
    {
        this.value = false;
    }

    booleanWrapper(boolean input)
    {
        this.value = input;
    }

    boolean getValue()
    {
        return this.value;
    }

    void setValue(boolean input)
    {
        this.value = input;
    }

}