import de.lucahdyt.SpawnHealSystem.main.Main;
import de.lucahdyt.SpawnHealSystem.util.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + "§cDu musst ein Spieler sein.");
            return true;
        }

        final Player player = (Player) sender;

        switch (args.length) {
            case 0:
                if (!ConfigUtils.file.exists()) {
                    player.sendMessage(Main.getPrefix() + "§cDer §6Spawn §cwurde noch nicht gesetzt." +
                            "\n§aSetze bitte den Spawn.");
                    return true;
                }

                teleport(player);
                break;
            case 1:
                if (player.hasPermission("spawn.admin")) {
                    final Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {

                        teleportTarget(target);
                        player.sendMessage(Main.getPrefix() +"§aDu hast §6" + target.getName() + " §a Teleportiert.");
                    } else {
                        player.sendMessage(Main.getPrefix() + "§cDer Spieler §6" + args[0] + " §cist nicht Online!");
                    }
                }else {
                    player.sendMessage(Main.getPrefix() + "§cKeine Rechte um andere zu Teleportieren.");
                }


                break;


            default:
                player.sendMessage("§cBitte Benutze: /spawn | /spawn [Player]");

                break;
        }


        return true;
    }

    private void teleport(final Player player) {
        Location location = (Location) ConfigUtils.config.get("Spawn");
        player.teleport(location);
        player.sendMessage(Main.getPrefix() + "§aDu wurdest teleportiert.");
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
    }

    private void teleportTarget(final Player player) {
        Location location = (Location) ConfigUtils.config.get("Spawn");
        player.teleport(location);
        player.sendMessage(Main.getPrefix() + "§aDu wurdest von einem §6Admin | Owner §ateleportiert");
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10, 10);
    }

}
