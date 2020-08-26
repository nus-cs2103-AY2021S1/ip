import java.time.LocalDate;

public class AddDirective implements Executable {
    private final String MESSAGE_ADD_SUCCESS = "sInCe yOu'rE So hElPlEsS, i'lL ReMeMbEr \"%s\" FoR YoU.\n"
            + "yOu hAvE MaDe mE ReMeMbEr %d tAsK(s).";

    private final Action action;
    private final String description;
    private final LocalDate date;

    public AddDirective(Action action, String description) {
        this.action = action;
        this.description = description;
        this.date = null;
    }

    public AddDirective(Action action, String description, LocalDate date) {
        this.action = action;
        this.description = description;
        this.date = date;
    }

    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui) throws StorageException {
        Task task;

        switch (action) {
        case ADD_DEADLINE:
            task = new Deadline(description, date);
            break;

        case ADD_EVENT:
            task = new Event(description, date);
            break;

        default:
            task = new Todo(description);
            break;
        }

        tasks.add(task);

        storage.save(tasks.getTaskList());

        return new Report(String.format(MESSAGE_ADD_SUCCESS, task, tasks.count()));
    }
}
