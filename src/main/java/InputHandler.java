import java.util.ArrayList;

public class InputHandler {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String EXIT_CMD = "bye";
    private static final String LIST_CMD = "list";
    private static final String DONE_CMD = "done";

    public InputHandler() {
        handleStart();
    }

    private static String createEchoMsg(String str) {
        String text = "added: " + str + "\n";
        return DIVIDER + "\n" + text + DIVIDER;
    }

    public boolean handleInput(String in, ArrayList<Task> taskList) {
        String[] input = in.split(" ");
        String cmdWord = input[0];
        switch (cmdWord) {
            case (LIST_CMD):
                return handleList(taskList);
            case (DONE_CMD):
                return handleDone(Integer.parseInt(input[1]), taskList);
            case (EXIT_CMD):
                return handleExit();
            default:
                return handleEcho(in, taskList);
        }
    }

    private boolean handleList(ArrayList<Task> taskList) {
        int len = taskList.size();
        System.out.println(DIVIDER);
        for (int i = 1; i <= len; i++) {
            Task task = taskList.get(i - 1);
            String output = i + "." + task.toString();
            System.out.println(output);
        }
        System.out.println(DIVIDER);
        return true;
    }

    private boolean handleDone(int index, ArrayList<Task> taskList) {
        Task task = taskList.get(index - 1);
        task.markAsDone();
        System.out.println(
            DIVIDER + "\n" +
            "Nice! I've marked this task as done\n" +
            "  " + task.toString() + "\n" +
            DIVIDER
        );
        return true;
    }

    private boolean handleEcho(String str, ArrayList<Task> taskList) {
        taskList.add(new Task(str));
        System.out.println(createEchoMsg(str));
        return true;
    }

    private static void handleStart() {
        String startMsg =
            DIVIDER + "\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            DIVIDER;
        System.out.println(startMsg);
    }

    private boolean handleExit() {
        String exitMsg =
            DIVIDER + "\n" +
            "Bye. Hope to see you again soon!\n" +
            DIVIDER;
        System.out.println(exitMsg);
        return false;
    }
}
