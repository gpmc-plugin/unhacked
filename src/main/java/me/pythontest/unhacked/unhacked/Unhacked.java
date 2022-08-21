package me.pythontest.unhacked.unhacked;

import me.pythontest.unhacked.TestMechanizm.StorageManager;
import me.pythontest.unhacked.commands.SetupCommands;
import me.pythontest.unhacked.events.EventsListiner;
import org.bukkit.plugin.java.JavaPlugin;

public final class Unhacked extends JavaPlugin {
    private StorageManager storageManager=new StorageManager(this);

    @Override
    public void onEnable() {
        getServer().getConsoleSender().sendMessage("[Unhacked] AntyCheat Enabled");
        SetupCommands.SetupCommands(this);
        getServer().getPluginManager().registerEvents(new EventsListiner(this),this);

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("[Unhacked] AntyCheat Disabled");
    }
    public StorageManager getStorageManager(){
        return storageManager;
    }
}
