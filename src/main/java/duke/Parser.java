package duke;

public class Parser {
    private TaskList taskList;
    private boolean terminate = false;
    private String filePath;
    private String lineSeparator = System.getProperty("line.separator");
    private String taskDetailsSeparator = " | ";
    Ui ui;

    public Parser() {
        this.ui = new Ui();
        this.taskList = new TaskList();
    }


    public String[] parse(String command) {
        String[] splitCommand = command.split(" ", 2);
        return splitCommand;
    }
}