public class CommandProcessor {

    private String currentFilePath;
    private Spreadsheet spreadsheet;
    private String command;
    private String filePath;



    public CommandProcessor() {
        this.currentFilePath =null;
        this.filePath = null;
        this.command = null;
    }

    public String executeCommand(String input){

        return translator(input);
    }

    protected String translator(String input){
        input = input.trim();
        String[] commandList = {"open" , "close" , "save" , "save as" , "help" , "exit"};

        filePath=null;
        String[] words = input.split(" ");
        filePath = words[words.length - 1];

        command=null;
        for (String currentCommand : commandList){
            if (input.startsWith(currentCommand)){
                command = currentCommand;
            }
        }

        if (command == null || command.isEmpty()){
            return "In-valet command tuy help";
        }

        return executeCommandLogic(command, filePath);
    }

    public String executeCommandLogic(String command , String filePath){
        switch (command){
            case "open":
                return filePath + " is open ";
            case "close"  :
                return filePath + " is close";
            case "save":
                return  filePath + " is save";
            case "save as"  :
                return getCurrentFilePath() + " is save at " + filePath;
            case "help" :
                return "The following commands are supported: \n" +
                        "open <file> opens <file> \n" +
                        "close             closes current opened file\n" +
                        "save              saves the currently open file\n" +
                        "save as <file>    saves the currently open file in <file>\n" +
                        "help              prints this information\n" +
                        "exit              exites the program";
            case "exit":
                return "Exiting the program...";
            default:
                return "unknown command";
        }
    }





    public String getCurrentFilePath() {
        return currentFilePath;
    }

    public void setCurrentFilePath(String currentFilePath) {
        this.currentFilePath = currentFilePath;
    }

    public Spreadsheet getSpreadsheet() {
        return spreadsheet;
    }

    public void setSpreadsheet(Spreadsheet spreadsheet) {
        this.spreadsheet = spreadsheet;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }


}
