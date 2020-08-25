public class Ui {
    public Ui() {
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Jia Le :)");
        System.out.println("How may I be of assistance to you?");
    }

    public void list(TaskList tasks) {
        int count = 1;
        for (Task task : tasks.list) {
            System.out.println(count + "." + task.toString());
            count++;
        }
    }

    public void done(TaskList tasks, int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
    }

    public void remove(TaskList tasks, int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index).toString());
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
    }

    public void add(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void bye() {
        System.out.print("Bye! Hope to see you again ;)");
    }
}
