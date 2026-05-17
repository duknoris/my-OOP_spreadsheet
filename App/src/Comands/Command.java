package Comands;
/**
 * Represents a single user command in the spreadsheet application.
 */
public interface Command {
    String execute(String arguments, CommandContext context);
}
