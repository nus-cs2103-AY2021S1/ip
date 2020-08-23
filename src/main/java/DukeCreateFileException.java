package main.java;

import java.io.IOException;

public class DukeCreateFileException extends DukeException{
    @Override
    public String toString(){
        return super.toString() + "Failed to create a file";
    }
}
