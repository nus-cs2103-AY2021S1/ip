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
        outputHandler.print("Hello from\n" + logo);
    }

    public void display(String output) {
        this.outputHandler.print(output);
    }

    public String readCommand() {
        return this.inputHandler.input();
    }

}
