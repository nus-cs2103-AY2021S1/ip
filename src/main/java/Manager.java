import java.util.List;

public class Manager {
    public static void manageInput(String input, List<Task> tasks) {
        String separator = "   ----------------------------------------------------------";
        System.out.println(separator);
        try {
            if (input.equals("list")) {
                ListOfTasks.showList(tasks);
            } else if (input.startsWith("done")) {
                Done.handleDone(input, tasks);
            } else {
                int idx = input.indexOf(' ');
                String firstWord = idx == -1 ? input : input.substring(0, idx);
                String restOfWord = idx == -1 ? "" : input.substring(idx);
                if (firstWord.equals("todo") || firstWord.equals("deadline") || firstWord.equals("event")) {
                    Adding.addTask(firstWord, restOfWord, tasks);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch (DukeException e) {
            System.out.println("    " + e.getMessage());
        } finally {
            System.out.println(separator);
            System.out.println();
        }
    }
}
