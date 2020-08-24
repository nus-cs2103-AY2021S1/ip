package main.java;

import java.io.IOException;

public class DukeFileException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + " Something went wrong when accessing your file";
    }
}
