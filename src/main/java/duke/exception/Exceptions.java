package duke.exception;

/**
 * Contains all String for the exception error message.
 */
public enum Exceptions {
    NUMBEREXCESSEXCEPTION {
        @Override
        public String toString() {
            return "OOPS!!! It seems like you don't have this option\n"
                    + "Enter list to see options";
        }
    },

    NUMBERFORMATEXCEPTION {
        @Override
        public String toString() {
            return "OOPS!!! The format of your index number is incorrect";
        }
    },

    INPUTFORMATEXCEPTION {
        @Override
        public String toString() {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    },

    EMPTYTASKEXCEPTION {
        @Override
        public String toString() {
            return "OOPS!!! The description of the task cannot be empty";
        }
    },

    FILEEXCEPTION {
        @Override
        public String toString() {
            return "OOPS!!!\n"
                    + "The file exists but is a directory rather than a regular file,\n"
                    + "or does not exist but cannot be created,\n"
                    + "or cannot be opened for any other reason";
        }
    },

    TIMEFORMATEXCEPTION {
        @Override
        public String toString() {
            return "OOPS!!! Your time format seems incorrect,"
                    + "use <help> to get the correct format";
        }
    },

    FINDDETAILMISSINGEXCEPTION {
        @Override
        public String toString() {
            return "OOPS!!! So what would you like to find?\n"
                    + "Try to add something behind 'find'.";
        }
    },

    FILEALREADYEXIST {
        @Override
        public String toString() {
            return "File to be created already exists";
        }
    },

    WRITINGEXCEPTION {
        @Override
        public String toString() {
            return "Errors occurs during writing into the file";
        }
    },

    UPDATEFORMATEXCEPTION {
        @Override
        public String toString() {
            return "Your update command format is incorrect.\n"
                    + "please use: update <number to update> <detail/time> /to <content>";
        }
    }
}
