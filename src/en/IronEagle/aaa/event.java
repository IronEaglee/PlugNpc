package en.IronEagle.aaa;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class event implements Listener {

    @EventHandler

    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();
        Main.getInstance().npc.createNPC(p, "NPC_SKIN", new Location(p.getWorld(), 0, 0, 0, (float) 0, (float) 0));
    }

}

