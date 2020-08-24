package task;

import exceptions.DukeException;

import java.util.regex.Pattern;

public enum CommandTypes {
    BYE{
        private static final String invalidCommandInput = "OOPS!!!! I'm sorry, but I don't know what that means";
        @Override
        public void checkInput (String input) throws DukeException{
            String patternString = "^(?)bye\\s*$";
            if(!Pattern.matches(patternString, input)){
                throw new DukeException(invalidCommandInput);
            }
        }
    },
    LIST{
        private static final String invalidCommandInput = "OOPS!!!! I'm sorry, but I don't know what that means";
        @Override
        public void checkInput (String input) throws DukeException{
            String patternString = "^(?)list\\s*$";
            if(!Pattern.matches(patternString, input)){
                throw new DukeException(invalidCommandInput);
            }
        }
    },
    TODO{
        private static final String todoErrorMessage = "OOPS!!! The description of a todo cannot be empty";
        @Override
        public void checkInput (String input) throws DukeException{
           String content = input.replaceFirst("^todo", "");
           if(content.isBlank()){
               throw new DukeException(todoErrorMessage);
           }
        }
    },
    EVENT{
        private static final String eventErrorMessage = "OOPS!!! The description of an event cannot be empty.";
        private static final String noDateTimeErrorMessage = "OOPS!!! Missing deadline date/time";
        @Override
        public void checkInput (String input) throws DukeException{
            input = input.replaceFirst("^event", "");
            String[] inputArgs = input.split("\\s*/at\\s*");
            try{
                String task = inputArgs[0];
                if(task.isBlank()){
                    throw new DukeException(eventErrorMessage);
                }
                String dateTime = inputArgs[1];
            } catch(ArrayIndexOutOfBoundsException e){
                throw new DukeException(noDateTimeErrorMessage);
            }
        }
    },
    DEADLINE{
        private static final String deadlineErrorMessage = "OOPS!!! The description of a deadline cannot be empty.";
        private static final String noDateTimeErrorMessage = "OOPS!!! Missing deadline date/time";
        @Override
        public void checkInput(String input) throws DukeException{
            input = input.replaceFirst("^(?i)deadline", "");
            String[] inputArgs = input.split("\\s*/by\\s*");
            try{
                String task = inputArgs[0];
                if(task.isBlank()){
                    throw new DukeException(deadlineErrorMessage);
                }
                String dateTime = inputArgs[1];
            } catch(ArrayIndexOutOfBoundsException e){
                throw new DukeException(noDateTimeErrorMessage);
            }
        }
    },
    DONE{
        private static final String invalidTaskIndexErrorMessage = "Invalid task index! Please choose another index.";
        @Override
        public void checkInput(String input) throws DukeException{
            String patternString = "^(?i)done\\s+\\d+\\s*$";
            if(!Pattern.matches(patternString, input)){
                throw new DukeException(invalidTaskIndexErrorMessage);
            }
        }
    },
    DELETE{
        private static final String invalidTaskIndexErrorMessage = "Invalid task index! Please choose another index.";
        @Override
        public void checkInput(String input) throws DukeException {
            String patternString = "^(?i)delete\\s+\\d+\\s*$";
            if(!Pattern.matches(patternString, input)){
                throw new DukeException(invalidTaskIndexErrorMessage);
            }

        }
    };

    public abstract void checkInput(String input) throws DukeException;
}
