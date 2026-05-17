package Comands;

import java.util.HashMap;
import java.util.Map;

/**
 * Parses and dispatches raw user input to the appropriate {@link Command}.
 *
 * <p>Registered commands </p>
 * <ul>
 *   <li>{@code open} → {@link OpenCommand}</li>
 *   <li>{@code close} → {@link CloseCommand}</li>
 *   <li>{@code save as} → {@link SaveAsCommand}</li>
 *   <li>{@code save} → {@link SaveCommand}</li>
 *   <li>{@code help} → {@link HelpCommand}</li>
 *   <li>{@code exit} → {@link ExitCommand}</li>
 *   <li>{@code print} → {@link PrintCommand}</li>
 *   <li>{@code edit} → {@link EditCommand}</li>
 * </ul>
 *
 * <p><strong>Note:</strong> {@code "save as"} must be checked before
 * {@code "save"} to avoid the shorter keyword consuming the longer one.</p>
 */
public class CommandProcessor {

    private CommandContext context;
    private Map<String, Command> commands;
    private String lastCommand;

    public CommandProcessor() {
        this.context = new CommandContext();
        this.commands = new HashMap<>();
        commands.put("open",    new OpenCommand());
        commands.put("close",   new CloseCommand());
        commands.put("save as", new SaveAsCommand());
        commands.put("save",    new SaveCommand());
        commands.put("help",    new HelpCommand());
        commands.put("exit",    new ExitCommand());
        commands.put("print",   new PrintCommand());
        commands.put("edit",    new EditCommand());

    }

    public String executeCommand(String input) {
        input = input.trim();
        lastCommand = null;

        String[] commandNames = {"open", "close", "save as", "save", "help", "exit", "print", "edit"};

        for (String name : commandNames) {
            if (input.startsWith(name)) {
                lastCommand = name;
                String arguments = input.substring(name.length()).trim();
                return commands.get(name).execute(arguments, context);
            }
        }

        return "Unknown command. Try \"help\"";
    }

    public String getLastCommand() {
        return lastCommand;
    }
}
