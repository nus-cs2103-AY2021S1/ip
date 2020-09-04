package duke.component;

import java.util.function.Predicate;

import duke.task.Task;

public interface Ui {
    String ADD_TASK_OUTPUT_FORMAT = "Got it. I've added this task:\n%s\nNow you have %d %s in the list.";
    String DELETE_TASK_OUTPUT_FORMAT = "Noted. I've removed this task:\n%s\nNow you have %d %s.";
    String DONE_TASK_OUTPUT_FORMAT = "Nice! I've marked this task as done:\n%s";
    String FIND_LIST_NOTE_FORMAT = "containing '%s' ";
    String HAPPEN_LIST_NOTE_FORMAT = "happening %s %s ";
    String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    String TASK_LIST_HEADING = "Here are the tasks %sin your list:\n\n";
    void print(String str);
    String printList(TaskList list, Predicate<Task> predicate, String note);
    void output(String message);
    String readInput();
    String greeting();
    void close();
}
