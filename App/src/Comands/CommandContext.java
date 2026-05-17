package Comands;
/**
 * Holds the shared mutable state of the running spreadsheet application.
 */
public class CommandContext {
    public Spreadsheet spreadsheet;
    public String currentFilePath;

    public CommandContext() {
        this.spreadsheet = new Spreadsheet();
        this.currentFilePath = null;
    }
}