//handles IO between app and user
public class Ui {

    private InputHandler inputHandler;
    private OutputHandler outputHandler;

    public Ui(InputHandler in, OutputHandler out) {
        this.inputHandler = in;
        this.outputHandler = out;
    }

    public void displayGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        this.display("Hello from\n" + logo);
    }

    public void displayGoodbye() {
        this.display("Bye. Hope to see you again soon!");
    }

    public void display(String output) {
        this.outputHandler.print(output);
    }

    public void displayException(Exception e) {
        this.display(e.getMessage());
    }

    public String readCommand() {
        return this.inputHandler.input();
    }

}
