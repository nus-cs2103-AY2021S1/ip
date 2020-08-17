import java.util.ArrayList;

public class InputHandler {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static final String DIVIDER = "____________________________________________________________";
    private static final String EXIT_CMD = "bye";
    private static final String LIST_CMD = "list";
    private static final String DONE_CMD = "done";

    public InputHandler() {
        handleStart();
    }

    private static void handleStart() {
        String startMsg =
                DIVIDER + "\n" +
                        "Hello! I'm Duke\n" +
                        "What can I do for you?\n" +
                        DIVIDER;
        System.out.println(startMsg);
    }

    public boolean handleInput(String in) {
        String[] input = in.split(" ");
        String cmdWord = input[0];
        switch (cmdWord) {
            case (LIST_CMD):
                return handleList();
            case (DONE_CMD):
                return handleDone(Integer.parseInt(input[1]));
            case (EXIT_CMD):
                return handleExit();
            default:
                return handleOthers(in, cmdWord);
        }
    }

    private void handleEcho(String str) {
        String msg = DIVIDER + "\n" + str + "\n" + DIVIDER;
        System.out.println(msg);
    }

    private boolean handleList() {
        int len = taskList.size();
        System.out.println(len);
        String firstLine = len == 0
            ? "There are no tasks in your list!"
            : "Here are the tasks in your list:";
        System.out.println(DIVIDER);
        System.out.println(firstLine);
        for (int i = 1; i <= len; i++) {
            Task task = taskList.get(i - 1);
            String output = i + "." + task.toString();
            System.out.println(output);
        }
        System.out.println(DIVIDER);
        return true;
    }

    private boolean handleDone(int index) {
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

    private boolean handleOthers(String in, String cmdWord) {
        if (Task.isTask(cmdWord)) {
            String taskDetails = in.replaceFirst(cmdWord, "").trim();
            Task task = Task.createTask(cmdWord, taskDetails);
            taskList.add(task);
            handleTaskCreated(task);
        } else {
            handleEcho(in);
        }
        return true;
    }

    private void handleTaskCreated(Task task) {
        int len = taskList.size();
        String msg =
            DIVIDER + "\n" +
            "Got it. I've added this task: \n" +
            "  " + task.toString() + "\n" +
            "Now you have " + len + " task" + (len == 1 ? "" : "s") + " in the list.\n" +
            DIVIDER;
        System.out.println(msg);
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
