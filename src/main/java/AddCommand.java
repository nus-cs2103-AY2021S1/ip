import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddCommand extends Command {
    String input;

    AddCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        String taskType = inputArr[0];
        String taskDescription = inputArr[1];

        Task newTask;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        if (taskType.equals(Instruction.TODO.getInstruction())) {
            if (taskDescription.equals(Instruction.EMPTY.getInstruction())) {
                throw new DukeException("Please key in a valid name for To Do");
            }
            newTask = new Todo(taskDescription);
        } else if (taskType.equals(Instruction.DEADLINE.getInstruction())) {
            String[] deadlineArr = taskDescription.split(" /by ", 2);

            if (deadlineArr.length != 2) {
                throw new DukeException("Please key in a valid name and date for the Deadline");
            }
            String deadlineName = deadlineArr[0];
            String deadlineDateTime = deadlineArr[1];

            if (deadlineDateTime.equals(Instruction.EMPTY.getInstruction())) {
                throw new DukeException("Please key in a valid date and time for the Deadline");
            }

            LocalDateTime localDateTime = LocalDateTime.parse(deadlineDateTime, formatter);
            newTask = new Deadline(deadlineName, localDateTime);

        } else if (taskType.equals(Instruction.EVENT.getInstruction())) {
            String[] eventArr = taskDescription.split(" /at ", 2);
            if (eventArr.length != 2) {
                throw new DukeException("Please key in a valid name and date for the Event");
            }
            String eventName = eventArr[0];
            String eventDuration = eventArr[1];

            if (eventDuration.equals(Instruction.EMPTY.getInstruction())) {
                throw new DukeException("Please key in a valid duration for the Event");
            }
            LocalDateTime localDateTime = LocalDateTime.parse(eventDuration, formatter);
            newTask = new Event(eventName, localDateTime);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        tasks.addTask(newTask);
        ui.showAddTask(newTask, tasks.getSize());
        storage.saveTasksToFile(tasks);
    }

}
