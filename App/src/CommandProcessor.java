public class CommandProcessor {

    private String currentFilePath;
    private Spreadsheet spreadsheet;
    private String command;
    private String filePath;



    public CommandProcessor() {
        this.currentFilePath =null;
        this.filePath = null;
        this.command = null;
        spreadsheet = new Spreadsheet();
    }

    public String executeCommand(String input){

        filePath=null;
        command=null;
        return translator(input);
    }

    protected String translator(String input){
        input = input.trim();
        String[] commandList = {"open" , "close" , "save" , "save as" , "help" , "exit"};


        String[] words = input.split(" ");
        filePath = words[words.length - 1];


        for (String currentCommand : commandList){
            if (input.startsWith(currentCommand)){
                command = currentCommand;
                input = input.replace(currentCommand,"");
                filePath = input.trim();
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
                String message;
                try {
                   message =  spreadsheet.loadFromFile(filePath);
                }catch (Exception e){
                    return "ERROR on opening the file";
                }
                currentFilePath = filePath;
                return message;

            case "close"  :
                if (currentFilePath != null){
                    String path = currentFilePath;
                    currentFilePath =null;
                    filePath =null;
                    command =null;
                    spreadsheet =new Spreadsheet();
                    return "Successfully closed " + path;
                }
                else {
                    return "no file is open to be closed try \"help\" for more information ";
                }
            case "save":
                if (currentFilePath != null){

                }
                else {
                    return "no file is open to be saved try \"help\" for more information ";
                }
            case "save as"  :
                if (currentFilePath != null){

                }
                else {
                    return "no file is open to be saved as try \"help\" for more information ";
                }
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
