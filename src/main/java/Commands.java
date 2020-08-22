public enum Commands {
    TODO("\n      todo _description_"),
    DEADLINE("\n      deadline _description_ /by dd/mm/yyyy hhhh(optional)"),
    EVENT("\n      event _description_ /at dd/mm/yyyy hhhh(optional)"),
    LIST("\n      list"),
    DONE("\n      done _task_number_"),
    DELETE("\n      delete _task_number_"),
    BYE("\n      bye");

    private String usage;

    private Commands(String usage) {
        this.usage = usage;
    }

    public String getUsage() {
        return this.usage;
    }
}
