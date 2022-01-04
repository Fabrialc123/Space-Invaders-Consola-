package Comandos;

import Juego.Game;

public class ListCommand extends Command {

	public ListCommand() {
		super("List", "L", "[L]ist: ", "Show the the info of the aliens");
	}
	@Override
	public boolean execute(Game game) {
		System.out.println("[R]egular ship: Points: 5 - Harm: 0 - Shield: 2\r\n" + 
				"[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1\r\n" + 
				"[O]vni: Points: 25 - Harm: 0 - Shield: 1\r\n" + 
				"^__^: Harm: 1 - Shield: 3");
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		Command c = null;
			if (matchCommandName(commandWords[0])) {
				c = this;
			}
		return c;
	}

}
