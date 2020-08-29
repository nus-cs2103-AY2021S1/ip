package com.siawsam.duke;

import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A parser for command-line user inputs.
 */
public class Parser {
    /**
     * An array of valid Duke commands to edit tasks.
     */
    private final static List<String> COMMANDS = Arrays.asList("done", "delete", "find");
    private final TaskList userTaskList;
    private final Storage storage;

    /**
     * Constructs a Parser when no saved task list exists.
     *
     * @param storage A Storage instance to use when the parser needs to save to disk.
     */
    public Parser(Storage storage) {
        this.userTaskList = new TaskList();
        this.storage = storage;
    }

    /**
     * Constructs a Parser when a saved task list exists.
     *
     * @param storage A Storage instance to use when the parser needs to save to disk.
     * @param userTaskList A TaskList instance that represents the existing save.
     */
    Parser(Storage storage, TaskList userTaskList) {
       this.userTaskList = userTaskList;
       this.storage = storage;
    }

    /**
     * Parses user input strings from the command line.
     *
     * @throws IOException if an IO exception occurs when trying to read standard input.
     */
    public void scan() throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader((System.in)));

        scanLoop:
        while(true) {
            String line = reader.readLine();
            String command = line.split(" ")[0];

            if (COMMANDS.contains(command) && line.split(" ").length > 1) {
                // handle commands with >1 argument
                switch (command) {
                case "done":
                    try {
                        userTaskList.markAsDone(
                                Integer.parseInt(line.split( " ")[1])
                        );
                    } catch (IllegalArgumentException ex) {
                        Ui.showErrorMessage(ex);
                    }
                    break;
                case "delete":
                    try {
                        userTaskList.removeItem(
                                Integer.parseInt(line.split( " ")[1])
                        );
                    } catch (IllegalArgumentException ex) {
                        Ui.showErrorMessage(ex);
                    }
                    break;
                case "find":
                    TaskSearcher searcher = new TaskSearcher(userTaskList);
                    searcher.searchAndDisplay(line.split("find")[1].trim());
                    break;
                default:
                    break;
                }
            } else {
                switch(line) {
                case "bye":
                    Ui.showGoodbyeMessage();
                    break scanLoop;
                case "list":
                    userTaskList.printList();
                    break;
                case "save":
                    storage.save(userTaskList);
                    break;
                default:
                    try {
                        Task addedTask = userTaskList.addItem(line);
                        Ui.showSuccessfulAdd(addedTask);
                    } catch (DukeException ex) {
                        Ui.showErrorMessage(ex);
                    }
                    break;
                }
            }
        }
    }
}
