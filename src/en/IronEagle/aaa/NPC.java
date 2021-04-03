package en.IronEagle.aaa;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R1.CraftServer;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NPC extends Packets {
    private final Main plugin = Main.getInstance();

    public void createNPC(Player p, String name, Location loc) {

        MinecraftServer s = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer w = ((CraftWorld) p.getWorld()).getHandle();
        GameProfile gP = new GameProfile(UUID.randomUUID(), "[NPC]");
        EntityPlayer npc = new EntityPlayer(s, w, gP, new PlayerInteractManager(w));
        npc.setLocation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        skin(gP, name);
        DataWatcher watcher = npc.getDataWatcher();
        watcher.watch(10, (byte) 127);
        sendPacket(new PacketPlayOutEntityMetadata(npc.getId(), watcher, true));
        sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.ADD_PLAYER, npc));
        sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
        sendPacket(new PacketPlayOutEntityHeadRotation(npc, (byte) (loc.getYaw() * 256 / 360)));

        for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
            Bukkit.getScheduler().runTaskLater(plugin,
                    () -> sendPacket(new PacketPlayOutPlayerInfo(EnumPlayerInfoAction.REMOVE_PLAYER, npc), pl)
                    , 100L);
        }


    }

    private void skin(GameProfile Gp, String name) {
        String texture = plugin.getConfig().getString("Skins." + name + ".Texture");
        String signature = plugin.getConfig().getString("Skins." + name + ".Signature");
        Gp.getProperties().put("textures", new Property("textures", texture, signature));
    }


}