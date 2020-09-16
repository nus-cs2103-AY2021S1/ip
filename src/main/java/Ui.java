
import java.time.LocalDate;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * manages user interfacce interactions
 */
public class Ui {

    /**
     * creates line
     *
     * @return a string of line
     */
    public String horizontalRule() {
        String myLine = "____________________________________________________________";
       // System.out.println(myLine);
        return myLine;
    }

    /**
     * A cool duke image
     *
     * @return string of a Duke image
     */
    public String greeting() {
        String greetings= "Welcome to Jarvis List \n" +
            "type help to show list of commands";
        return greetings;


    }

    public String help(){
        String myHelp =
            "--These are Jarvis Functions--\n" +
                "help \n" +
                "list\n" +
                "todo <taskName>\n" +
                "done <index>\n" +
                "deadline/weekly <taskName> /by 2/12/2019 1800\n" +
                "find <taskName>\n"+
                "delete <weeklyTaskIndex> \n " +
                "remove <taskToRemovePermanently>";
        return myHelp;
    }



}



