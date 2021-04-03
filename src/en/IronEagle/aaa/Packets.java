package en.IronEagle.aaa;

import net.minecraft.server.v1_8_R1.Packet;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Packets {
    public void sendPacket(Packet pc, Player p) {
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(pc);
    }

    public void sendPacket(Packet pc) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            sendPacket(pc, p);
        }
    }

}
