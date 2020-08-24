import java.util.ArrayList;

public class Helper extends Duke{
    public static void addToDo(String[] line, ArrayList<Task> taskList) throws DukeException {
        String description = line[1];
        // Validate description
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! Description of todo unclear. Here's an example: 'todo borrow book'");
        }
        Task task = new ToDo(description);
        taskList.add(task);
        ReadWriteFile.writeToFile(taskList);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
    }

    public static void showList(ArrayList<Task> taskList) throws DukeException {
        if (taskList.isEmpty()) {
            throw new DukeException("The list is empty! Add in some new tasks!");
        }
        int counter = 1;
        for (Task item : taskList) {
            System.out.println(counter + ". " + item);
            counter++;
        }
    }

    public static void markAsDone(String[] line, ArrayList<Task> taskList) throws DukeException{
        // Validate task index
        if (line.length == 1) {
            throw new DukeException("Tell me which task you have done!");
        }
        if (line[1].isEmpty()) {
            throw new DukeException("Tell me which task you have done!");
        }
        int task_index = Integer.parseInt(line[1]);
        if (task_index > taskList.size()) {
            throw new DukeException("Only existing tasks of index <= " + taskList.size() + " can be marked as done!");
        }
        Task task = taskList.get(task_index - 1);
        task.markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public static void addDeadline(String[] line, ArrayList<Task> taskList) throws DukeException{
        // Validate date/time
        String[] description = line[1].split(" /by ");
        if (description.length != 2) {
            throw new DukeException("☹ OOPS!!! Description of deadline unclear. Here's an example: 'deadline return book /by Sunday'");
        }
        Task task = new Deadline(description[0], description[1]);
        taskList.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
    }

    public static void addEvent(String[] line, ArrayList<Task> taskList) throws DukeException{
        // Validate date/time
        String[] description = line[1].split(" /at ");
        if (description.length != 2) {
            throw new DukeException("☹ OOPS!!! Description of event unclear. Here's an example: 'event project meeting /at Mon 2-4pm'");
        }
        Task task = new Event(description[0], description[1]);
        taskList.add(task);

        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
    }

    public static void deleteTask(String[] line, ArrayList<Task> taskList) throws DukeException {
        // Validate task index
        if (line.length == 1) {
            throw new DukeException("Tell me which task you want to delete!");
        }
        if (line[1].isEmpty()) {
            throw new DukeException("Tell me which task you want to delete!");
        }
        int task_index = Integer.parseInt(line[1]);
        // Validate task index
        if (task_index > taskList.size()) {
            throw new DukeException("Only existing tasks of index <= " + taskList.size() + " can be deleted!");
        }
        Task task = taskList.get(task_index - 1);
        taskList.remove(task);

        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " task(s) in the list.");
    }
}
