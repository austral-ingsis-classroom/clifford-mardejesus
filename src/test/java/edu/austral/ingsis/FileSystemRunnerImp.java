package edu.austral.ingsis;

import edu.austral.ingsis.clifford.CommandFactory;
import edu.austral.ingsis.clifford.FileSystem;
import edu.austral.ingsis.clifford.FileSystemImp;
import edu.austral.ingsis.clifford.cli.Command;
import java.util.ArrayList;
import java.util.List;

public class FileSystemRunnerImp implements FileSystemRunner {

  private final FileSystem fileSystem;
  private final CommandFactory commandFactory;

  public FileSystemRunnerImp() {
    this.fileSystem = new FileSystemImp();
    this.commandFactory = new CommandFactory();
  }

  @Override
  public List<String> executeCommands(List<String> commands) {
    List<String> results = new ArrayList<>();
    for (String commandLine : commands) {
      String[] commandParts = commandLine.split(" ");
      String commandName = commandParts[0];
      List<String> args = List.of(commandParts).subList(1, commandParts.length);
      Command command = commandFactory.createCommand(commandName);
      String result = command.execute(fileSystem, args);
      results.add(result);
    }
    return results;
  }
}
