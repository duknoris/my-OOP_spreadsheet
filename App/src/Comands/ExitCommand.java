package Comands;

/**
 * Command that signals the application to terminate.
 * <p>
 * Invoked with: {@code exit}
 * </p>
 */
public class ExitCommand implements Command {
    @Override
    public String execute(String arguments, CommandContext context) {
        return "Exiting the program...";
    }
}
