package Comands;

public class ExitCommand implements Command {
    @Override
    public String execute(String arguments, CommandContext context) {
        return "Exiting the program...";
    }
}
