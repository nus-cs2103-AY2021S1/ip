package duke.tasks;

import java.io.IOException;

public class EventCommand extends Command {
    protected String event;

    public EventCommand(String event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        //add event task to list of tasks
        tasks.event(this.event);

        //write to file
        String s = storage.genList(tasks.getTaskLs());
        storage.writeToFile("data/duke.rtf", s);
    }

    public static class Parser {

        public static Command parse(String toPrint) {
                if (toPrint.startsWith("list")) {
                    return new ListCommand(toPrint);
                }
                else if (toPrint.startsWith("done")) {
                    return new DoneCommand(toPrint);
                }
                else if (toPrint.startsWith("delete")) {
                    return new DeleteCommand(toPrint);
                }
                else if (toPrint.startsWith("todo")) {
                    return new TodoCommand(toPrint);
                }
                else if (toPrint.startsWith("event")) {
                    return new EventCommand(toPrint);
                }
                else if (toPrint.startsWith("deadline")) {
                    return new DeadlineCommand(toPrint);
                }
                else if (toPrint.startsWith("bye")) {
                    return new ByeCommand();
                }
                else {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    return null;
                }
        }

    }
}
