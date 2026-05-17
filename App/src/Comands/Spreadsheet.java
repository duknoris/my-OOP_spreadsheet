package Comands;

import Cells.Cell;
import Cells.CellFactory;
import Cells.FormulaCell;
import Cells.StringCell;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * <p>
 * The spreadsheet is modelled as a list of rows, each row being a list of
 * {@link Cell} objects.
 * </p>
 * <h3>File format</h3>
 * <p>
 * Files are plain-text xml where each line represents a row and commas
 * separate cells.
 * </p>
 * <ul>
 *   <li>Empty string → {@link Cells.EmptyCell}</li>
 *   <li>Integer literal → {@link Cells.IntCell}</li>
 *   <li>Floating-point literal → {@link Cells.DoubleCell}</li>
 *   <li>Double-quoted text (e.g. {@code "hello"}) → {@link StringCell}</li>
 *   <li>Formula starting with {@code =} (e.g. {@code =R1C1+R1C2}) →
 *       {@link FormulaCell}</li>
 * </ul>
 */
public class Spreadsheet {


    List<List<Cell>> table;

    public Spreadsheet() {
        this.table = new ArrayList<>();
    }

    public void addRow(List<Cell> row) {
        this.table.add(row);
    }

    /**
     * Renders the spreadsheet
     */
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


    /**
     * Returns the display value of a cell
     */
    private String getDisplayValue(Cell cell) {
        if (cell instanceof FormulaCell) {
            return ((FormulaCell) cell).evaluate(table);
        }
        return cell.getDisplayValue();
    }

    /**
     * Loads the spreadsheet from a hml file, replacing all current data.
     * <p>
     * If the file does not exist it is created as an empty file and the
     * spreadsheet remains empty. Each line of the file is split on commas
     */
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


    /**
     * Serialises the spreadsheet back to its XML text representation.
     * @return the full XML content of the spreadsheet as a single string
     */
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


    /**
     * Sets or replaces the cell at the given 1-based row and column.
     * <p>
     * If the target row or column does not yet exist, the table is extended
     * with {@link Cells.EmptyCell}
     * </p>
     *
     * @param row     the 1-based row
     * @param column  the 1-based column
     * @param content the new cell content in any format accepted by
     *                {@link CellFactory}
     */
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

    /**
     * Writes the spreadsheet to a file at the given path using the XML format
     */
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
