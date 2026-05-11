//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//
//
//public class CommandProcessore {
//
//    private String currentFilePath;
//    private Spreadsheet spreadsheet;
//    private String command;
//    private String filePath;
//
//
//
//    public CommandProcessore() {
//        this.currentFilePath =null;
//        this.filePath = null;
//        this.command = null;
//        spreadsheet = new Spreadsheet();
//    }
//
//    public String executeCommand(String input){
//
//        filePath=null;
//        command=null;
//        return translator(input);
//    }
//
//    protected String translator(String input){
//        input = input.trim();
//        String[] commandList = {"open" , "close" , "save as", "save"  , "help" , "exit" , "print", "edit"};
//
//        String[] words = input.split(" ");
//        filePath = words[words.length - 1];
//
//        for (String currentCommand : commandList){
//            if (input.startsWith(currentCommand)){
//                command = currentCommand;
//                input = input.replace(currentCommand,"");
//                filePath = input.trim();
//                break;
//            }
//        }
//        if (command == null || command.isEmpty()){
//            return "In-valet command tuy help";
//        }
//
//        return executeCommandLogic(command, filePath);
//    }
//
//    public String executeCommandLogic(String command , String filePath){
//        switch (command){
//            case "open":
//                String message;
//                try {
//                   message =  spreadsheet.loadFromFile(filePath);
//                }catch (Exception e){
//                    return "ERROR on opening the file" + e.getMessage();
//                }
//                currentFilePath = filePath;
//                return message;
//
//            case "close"  :
//                if (currentFilePath != null && !currentFilePath.isEmpty() ){
//
//                    String path = currentFilePath;
//                    currentFilePath =null;
//                    filePath =null;
//                    command =null;
//                    spreadsheet =new Spreadsheet();
//                    return "Successfully closed " + path;
//                }
//                else {
//                    return "no file is open to be closed try \"help\" for more information ";
//                }
//
//            case "save":
//                if (currentFilePath != null && !currentFilePath.isEmpty()){
//                    String content = spreadsheet.translateToText();
//                    return saveToFail(currentFilePath , content);
//                }
//                else {
//                    return "no file is open to be saved try \"help\" for more information ";
//                }
//
//            case "save as"  :
//                if (currentFilePath != null && !currentFilePath.isEmpty()){
//                    String content = spreadsheet.translateToText();
//                    return saveToFail(filePath , content);
//                }
//                else {
//                    return "no file is open to be saved as try \"help\" for more information ";
//                }
//            case "help" :
//                return "The following commands are supported: \n" +
//                        "open <file>       opens <file> \n" +
//                        "close             closes current opened file\n" +
//                        "save              saves the currently open file\n" +
//                        "save as <file>    saves the currently open file in <file>\n" +
//                        "help              prints this information\n" +
//                        "exit              exites the program \n" +
//                        "print             prints the content of the spreadsheet \n" +
//                        "edit              R<number of row>C<number of line> <new content of cell>      \n" +
//                        "                  replace the content of the selector cell whet the new content \n " ;
//            case "exit":
//                return "Exiting the program...";
//            case "print":
//                if (currentFilePath != null && !currentFilePath.isEmpty()){
//                    return spreadsheet.printTable();
//                }
//                else {
//                    return "no file is open to be pointed try \"help\" for more information ";
//                }
//            case "edit":
//                if (currentFilePath != null && !currentFilePath.isEmpty()){
//
//                    if (filePath.startsWith("R")) {
//
//                        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("R(\\d+)C(\\d+)\\s+(.+)");
//                        java.util.regex.Matcher matcher = pattern.matcher(filePath);
//
//                        if (matcher.find()) {
//                            int row = Integer.parseInt(matcher.group(1));
//                            int column = Integer.parseInt(matcher.group(2));
//                            String content = matcher.group(3);
//
//                            return spreadsheet.setCell(row, column, content);
//                        } else {
//                            throw new RuntimeException(filePath + " is not valued address. Use R[row]C[col] [content]");
//                        }
//                    }
//                    return "Unknown command";
//                }
//                else {
//                    return "no file is open to be edited try \"help\" for more information ";
//                }
//
//            default:
//                return "unknown command";
//        }
//    }
//
//
//    public String saveToFail(String filePath , String content){
//        try {
//            FileWriter writer = new FileWriter(filePath);
//            writer.write(content);
//            writer.close();
//
//            if (currentFilePath != filePath){
//                File file = new File(currentFilePath);
//                file.delete();
//            }
//
//            return "Successfully saved " + filePath;
//        }
//        catch (IOException e){
//            return "ERROR saving fail: " + e.getMessage();
//        }
//    }
//
//    public String getCurrentFilePath() {
//        return currentFilePath;
//    }
//
//    public void setCurrentFilePath(String currentFilePath) {
//        this.currentFilePath = currentFilePath;
//    }
//
//    public Spreadsheet getSpreadsheet() {
//        return spreadsheet;
//    }
//
//    public void setSpreadsheet(Spreadsheet spreadsheet) {
//        this.spreadsheet = spreadsheet;
//    }
//
//    public String getCommand() {
//        return command;
//    }
//
//    public void setCommand(String command) {
//        this.command = command;
//    }
//
//}
