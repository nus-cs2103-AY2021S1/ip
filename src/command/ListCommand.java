package command;

import ui.UIPrint;
import data.DukeData;

public class ListCommand extends Command {

    public ListCommand() {
        names = new String[] { "list" };
    }

    @Override
    public void execute(String str) {
        UIPrint.drawLine(UIPrint.star, 50);
        System.out.println("Current tasks:\n");

        for (int i = 0; i < DukeData.tasks.size(); i++) {
            System.out.println(i + 1 + ". " + DukeData.tasks.get(i));
        }

        if (DukeData.tasks.size() == 0) {
            System.out.println("None");
        }

        UIPrint.drawLine(UIPrint.star, 50);
    }
}
