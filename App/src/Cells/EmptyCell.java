package Cells;
/**
 * A {@link Cell} that contains no value.
 * <p>
 * {@code EmptyCell} is used to represent blank entries in the spreadsheet –
 * cells that have never been assigned a value
 * </p>
 */
public class EmptyCell implements Cell {


    @Override
    public String getValue() {
        return "";
    }

    @Override
    public String getDisplayValue() {
        return "";
    }
}
