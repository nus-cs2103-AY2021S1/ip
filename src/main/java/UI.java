import java.util.List;

public class UI {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static Printable greetingMessage = () -> "Hello! I'm Duke \nWhat can I do for you?";
    private static Printable goodbyeMessage = () -> "Bye. Hope to see you again soon!";
    
    public void greetUser() {
        System.out.println("Hello from\n" + LOGO);
        speak(greetingMessage);
    }
    
    public void goodbye() {
        speak(goodbyeMessage);
    }
    
    public void taskCompletedMessage(Task task) {
        speak(() -> "Nice! I've marked this task as done:\n[✓] " + task.toString());
    }
    
    public void taskDeletedMessage (Task task) {
        speak(() -> String.format("Noted. I've removed this task:\n" +
                "%s%s %s\n" +
                "Now you have %d tasks in the list.",task.getTaskSymbol(),task.getSymbol(),task.toString(),Task.remainingTasks()));
    }
    
    public void taskAddedMessage (Task task) {
        speak(() -> String.format("Got it. i've added this task:\n %s%s %s\n" +
                "Now you have %d tasks in the list.", task.getTaskSymbol(), task.getSymbol(), task.toString(), Task.remainingTasks()));
    }

    public static void listTasks (List<Task> tasks) {
        int i;
        StringBuilder sb = new StringBuilder();
        for (i = 0 ; i < tasks.size() ; i++) {
            Task task = tasks.get(i);
            if (i==tasks.size()-1) {
                sb.append(String.format("%d.%s %s",i+1,task.getSymbol(),task));
            } else {
                sb.append(String.format("%d.%s %s\n", i+1,task.getSymbol(), task));
            }
        }
        Printable task = () -> sb.toString();
        speak(task);
        return;
    }
    

    public static void speak(Printable printable) {
        System.out.println("------------------------------------------------------");
        System.out.println(printable.print() + "\n");
        System.out.println("------------------------------------------------------");
    }
}
