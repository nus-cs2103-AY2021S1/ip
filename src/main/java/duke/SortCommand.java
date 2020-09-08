package duke;

public class SortCommand extends Command {

    public SortCommand() {
        super();
    }

    @Override
    public String execute(Tasklist tasks, Storage storage) throws DukeException {
        tasks.sort();
        storage.save(tasks);
        return tasks.toDisplayString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
