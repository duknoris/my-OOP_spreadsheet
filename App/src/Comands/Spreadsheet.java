package Comands;

import Cells.Cell;
import Cells.CellFactory;
import Cells.FormulaCell;
import Cells.StringCell;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Spreadsheet {


    List<List<Cell>> table;

    public Spreadsheet() {
        this.table = new ArrayList<>();
    }

    public void addRow(List<Cell> row) {
        this.table.add(row);
    }

    public String printTable() {
        if (table.isEmpty()) return "Empty table";

        int numCols = 0;
        for (List<Cell> row : table) {
            if (row.size() > numCols) numCols = row.size();
        }

        int[] colWidths = new int[numCols];
        for (List<Cell> row : table) {
            for (int i = 0; i < row.size(); i++) {
                String val = getDisplayValue(row.get(i));
                if (val.length() > colWidths[i]) colWidths[i] = val.length();
            }
        }

        StringBuilder builder = new StringBuilder();
        for (List<Cell> row : table) {
            builder.append("|");
            for (int i = 0; i < numCols; i++) {
                String val = (i < row.size()) ? getDisplayValue(row.get(i)) : "";
                builder.append(String.format(" %-" + colWidths[i] + "s |", val));
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private String getDisplayValue(Cell cell) {
        if (cell instanceof FormulaCell) {
            return ((FormulaCell) cell).evaluate(table);
        }
        return cell.getDisplayValue();
    }

    public String loadFromFile(String filePath) {
        this.table.clear();
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
                return "Successfully created and opened new empty file: " + filePath;
            } catch (IOException e) {
                return "Error creating file: " + e.getMessage();
            }
        }

        try (Scanner fileScanner = new Scanner(file)) {
            int rowIndex = 0;
            while (fileScanner.hasNextLine()) {
                rowIndex++;
                String line = fileScanner.nextLine();
                String[] stringCells = line.split(",", -1);

                List<Cell> currentRow = new ArrayList<>();
                for (int colIndex = 0; colIndex < stringCells.length; colIndex++) {
                    try {
                        currentRow.add(CellFactory.create(stringCells[colIndex]));
                    } catch (RuntimeException e) {
                        return "Error row " + rowIndex + " col " + (colIndex + 1)
                          + " "  + stringCells[colIndex].trim() + " is unknown data type";
                    }
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

    public String setCell(int row, int column,String content ){
        int rowIndex = row - 1;
        int colIndex = column - 1;

        if (rowIndex < 0 || colIndex < 0) {
            return "Error: row and column must be greater than 0";
        }

        while (table.size() <= rowIndex) {
            table.add(new ArrayList<>());
        }

        List<Cell> currentRow = table.get(rowIndex);

        while (currentRow.size() <= colIndex) {
            currentRow.add(new Cells.EmptyCell());
        }

        try {
            currentRow.set(colIndex, CellFactory.create(content));
            return "Successfully edited R" + row + "C" + column;
        } catch (RuntimeException e) {
            return "Error: " + e.getMessage();
        }
    }

    public String saveToFile(String filePath) {
        String content = translateToText();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
            return "Successfully saved " + filePath;
        } catch (IOException e) {
            return "Error saving file: " + e.getMessage();
        }
    }
}
