package me.minemis.topkamc.listeners;

import me.minemis.topkamc.TopkaMC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {

    private final TopkaMC topkaMC;

    public OnPlayerJoin(TopkaMC topkaMC){
        this.topkaMC = topkaMC;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        topkaMC.getDataManager().getPlayerCache(e.getPlayer().getName());
    }
}
