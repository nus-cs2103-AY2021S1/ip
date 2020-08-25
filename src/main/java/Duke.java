package main.java;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    public static void echoInput() {
        Scanner readInput = new Scanner(System.in);
        String currentWord = readInput.next();
        while (!currentWord.equals("bye")) {
            System.out.println(currentWord);
            currentWord = readInput.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Split the phrase into each individual word
     * @param command
     * @return String array contains each individual word
     *
     *
     */
    public static String[] split(String command) {
        String[] array = command.split(" ");
        return array;
    }

    /**
     * Join each word in the input to form a proper phrase
     * @param stringArray String array contains the splitted word
     * @param index Position to start joining the words
     * @return Phrase without the command word
     */
    public static String join(String[] stringArray, int index) {
        String result = "";
        for (int i = index; i < stringArray.length; i++) {
            result += stringArray[i] + (i == stringArray.length - 1 ? "" : " ");
        }
        return result;
    }

    /**
     * Find the time given in the input
     * @param task Input from user
     * @param keyword Keyword to specify whether it is deadline or event
     * @return Time specified in the input
     * @throws EmptyDateException
     */
    public static String findTime(String task, String keyword) throws EmptyDateException {
        String[] array = task.split("/" + keyword + " ");
        if (array.length < 2) {
            throw new EmptyDateException();
        } else {
            return array[1];
        }
    }


    public static boolean isValidDate(String time) throws IllegalArgumentException {
        String[] stringArr = time.split("-");
        if (stringArr.length != 3) {
            throw new IllegalArgumentException("Entered date is in the wrong format. Please " +
                    "specify in this format YYYY-MM-DD");
        }

        return true;
    }

    public static boolean hasTime(String time) {
        String[] stringArr = time.split(" ");
        return stringArr.length == 2;
    }

    /**
     * Parse the description of the input
     * @param task Input
     * @return Description of the task in String
     * @throws EmptyTaskException
     */
    public static String findDescription(String task) throws EmptyTaskException {
        String[] array = task.split("/");
        if (array[0].equals("")) {
            throw new EmptyTaskException();
        } else {
            return array[0];
        }
    }

    /**
     * Driver function
     */
    public static void run() {
        Scanner scanner = new Scanner(System.in);
        String currentWord = scanner.nextLine();
        try {
            Storage storage = new Storage("duke.txt");
            TaskList taskList = storage.formTaskList(storage.readStorageFile());
            while (!currentWord.equals("bye")) {
                String command = split(currentWord)[0];
                try {
                    if (Keyword.isValid(command)) {
                        if (command.equals("list")) {
                            System.out.println(taskList.toString());
                        } else {

                            if (command.equals("done")) {
                                String indexText = join(split(currentWord), 1);
                                if (indexText.equals("")) {
                                    throw new InvalidIndexException();
                                } else {
                                    int taskNumber = Integer.parseInt(indexText);
                                    taskList.getTask(taskNumber - 1).markAsDone();
                                }
                            } else if (command.equals("delete")) {
                                String indexText = join(split(currentWord), 1);
                                if (indexText.equals("")) {
                                    throw new InvalidIndexException();
                                } else {
                                    int taskNumber = Integer.parseInt(indexText);
                                    taskList.deleteTask(taskNumber - 1);
                                }


                            } else if (command.equals("todo")) {
                                String description = findDescription(join(split(currentWord), 1));
                                Task newTask = new ToDo(description);
                                taskList.addTask(newTask);

                            } else if (command.equals("deadline")) {
                                String detail = join(split(currentWord), 1);
                                String description = findDescription(detail);
                                String deadlineTime = findTime(detail, "by");
                                isValidDate(deadlineTime);
                                boolean hasTime = hasTime(deadlineTime);
                                Task newTask = new DeadLine(description, deadlineTime, hasTime, false);
                                taskList.addTask(newTask);
                            } else {
                                String detail = join(split(currentWord), 1);
                                String description = findDescription(detail);
                                String time = findTime(detail, "at");
                                isValidDate(time);
                                boolean hasTime = hasTime(time);
                                Task newTask = new Event(description, time, hasTime, false);
                                taskList.addTask(newTask);
                            }

                        }
                    } else {
                        throw new InvalidCommandException();
                    }
                } catch (InvalidIndexException err) {
                    System.out.println(err.toString());
                } catch (EmptyTaskException err) {
                    System.out.println(err.toString());
                } catch (EmptyDateException err) {
                    System.out.println(err.toString());
                } catch (InvalidCommandException err) {
                    System.out.println(err.toString());
                } catch (IllegalArgumentException err) {
                    System.out.println(err.toString());
                } finally {
                    storage.writeTasks(taskList);
                    currentWord = scanner.nextLine();

                }
            }
            System.out.println("Bye. Hope to see you again soon!");
        } catch (IOException err) {
            System.out.println(err.toString());
        }
    }

    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hola! Duke reporting for duty\nWhat can I do for you sir?");
        run();
    }
}
