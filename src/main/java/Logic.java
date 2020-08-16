package main.java;

import java.util.Arrays;

public class Logic {
    private Store store;

    private final String line = "____________________________________________________________\n";

    public Logic() {
        this.store = new Store();
    }

    private final String LIST_COMMAND = "list";
    private final String EXIT_COMMAND = "bye";
    private final String DONE_COMMAND = "done";
    private final String TODO_COMMAND = "todo";
    private final String DEADLINE_COMMAND = "deadline";
    private final String EVENT_COMMAND = "event";

    public boolean digestString(String answer) {
        String editted_answer = answer.strip().toLowerCase();
        String[] answers = answer.split(" ");
        if (answers.length == 2 && answers[0].equals(DONE_COMMAND)) {
            return store.completeTask(answers[1]);
        } else if (editted_answer.equals(LIST_COMMAND)) {
            return this.store.printStore();
        } else if (editted_answer.equals(EXIT_COMMAND)) {
            return this.exit();
        } else if (answers[0].equals(TODO_COMMAND) || answers[0].equals(DEADLINE_COMMAND) || answers[0].equals(EVENT_COMMAND) ) {
            if (answers.length > 1) {
                String[] command = answer.split(" ", 2);
                this.store.addToStore(command[0], command[1]);
            } else {
                System.out.println("Please re-enter in this manner: <type of task> <description>.\n" +
                        "The description can't be empty.\n" + line);
                return true;
            }
        } else {
            System.out.println("Hmm I did not understand what you meant.\n" +
                    "Could you try again?\n" + line);
        }
        return true;
    }

    private boolean exit() {
        System.out.println("Bye! See you later :)" );
        return false;
    }

}
