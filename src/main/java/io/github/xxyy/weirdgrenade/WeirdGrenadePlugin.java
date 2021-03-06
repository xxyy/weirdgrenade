package io.github.xxyy.weirdgrenade;

import io.github.xxyy.weirdgrenade.config.ConfigWrapper;
import io.github.xxyy.weirdgrenade.listeners.GrenadeHandler;
import io.github.xxyy.weirdgrenade.util.Util;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

/**
 * Plugin that apparently allows crafting of some weird kind of grenade
 */
public class WeirdGrenadePlugin extends JavaPlugin {

    private ConfigWrapper configWrapper;

    @Override
    public void onEnable() {

        if (getServer().getPluginManager().getPlugin("ItemRenamer") == null) {
            getLogger().warning("ItemRenamer not found. To allow some fancy stuff, please drop it into your plugins folder." +
                    " Get it at http://dev.bukkit.org/bukkit-plugins/itemrenamer/");
            getLogger().severe("There WILL be weird texts on grenades. These will disappear if you install it.");
        } else {
            try {
                Util.hookItemRenamer(this);
            } catch (Throwable throwable) {
                getLogger().log(Level.WARNING, "Could not hook ItemRenamer!", throwable);
            }
        }

        configWrapper = new ConfigWrapper(this);

        //REGISTER STUFF
        getServer().getPluginManager().registerEvents(new GrenadeHandler(this), this);
        Util.registerRecipes(getServer());
    }

    public ConfigWrapper getConfigWrapper() {
        return configWrapper;
    }
}
