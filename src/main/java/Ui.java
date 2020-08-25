
import java.time.LocalDate;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }


    public void horizontalRule() {
        System.out.println("____________________________________________________________");
    }

    public void greeting() {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


    }



}



