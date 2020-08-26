package duke;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        if (taskList.getTasks().isEmpty()) {
            System.out.println(Ui.line);
            System.out.println(Ui.bot);
            System.out.println("There are no tasks in your list yet! >_<");
        } else {
            System.out.println(Ui.line);
            System.out.println(Ui.bot);
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(i + 1 + "." + " " + taskList.getTasks().get(i));
            }
        }
    }
}
