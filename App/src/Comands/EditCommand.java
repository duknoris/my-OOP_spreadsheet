package Comands;

public class EditCommand implements Command {
    @Override
    public String execute(String arguments, CommandContext context) {
        if (context.currentFilePath == null || context.currentFilePath.isEmpty()) {
            return "No file is open to be edited";
        }
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("R(\\d+)C(\\d+)\\s+(.+)");
        java.util.regex.Matcher matcher = pattern.matcher(arguments);

        if (matcher.find()) {
            int row = Integer.parseInt(matcher.group(1));
            int column = Integer.parseInt(matcher.group(2));
            String content = matcher.group(3);
            return context.spreadsheet.setCell(row, column, content);
        }
        return "Error: invalid address. Use R[row]C[col] [content]";
    }
}
