public class Ui {
    private static final String BORDER = "____________________________________________________________\n";

    public Ui() {
    }

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

    public String getBorder() {
        return BORDER;
    }
}
