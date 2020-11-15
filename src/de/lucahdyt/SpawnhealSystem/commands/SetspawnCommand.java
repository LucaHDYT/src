package de.lucahdyt.SpawnhealSystem.commands;

import de.lucahdyt.SpawnhealSystem.main.Main;
import de.lucahdyt.SpawnhealSystem.util.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetspawnCommand implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getPrefix() + "§cDu bist kein Spieler!");
            return true;
        }
        Player player = (Player)sender;
        if (player.hasPermission("setspawn.setspawn"))
        { switch (args.length)
        { case 0:
            ConfigUtils.config.set("Spawn", player.getLocation());
            ConfigUtils.savedata();
            player.sendMessage(Main.getPrefix() + "§aDer §6Spawn §awurde erfolgreich gesetzt.");
            return true; }
            player.sendMessage("§cBitte Benutze: /setspawn");
        } else { player.sendMessage(Main.getPrefix() + "§cDazu hast du keine Rechte!"); }
        return true;
    }
}
