import Cells.Cell;
import Cells.IntCell;
import Cells.StringCell;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

            if (commandProcessor.getCommand() == "exit"){
                isRunning=false;
                scanner.close();
            }
        }while (isRunning);

    }
}
