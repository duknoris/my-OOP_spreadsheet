import Cells.Cell;
import Cells.CellFactory;

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

    public String loadFromFile(String filePath) {
        this.table.clear();
        java.io.File file = new java.io.File(filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
                return "Successfully created and opened new empty file: " + filePath;
            } catch (java.io.IOException e) {
                return "Error creating file: " + e.getMessage();
            }
        }

        try (java.util.Scanner fileScanner = new java.util.Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] stringCells = line.split(",", -1);

                List<Cell> currentRow = new ArrayList<>();
                for (String rawCellData : stringCells) {
                    currentRow.add(CellFactory.create(rawCellData));
                }
                this.addRow(currentRow);
            }
            return "Successfully opened " + filePath;
        } catch (java.io.FileNotFoundException e) {
            return "Error: File not found.";
        }
    }


    public String saveToFile(String filePath){
        return "";
    }

}
