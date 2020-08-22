public class ListCommand extends Command {
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException("OOPS!!! I'm sorry, your list is empty :<");
        }
        String textOutput = "Here is the complete list of your task: \n";
        textOutput += list.toString();
        ui.printLine(textOutput);
    }
}
