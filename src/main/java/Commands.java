public enum Commands {
    TODO("\n      todo _description_"),
    DEADLINE("\n      deadline _description_ /by _deadline_details_"),
    EVENT("\n      event _description_ /at _event_scheduled_at_"),
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
