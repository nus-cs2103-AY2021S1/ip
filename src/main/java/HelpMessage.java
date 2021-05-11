public enum HelpMessage {
    TODO("todo <description>: Stores Todo task with given <description> into your TaskList."),

    DEADLINE("deadline <description> /by <time constraint>: Stores Deadline task"
        + " with given <description> and <time constraint> into your TaskList."),

    EVENT("event <description> /at <time constraint>: Stores Event task"
        + " with given <description> and <time constraint> into your TaskList."),

    LIST("list: Lists out all tasks currently stored in your TaskList."),

    DELETE("delete <task number>: Deletes Task at given index from your TaskList."),

    BYE("bye: Ends Jimmy program and saves all changes to TaskList in hard drive."),

    FIND("find <key words>: Lists out all tasks that have <key words> in their description."),

    HELP("help: Helps me help you!"),

    DONE("done <task number>: Marks Task at given index as Completed.");

    private final String helpMessage;

    HelpMessage(String help) {
        this.helpMessage = help;
    }

    public String getHelpMessage() {
        return this.helpMessage;
    }
}
