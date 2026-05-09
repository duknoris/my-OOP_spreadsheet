import Cells.Cell;
import Cells.CellFactory;
import Cells.StringCell;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import  java.io.File;
import java.util.Scanner;

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
        File file = new java.io.File(filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
                return "Successfully created and opened new empty file: " + filePath;
            } catch (java.io.IOException e) {
                return "Error creating file: " + e.getMessage();
            }
        }

        try (Scanner fileScanner = new Scanner(file)) {
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
        } catch (FileNotFoundException e) {
            return "Error: File not found.";
        }
    }


    public String translateToText(){
        StringBuilder sd = new StringBuilder();
        for (List<Cell> row : table){
            for (int i =0 ; i<row.size() ; i++){
                Cell cell =row.get(i);
                String val =cell.getValue();
                if (cell.getClass() == StringCell.class){
                    val = val.replace("\\", "\\\\").replace("\"", "\\\"");
                    val = "\"" + val + "\"";
                }
                sd.append(val);
                if (i<row.size()-1){
                    sd.append(",");
                }
            }
            sd.append("\n");
        }
        return sd.toString();
    }

}
