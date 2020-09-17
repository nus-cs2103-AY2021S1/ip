package duke;

/**
 * Deals with user interactions
 */
public class UI {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static Printable greetingMessage = () -> "Hello! I'm Duke \nWhat can I do for you?";
    private static Printable goodbyeMessage = () -> "Bye. Hope to see you again soon!";

    /**
     * Generates greeting message for user
     */
    public void greetUser() {
        System.out.println("Hello from\n" + LOGO);
        speak(greetingMessage);
    }

    /**
     * Generates goodbye message for user
     */
    public String goodbye() {
        
        return goodbyeMessage.print();
    }

    /**
     * Generates Task completion message for user
     * @param task Completed Task as input
     */
    public String taskCompletedMessage(Task task) {
        return "Nice! I've marked this task as done:\n[✓] " + task.toString();
    }

    /**
     * Generates task deletion message for user
     * @param task Task to be deleted as input
     */
    public String taskDeletedMessage (Task task) {
        assert task!=null : "The task to be deleted cannot be null";
        return String.format("Noted. I've removed this task:\n" +
                "%s%s %s\n" +
                "Now you have %d tasks in the list.",task.getTaskSymbol(),task.getSymbol(),task.toString(),Task.remainingTasks());
    }
    
    public String taskAddedMessage (Task task) {
        assert task!=null : "Null task cannot be added";
        return String.format("Got it. i've added this task:\n %s%s %s\n" +
                "Now you have %d tasks in the list.", task.getTaskSymbol(), task.getSymbol(), task.toString(), Task.remainingTasks());
    }

    /**
     * Lists completed tasks for user
     * @param taskList list of tasks as input
     */
    public static String listTasks (TaskList taskList) {
        int i;
        StringBuilder sb = new StringBuilder();
        for (i = 0 ; i < taskList.getTotalTask() ; i++) {
            Task task = taskList.get(i);
            if (i==taskList.getTotalTask()-1) {
                sb.append(String.format("%d.%s %s",i+1,task.getSymbol(),task));
            } else {
                sb.append(String.format("%d.%s %s\n", i+1,task.getSymbol(), task));
            }
        }
        Printable task = () -> sb.toString();
        
        return task.print();
    }
    

    private static void speak(Printable printable) {
        System.out.println("------------------------------------------------------");
        System.out.println(printable.print() + "\n");
        System.out.println("------------------------------------------------------");
    }
}
