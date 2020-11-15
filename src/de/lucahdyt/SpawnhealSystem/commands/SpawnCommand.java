package de.lucahdyt.SpawnhealSystem.commands;

import de.lucahdyt.SpawnhealSystem.main.Main;
import de.lucahdyt.SpawnhealSystem.util.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + "§cDu bist kein Spieler!");
            return true;
        }
        Player player = (Player) sender;
        switch (args.length) {
            case 0:
                if (!ConfigUtils.file.exists()) {
                    player.sendMessage(Main.getPrefix() + "§cDer §6Spawn §cwurde noch nicht gesetzt.\n§aSetze bitte den Spawn.");
                    return true;
                }
                teleport(player);
                return true;
            case 1:
                if (player.hasPermission("spawn.admin")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        teleportTarget(target);
                        player.sendMessage(Main.getPrefix() + "§aDu hast §6" + target.getName() + " §a Teleportiert.");
                    } else {
                        player.sendMessage(Main.getPrefix() + "§cDer Spieler §6" + args[0] + " §cist nicht Online!");
                    }
                } else {
                    player.sendMessage(Main.getPrefix() + "§cKeine Rechte um andere zu Teleportieren.");
                }
                return true;
        }
        player.sendMessage("§cBitte Benutze: /spawn | /spawn [Player]");
        return true;
    }

    private void teleport(Player player) {
        Location location = (Location) ConfigUtils.config.get("Spawn");
        player.teleport(location);
        player.sendMessage(Main.getPrefix() + "§aDu wurdest teleportiert.");
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10.0F, 10.0F);
    }

    private void teleportTarget(Player player) {
        Location location = (Location) ConfigUtils.config.get("Spawn");
        player.teleport(location);
        player.sendMessage(Main.getPrefix() + "§aDu wurdest von einem §6Admin | Owner §ateleportiert");
        player.playSound(player.getLocation(), Sound.LEVEL_UP, 10.0F, 10.0F);
    }
}