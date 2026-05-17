package Cells;
/**
 * A {@link Cell} that holds an integer value.
 * <p>
 * Created by {@link CellFactory} when the raw cell data can be parsed by
 * {@link Integer}.
 * </p>
 */
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
