package org.vcazan.boatlaunch;

import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BoatLaunch extends JavaPlugin {
	
    private final BoatBlockListener blockListener = new BoatBlockListener(this);

	Logger log = Logger.getLogger("Minecraft");
	
	public void main(){
		PluginManager pm = this.getServer().getPluginManager();
	    pm.registerEvent(Event.Type.BLOCK_DISPENSE, blockListener, Event.Priority.Normal, this);
	}
    
	public void onEnable(){
		log.info("BoatLaunch v0.2 has loaded.");
		main();

	}
	
	public void onDisable(){
		log.info("BoatLaunch v0.2 has unloaded.");
	}
	
}