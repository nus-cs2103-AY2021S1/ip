import java.util.List;

enum ListChange
{
    ADD, DELETE
}

public class TaskList {
    protected List<Task> tasks;

    /**
     * Constructs a new TaskList object.
     *
     * @param taskList List of Tasks.
     */
    public TaskList(List<Task> taskList) {
        tasks = taskList;
    }

    /**
     * Gets the list of tasks.
     * 
     * @return List of Tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Finds the number of tasks in the task list.
     * 
     * @return Number of tasks.
     */
    public String numTasks() {
        int numTasks = tasks.size();
        return numTasks == 1 ? "1 task" : numTasks + " tasks";
    }

    /**
     * Prints the correct message to signify adding/deleting a task.
     * 
     * @param task Task to be added or deleted.
     * @param change Enum of ADD or DELETE depending on command.
     */
    public void listChangePrint(Task task, ListChange change) {
        String keyword = "";
        switch (change) {
            case ADD:
                keyword = "added";
                break;
            case DELETE:
                keyword = "removed";
        }

        System.out.println("Noted. I've " + keyword + " this task:\n" + task.toString());
        System.out.println("Now you have " + this.numTasks() + " in the list.");
    }

    /**
     * Prints the tasks in the task list sequentially.
     */
    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).toString());
        }
    }
    
    public void done(String command) {
        try {
            int taskId = Parser.getTaskId(command);
            tasks.get(taskId).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskId).toString());
        } catch (Exception e) {
            new DukeException("invalidMarkingDone");
        }
    }
    
    public void newToDo(String command) {
        if (command.equals("todo")) {
            new DukeException("invalidTodo");
        } else {
            String detail = Parser.getDetail(command);
            if (detail.isBlank()) {
                new DukeException("invalidTodo");
            } else {
                Task newTask = new ToDo(detail);
                tasks.add(newTask);
                listChangePrint(newTask, ListChange.ADD);
            }
        }
    }
    
    public void newDeadline(String command) {
        try {
            String desc = Parser.getDeadlineDesc(command);
            String by = Parser.getBy(command);
            Task newTask = new Deadline(desc, by);
            tasks.add(newTask);
            listChangePrint(newTask, ListChange.ADD);
        } catch (Exception e) {
            new DukeException("invalidDeadlineTask");
        }
    }
    
    public void newEvent(String command) {
        try {
            String desc = Parser.getEventDesc(command);
            String at = Parser.getAt(command);
            Task newTask = new Event(desc, at);
            tasks.add(newTask);
            listChangePrint(newTask, ListChange.ADD);
        } catch (Exception e) {
            new DukeException("invalidEvent");
        }
    }
    
    public void delete(String command) {
        try {
            int taskId = Parser.getTaskId(command);
            Task removedTask = tasks.remove(taskId);
            listChangePrint(removedTask, ListChange.DELETE);
        } catch (Exception e) {
            new DukeException("invalidDelete");
        }
    }

    /**
     * Returns the default invalid command error.
     */
    public void defaultError() {
        new DukeException("invalidCommand");
    }
}
