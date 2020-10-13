import de.luca.SpawnHealSystem.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(player.hasPermission("heal.heal")) {
				if(args.length == 0) {
					player.setHealth(20);
					player.setFoodLevel(20);
					player.sendMessage(Main.getPrefix() + ("§aDu wurdest geheilt!"));
				} else if(args.length == 1) {
					Player target = Bukkit.getPlayer(args[0]);
					if(target != null) {
						target.setHealth(20);
						target.setFoodLevel(20);
						target.sendMessage(Main.getPrefix() + ("§aDu wurdest von §6" + player.getName() + " §ageheilt!"));
						player.sendMessage(Main.getPrefix() + ("§aDu hast den Spieler §6" + target.getName() + " §ageheilt."));
					} else
						player.sendMessage(Main.getPrefix() + ("§cDer Spieler §6" + args[0] + " §cist nicht auf dem Server!"));
				} else
					player.sendMessage(Main.getPrefix() + ("§cBitte benutze §6/heal <Spieler>"));
			} else
				player.sendMessage(Main.getPrefix() + ("§cDazu hast du keine Rechte!"));
		} else
			sender.sendMessage(Main.getPrefix() + ("§cDu bist kein Spieler!"));
		
		return false;
	}

}
