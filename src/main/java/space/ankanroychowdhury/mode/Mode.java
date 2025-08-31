package space.ankanroychowdhury.mode;

import space.ankanroychowdhury.OutputPrinter;
import space.ankanroychowdhury.commands.CommandExecutor;
import space.ankanroychowdhury.exceptions.InvalidCommandException;
import space.ankanroychowdhury.factory.CommandExecutorFactory;
import space.ankanroychowdhury.models.Command;
import java.io.IOException;

public abstract class Mode {

    private CommandExecutorFactory commandExecutorFactory;
    protected OutputPrinter outputPrinter;

    public Mode(final CommandExecutorFactory commandExecutorFactory, OutputPrinter outputPrinter) {
        this.commandExecutorFactory = commandExecutorFactory;
        this.outputPrinter = outputPrinter;
    }

    protected void processCommand(final Command command){
        final CommandExecutor commandExecutor = commandExecutorFactory.getCommandExecutor(command);
        if(commandExecutor.validate(command)){
            commandExecutor.execute(command);
        } else {
            throw new InvalidCommandException();
        }
    }

    public abstract void process() throws IOException;
}
