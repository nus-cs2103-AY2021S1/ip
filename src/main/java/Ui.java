public class Ui {
    private static final String BORDER = "____________________________________________________________\n";

    public boolean checkBye(String s) {
        return s.equals("bye");
    }

    public void exitLine() {
        System.out.println(BORDER + "Bye. Hope to see you again soon!\n" + BORDER);
    }


    public void startUp(TaskList taskList, Storage storage) {
        storage.fileCheck();
        if (!storage.getFile().exists() || storage.getFile().length() == 0) {
            System.out.println(
                    BORDER + "Hello! I'm Duke\n"
                            + "What can I do for you?\n" + BORDER
            );
        } else {
            System.out.println(
                    BORDER + "Well come back!\n" + "You still have "
                            + taskList.getList().size() + " tasks left to clear.\n" + BORDER
            );
        }
    }

    public void addTaskLine(Task toAdd, int size) {
        System.out.println(
                BORDER + "Got it. I've added this task:\n"
                + "  " + toAdd + "\n"
                + "Now you have " + size + " tasks in the list.\n"
                + BORDER);
    }

    public void removeTaskLine(Task toRemove, int size) {
        System.out.println(
                BORDER + "Noted. I've removed this task:\n" + "  "
                        + toRemove + "\n"
                        + "Now you have " + size + " tasks in the list.\n" + BORDER
        );
    }



    public String getBorder() {
        return BORDER;
    }
}
