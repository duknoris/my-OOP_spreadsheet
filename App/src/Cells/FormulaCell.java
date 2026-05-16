package Cells;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormulaCell implements Cell {
    private String formula;

    public FormulaCell(String formula) {
        this.formula = formula;
    }
    @Override
    public String getValue() {
        return formula;
    }

    @Override
    public String getDisplayValue() {
        return "ERROR: no table context";
    }

    public String evaluate(List<List<Cell>> table) {
        try {
            String expression = formula.substring(1); // маха "="
            expression = resolveReferences(expression, table);
            double result = evaluateExpression(expression);

            if (result == Math.floor(result) && !Double.isInfinite(result)) {
                return String.valueOf((long) result);
            }
            return String.valueOf(result);

        } catch (ArithmeticException e) {
            return "ERROR";
        } catch (Exception e) {
            return "ERROR";
        }
    }

    private String resolveReferences(String expression, List<List<Cell>> table) {
        Pattern pattern = Pattern.compile("R(\\d+)C(\\d+)");
        Matcher matcher = pattern.matcher(expression);
        StringBuffer result = new StringBuffer();

        while (matcher.find()) {
            int row = Integer.parseInt(matcher.group(1)) - 1;
            int col = Integer.parseInt(matcher.group(2)) - 1;

            double value = getCellNumericValue(row, col, table);
            matcher.appendReplacement(result, String.valueOf(value));
        }
        matcher.appendTail(result);
        return result.toString();
    }



    private double getCellNumericValue(int row, int col, List<List<Cell>> table) {
        if (row < 0 || row >= table.size()) return 0;
        List<Cell> tableRow = table.get(row);
        if (col < 0 || col >= tableRow.size()) return 0;

        Cell cell = tableRow.get(col);

        if (cell instanceof EmptyCell) return 0;

        String val = cell.getValue();

        try {
            return Double.parseDouble(val);
        } catch (NumberFormatException e) {
            return convertStringToNumber(val);
        }
    }


    private double convertStringToNumber(String val) {
        try {
            return Double.parseDouble(val);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double evaluateExpression(String expr) {
        expr = expr.trim();

        // намери последното + или - (с най-нисък приоритет)
        int i = expr.length() - 1;
        int parenDepth = 0;
        while (i >= 0) {
            char c = expr.charAt(i);
            if (c == ')') parenDepth++;
            if (c == '(') parenDepth--;
            if (parenDepth == 0 && (c == '+' || c == '-') && i > 0) {
                double left = evaluateExpression(expr.substring(0, i));
                double right = evaluateExpression(expr.substring(i + 1));
                return c == '+' ? left + right : left - right;
            }
            i--;
        }

        // намери последното * или /
        i = expr.length() - 1;
        parenDepth = 0;
        while (i >= 0) {
            char c = expr.charAt(i);
            if (c == ')') parenDepth++;
            if (c == '(') parenDepth--;
            if (parenDepth == 0 && (c == '*' || c == '/')) {
                double left = evaluateExpression(expr.substring(0, i));
                double right = evaluateExpression(expr.substring(i + 1));
                if (c == '/' && right == 0) throw new ArithmeticException("Division by zero");
                return c == '*' ? left * right : left / right;
            }
            i--;
        }

        // намери ^ (степенуване)
        i = expr.length() - 1;
        parenDepth = 0;
        while (i >= 0) {
            char c = expr.charAt(i);
            if (c == ')') parenDepth++;
            if (c == '(') parenDepth--;
            if (parenDepth == 0 && c == '^') {
                double left = evaluateExpression(expr.substring(0, i));
                double right = evaluateExpression(expr.substring(i + 1));
                return Math.pow(left, right);
            }
            i--;
        }

        // скоби
        if (expr.startsWith("(") && expr.endsWith(")")) {
            return evaluateExpression(expr.substring(1, expr.length() - 1));
        }

        // просто число
        return Double.parseDouble(expr.trim());
    }




}
