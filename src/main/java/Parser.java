package main.java;

import java.time.DateTimeException;
import java.util.Arrays;
import java.util.zip.DataFormatException;

public class Parser {

    enum COMMANDS {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        LIST("list"),
        EXIT("bye"),
        DONE("done"),
        DELETE("delete");
        public String text;

        COMMANDS(String text) {
            this.text = text;
        }
    }

    public static boolean understandText(String userText, TaskList taskList) {
        try {
            String edittedAnswer = userText.strip().toLowerCase();
            String[] answers = userText.split(" ");
            if (answers.length == 2 && answers[0].equals(COMMANDS.DONE.text)) {
                Command command = new DoneCommand();
                Parser.furtherUnderstandTaskNumber(command, answers[1], taskList);
//                return taskList.completeTask(answers[1]);
            } else if (answers.length == 2 && answers[0].equals(COMMANDS.DELETE.text)) {

                Command command = new DeleteCommand();
                Parser.furtherUnderstandTaskNumber(command, answers[1], taskList);
//                return taskList.deleteTask(answers[1]);
            } else if (edittedAnswer.equals(COMMANDS.LIST.text)) {


                Command command = new ListCommand();
                command.execute( " ", taskList );
//                return this.taskList.printStore();
            } else if (edittedAnswer.equals(COMMANDS.EXIT.text)) {

                Command command = new ByeCommand();
                command.execute( " ", taskList );
                return false;
//                return this.exit();
            } else if (answers[0].equals(COMMANDS.TODO.text) ||
                    answers[0].equals(COMMANDS.DEADLINE.text) ||
                    answers[0].equals(COMMANDS.EVENT.text)) {

                Parser.furtherUnderstandTask(userText, taskList);
            } else {
                throw new DukeCannotUnderstandException();
            }
        } catch (DukeCannotUnderstandException e) {
            System.out.println(e.getMessage() + "\n" + Ui.LINE);
        }
        return true;
    }

    private static void furtherUnderstandTaskNumber(Command command, String answer, TaskList taskList) {
        try {
            int oneIndex = Integer.parseInt(answer);
            int realIndex = oneIndex - 1;
            command.execute(String.valueOf(realIndex), taskList);
        } catch (NumberFormatException e) {
            System.out.println("I can't seem to understand what task you are referring to.\n" +
                    "Please let me know in this format: " + command.commandText + " <number of task>\n" + Ui.LINE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Hmm... I don't have a task numbered " + answer + "\n" + Ui.LINE);
        }
    }
    
    private static void furtherUnderstandTask(String answer, TaskList taskList) {
        try {
            String[] answers = answer.split(" ", 2);
            if (answers.length > 1) {
                String type = answers[0].strip();
                String task = answers[1].strip();
                String[] partsOfTask = task.split("/");

                // checking for event and deadline if the date is given or not
                if (type.equals(COMMANDS.TODO.text) || partsOfTask.length == 2) {
                    Command command = new AddCommand(type);
                    command.execute(task, taskList);
                } else {
                    String description = partsOfTask[0].strip();
                    String date = partsOfTask[1].strip();
                    // check if the date is given in correct format
                    if (date.length() != 8) {
                        throw new DataFormatException();
                    }
                    String instruction = "<type of task> <description> / <deadline>";
                    if (type.equals(COMMANDS.EVENT.text)) {
                        instruction = "<type of task> <description> / <date of event>";
                    }
                    throw new DukeGotNoArgumentsException(instruction);
                }
            } else {
                // no description
                String instruction = "<type of task> <description>";
                if (answers[0].equals(COMMANDS.DEADLINE.text))
                    instruction = "<type of task> <description> / <due date>";
                else if (answers[0].equals(COMMANDS.EVENT.text))
                    instruction = "<type of task> <description> / <date of event>";
                throw new DukeGotNoArgumentsException(instruction);
            }
        } catch (DukeGotNoArgumentsException e) {
            System.out.println(e.getMessage() + "\n" + Ui.LINE);
        } catch (DataFormatException e) {
            System.out.println("Please key in again with the date in the ddmmyyyy format." + "\n" + Ui.LINE);
        }
    }
}
