package main.java;

public class DukeFileWriterException extends DukeException {

    @Override
    public String toString() {
        return super.toString() + " Something went wrong when writing to your file";
    }
}
