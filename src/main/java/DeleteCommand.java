public class DeleteCommand extends Command {
    private final String[] instructions;

    public DeleteCommand(String[] instructions) {
        super();
        this.instructions = instructions;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (instructions.length < 2) {
            ui.incompleteInstructionError();
        }
        int index = Integer.parseInt(instructions[1]) - 1;
        if (index >= tasks.getSize()) {
            ui.deleteError();
        } else {
            tasks.deleteTask(index);
            storage.save(tasks);
        }
    }
}

