package de.lucahdyt.SpawnhealSystem.scoreboard;

import de.lucahdyt.SpawnhealSystem.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardHandler {
    private static Integer animationCount;
    private String[] animation = new String[]{"§6§lWillkommen", "§e§lWillkommen", "§e§lWillkommen", "§6§lWillkommen"};

    public void setScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("main-content", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(this.animation[animationCount.intValue()]);

        objective.getScore("§a§lNOCH IN ARBEIT").setScore(5);
        objective.getScore("§6§lNOCH IN ARBEIT").setScore(4);
        objective.getScore("§e§lNOCH IN ARBEIT").setScore(3);
        objective.getScore("§a§lNOCH IN ARBEIT").setScore(2);
        objective.getScore("§a§lNOCH IN ARBEIT").setScore(1);
        player.setScoreboard(scoreboard);
    }

    public void startAnimation() {
        animationCount = Integer.valueOf(0);
        Bukkit.getScheduler().runTaskTimer(Main.getPlugin(Main.class), new Runnable() {
            public void run() {
                Bukkit.getOnlinePlayers().forEach(current -> {
                    if (current.getScoreboard() == null) {
                        ScoreboardHandler.this.setScoreboard(current);
                    }
                    current.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName(ScoreboardHandler.this.animation[ScoreboardHandler.animationCount.intValue()]);
                });
                Integer integer1 = ScoreboardHandler.animationCount, integer2 = ScoreboardHandler.animationCount = Integer.valueOf(ScoreboardHandler.animationCount.intValue() + 1);
                if (ScoreboardHandler.animationCount.intValue() == ScoreboardHandler.this.animation.length)
                    ScoreboardHandler.animationCount = Integer.valueOf(0);
            }
        }, 0L, 5L);
    }
}
