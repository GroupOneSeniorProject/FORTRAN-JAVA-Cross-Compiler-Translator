public class doubleWrapper {

    double value;

    doubleWrapper()
    {
        this.value = 0;
    }

    doubleWrapper(double input)
    {
        this.value = input;
    }

    double getValue()
    {
        return this.value;
    }

    void setValue(double input)
    {
        this.value = input;
    }

    void addValue(double input)
    {
        value += input;
    }

    void subtractValue(double input)
    {
        value -= input;
    }

    void multiplyValue(double input)
    {
        value *= input;
    }

    void divideValue(double input)
    {
        value /= input;
    }

}