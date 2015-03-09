package Modules;

public class ProgressValues {

    private int progressStartValue = 0;
    private int progressCurrentValue = 0;
    private int progressEndValue = 0;

    public ProgressValues(int startValue, int endValue) {
        progressStartValue = startValue;
        progressEndValue = endValue;
    }

    public int getStratValue() {
        return progressStartValue;
    }

    public int getEndtValue() {
        return progressEndValue;
    }

    public void setCurrentValue(int value) {
        progressCurrentValue = value;

    }

    public int getCurrentValue() {
        return progressCurrentValue;
    }
}
