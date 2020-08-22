public class DoneCommand extends Command {
    private final String[] instructions;

    public DoneCommand(String[] instructions) {
        super();
        this.instructions = instructions;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (instructions.length < 2) {
            ui.incompleteInstructionError();
        }
        int index = Integer.parseInt(instructions[1]) - 1;
        tasks.completeTask(index);
        storage.save(tasks);
    }
}

