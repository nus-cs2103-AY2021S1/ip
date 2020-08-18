package main.java;

public class ExitCommand extends Command {

    public ExitCommand() {
        names = new String[] { "bye" };
    }

    @Override
    public void execute() {
        String exitWords = "Bye, hope to see you again soon!";

        UIPrint.drawLine(UIPrint.star, 50);
        System.out.println(exitWords);
        UIPrint.drawLine(UIPrint.star, 50);

        Duke.exitLoop = true;
    }
}
