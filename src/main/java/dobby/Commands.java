package dobby;

/**
 * Specifies all commands the chat bot can work with
 */
public enum Commands {
    TODO("todo _description_"),
    DEADLINE("deadline _description_ /by dd/mm/yyyy hhhh(optional)"),
    EVENT("event _description_ /at dd/mm/yyyy hhhh(optional)"),
    LIST("list"),
    DONE("done _task_number_"),
    DELETE("delete _task_number_"),
    BYE("bye"),
    FINDTYPE("findtype _T/D/E_"),
    FIND("find _keyword_"),
    SCHEDULED("scheduled dd/mm/yyyy");

    private String usage;

    private Commands (String usage) {
        this.usage = usage;
    }

    public String getUsage () {
        return this.usage;
    }
}
