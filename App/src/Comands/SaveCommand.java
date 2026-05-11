package Comands;

public class SaveCommand implements Command {
    @Override
    public String execute(String arguments, CommandContext context) {
        if (context.currentFilePath == null || context.currentFilePath.isEmpty()) {
            return "No file is open to be saved";
        }
        return context.spreadsheet.saveToFile(context.currentFilePath);
    }
}
