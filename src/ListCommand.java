public class ListCommand extends Command {

    public ListCommand() {
        names = new String[] { "list" };
    }

    @Override
    public void execute(String str) {
        UIPrint.drawLine(UIPrint.star, 50);
        System.out.println("Current tasks:\n");

        for (int i = 0; i < Duke.tasks.size(); i++) {
            System.out.println(i + 1 + ". " + Duke.tasks.get(i));
        }

        if (Duke.tasks.size() == 0) {
            System.out.println("None");
        }

        UIPrint.drawLine(UIPrint.star, 50);
    }
}
