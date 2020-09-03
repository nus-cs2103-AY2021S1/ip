package duke;

class FindCommand extends Command {

    private String search;

    FindCommand(String search) {
        this.search = search;
    }

    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = tasks.findTasks(search);
        return ui.printOutput(output);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
