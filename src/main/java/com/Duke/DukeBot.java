package com.Duke;

import com.Duke.Commands.*;
import com.Duke.DataManager.Storage;
import com.Duke.TaskManager.DukeParser;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;

import java.util.Scanner;

public class DukeBot {
    //This class handles the simulation of the Duke chat bot
    public static void simulate(){
        TaskList ls = new TaskList(Storage.read());
        boolean hasBye = false;
        String input;
        String[] splitList;

        UI.dukeInit();
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        DukeParser parser = new DukeParser(ls);


        while(!hasBye) {
            Command command = parser.parse(input);
            command.execute();
            hasBye = command.isDone();
            if(!hasBye){
                input = sc.nextLine();
            }
        }
    }
}
