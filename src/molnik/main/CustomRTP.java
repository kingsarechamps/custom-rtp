package molnik.main;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomRTP extends JavaPlugin {
	
	Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable() {
		//log.info("");
		File config = new File(getDataFolder() + File.separator + "config.yml");
		if(!config.exists()) {
			getLogger().info("No config file, creating one...");
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
		}
		
		
		getLogger().info("enabled!");
		
		getCommand("rtp").setExecutor(new Rtp(this));
		getCommand("customrtp_reload").setExecutor(new MainCommand(this));
		
		
		
	}
	
	public void onDisable() {
		
		getLogger().info("disabled!");
		
	}
	
}
