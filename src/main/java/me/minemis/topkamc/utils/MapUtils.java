package me.minemis.topkamc.utils;

import me.minemis.topkamc.system.PlayerCache;

import java.util.*;

public class MapUtils {
    public static <String, Long extends Comparable<? super Long>> Map<String, Long> sortByValue(Map<String, Long> map){
        List<Map.Entry<String, Long>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        Map<String, Long> result = new LinkedHashMap<>();
        for (Map.Entry<String, Long> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    public static Map<String, Long> ConvertPlayerCacheMapToPlayerLongMap (Map<String, PlayerCache> map, String typeOfValue){
        Map<String, Long> newMap = new HashMap<>();

        if (typeOfValue.equals("kills")){
            for (Map.Entry<String, PlayerCache> entry : map.entrySet()) {
                newMap.put(entry.getKey(),entry.getValue().getKillsAmount());
            }
        }

        if (typeOfValue.equals("deaths")){
            for (Map.Entry<String, PlayerCache> entry : map.entrySet()) {
                newMap.put(entry.getKey(),entry.getValue().getDeathAmount());
            }
        }
        return newMap;
    }
}
