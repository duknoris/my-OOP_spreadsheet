package Cells;

public class CellFactory {
    public static Cell create(String data){
        data = data.trim();
        if (data.isEmpty()){
            return new EmptyCell();
        }
        if (data.startsWith("\"") && data.endsWith("\"")){
            String textContent = data.substring(1 ,data.length() -1);
            return new StringCell(textContent);
        }
        if (data.startsWith("=")) {
            return new FormulaCell(data);
        }
        try {
            int intValue = Integer.parseInt(data);
            return new IntCell(intValue);
        } catch (NumberFormatException e) {

        }
        try {
            double doubleValue = Double.parseDouble(data);
            return new DoubleCell(doubleValue);
        } catch (NumberFormatException e) {
            throw new RuntimeException(" \""+ data + "\" is not valued input");

        }


    }
}
