package space.ankanroychowdhury.factory;

import space.ankanroychowdhury.OutputPrinter;
import space.ankanroychowdhury.commands.AddUserCommandExecutor;
import space.ankanroychowdhury.commands.CommandExecutor;
import space.ankanroychowdhury.commands.GetUserByIdCommandExecutor;
import space.ankanroychowdhury.exceptions.InvalidCommandException;
import space.ankanroychowdhury.models.Command;
import space.ankanroychowdhury.services.UserService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {
    private Map<String, CommandExecutor> commands = new HashMap<>();
    final OutputPrinter outputPrinter = new OutputPrinter();
    public CommandExecutorFactory(final UserService userService){
        commands.put(AddUserCommandExecutor.COMMAND_NAME, new AddUserCommandExecutor(userService, outputPrinter));
        commands.put(GetUserByIdCommandExecutor.COMMAND_NAME, new GetUserByIdCommandExecutor(userService, outputPrinter));
    }

    public CommandExecutor getCommandExecutor(final Command command){
        final CommandExecutor commandExecutor = commands.get(command.getCommandName());
        if(commandExecutor == null){
            throw new InvalidCommandException();
        }
        return commandExecutor;
    }

}
