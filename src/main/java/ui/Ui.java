package ui;

import parser.Parser;
import command.Command;
import constant.DukeConstants;
import service.DukeService;

import java.util.Objects;
import java.util.Scanner;

public class Ui {

    private final DukeService dukeService = new DukeService();

    private final Scanner scanner = new Scanner(System.in);

    private final Parser parser = new Parser();

    private boolean isOn = true;

    public void run() {
        printGreetingMessage();
        while (isOn) {
            String input = this.getInput();
            if (this.parser.isExit(input)) {
                this.exit();
            } else {
                this.respond(input);
            }
        }
    }


    private void exit() {
        dukeService.printResponse(DukeConstants.EXIT_RESPONSE);
        dukeService.saveList();
        this.isOn = false;
    }

    private void respond(String input) {
        Command command = this.parser.parse(input);
        if (Objects.isNull(command)) {
            this.handleInvalidInput();
        } else {
            command.execute(dukeService);
        }
    }

    private void handleInvalidInput() {
        this.dukeService.printResponse("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private void printGreetingMessage() {
        System.out.println("Hello from\n" + DukeConstants.LOGO);
        this.dukeService.printResponse(DukeConstants.INITIAL_RESPONSE);
    }

    private String getInput() {
        return scanner.nextLine();
    }
}
