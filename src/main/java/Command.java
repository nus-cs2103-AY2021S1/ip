interface Command {
    void execute(TaskList taskList, UI ui, Storage storage) throws DukeError;
    boolean isExit();
}
