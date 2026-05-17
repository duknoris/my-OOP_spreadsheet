package Comands;
/**
 * Command that saves the current spreadsheet to its existing file path.
 * <p>
 * Invoked with: {@code save}
 * </p>
 */
public class SaveCommand implements Command {
    @Override
    public String execute(String arguments, CommandContext context) {
        if (context.currentFilePath == null || context.currentFilePath.isEmpty()) {
            return "No file is open to be saved";
        }
        return context.spreadsheet.saveToFile(context.currentFilePath);
    }
}
