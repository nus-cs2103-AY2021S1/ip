package duke;

class FindCommand extends Command {

    private String search;

    FindCommand(String search) {
        this.search = search;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.findTasks(search);
        ui.printOutput(output, false);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
