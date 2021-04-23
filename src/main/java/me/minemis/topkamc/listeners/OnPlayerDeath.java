package me.minemis.topkamc.listeners;

import me.minemis.topkamc.TopkaMC;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class OnPlayerDeath implements Listener {

    private final TopkaMC topkaMC;

    public OnPlayerDeath(TopkaMC topkaMC){
        this.topkaMC = topkaMC;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){

        //increments death amount of the killed player
        topkaMC.getDataManager().getPlayerCache(e.getEntity().getName()).incrementDeathAmount();

        Player killer = e.getEntity().getPlayer().getKiller();

        if (killer == null){
            return;
        }

        //increments kill amount of the killer
        topkaMC.getDataManager().getPlayerCache(killer.getName()).incrementKillsAmount();

        System.out.println("kills: " + topkaMC.getDataManager().getPlayerCache(killer.getName()).getKillsAmount());
        System.out.println("deaths: " + topkaMC.getDataManager().getPlayerCache(killer.getName()).getDeathAmount());
    }
}
