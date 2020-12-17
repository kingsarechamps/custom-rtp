package molnik.main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {

	private CustomRTP plugin;

	public MainCommand(CustomRTP plugin) {
		
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("customrtp.reload")) {
			plugin.reloadConfig();
			sender.sendMessage("Configuration reloaded!");
		}
		return true;
	}

}
