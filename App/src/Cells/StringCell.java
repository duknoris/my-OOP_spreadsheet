package Cells;

public class StringCell implements Cell {

    private String text;

    public StringCell(String text) {
        this.text = text;
    }


    @Override
    public String  getValue() {
        return text;

    }

    @Override
    public String  getDisplayValue() {
        return text;

    }
}
