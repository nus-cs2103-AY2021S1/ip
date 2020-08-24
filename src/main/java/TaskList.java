import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> todoList;
    private int numberOfTasks;

    TaskList() {
        todoList = new ArrayList<>();
        numberOfTasks = 0;
    }

    public String showList() {
        int count = 1;
        String output = "  The tasks in your Todo List: ";
        for (Task item : todoList) {
            output += "\n    " + Integer.toString(count) + ". " + item.getItem();
            count += 1;
        }
        return output;
    }

    public String completeItem(String input) throws DukeException {
        String indexString = input.split(" ")[1];
        try {
            String output = "";
            int index = Integer.valueOf(indexString) - 1;
            Task item = todoList.get(index);
            item.completeTask();
            output += "     \\\\(^o^)/ *.*.* \\\\(^o^)/"+ "\n  Yay! This task has been completed:" + "\n  " + item.getItem();
            return output;
        } catch (Exception e) {
            throw new DukeException("Oops! Invalid task number. Please try again >.<");
        }
    }

    public String deleteItem(String input) throws DukeException {
        String indexString = input.split(" ")[1];
        try {
            int index = Integer.valueOf(indexString) - 1;
            Task item = todoList.get(index);
            String output = "";
            output += "  Noted. This task has now been removed from the list:" + "\n    " + item.getItem();
            todoList.remove(index);
            numberOfTasks -= 1;
            output += "\n  There are now " + Integer.toString(numberOfTasks) + " todo items in the list";
            return output;
        } catch (Exception e) {
            throw new DukeException("Oops! Invalid task number. Please try again >.<");
        }
    }

    public String addItem(String instruction, String item) throws DukeException {
        if (item.equals("") || item.equals(" ")) {
            throw new DukeException("Oops! The description cannot be empty >.<");
        }
        String output = "";
        if (instruction.equals("todo")) {
            output += addTodoItem(item, false);
        } else if (instruction.equals("deadline")) {
            output += addDeadline(item, false);
        } else {
            output += addEvent(item, false);
        }
        output += "\n  New todo item added to the list!";
        output += "\n  There are now " + Integer.toString(numberOfTasks + 1) + " todo items in the list";
        return output;
    }

    public String addTodoItem(String item, boolean completed) {
        Todo newTask = new Todo(item, completed);
        todoList.add(newTask);
        numberOfTasks += 1;
        return "  " + newTask.getItem();
    }


    public String addDeadline(String item, boolean completed) throws DukeException {
        Deadline newTask = Parser.parseDeadline(item, completed);
        todoList.add(newTask);
        numberOfTasks += 1;
        return "  " + newTask.getItem();
    }

    public String addEvent(String item, boolean completed) throws DukeException {
        Event newTask = Parser.parseEvent(item, completed);
        todoList.add(newTask);
        numberOfTasks += 1;
        return "  " + newTask.getItem();
    }

    public String formatTodoListToString() {
        String tasks = "";
        for (Task task : todoList) {
            tasks += task.getInput() + "\n";
        }
        return tasks;
    }
}
