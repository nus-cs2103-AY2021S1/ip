package duke.ui;

import duke.tasks.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public interface Ui {
    
    void respond(Scanner sc, TaskList myTasks);
    
    ArrayList<String> prettify(ArrayList<String> rawResponse); // unsure if prettify should be in the contract
}
