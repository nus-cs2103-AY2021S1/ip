package parser;

import static ui.Ui.echo;
import static ui.Ui.line;
import static ui.Ui.listOut;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import data.task.Task;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class Parser {
    Parser() {
        // empty constructor
    }

    /**
     * scans for user inputs
     */
    public static void accept(TaskList tl, Storage stores) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String output = scanner.nextLine();
            // special 1 word cases
            // bye exits while
            if (output.equals("bye")) {
                echo("Ciao!");
                break;
            } else if (output.equals("list")) {
                listOut(tl.getStorage());
            } else {
                Scanner multiWord = new Scanner(output);
                // can't use enums here, firstWord can be literally anything
                String firstWord = multiWord.next();
                // wow intelliJ is a better programmer than i'll ever be
                switch (firstWord) {
                case "done": {
                    String index = "0";
                    try {
                        if (multiWord.hasNext()) {
                            index = multiWord.next();
                        } else {
                            throw Ui.DukeException.empty("done");
                        }
                    } catch (Ui.DukeException e) {
                        echo(e.getMessage());
                    }
                    int intIndex = Integer.parseInt(index);
                    try {
                        if (intIndex <= tl.getStorage().size() && intIndex > 0) {
                            tl.markComplete(intIndex - 1);
                            // save to save file
                            stores.save(tl.getStorage());
                        } else {
                            throw Ui.DukeException.outOfBounds(intIndex);
                        }
                    } catch (Ui.DukeException e) {
                        echo(e.getMessage());
                    }

                    break;
                }
                case "delete": {
                    String index = multiWord.next();
                    int intIndex = Integer.parseInt(index);
                    try {
                        if (intIndex <= tl.getStorage().size() && intIndex > 0) {
                            tl.delete(intIndex - 1);
                            // save to save file
                            stores.save(tl.getStorage());
                        } else {
                            throw Ui.DukeException.outOfBounds(intIndex);
                        }
                    } catch (Ui.DukeException e) {
                        echo(e.getMessage());
                    }
                    break;
                }
                case "due": {
                    try {
                        LocalDate dueDate = LocalDate.parse(multiWord.nextLine().trim());
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
                        line();
                        System.out.println("These tasks are due:");
                        for (Task task: tl.getDateStorage().get(dueDate.format(dateFormat))) {
                            System.out.println(task);
                        }
                        line();
                    } catch (Exception e) {
                        // time can't be parsed
                        echo(e.getMessage());
                    }
                    break;
                }
                // it's a task
                case "todo":
                case "deadline":
                case "event":
                    try {
                        // whitespace in front of nextLine
                        if (multiWord.hasNextLine()) {
                            String remainingWords = multiWord.nextLine().trim();
                            tl.store(firstWord, remainingWords);
                            // save to save file
                            stores.save(tl.getStorage());
                        } else {
                            throw Ui.DukeException.empty(firstWord);
                        }
                    } catch (Ui.DukeException e) {
                        echo(e.getMessage());
                    }
                    break;
                // invalid order
                default:
                    // skip the try catch block
                    echo(Ui.DukeException.invalid(output).getMessage());
                }
            }
        }
    }
}
