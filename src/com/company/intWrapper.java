public class intWrapper {

    int value;

    intWrapper()
    {
        this.value = 0;
    }

    intWrapper(int input)
    {
        this.value = input;
    }

    int getValue()
    {
        return this.value;
    }

    void setValue(int input)
    {
        this.value = input;
    }

    void addValue(int input)
    {
        value += input;
    }

    void subtractValue(int input)
    {
        value -= input;
    }

    void multiplyValue(int input)
    {
        value *= input;
    }

    void divideValue(int input)
    {
        value /= input;
    }

}