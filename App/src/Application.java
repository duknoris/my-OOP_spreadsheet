import Cells.Cell;
import Cells.IntCell;
import Cells.StringCell;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {

        Spreadsheet spreadsheet= new Spreadsheet();

        List<Cell> row1 = new ArrayList<>();
        row1.add(new IntCell(10));
        row1.add(new IntCell(20));
        row1.add(new StringCell("Hello"));

        List<Cell> row2 = new ArrayList<>();
        row2.add(new IntCell(100));
        row2.add(new StringCell("OOP"));
        row2.add(new IntCell(50));

        spreadsheet.addRow(row1);
        spreadsheet.addRow(row2);

        System.out.println(spreadsheet.printTable());

    }
}
