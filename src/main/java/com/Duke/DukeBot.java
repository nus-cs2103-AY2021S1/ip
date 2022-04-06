package com.Duke;

import com.Duke.Commands.*;

import com.Duke.DataManager.Storage;
import com.Duke.TaskManager.DukeParser;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;

import java.util.Scanner;

/**
* This Class handles the simulation of the Duke Chat bot
* It creates the lists for storing data and facilitates the
* parser and storage processes
 */
public class DukeBot {

    /**
     * This method begins the CLI simulation of Duke
     */
    public static void simulate(){
        TaskList ls = new TaskList(Storage.read());
        boolean hasBye = false;
        String input;

        UI.dukeInit();
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        DukeParser inputParser = new DukeParser(ls);


        while(!hasBye) {
            Command command = inputParser.parse(input);
            command.execute();
            hasBye = command.isDone();
            if(!hasBye){
                input = sc.nextLine();
            }
        }
    }

    /**
     * This method begins the GUI simulation of Duke
     */
    public static String simulateGui(String input) {
        TaskList ls = new TaskList(Storage.read());
        DukeParser inputParser = new DukeParser(ls);
        Command command = inputParser.parse(input);
        return command.execute();

    }
}
