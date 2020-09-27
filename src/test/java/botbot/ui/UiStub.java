package botbot.ui;

import botbot.tasks.Task;

public class UiStub extends Ui {
    public static final String INDENT = "  ";

    private static final String RESPONSE_ADD = "ok! I've added this task:\n" + INDENT
            + "%s\nyou now have %d task%s in your list\n";
    private static final String RESPONSE_DELETE = "ok! I've removed this task:\n" + INDENT
            + "%s\nyou now have %d task%s in your list\n";
    private static final String RESPONSE_EDIT = "ok! I've edited the task to:\n" + INDENT + "%s";
    private static final String RESPONSE_MARK_AS_DONE = "nice! I've marked this task as done:\n"
            + INDENT + "%s\n";

    @Override
    public String showAddResponse(Task task, int numOfTasks) {
        String plurality = makePlural(numOfTasks);
        return String.format(RESPONSE_ADD, "task", numOfTasks, plurality);
    }

    @Override
    public String showDeleteResponse(Task task, int numOfTasks) {
        String plurality = makePlural(numOfTasks);
        return String.format(RESPONSE_DELETE, "task", numOfTasks, plurality);
    }

    @Override
    public String showEditResponse(Task task) {
        return String.format(RESPONSE_EDIT, "task");
    }

    @Override
    public String showMarkAsDoneResponse(Task task) {
        return String.format(RESPONSE_MARK_AS_DONE, "task");
    }
}
