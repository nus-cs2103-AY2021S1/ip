import java.time.LocalDate;

public class SnoozeCommand extends Command{

    public int taskIndex;
    public LocalDate newDate;

    public SnoozeCommand(int taskIndex, LocalDate newDate){
        super();
        this.taskIndex = taskIndex;
        this.newDate = newDate;
    }

    /**
     * Execute the command
     * @param inputTasks the list of tasks used
     * @param storage the storage used
     */
    public void execute(TaskList inputTasks, Storage storage, Ui ui){
        assert taskIndex > 0;
        inputTasks.snooze(taskIndex, newDate, ui);
        storage.writeToFile(inputTasks);
    }
}
