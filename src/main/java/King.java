package main.java;
import com.sun.source.util.TaskListener;

import java.util.ArrayList;
import java.util.Scanner;

public class King {
    Storage storage;
    Parser parser;
    TaskList taskList;

    King(String filepath){
        storage = new Storage(filepath);
        taskList = new TaskList();
        taskList.addAll(storage.load());
        this.parser = new Parser(storage,taskList);
    }

    // handles user input
    public void chat() {
        Scanner scanner = new Scanner(System.in);
        String phrase;
        while(scanner.hasNextLine() && !(phrase = scanner.nextLine()).equals("bye")){
            try {
                System.out.println(parser.parse(phrase));
            } catch (KingException e) {
                System.out.println(UI.chatBox(e.message));
            }
        }
        System.out.print(UI.chatBox("Bye. Hope to see you again soon!"));
        scanner.close();
    }

    public static void main(String[] args) {
        System.out.println(UI.welcome());
        King king = new King("data/king.txt");
        king.chat();
    }
}
