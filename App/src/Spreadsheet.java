import Cells.Cell;

import java.util.ArrayList;
import java.util.List;

public class Spreadsheet {


    List<List<Cell>> table;

    public Spreadsheet() {
        this.table = new ArrayList<>();
    }

    public void addRow(List<Cell> row) {
        this.table.add(row);
    }

    public String printTable(){
        StringBuilder builder = new StringBuilder();

        for (List<Cell> row : table){
            for (Cell cell :row){

                builder.append(cell.getDisplayValue()).append(" | ");
            }
            builder.append("\n");
        }

        return builder.toString();

    }

}
