import java.util.List;

public class Adding {

    public static void AddTask(String input, List<Task> tasks) {
        Task newTask = null;
        int startIndex;
        String description;
        String by;

        if (input.startsWith("todo")) {
            startIndex = 5;
            description = input.substring(startIndex);
            newTask = new ToDo(description);
        } else if (input.startsWith("deadline")) {
            int indexBy = input.indexOf(" /by ");
            startIndex = 9;
            description = input.substring(startIndex, indexBy);
            by = input.substring(indexBy + 5);
            newTask = new Deadline(description, by);
        } else if (input.startsWith("event")) {
            int indexBy = input.indexOf(" /at ");
            startIndex = 6;
            description = input.substring(startIndex, indexBy);
            by = input.substring(indexBy + 5);
            newTask = new Event(description, by);
        }
        tasks.add(newTask);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + newTask);
        System.out.println("    Now you have " + tasks.size() + " tasks in your list.");
    }
}
