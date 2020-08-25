package main.java;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    String command;
    Ui ui;
    Storage storage;
    TaskList tList;

    public Parser (String command, Ui ui, Storage storage, TaskList tList) {
        this.command = command;
        this.ui = ui;
        this.storage = storage;
        this.tList = tList;
    }

    public void parseCommand() throws IOException {
        if (command.equals("bye")) {
            ui.showByeMessage();
            Duke.running = false; // DONT KNOW IF THIS IS OK
        } else if (command.equals("list")) {
            ui.printList(tList);
        } else if (command.substring(0, 4).equals("done")) {
            int index = Integer.parseInt(command.substring(5)) - 1;
            tList.get(index).setStatus(true);
            ui.showDoneMessage(tList, index);
            storage.save(tList);
        } else if (command.substring(0, 6).equals("delete")) {
            int index = Integer.parseInt(command.substring(7)) - 1;
            try {
                ui.showDeleteMessage(tList, index);
                tList.remove(index);
                storage.save(tList);
            } catch (IndexOutOfBoundsException e) {
                ui.showIndexErrorMessage();
            }
        } else if (command.substring(0, 4).equals("todo")) {
            try {
                String name = command.substring(5);
                ToDo t = new ToDo(name, false);
                tList.add(t);
                ui.showAddTaskMessage(tList, t);
                storage.save(tList);
            } catch (IndexOutOfBoundsException e) {
                ui.showFormatErrorMessage();
            }
        } else if (command.substring(0, 5).equals("event")) {
            try {
                int escapeIndex = command.lastIndexOf("/");
                String name = command.substring(6, escapeIndex - 1);
                LocalDate date = LocalDate.parse(command.substring(escapeIndex + 4), DateTimeFormatter.ISO_DATE);
                Event e = new Event(name, false, date);
                tList.add(e);
                ui.showAddTaskMessage(tList, e);
                storage.save(tList);
            } catch (IndexOutOfBoundsException e) {
                ui.showFormatErrorMessage();
            } catch(DateTimeParseException e) {
                ui.showDateFormatErrorMessage();
            }
        } else if (command.substring(0, 8).equals("deadline")) {
            try {
                int escapeIndex = command.lastIndexOf("/");
                String name = command.substring(9, escapeIndex - 1);
                LocalDate date = LocalDate.parse(command.substring(escapeIndex + 4), DateTimeFormatter.ISO_DATE);
                Deadline d = new Deadline(name, false, date);
                tList.add(d);
                ui.showAddTaskMessage(tList, d);
                storage.save(tList);
            } catch (IndexOutOfBoundsException e) {
                ui.showFormatErrorMessage();
            } catch (DateTimeParseException e) {
                ui.showDateFormatErrorMessage();
            }
        } else {
            ui.showUnexpectedCommandMessage();
        }
    }

}
