package dobby;

/**
 * Specifies all commands the chat bot can work with
 */
public enum Commands {
    TODO("\n      todo _description_"),
    DEADLINE("\n      deadline _description_ /by dd/mm/yyyy hhhh(optional)"),
    EVENT("\n      event _description_ /at dd/mm/yyyy hhhh(optional)"),
    LIST("\n      list"),
    DONE("\n      done _task_number_"),
    DELETE("\n      delete _task_number_"),
    BYE("\n      bye"),
    FINDTYPE("\n      findtype _T/D/E_"),
    FIND("\n      find _keyword_"),
    SCHEDULED("\n      scheduled dd/mm/yyyy");

    private String usage;

    private Commands (String usage) {
        this.usage = usage;
    }

    public String getUsage () {
        return this.usage;
    }
}
