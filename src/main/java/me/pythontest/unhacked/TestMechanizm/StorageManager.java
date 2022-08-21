package me.pythontest.unhacked.TestMechanizm;

import me.pythontest.unhacked.unhacked.Unhacked;

import java.util.HashMap;
import java.util.Map;

public class StorageManager {
    private Map<String,UHPlayer> players=new HashMap<>();
    public Map<String,Double> entityHealth = new HashMap<>();
    private Unhacked plugin;
    public StorageManager(Unhacked plugin){
        this.plugin=plugin;
    }
    public void addPlayer(String uuid){
        players.put(uuid,new UHPlayer(uuid,plugin));
    }
    public void removePlayer(String uuid){
        players.remove(uuid);
    }
    public UHPlayer getPlayer(String uuid){
        return players.getOrDefault(uuid,null);
    }
}
