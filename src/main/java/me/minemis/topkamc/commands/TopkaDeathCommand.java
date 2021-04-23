package me.minemis.topkamc.commands;

import me.minemis.topkamc.TopkaMC;
import me.minemis.topkamc.utils.CommandUtlis;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TopkaDeathCommand implements CommandExecutor {

    private final TopkaMC topkaMC;

    public TopkaDeathCommand(TopkaMC topkaMC) {
        this.topkaMC = topkaMC;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        //creates topka using a given playerCacheMap, a commands sender, a type of value for search (example kills, deaths),
        //a name for topka and a genitive displayed after value
        CommandUtlis.createTopka(topkaMC.getDataManager().getPlayerCacheMap(),commandSender,"deaths",
                "Topka najbardziej problematych graczy","zgonów");


        return false;
    }
}
