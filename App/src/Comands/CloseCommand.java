package Comands;

public class CloseCommand implements Command {
    @Override
    public String execute(String arguments, CommandContext context) {
        if (context.currentFilePath == null || context.currentFilePath.isEmpty()) {
            return "No file is open to be closed";
        }
        String path = context.currentFilePath;
        context.currentFilePath = null;
        context.spreadsheet = new Spreadsheet();
        return "Successfully closed " + path;
    }
}
