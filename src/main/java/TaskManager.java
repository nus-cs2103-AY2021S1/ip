import java.util.List;

public class TaskManager {

    List<Task> tasks;
    private final String keyWord;
    private final String restOfWord;

    protected TaskManager(List<Task> tasks, String keyWord, String restOfWord) {
        this.tasks = tasks;
        this.keyWord = keyWord;
        this.restOfWord = restOfWord;
    }

    protected void addTask() throws DukeException {
        Task newTask = null;
        String typeOfTask = keyWord;
        String taskDetails = restOfWord;
        if (typeOfTask.equals("todo")) {
            if (taskDetails.isEmpty()) {
                throw new EmptyTaskException(TaskType.TODO);
            }
            newTask = new ToDo(taskDetails);
        } else if (typeOfTask.equals("deadline")) {
            ComplexTaskManager managerCT = new ComplexTaskManager(taskDetails, TaskType.DEADLINE);
            newTask = managerCT.getTask();
        } else if (typeOfTask.equals("event")) {
            ComplexTaskManager managerCT = new ComplexTaskManager(taskDetails, TaskType.EVENT);
            newTask = managerCT.getTask();
        }
        tasks.add(newTask);
        Ui.addTask(newTask, tasks.size());
    }

    protected void handleDone() throws DukeException {
        if (NumberAction.checkIfNumber(restOfWord)) {
            int digit = Integer.parseInt(restOfWord);
            if (NumberAction.checkIfValid(digit, tasks)) {
                Task current = tasks.get(digit - 1);
                if (current.checkIfDone()) {
                    throw new TaskAlreadyDoneException();
                } else {
                    current.markAsDone();
                    Ui.markTaskAsDone(current);
                }
            } else {
                throw new InvalidTaskNumberException(tasks.size());
            }
        } else {
            throw new InvalidDoneException();
        }
    }

    protected void handleDeletion() throws DukeException{
        if (NumberAction.checkIfNumber(restOfWord)) {
            int digit = Integer.parseInt(restOfWord);
            if (NumberAction.checkIfValid(digit, tasks)) {
                Task current = tasks.get(digit - 1);
                tasks.remove(digit - 1);
                Ui.deleteTask(current, tasks.size());
            } else {
                throw new InvalidTaskNumberException(tasks.size());
            }
        } else {
            throw new InvalidDeleteException();
        }
    }
}
