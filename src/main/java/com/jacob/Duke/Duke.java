package com.jacob.Duke;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {
    /**
     * Basic Driver code to get the inputs
     * @param args Basic syntax requirement
     */
    public static void main(String[] args) {
        List<Task> toDoList = new ArrayList<>();
        CommandHandlers comHandlers = new CommandHandlers();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Omo!! hello from\n" + logo);

        // get file commands and turn them into task objects
        String filename = FileEditor.accessTaskListInFileSystem(FileEditor.getCurrentDirectory());
        toDoList = FileEditor.readFile(filename, comHandlers, toDoList);

        //get console inputs
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            // all encompassing driver code
            comHandlers.handleConsoleCommands(input, toDoList);
            FileEditor.writeToFile(filename);
            // checks for the next input
            input = sc.nextLine();
            }

        //handle the bye command when encountered
        System.out.println(" -----------------");
        System.out.println(comHandlers.byeHandler());
        System.out.println(" -----------------");
        sc.close();
    }




}
