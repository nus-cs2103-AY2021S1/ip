package duke;

import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;

import duke.command.*;
import duke.ui.Ui;


/**
 * Understands inputs given by user and validates them.
 */
public class Parser {

    /**
     * Understands input from user
     *
     * @param userText input from user
     * @param taskList list of tasks user has currently
     * @return boolean value of whether conversation is to be continued
     */
    public static String understandText(String userText, TaskList taskList) {
        try {
            String editedAnswer = userText.strip().toLowerCase();
            String[] answers = editedAnswer.split(" ");
            String commandWord = answers[0];
            if (answers.length == 2 && Commands.DONE.containsKeyWord(commandWord)) {
                return Parser.understandTaskNumber(answers[0], answers[1], taskList);

            } else if (answers.length == 2 && Commands.DELETE.containsKeyWord(commandWord)) {
                Command command = new DeleteCommand();
                return Parser.understandTaskNumber(answers[0], answers[1], taskList);

            } else if (Commands.FIND.containsKeyWord(commandWord)) {
                String toMatch = editedAnswer.split(" ", 2)[1];
                Command command = new FindCommand();
                return command.execute(toMatch, taskList);

            } else if (Commands.LIST.containsKeyWord(commandWord)) {
                Command command = new ListCommand();
                return command.execute(" ", taskList);

            } else if (Commands.EXIT.containsKeyWord(commandWord)) {
                Command command = new ByeCommand();
                return command.execute(" ", taskList);

            } else if (Commands.HELLO.containsKeyWord(commandWord)) {
                Command command = new HelloCommand();
                return command.execute(" ", taskList);

            } else if (Commands.TODO.containsKeyWord(commandWord)
                    || Commands.DEADLINE.containsKeyWord(commandWord)
                    || Commands.EVENT.containsKeyWord(commandWord)) {
                return Parser.understandTaskDescription(userText, taskList);

            } else {
                throw new DukeCannotUnderstandException();
            }
        } catch (DukeCannotUnderstandException e) {
            return Ui.printErrorMessage(e.getMessage());
        }
    }

    private static String understandTaskNumber(String stringCommand, String answer, TaskList taskList) {
        try {
            int oneIndex = Integer.parseInt(answer);
            int realIndex = oneIndex - 1;
            Command command;
            if (Commands.DONE.containsKeyWord(stringCommand)) {
                command = new DoneCommand();
            } else {
                command = new DeleteCommand();
            }
            return command.execute(String.valueOf(realIndex), taskList);
        } catch (NumberFormatException e) {
            return Ui.printErrorMessage("I can't seem to understand what task you are referring to.\n"
                    + "Please let me know in this format: " + stringCommand
                    + " <number of task>\n");
        } catch (IndexOutOfBoundsException e) {
            return Ui.printErrorMessage("Hmm... I don't have a task numbered " + answer);
        }
    }

    private static String understandTaskDescription(String answer, TaskList taskList) {
        try {
            String[] answers = answer.split(" ", 2);
            String type = answers[0].strip();
            if (answers.length > 1) {
                String task = answers[1].strip();
                String[] partsOfTask = task.split("/");

                // checking for event and deadline if the date is given or not
                if (Commands.TODO.containsKeyWord(type) || partsOfTask.length == 2) {
                    if (Commands.TODO.containsKeyWord(type)) {
                        Command command = new AddCommand(type);
                        return command.execute(task, taskList);

                    } else {
                        String description = partsOfTask[0].strip();
                        String date = partsOfTask[1].strip();

                        // check if the date is given in correct format
                        if (date.length() != 8) {
                            throw new DataFormatException();
                        }

                        Command command = new AddCommand(type);
                        return command.execute(task, taskList);
                    }
                } else {
                    String instruction = "<type of task> <description> / <deadline>";
                    if (Commands.EVENT.containsKeyWord(type)) {
                        instruction = "<type of task> <description> / <date of event>";
                    }
                    throw new DukeGotNoArgumentsException(instruction);
                }
            } else {
                // handles the case when no description
                String instruction = "<type of task> <description>";
                if (Commands.DEADLINE.containsKeyWord(type)) {
                    instruction = "<type of task> <description> / <due date>";
                } else if (Commands.EVENT.containsKeyWord(type)) {
                    instruction = "<type of task> <description> / <date of event>";
                }
                throw new DukeGotNoArgumentsException(instruction);
            }
        } catch (DukeGotNoArgumentsException e) {
            return Ui.printErrorMessage(e.getMessage());
        } catch (DataFormatException e) {
            return Ui.printErrorMessage("Please key in again with the date in the ddmmyyyy format.");
        } catch (ArrayIndexOutOfBoundsException e) {
            return Ui.printErrorMessage(answer);
        }
    }

    enum Commands {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        LIST("list", "name"),
        EXIT("bye", "goodbye", "cya", "exit"),
        DONE("done", "finish"),
        DELETE("delete", "remove"),
        FIND("find", "search"),
        HELLO("hello", "hi", "yo");

        private List<String> relevantWords;

        Commands(String... relevantWords) {
            this.relevantWords = Arrays.asList(relevantWords);
        }

        public boolean containsKeyWord(String userInput) {
            return this.relevantWords.contains(userInput);
        }
    }

}
