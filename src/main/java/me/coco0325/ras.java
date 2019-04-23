package me.coco0325;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ras extends JavaPlugin implements Listener {

    static String warning;

    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent e){
        if(e.getDamager() instanceof Player){
            Player player = (Player)e.getDamager();
            if(!player.hasPermission("ras.ignore")){
                Double attack = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue();
                if(e.getDamage() < attack){
                    e.setCancelled(true);
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new ComponentBuilder(warning.replaceAll("&", "§")).create());
                }
            }
        }
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        warning = getConfig().getString("warning_message");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    }
}
