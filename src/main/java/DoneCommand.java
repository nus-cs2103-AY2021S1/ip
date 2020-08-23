public class DoneCommand extends  Command{

    public DoneCommand(String input) {
        super(input);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException, InvalidSaveFileException {
        if(super.input.length() <= 5) {
            throw new InvalidInputException("\t☹ OOPS!!! Please specify which task you want to complete!");
        }
        int completed = Integer.parseInt(super.input.substring(5));
        try {
            Task current = tasks.getTasks().get(completed - 1);
            current.completeTask();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t" + current.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("\tIndex out of bounds! Please try again.");
        }
        storage.saveFile(tasks.getTasks());
    }
    public boolean isExit() {
        return false;
    }
}
