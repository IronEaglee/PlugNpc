package en.IronEagle.aaa;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    public NPC npc;

    public static Main getInstance() {
        return instance;
    }

    private void setInstance(Main instance) {
        Main.instance = instance;
    }

    @Override
    public void onEnable() {
        setInstance(this);
        getLogger().info("\n\n\u001b[37m\u001b[1m" +
                "----------------------------------------------------------------------------------------------------\n\u001b[32mPlugins PlugNpc Enabling.\n\u001b[37m\u001b[1m----------------------------------------------------------------------------------------------------\u001b[0m\n");
        Bukkit.getServer().getPluginManager().registerEvents(new event(), instance);

        this.npc = new NPC();
        config();
    }

    public void config() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

    }


}
