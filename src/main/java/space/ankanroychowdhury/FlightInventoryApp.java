package space.ankanroychowdhury;

import space.ankanroychowdhury.exceptions.InvalidModeException;
import space.ankanroychowdhury.factory.CommandExecutorFactory;
import space.ankanroychowdhury.mode.InteractiveMode;
import space.ankanroychowdhury.repository.InMemoryUserRepository;
import space.ankanroychowdhury.repository.UserRepository;
import space.ankanroychowdhury.services.UserService;
import java.io.IOException;

public class FlightInventoryApp {
    public static void main(String[] args) throws IOException {
        final OutputPrinter outputPrinter = new OutputPrinter();
        final UserRepository userRepository = new InMemoryUserRepository();
        final UserService userService = new UserService(userRepository);
        final CommandExecutorFactory commandExecutorFactory = new CommandExecutorFactory(userService);

        if(isInteractiveMode(args)){
            new InteractiveMode(commandExecutorFactory, outputPrinter).process();
        }else if (isFileInputMode(args)) {
            // new FileMode(commandExecutorFactory, outputPrinter, args[0]).process();
            outputPrinter.printWithNewLine("File mode not implemented yet");
        }else {
            throw new InvalidModeException();
        }

    }

    private static boolean isFileInputMode(String[] args) {
        return args.length == 1;
    }

    private static boolean isInteractiveMode(String[] args) {
        return args.length == 0;
    }
}
