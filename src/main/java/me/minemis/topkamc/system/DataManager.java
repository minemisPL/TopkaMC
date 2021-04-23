package me.minemis.topkamc.system;

import me.minemis.topkamc.TopkaMC;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class DataManager {

    private final TopkaMC topkaMC;

    private ConcurrentHashMap<String,PlayerCache> playerCacheMap = new ConcurrentHashMap<>();

    public DataManager(TopkaMC topkaMC){
        this.topkaMC = topkaMC;
        createFileIfNotExist();
    }

    private void createFileIfNotExist(){
        File folder = new File(topkaMC.getDataFolder().getPath());
        File file = new File(folder.getPath() + "Topka.json");

        if (file.exists()){
            return;
        }

        try {
            folder.mkdir();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ConcurrentHashMap<String, PlayerCache> getPlayerCacheMap() {
        return playerCacheMap;
    }
    public PlayerCache getPlayerCache(String name){
        if (!playerCacheMap.containsKey(name)){
            playerCacheMap.put(name,new PlayerCache());
        }
        return playerCacheMap.get(name);
    }

    public void setPlayerCacheMap(ConcurrentHashMap<String, PlayerCache> playerCacheMap) {
        this.playerCacheMap = playerCacheMap;
    }
}
