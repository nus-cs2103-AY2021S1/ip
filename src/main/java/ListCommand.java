package main.java;

public class ListCommand extends Command {

    public ListCommand() {
        names = new String[] { "list" };
    }

    @Override
    public void execute() {
        UIPrint.drawLine(UIPrint.star, 50);

        for (int i = 0; i < Duke.inputThings.size(); i++) {
            System.out.println(i + 1 + ". " + Duke.inputThings.get(i));
        }

        UIPrint.drawLine(UIPrint.star, 50);
    }
}
