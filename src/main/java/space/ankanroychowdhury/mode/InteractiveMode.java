package space.ankanroychowdhury.mode;

import space.ankanroychowdhury.OutputPrinter;
import space.ankanroychowdhury.commands.ExitCommandExecutor;
import space.ankanroychowdhury.factory.CommandExecutorFactory;
import space.ankanroychowdhury.models.Command;

import java.io.*;

public class InteractiveMode extends Mode {

    public InteractiveMode(final CommandExecutorFactory commandExecutorFactory, final OutputPrinter outputPrinter) {
        super(commandExecutorFactory, outputPrinter);
    }

    @Override
    public void process() throws IOException {
        outputPrinter.welcome();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            final String input = reader.readLine();
            final Command command = new Command(input);
            processCommand(command);
            if(command.getCommandName().equals(ExitCommandExecutor.COMMAND_NAME)){
                outputPrinter.end();
            }
        }

    }
}
