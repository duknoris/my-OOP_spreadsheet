package Comands;

import java.io.File;

public class SaveAsCommand implements Command {
    @Override
    public String execute(String filePath, CommandContext context) {
        if (context.currentFilePath == null || context.currentFilePath.isEmpty()) {
            return "No file is open to be saved";
        }
        if (filePath.isEmpty()) {
            return "Error: no file path provided";
        }

        String result = context.spreadsheet.saveToFile(filePath);

        if (!result.startsWith("Error")) {
            if (!context.currentFilePath.equals(filePath)) {
                new File(context.currentFilePath).delete();
            }
            context.currentFilePath = filePath;
        }

        return result;
    }
}