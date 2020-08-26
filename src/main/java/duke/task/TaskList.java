package duke.task;

import duke.Storage;
import duke.Ui;
import duke.exception.DateParseException;
import duke.exception.InvalidTaskException;
import duke.exception.StorageException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private Storage storage;

    private TaskList(Storage storage){
        this.taskList = new ArrayList<>();
        this.storage = storage;
    }

    public static TaskList initialiseTaskList(Storage storage) throws StorageException, DateParseException {
        TaskList newTaskList = new TaskList(storage);

        try {
            storage.readTaskStorage().forEach(taskString -> {
                String[] t = taskString.split(" [|] ");
                // Tasks are stored in the format: type | isCompleted | taskName | date
                switch (t[0]){
                    case "T":
                        newTaskList.taskList.add(Todo.existingTodo(t[2], t[1].equals("1")));
                        break;
                    case "D":
                        newTaskList.taskList.add(Deadline.existingDeadline(t[2], t[1].equals("1"), LocalDate.parse(t[3])));
                        break;
                    case "E":
                        newTaskList.taskList.add(Event.existingEvent(t[2], t[1].equals("1"), LocalDate.parse(t[3])));
                        break;
                }
            });
        } catch (DateTimeParseException e) {
            throw new DateParseException("Oops! Please make sure your date is of YYYY-MM-DD format ;A;");
        }

        return newTaskList;
    }

    public void printTasks(){
        Ui.listMessage(this.taskList);
    }

    public void printTasksToday(){
        Ui.todayMessage(this.taskList);
    }

    public void addTask(String taskName) throws StorageException {
        Todo newTodo = Todo.newTodo(taskName);
        this.taskList.add(newTodo);
        Ui.addTaskMessage(newTodo, this.taskList.size());
        appendTask(newTodo);
    }

    public void addTask(TaskType type, String taskName, LocalDate taskDate) throws StorageException {
        switch(type){
            case EVENT:
                Event newEvent = Event.newEvent(taskName, taskDate);
                this.taskList.add(newEvent);
                Ui.addTaskMessage(newEvent, this.taskList.size());
                appendTask(newEvent);
                break;
            case DEADLINE:
                Deadline newDeadline = Deadline.newDeadline(taskName, taskDate);
                this.taskList.add(newDeadline);
                Ui.addTaskMessage(newDeadline, this.taskList.size());
                appendTask(newDeadline);
                break;
        }
    }

    public void completeTask(int index) throws InvalidTaskException, StorageException {
        if(index > this.taskList.size() || index <= 0) {
            throw new InvalidTaskException("Oh noes! I don't think you specified a valid task index :<");
        }
        Task task = this.taskList.get(index - 1).markAsDone();
        saveTasks();

        Ui.doneTaskMessage(task);
    }

    public void deleteTask(int index) throws InvalidTaskException, StorageException {
        if(index > this.taskList.size() || index <= 0) {
            throw new InvalidTaskException("Oh noes! I don't think you specified a valid task index :<");
        }

        Task task = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        saveTasks();

        Ui.deleteTaskMessage(task, this.taskList.size());
    }

    private void appendTask(Task t) throws StorageException {
        this.storage.appendTaskStorage(t.toSaveString());
    }

    private void saveTasks() throws StorageException {
        StringBuilder saveString = new StringBuilder();
        for(Task task : this.taskList) {
            saveString.append(task.toSaveString());
        }
        this.storage.writeTaskStorage(saveString.toString());
    }
}
