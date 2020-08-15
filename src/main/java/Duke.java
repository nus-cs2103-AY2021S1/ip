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
        String[] parsedResponse = response.split(" ", 2);
        String command = parsedResponse[0];
        String rest = parsedResponse.length == 1 ? null : parsedResponse[1];
        switch (command) {
            case "bye":
                exit();
            case "list":
                showList();
                break;
            case "done":
                int taskDone = Integer.parseInt(rest) - 1;
                if (taskDone < 0 || taskDone >= taskIndex) {
                    DukeOut.print("No such task!");
                    break;
                }
                markTaskDone(taskDone);
                DukeOut.print("Nice! I've marked this task as done:\n      " + taskList[taskDone]);
                break;
            case "todo":
                addToList(new Todo(rest));
                break;
            case "deadline":
                String[] deadlineParsed = rest.split("/");
                String deadlineName = deadlineParsed[0].trim();
                String by = deadlineParsed[1].split(" ", 2)[1];
                addToList(new Deadline(deadlineName, by));
                break;
            case "event":
                String[] eventParsed = rest.split("/");
                String eventName = eventParsed[0].trim();
                String at = eventParsed[1].split(" ", 2)[1];
                addToList(new Event(eventName, at));
                break;
            default:
                DukeOut.print("Unrecognized command!");
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

    private static void addToList(Task task) {
        taskList[taskIndex] = task;
        ++taskIndex;
        DukeOut.print("Got it. I've added this task:\n      " + task + "\n    " + "Now you have " + taskIndex + " task(s) in the list.");
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
