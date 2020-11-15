package de.lucahdyt.SpawnhealSystem.scoreboard;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        (new ScoreboardHandler()).setScoreboard(event.getPlayer());
    }
}