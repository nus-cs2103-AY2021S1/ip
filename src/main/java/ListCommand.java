public class ListCommand extends Command{
    public ListCommand(String inputCommand) {
        super(inputCommand);
    }

    @Override
    public void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException{
        if(this.inputCommand.split(" ").length != 1) {
            throw new DukeCommandException("\u2639 OOPS!!! Wrong 'list' command parameters!");
        }

        if(list.getList().size() == 0) {
            ui.printMessage("There is nothing in the list!");
        } else {
            ui.printMessage("Here are the tasks in your list:");

            int ctr = 1;
            for(Task task: list.getList()) {
                ui.printMessage("" + ctr + "." + task);
                ctr++;
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
