public class DoneCommand extends Command {

    //constructor function - constructs an object
    public DoneCommand(String description) throws IllegalArgumentException {
        super(description); //the task number to mark it as done
    }

    public void execute(TaskList taskList) {
        int taskNumber;
        taskNumber = Integer.parseInt(this.getTaskName());
        Task doneTask = taskList.getTask(taskNumber);
        doneTask.markAsDone();
        System.out.println(doneTask + "\n" + TextUi.DIVIDER);
    }
}

