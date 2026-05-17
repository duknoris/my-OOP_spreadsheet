package Cells;
/**
 * A {@link Cell} that holds a plain-text string value.
 * <p>
 * Created by {@link CellFactory} when the raw cell data is enclosed in
 * double-quotes (e.g. {@code "Hello"})
 */
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
