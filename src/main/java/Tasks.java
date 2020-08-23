import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;

public class Tasks {
    protected ArrayList<Task> tasks;

    public Tasks() {
        this.tasks = new ArrayList<>();
    }

    public String getData() {
        String data = "";
        for (Task task: tasks) {
            data += task.getData() + "\n";
        }
        return data;
    }

    private static Todo addTodo(String input) throws EmptyTaskException {
        if (input.length() == 4) {
            throw new EmptyTaskException("description", TaskType.TODO);
        }

        String description = input.substring(5).trim();
        if (description.length() < 1) {
            throw new EmptyTaskException("description", TaskType.TODO);
        }

        return new Todo(description);
    }

    private static Event addEvent(String input) throws InvalidTaskException, EmptyTaskException {
        int slashIndex = input.indexOf("/at");
        if (slashIndex == -1) {
            throw new InvalidTaskException("The /at command cannot be found.");
        }

        String description = input.substring(6, slashIndex).trim();
        if (description.length() < 1) {
            throw new EmptyTaskException("description", TaskType.EVENT);
        }

        String dateStr = input.substring(slashIndex + 3).trim();
        if (dateStr.length() < 1) {
            throw new EmptyTaskException("date", TaskType.EVENT);
        }
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException ex) {
            throw new InvalidTaskException("The date of event is invalid, it should be in YYYY-MM-DD format.");
        }

        return new Event(description, localDate);
    }

    private static Deadline addDeadline(String input) throws InvalidTaskException, EmptyTaskException {
        int slashIndex = input.indexOf("/by");
        if (slashIndex == -1) {
            throw new InvalidTaskException("The /by command cannot be found.");
        }

        String description = input.substring(9, slashIndex).trim();
        if (description.length() < 1) {
            throw new EmptyTaskException("description", TaskType.DEADLINE);
        }

        String deadlineStr = input.substring(slashIndex + 3).trim();
        if (deadlineStr.length() < 1) {
            throw new EmptyTaskException("date", TaskType.DEADLINE);
        }
        
        LocalDate localDeadline;
        try {
            localDeadline = LocalDate.parse(deadlineStr);
        } catch (DateTimeParseException ex) {
            throw new InvalidTaskException("The deadline of deadline is invalid, it should be in YYYY-MM-DD format.");
        }

        return new Deadline(description, localDeadline);
    }

    protected void addTask(Storage storage, TaskType type, String input) throws EmptyTaskException, InvalidTaskException, UnknownInputException, SaveTaskFailedException {
        Task task;
        switch (type) {
            case TODO:
                task = addTodo(input);
                break;
            case EVENT:
                task = addEvent(input);
                break;
            case DEADLINE:
                task = addDeadline(input);
                break;
            default:
                throw new UnknownInputException();
        }
        this.tasks.add(task);
        try {
            storage.addTask(task); 
        } catch (IOException ex) {
            throw new SaveTaskFailedException(this.tasks.size());
        }
        PrintDuke.printAddTask(task, this.tasks.size());
    }
    
    protected void addTask(String[] stringArr) throws ReadFailedException {
        Task task = Task.createTask(stringArr); 
        this.tasks.add(task);
    }
    
    protected void markTaskAsDone(Storage storage, String input) throws EmptyInputException, InvalidTaskNumberException, SaveTaskFailedException, UnknownInputException {
        String taskIndexStr;
        try {
            taskIndexStr = input.substring(5).trim();
        } catch (IndexOutOfBoundsException ex) {
            throw new EmptyInputException("The task to be marked as done is not specified.");
        }

        if (taskIndexStr.length() < 1) {
            throw new EmptyInputException("The task to be marked as done is not specified.");
        }
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskIndexStr) - 1;
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            throw new UnknownInputException();
        }

        try {
            this.tasks.get(taskIndex).markAsDone();
            storage.updateTasks(this);
            PrintDuke.printMarkTaskAsDone(this.tasks.get(taskIndex));
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidTaskNumberException("The task to be marked as done does not exist!");
        } catch (IOException ex) {
            throw new SaveTaskFailedException(taskIndex);
        }
    }

    protected void deleteTask(Storage storage, String input) throws EmptyInputException, InvalidTaskNumberException, SaveTaskFailedException, UnknownInputException {
        String taskIndexStr;
        try {
            taskIndexStr = input.substring(7).trim();
        } catch (IndexOutOfBoundsException ex) {
            throw new EmptyInputException("The task to be deleted is not specified.");
        }

        if (taskIndexStr.length() < 1) {
            throw new EmptyInputException("The task to be deleted is not specified.");
        }
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskIndexStr) - 1;
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            throw new UnknownInputException();
        }
        try {
            Task task = this.tasks.get(taskIndex);
            this.tasks.remove(taskIndex);
            storage.updateTasks(this);
            PrintDuke.printDeleteTask(task, this.tasks.size());
        } catch (IndexOutOfBoundsException ex) {
            throw new InvalidTaskNumberException("The task to be deleted does not exist!");
        } catch (IOException ex) {
            throw new SaveTaskFailedException(taskIndex);
        }
    }
    
    protected void find(String input) throws EmptyInputException, InvalidInputException {
        String dateStr; 
        try {
            dateStr = input.substring(5).trim();
        } catch (IndexOutOfBoundsException ex) {
            throw new EmptyInputException("The item to be retrieved is not specified.");
        }
        
        if (dateStr.length() < 1) {
            throw new EmptyInputException("The item to be retrieved is not specified.");
        }

        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException ex) {
            throw new InvalidInputException("The date to be retrieved is invalid, it should be in YYYY-MM-DD format.");
        }
        
        ArrayList<Task> taskList = new ArrayList<>();
        for (Task task: this.tasks) {
            if (task.hasDate()) {
                boolean isEqual = task.type == TaskType.DEADLINE 
                        ? ((Deadline) task).isDateEqual(localDate) 
                        : ((Event) task).isDateEqual(localDate);
                if (isEqual) taskList.add(task);
            }
        }
        
        PrintDuke.printFound(localDate, taskList);
    }
}
