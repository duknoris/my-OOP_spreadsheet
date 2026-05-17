package Comands;
/**
 * Command that prints the contents of the currently open spreadsheet to the
 * console.
 * <p>
 * Invoked with: {@code print}
 * </p>
 */
public class PrintCommand implements Command {
    @Override
    public String execute(String arguments, CommandContext context) {
        if (context.currentFilePath == null || context.currentFilePath.isEmpty()) {
            return "No file is open to be printed";
        }
        return context.spreadsheet.printTable();
    }
}
