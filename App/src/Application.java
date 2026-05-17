import Comands.CommandProcessor;

import java.util.Scanner;

/**
 * Entry point of the spreadsheet application.
 * <ol>
 *   <li>Reads a line of text from standard input.</li>
 *   <li>Prints the resulting message to standard output.</li>
 *   <li>Terminates when the {@code exit} command is executed.</li>
 * </ol>
 */
public class Application {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean isRunning =true;
        CommandProcessor commandProcessor =new CommandProcessor();
        do {
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()){
                System.out.println(commandProcessor.executeCommand(input));
            }

            if (commandProcessor.getLastCommand() == "exit"){
                isRunning=false;
                scanner.close();
            }
        }while (isRunning);

    }
}
