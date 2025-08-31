package space.ankanroychowdhury.commands;
import space.ankanroychowdhury.models.Command;

public interface CommandExecutor {
    boolean validate(Command command);
    void execute(Command command);
}
