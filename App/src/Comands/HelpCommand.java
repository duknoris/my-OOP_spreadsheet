package Comands;

/**
 * Command that prints a summary of all available commands and their syntax.
 * <p>
 * Invoked with: {@code help}
 * </p>
 */
public class HelpCommand implements Command {
    @Override
    public String execute(String arguments, CommandContext context) {
        return "The following commands are supported: \n" +
                "open <file>       opens <file> \n" +
                "close             closes current opened file\n" +
                "save              saves the currently open file\n" +
                "save as <file>    saves the currently open file in <file>\n" +
                "help              prints this information\n" +
                "exit              exites the program \n" +
                "print             prints the content of the spreadsheet \n" +
                "edit              R<number of row>C<number of line> <new content of cell>      \n" +
                "                  replace the content of the selector cell whet the new content \n " ;
    }
}
