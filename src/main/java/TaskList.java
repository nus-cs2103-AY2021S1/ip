import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void addTask(Task newTask) {

        listOfTasks.add(newTask);
    }

    public void deleteTask(int index) throws InvalidRequestException {
            if (listOfTasks.size() < index || index < 0) {
                throw new InvalidRequestException("You have entered an invalid task "
                        + "number! Please try again.");
            }
            listOfTasks.remove(index - 1);
    }

    public void setAsDone(int index) throws InvalidRequestException {
            if (listOfTasks.size() < index || index < 0) {
                throw new InvalidRequestException("You have entered an invalid task "
                        + "number! Please try again.");
            }
            this.listOfTasks.get(index - 1).setTaskToBeDone();

    }

    public Task getTask(int index) throws InvalidRequestException {
        if (listOfTasks.size() < index || index < 0) {
            throw new InvalidRequestException("You have entered an invalid task "
                    + "number! Please try again.");
        }
        return this.listOfTasks.get(index - 1);
    }

    public ArrayList<Task> getListOfTasks() {
        return this.listOfTasks;
    }

 }
