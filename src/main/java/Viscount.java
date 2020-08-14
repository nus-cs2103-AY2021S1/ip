import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Viscount {
    private static final String VISCOUNT_LOGO = "        _  _____  _____                  _    \n" +
            "       (_)/ ____|/ ____|                | |   \n" +
            " __   ___| (___ | |     ___  _   _ _ __ | |_  \n" +
            " \\ \\ / / |\\___ \\| |    / _ \\| | | | '_ \\| __| \n" +
            "  \\ V /| |____) | |___| (_) | |_| | | | | |_  \n" +
            "   \\_/ |_|_____/ \\_____\\___/ \\__,_|_| |_|\\__|";
    private static final String HORIZONTAL_LINE = "__________________________________________________";

    private static List<Task> taskList = new ArrayList<>();

    private static void printLogo() {
        System.out.println(Viscount.VISCOUNT_LOGO);
    }

    private static void speak(String message) {
        System.out.println(Viscount.HORIZONTAL_LINE);
        System.out.println(message);
        System.out.println(Viscount.HORIZONTAL_LINE);
        System.out.println();
    }

    private static String taskListToString() {
        String result = "";

        for (int i = 0; i < taskList.size(); i++) {
            result += (i == taskList.size() - 1)
                ? String.format("%d.%s", i + 1, taskList.get(i).toString())
                : String.format("%d.%s\n", i + 1, taskList.get(i).toString());
        }

        return result;
    }

    private static void addToTaskList(String description) {
        Task task = new Task(description);
        taskList.add(task);
        Viscount.speak(String.format("added: %s", description));
    }

    private static void markAsDone(Task task) {
        task.check();
        Viscount.speak("Very good! I have marked this task as done:\n" + task.toString());
    }

    private static void chat() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                Viscount.speak("Here are the tasks in your list:\n" + Viscount.taskListToString());
            } else if (input.split(" ")[0].equals("done")) {
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                Viscount.markAsDone(taskList.get(index));
            } else {
                Viscount.addToTaskList(input);
            }

            input = sc.nextLine();
        }
    }

    public static void main(String[] args) {
        Viscount.printLogo();

        Viscount.speak("Good day to you! I'm Viscount.\nWhat can I do for you on this blessed day?");

        Viscount.chat();

        Viscount.speak("Farewell my friend, I hope to see you again!");
    }
}
