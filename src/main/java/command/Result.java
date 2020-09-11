package command;

public class Result {
    private String message;
    public boolean isSuccessful;

    public Result(String ) {

    }

    public boolean isSuccessful(){
        return this.isSuccessful;
    }

    @Override
    public String toString(){
        return this.message;
    }
}
