package Cells;

/**
 * A {@link Cell} that holds a double-precision floating-point value.
 * <p>
 * Created by {@link CellFactory} when the raw cell data can be parsed by
 * {@link Double}.
 * </p>
 */
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
