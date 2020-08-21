package main.java;

import java.util.Arrays;

public class Logic {
    private Store store;

    private final String line = "____________________________________________________________\n";

    public Logic() {
        this.store = new Store();
    }

    enum COMMANDS {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        LIST("list"),
        EXIT("bye"),
        DONE("done"),
        DELETE("delete")
        ;
        private String text;
        COMMANDS(String text) {
            this.text = text;
        }
    }
//    private final String LIST_COMMAND = "list";
//    private final String EXIT_COMMAND = "bye";
//    private final String DONE_COMMAND = "done";
//    private final String TODO_COMMAND = "todo";
//    private final String DEADLINE_COMMAND = "deadline";
//    private final String EVENT_COMMAND = "event";
//    private final String DELETE_COMMAND = "delete";


    public boolean digestString(String answer) {

        try {
            String editted_answer = answer.strip().toLowerCase();
            String[] answers = answer.split(" ");
            if (answers.length == 2 && answers[0].equals(COMMANDS.DONE.text)) {
                return store.completeTask(answers[1]);
            } else if (answers.length == 2 && answers[0].equals(COMMANDS.DELETE.text)) {
                return store.deleteTask(answers[1]);
            }  else if (editted_answer.equals(COMMANDS.LIST.text)) {
                return this.store.printStore();
            } else if (editted_answer.equals(COMMANDS.EXIT.text)) {
                return this.exit();
            } else if (answers[0].equals(COMMANDS.TODO.text) || answers[0].equals(COMMANDS.DEADLINE.text) || answers[0].equals(COMMANDS.EVENT.text) ) {
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
                if (answers[0].equals(COMMANDS.DEADLINE.text)) instruction = "<type of task> <description> / <due date>";
                else if (answers[0].equals(COMMANDS.EVENT.text)) instruction = "<type of task> <description> / <date of event>";
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
