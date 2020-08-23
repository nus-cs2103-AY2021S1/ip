public class Ui {

    public Ui() {

    }

    public void showLoadingError() {
        System.out.println("The test file cannot be loaded properly.");
    }

    public void introduce() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void printExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
