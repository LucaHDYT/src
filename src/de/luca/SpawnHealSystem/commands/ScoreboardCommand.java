// This class was created by LucaHD

package de.luca.SpawnHealSystem.commands;

import de.luca.SpawnHealSystem.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("sb.reload")) {
                if(args.length == 1) {
                    player.sendMessage(Main.getPrefix() + ("ßeScoreBoard wird neu geladen..."));
                    try {
                        reload(player);
                        player.sendMessage(Main.getPrefix() + ("ßaScoreBoard wurde neu geladen!"));
                    }catch (Exception e){
                        player.sendMessage(Main.getPrefix() + ("ßcFehler: " + e.getMessage()));
                    }

                }else {
                    player.sendMessage(Main.getPrefix() + ("ßcBitte benutze: ß6/sb reload"));
                }
            }else {
                player.sendMessage(Main.getPrefix() + ("ßcDazu hast du keine Rechte!"));
            }
        } else {
            sender.sendMessage(Main.getPrefix() + ("ßcDu bist kein Spieler"));
        }

        return true;
    }

    void reload(Player player){
        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = board.registerNewObjective("abcd", "abcd");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("ß6ßlWillkommen auf DeinServer.de");
        objective.getScore("ß6ßl--------------------------").setScore(6);
        objective.getScore("ßaßlHallo ß6ßl" + player.getName() + "ßaßl!").setScore(5);
        objective.getScore("ßeßl----------------------").setScore(4);
        objective.getScore("ßaßlViel Spaﬂ ß6ßl" + player.getName() + "ßaßl!").setScore(3);
        objective.getScore("ß6ßl-------------------------").setScore(2);
        objective.getScore("ßaßlgecodet von: ß6ßlLucaHD").setScore(1);
        objective.getScore("ßeßl--------------------------------").setScore(0);
        player.setScoreboard(board);
    }
}