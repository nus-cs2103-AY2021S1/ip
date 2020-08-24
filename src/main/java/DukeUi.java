public class DukeUi {

    public DukeUi() { }

    public void showLine() {
        System.out.println("\n_________________________________________\n");
    }

    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Initializing\n" + logo);
        System.out.println("Yo what's up man, it's ya boi DUKE \n" + "What can I do for you today, Sir?");
    }

    public void goodbyeMessage() {
        System.out.println("_________________________________________\n" + "Bye. Hope to see you again soon!" + "\n" + "_________________________________________");
    }

    public void showError(String error) {
        System.out.println("Sorry an unexpected error occured!! :( \n" +
                            "\n_________________________________________\n"
                            + error
                            + "\n_________________________________________");
    }

}
