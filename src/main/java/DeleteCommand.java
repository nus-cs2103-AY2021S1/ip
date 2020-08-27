public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        try {
            if (index > tasks.size()) throw new DukeException("C'mon parder! That task doesn't exist cos you don't go so many!");
            Task toBeRemoved = tasks.get(index);
            tasks.deleteTask(index);
            System.out.println("Alrighty, I'm taking that one out:\n" + toBeRemoved.toString() + "\n" + "You've got a total of " + tasks.size() + " items right now.");
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
