package duke;

import java.awt.image.BandedSampleModel;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelloCommand;
import duke.command.ListCommand;
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
            String[] answers = editedAnswer.split(" ", 2);
            String commandWord = answers[0];
            if (answers.length == 2 && Commands.DONE.containsKeyWord(commandWord)
                    || Commands.DELETE.containsKeyWord(commandWord)) {
                return Parser.understandTaskNumber(answers[0], answers[1], taskList);

            } else if (Commands.FIND.containsKeyWord(commandWord)) {
                return Parser.understandMatchingString(editedAnswer, taskList);

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

    private static String understandMatchingString(String userInput, TaskList taskList) {
        try {
            String[] dissectedInput = userInput.split(" ", 2);
            if (dissectedInput.length > 1) {
                String toMatch = dissectedInput[1];
                Command command = new FindCommand();
                return command.execute(toMatch, taskList);
            } else {
                throw new DukeGotNoArgumentsException("find <thing to find>");
            }
        } catch (DukeGotNoArgumentsException e) {
            return Ui.printErrorMessage(e.getMessage());
        }
    }

    private static String understandTaskNumber(String stringCommand, String answer, TaskList taskList) {
        try {
            int givenIndex = Integer.parseInt(answer);
            int realIndex = givenIndex - 1;
            if (realIndex >= taskList.getNumberOfTasks()) {
                throw new IndexOutOfBoundsException();
            }
            Command command;
            if (Commands.DONE.containsKeyWord(stringCommand)) {
                command = new DoneCommand();
            } else {
                assert Commands.DELETE.containsKeyWord(stringCommand);
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
            if (answers.length > 1 || Commands.TODO.containsKeyWord(type)) {
                String task = answers[1].strip();
                if (Commands.TODO.containsKeyWord(type)) {
                    Command command = new AddCommand(type);
                    return command.execute(task, taskList);
                } else {
                    // it is an event or deadline
                    return Parser.understandDeadlineOrEventDate(type, task, taskList);
                }
            } else {
                //  no description given
                String instruction = Commands.DEADLINE.containsKeyWord(type)
                        ? "<type of task> <description> / <due date>"
                        : "<type of task> <description> / <date of event>";
                throw new DukeGotNoArgumentsException(instruction);
            }
        } catch (DukeGotNoArgumentsException e) {
            return Ui.printErrorMessage(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            return Ui.printErrorMessage(answer);
        }
    }

    private static String understandDeadlineOrEventDate(String type, String task, TaskList taskList) {
        try {
            String[] partsOfTask = task.strip().split("/", 2);
            if (partsOfTask.length > 1) {
                String date = partsOfTask[1].strip();
                // check if the date is given in correct format
                if (date.length() == 8) {
                    Command command = new AddCommand(type);
                    return command.execute(task, taskList);
                } else {
                    throw new DataFormatException();
                }
            } else {
                // date details not given
                String instruction = Commands.DEADLINE.containsKeyWord(type)
                        ? "<type of task> <description> / <due date>"
                        : "<type of task> <description> / <date of event>";
                throw new DukeGotNoArgumentsException(instruction);
            }
        } catch (DukeGotNoArgumentsException e) {
            return Ui.printErrorMessage(e.getMessage());
        } catch (DataFormatException e) {
            return Ui.printErrorMessage("Please key in again with the date in the ddmmyyyy format.");
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
