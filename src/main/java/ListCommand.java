class ListCommand extends Command {

    @Override
    public void exec(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks);
    }
}
