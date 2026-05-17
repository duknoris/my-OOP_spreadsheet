package Comands;
/**
 * Command that opens a spreadsheet file for editing.
 * <p>
 * Invoked with: {@code open <file>}
 * </p>
 */
public class OpenCommand implements Command {
    @Override
    public String execute(String filePath, CommandContext context) {
        if (filePath.isEmpty()) {
            return "Error: no file path provided";
        }
        String result = context.spreadsheet.loadFromFile(filePath);
        if (!result.startsWith("Error")) {
            context.currentFilePath = filePath;
        }
        return result;
    }
}
