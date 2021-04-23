package me.minemis.topkamc.utils;

import me.minemis.topkamc.TopkaMC;
import me.minemis.topkamc.system.PlayerCache;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandUtlis {
    public static void createTopka (Map<String, PlayerCache> map, CommandSender sender,String typeOfValue, String topkaName, String genitive){

        Map<String, Long> topKillMap = MapUtils.ConvertPlayerCacheMapToPlayerLongMap(map, typeOfValue);

        topKillMap = MapUtils.sortByValue(topKillMap);

        Player player = (Player) sender;

        player.sendMessage(ChatColor.GREEN +  "-------------------------");
        player.sendMessage(ChatColor.GREEN + topkaName);

        int i = 0;
        for (Map.Entry<String, Long> entry : topKillMap.entrySet()) {
            if (i >= 10){
                break;
            }
            player.sendMessage( ChatColor.BLUE + entry.getKey() + " " + ChatColor.RED + entry.getValue() + ChatColor.WHITE + " " + genitive);
            i++;
        }
        player.sendMessage(ChatColor.GREEN + "-------------------------");
    }
}
