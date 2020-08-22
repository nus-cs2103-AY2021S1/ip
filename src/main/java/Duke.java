package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        taskList = new TaskList(storage.readFile());
    }

    public void run() {

        ui.printWelcome();

        String input = ui.readLine();

        while (!input.equals("bye")) {
            try {
                Parser.parse(input, taskList, ui);
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
                ui.printLine();
            }
            finally {
                input = ui.readLine();
            }
        }

        storage.saveFile(taskList.getList());

        ui.printLine();
        System.out.println("Bye. Hope to see you again soon!");
        ui.printLine();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
