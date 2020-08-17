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
    private final String DELETE_COMMAND = "delete";


    public boolean digestString(String answer) {
        try {
            String editted_answer = answer.strip().toLowerCase();
            String[] answers = answer.split(" ");
            if (answers.length == 2 && answers[0].equals(DONE_COMMAND)) {
                return store.completeTask(answers[1]);
            } else if (answers.length == 2 && answers[0].equals(DELETE_COMMAND)) {
                return store.deleteTask(answers[1]);
            }  else if (editted_answer.equals(LIST_COMMAND)) {
                return this.store.printStore();
            } else if (editted_answer.equals(EXIT_COMMAND)) {
                return this.exit();
            } else if (answers[0].equals(TODO_COMMAND) || answers[0].equals(DEADLINE_COMMAND) || answers[0].equals(EVENT_COMMAND) ) {
                return this.understandingTask(answer);
            } else {
                throw new DukeCannotUnderstandException();
            }
        } catch (DukeCannotUnderstandException e) {
            System.out.println(e.getMessage() + "\n" + line);
        }
        return true;
    }

    private boolean understandingTask(String answer) {
        try {
            String[] answers = answer.split(" ", 2);
            if (answers.length > 1) {
                this.store.addToStore(answers[0], answers[1]);
            } else {
                // no description
                String instruction =  "<type of task> <description>" ;
                if (answers[0].equals(DEADLINE_COMMAND)) instruction = "<type of task> <description> / <due date>";
                else if (answers[0].equals(EVENT_COMMAND)) instruction = "<type of task> <description> / <date of event>";
                throw new DukeGotNoArgumentsException(instruction);
            }
        } catch (DukeGotNoArgumentsException e) {
            System.out.println(e.getMessage() + "\n" + line);
        }
        return true;
    }

    private boolean exit() {
        System.out.println("Bye! See you later :)" );
        return false;
    }

}
