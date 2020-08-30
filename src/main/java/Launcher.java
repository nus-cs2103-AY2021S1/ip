import javafx.application.Application;

import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {

//        Scanner fetch = new Scanner(System.in);
//
//
//
//        Ui myDukeBot = new Ui();
//        Storage myStorage = new Storage();
//        Parser myParser = new Parser();
//        TaskList myTaskList = new TaskList();
//
//
//        System.out.println(myDukeBot.greeting());
//        myStorage.createDirectory("ToDo");
//        myStorage.populateList(myTaskList);
//        System.out.println(myTaskList.listUI());
//
//        String myInput =fetch.nextLine();
//
//
//        while(!myInput.equals("bye")){
//            System.out.println(myParser.listenerForUI(myTaskList, myDukeBot,myInput));
//            myInput = fetch.nextLine();
//        }
//
//
//        myStorage.updateDirectory(myTaskList);
//        System.out.println("End");

        Application.launch(Duke.class,args);
    }
}
