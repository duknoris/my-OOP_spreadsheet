package Comands;

public interface Command {
    String execute(String arguments, CommandContext context);
}
