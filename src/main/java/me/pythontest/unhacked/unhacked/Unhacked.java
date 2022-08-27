package me.pythontest.unhacked.unhacked;
import me.pythontest.unhacked.TestMechanizm.StorageManager;
import me.pythontest.unhacked.commands.SetupCommands;
import me.pythontest.unhacked.events.EventsListiner;
import me.pythontest.unhacked.events.PacketEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Unhacked extends JavaPlugin {
    private final  StorageManager storageManager=new StorageManager(this);
    private static Unhacked instance;

    @Override
    public void onEnable() {
        instance=this;
        getServer().getConsoleSender().sendMessage("[Unhacked] AntyCheat Enabled");
        SetupCommands.SetupCommands(this);
        getServer().getPluginManager().registerEvents(new EventsListiner(this),this);
        new PacketEventListener();
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("[Unhacked] AntyCheat Disabled");
    }
    public StorageManager getStorageManager(){
        return storageManager;
    }

    public static Unhacked getInstance() {
        return instance;
    }
}
