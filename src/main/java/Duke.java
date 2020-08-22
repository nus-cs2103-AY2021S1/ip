package main.java;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    
    public static void main(String[] args) throws DukeException, FileNotFoundException {
        ChatBot duke = new ChatBot();
        duke.run();
    }
}
