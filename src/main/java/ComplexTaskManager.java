public class ComplexTaskManager {

    private final String taskDetails;
    private final TaskType complexTask;

    protected ComplexTaskManager(String taskDetails, TaskType complexTask) {
        this.taskDetails = taskDetails;
        this.complexTask = complexTask;
    }

    protected Task getTask() throws DukeException {
        if (complexTask == TaskType.DEADLINE) {
            String[] inputArr = taskDetails.split(" /by", 2);
            if (inputArr.length == 1) {
                throw new InvalidDeadlineException();
            } else {
                checkIfEmpty(inputArr);
                return new Deadline(inputArr[0], inputArr[1]);
            }
        } else { // EVENT type
            String[] inputArr = taskDetails.split(" /at", 2);
            if (inputArr.length == 1) {
                throw new InvalidEventException();
            } else {
                checkIfEmpty(inputArr);
                return new Event(inputArr[0], inputArr[1]);
            }
        }
    }

    private void checkIfEmpty(String[] inputArr) throws DukeException {
        String description = inputArr[0];
        String by = inputArr[1];
        if (description.isEmpty()) {
            throw new EmptyTaskException(complexTask);
        } else if (by.isBlank()) {
            throw new EmptyByException(complexTask);
        }
    }
}
