package me.minemis.topkamc.system;

import me.minemis.topkamc.TopkaMC;
import org.bukkit.Bukkit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileManager {

    private final TopkaMC topkaMC;

    private final ConcurrentHashMap<String,PlayerCache> loadedMap = new ConcurrentHashMap<>();


    public FileManager(TopkaMC topkaMC){
        this.topkaMC = topkaMC;
    }

    public void loadTopkaJSON() {
        //Reads from file and converts data to JSONObject
        try{
            JSONParser jsonParser = new JSONParser();
            FileReader fileReader = new FileReader(topkaMC.getDataFolder().getPath() + "/Topka.json");
            JSONObject allPlayerData = (JSONObject) jsonParser.parse(fileReader);

            //loop making possible to operate on separate keys and data
            for (Object key : allPlayerData.keySet()) {

                JSONObject playerValues =(JSONObject) allPlayerData.get(key);
                String name = (String) key;
                PlayerCache playerCache = topkaMC.getDataManager().getPlayerCache(name);

                Object killAmountObject = playerValues.get("killAmount");
                Object deathAmountObject = playerValues.get("deathAmount");

                if (killAmountObject == null){
                    killAmountObject = 0;
                }
                if( deathAmountObject == null){
                    deathAmountObject = 0;
                }

                playerCache.setKillsAmount((long) killAmountObject);
                playerCache.setDeathAmount((long) deathAmountObject);

                loadedMap.put(name,playerCache);

                topkaMC.getDataManager().setPlayerCacheMap(loadedMap);
            }
        } catch (Exception e){
            e.printStackTrace();
        }


    }

    public void saveTopkaJsonAsync(){

        //Function repeats itself every 5 minutes in asynchronised threat declared in onEnable
        Bukkit.getScheduler().scheduleSyncRepeatingTask(topkaMC, () -> {
            System.out.println("Saving data...");
            saveTopkaJSON();
        },6000L,6000L);
    }

    public void saveTopkaJSON(){
        ConcurrentHashMap<String,PlayerCache> playerCacheMap = topkaMC.getDataManager().getPlayerCacheMap();

        JSONObject allPlayersData = new JSONObject();

        //Puts players and their cache into JSONObjects
        //and then puts all player JSONObjects into one
        for (Map.Entry<String, PlayerCache> entry : playerCacheMap.entrySet()) {
            JSONObject playerData = new JSONObject();
            playerData.put("killAmount",entry.getValue().getKillsAmount());
            playerData.put("deathAmount",entry.getValue().getDeathAmount());

            allPlayersData.put(entry.getKey(),playerData);
        }

        try {
            //saves to Topka.json
            FileWriter fileWriter = new FileWriter(topkaMC.getDataFolder().getPath() + "/Topka.json");
            fileWriter.write(allPlayersData.toJSONString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saving done");
    }
}
