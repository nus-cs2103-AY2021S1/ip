public class Duke {

    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static final Task[] taskList = new Task[100];
    private static int taskIndex = 0;

    public static void main(String[] args) {
        greet();
        while (true) {
            String response = DukeIn.prompt();
            handleResponse(response);
        }
    }

    private static void handleResponse(String response) {
        String[] parsedResponse = response.split(" ");
        switch (parsedResponse[0]) {
            case "bye":
                exit();
            case "list":
                showList();
                break;
            case "done":
                int taskDone = Integer.parseInt(parsedResponse[1]) - 1;
                if (taskDone < 0 || taskDone >= taskIndex) {
                    DukeOut.print("No such task!");
                    break;
                }
                markTaskDone(taskDone);
                DukeOut.print("Nice! I've marked this task as done:\n      " + taskList[taskDone]);
                break;
            default:
                addToList(response);
                DukeOut.print("added: " + response);
        }
    }

    private static void greet() {
        String greeting = "Hi! I am\n" + LOGO + "\n" + "What can I do for you?";
        System.out.println(greeting);
    }

    private static void exit() {
        String goodbye = "Bye! Hope to see you again!";
        DukeOut.print(goodbye);
        System.exit(0);
    }

    private static void addToList(String task) {
        taskList[taskIndex] = new Task(task);
        ++taskIndex;
    }

    private static void showList() {
        if (taskIndex == 0) {
            DukeOut.print("You have no tasks!");
            return;
        }

        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n    ");
        for (int i = 0; i < taskIndex; ++i) {
            sb.append((i + 1) + "." + taskList[i] + "\n    ");
        }
        sb.setLength(sb.length() - 5);
        DukeOut.print(sb.toString());
    }

    private static void markTaskDone(int taskNum) {
        Task task = taskList[taskNum];
        task.markDone();
    }
}
