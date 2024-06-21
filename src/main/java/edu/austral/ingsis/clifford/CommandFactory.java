package edu.austral.ingsis.clifford;

import edu.austral.ingsis.clifford.cli.*;

public class CommandFactory {

    public Command createCommand(String command){
        switch(command){
            case "ls": return new LsCommand();
            case "cd": return new CdCommand();
            case "touch": return new TouchCommand();
            case "mkdir": return new MkdirCommand();
            case "rm": return new RmCommand();
            case "pwd": return new PwdCommand();
            default: throw new IllegalArgumentException("Command not found");
        }
    }
}