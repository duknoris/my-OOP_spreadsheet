package Comands;

public class CommandContext {
    public Spreadsheet spreadsheet;
    public String currentFilePath;

    public CommandContext() {
        this.spreadsheet = new Spreadsheet();
        this.currentFilePath = null;
    }
}