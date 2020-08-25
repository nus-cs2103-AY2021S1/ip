public class Ui {
    private static final String HORIZONTAL_RULE = "____________________________________________________________";
    public void displayGreeting() {
        final String HELLO_MESSAGE = "Hello, and welcome to my humble abode. \n" +
                "I'm supposed to help you but I'll be the judge of that. Anyway what do you want.";

        System.out.println(HORIZONTAL_RULE);
        System.out.println(HELLO_MESSAGE);
        System.out.println(HORIZONTAL_RULE);
    }

    public void displayExit() {
        final String BYE_MESSAGE = "Thank you for that utter waste of time.\n" +
                "Can't wait to see you again...";

        System.out.println(HORIZONTAL_RULE);
        System.out.println(BYE_MESSAGE);
        System.out.println(HORIZONTAL_RULE);
    }

    public void printList(TaskList tasks) {
        final String CHECKMARK = "[✓]";
        final String CROSS = "[✗]";

        System.out.println(HORIZONTAL_RULE);
        System.out.println("Here are all your burdens");

        for (int i = 1; i <= tasks.numTasks(); i++) {
            Task item = tasks.getTask(i - 1);
            String fullItem = i + ". " + item.toString();

            System.out.println(fullItem);
        }

        System.out.println(HORIZONTAL_RULE);
    }

    public void displayCompleteTask() {
        System.out.println(HORIZONTAL_RULE);
        System.out.println("Congratulations, you actually did something");
        System.out.println(HORIZONTAL_RULE);
    }

    public void displayAddTask(Task task, int size) {
        System.out.println(HORIZONTAL_RULE);
        System.out.println("One more task added for you sire");
        System.out.println(task);
        System.out.println("You have " + size + " tasks. Enjoy!");
        System.out.println(HORIZONTAL_RULE);
    }

    public void displayDeleteTask(Task removedTask, int size) {
        System.out.println(HORIZONTAL_RULE);
        System.out.println("Finished so soon? Fine I've removed the following task. Good day.");
        System.out.println("    " + removedTask);
        System.out.println("You have " + size + " tasks. Enjoy!");
        System.out.println(HORIZONTAL_RULE);
    }

    public void showLoadingError() {
        System.out.println(HORIZONTAL_RULE);
        System.out.println("Something messed up with your hard disk file i don't really know what. Figure it out.");
        System.out.println(HORIZONTAL_RULE);
    }
}
