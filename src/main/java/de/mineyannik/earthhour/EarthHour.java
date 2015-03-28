package de.mineyannik.earthhour;

import java.util.Calendar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author mineyannik
 */
public class EarthHour extends JavaPlugin implements Listener {

    @Override
    public void onEnable()
    {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            @Override
            public void run()
            {
                if (isEarthHour()) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.kickPlayer("http://www.earthhour.org");
                    }
                }
            }
        }, 1200L, 1200L);
        
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerLoginEvent e)
    {
        if (isEarthHour()) {
            e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "http://www.earthhour.org");
        }
    }

    public boolean isEarthHour()
    {
        Calendar d = Calendar.getInstance();
        if (d.get(Calendar.DAY_OF_MONTH) == 28 && d.get(Calendar.MONTH) == 2) {
            if (d.get(Calendar.HOUR_OF_DAY) == 20 && d.get(Calendar.MINUTE) >= 30) {
                return true;
            }

            if (d.get(Calendar.HOUR_OF_DAY) == 21 && d.get(Calendar.MINUTE) <= 30) {
                return true;
            }
        }
        return false;
    }

}
