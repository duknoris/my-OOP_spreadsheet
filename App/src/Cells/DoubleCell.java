package Cells;

public class DoubleCell implements Cell {
    private double value;

    public DoubleCell(double value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public String getDisplayValue() {
        return String.valueOf(value);
    }
}
