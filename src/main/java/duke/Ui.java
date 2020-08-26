package duke;

import duke.exceptions.*;
import duke.task.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    protected final String INDENTATION = "     ";
    protected final String LINE = "    ____________________________________________________________";


    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    void intro() {
        reply("Hello, I'm Ravenloss");
        reply("What can I do for you?");
        System.out.println(LINE);
    }

    public void reply(String string) {
        System.out.println(INDENTATION + string);
    }

    public String nextInquiry() {
        return scanner.nextLine();
    }

    public void list(List<Task> planner) {
        if (planner.size() == 0) {
            reply("You have no pending tasks");
        } else {
            reply("Here are the tasks in your list:");
            for (int i = 0; i < planner.size(); i++) {
                String number = (i + 1) + ".";
                Task currentTask = planner.get(i);
                reply(number + currentTask.toString());
            }
        }
    }

    public void addMessage(Task currentTask, Integer size) {
        reply("Got it. I've added this duke.task:");
        reply(INDENTATION + currentTask.toString());
        reply("Now you have " + size + " tasks in the list.");
    }

    public void doneMessage(Task currentTask) {
        currentTask.done();
        reply("Good job! I've marked this task as done");
        reply(INDENTATION + currentTask.toString());
    }

    public void farewell() {
        reply("Bye. Hope to see you again soon!");
    }

    public void deleteMessage(Task currentTask, Integer num) {
        reply("Noted. I've removed this duke.task: ");
        reply(INDENTATION + currentTask.toString());
        reply("Now you have " + num + " tasks in the list.");
    }

    public void showLine() {
        System.out.println(LINE);
    }


}
