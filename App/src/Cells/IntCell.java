package Cells;

public class IntCell implements Cell {

    private int value;

    public IntCell(int value) {
        this.value = value;
    }


    @Override
    public String  getValue() {
        return String.valueOf(value);

    }

    @Override
    public String  getDisplayValue() {
        return String.valueOf(value);
    }
}
