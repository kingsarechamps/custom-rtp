package molnik.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rtp implements CommandExecutor{

	private CustomRTP plugin;

	
	
	public Rtp(CustomRTP plugin) {
		this.plugin = plugin;
	}
	
	/**
	 * @return
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String m_rtp = plugin.getConfig().getString("messages.rtp");
		String m_prefix = plugin.getConfig().getString("messages.prefix");
		String m_rs = plugin.getConfig().getString("messages.rtp_sucess");
		String m_error = plugin.getConfig().getString("messages.rtp_error");
		String m_dnt_per = plugin.getConfig().getString("messages.rtp_dont_permission");
		m_rtp = m_rtp.replaceAll("&", "\u00a7");
		m_prefix = m_prefix.replaceAll("&", "\u00a7");
		m_rs = m_rs.replaceAll("&", "\u00a7");
		m_error = m_error.replaceAll("&", "\u00a7");
		m_dnt_per = m_dnt_per.replaceAll("&", "\u00a7");
		
		int ch_xi_min = plugin.getConfig().getInt("location.radius.x_min");
		int ch_yi_min = plugin.getConfig().getInt("location.radius.z_min");
		int ch_xi_max = plugin.getConfig().getInt("location.radius.x_max");
		int ch_yi_max = plugin.getConfig().getInt("location.radius.z_max");
		double ch_x_min = (double) ch_xi_min;
		double ch_y_min = (double) ch_yi_min;
		double ch_x_max = (double) ch_xi_max;
		double ch_y_max = (double) ch_yi_max;
		String world = plugin.getConfig().getString("location.world");
		
			
		
		//m_prefix = "\u00a77[" + m_prefix +"\u00a77]\u00a7e";
		
		if(!sender.hasPermission("customrtp.rtp")) {
			sender.sendMessage(m_prefix + " " + ChatColor.RED + m_dnt_per);
			return true;
		}
		
		
		
		Player p = (Player) sender;
		try {
			teleport(p,sender,m_rtp,m_prefix,m_rs,m_error, ch_x_min,ch_y_min,ch_x_max,ch_y_max,world);
		} catch (InterruptedException e) {
	
			e.printStackTrace();
		}
		
		
		return true;
		
	
		
		}
	
	private void teleport(Player p, CommandSender s, String rtp, String prefix, String rs, String rerror,double minx,double minz,double maxx,double maxz,String w) throws InterruptedException  {
		Location loc = p.getLocation();
		double miny = 67.0;
		double maxy = 97.0;
		double yd = (int) (Math.random() * (maxy - miny) + miny);
		int y = (int) yd;
		String ys = String.valueOf(y);
		
		double xd = (int) (Math.random() * (maxx - minx) + minx);
		int x = (int) xd;
		String xs = String.valueOf(x);
		
		double zd = (int) (Math.random() * (maxz - minz) + minz);
		int z = (int) zd;
		String zs = String.valueOf(z);
		
		rs.replaceAll("%x", xs);
		rs.replaceAll("%y", ys);
		rs.replaceAll("%z", zs);
		
		boolean a = block(loc, x, y, z);
		if(a) {
			s.sendMessage(prefix + " " + rtp);
			loc.setX(x);
			loc.setY(y);
			loc.setZ(z);
			p.teleport(new Location(Bukkit.getWorld(w), x, y, z));
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "effect give "+ s.getName() +" resistance 10 45 true");
		
			s.sendMessage(prefix + " " + ChatColor.GREEN + rs + " " + x + " " + y + " " + z);
		} else {
			s.sendMessage(prefix + " " + ChatColor.YELLOW + rerror);
			//s.sendMessage(x + " " + y + " " + z);
		}
	}
	
	private boolean block(Location loc, int x, int y, int z){
		if(loc.getWorld().getBlockAt(x, y, z).getType() == Material.AIR) {
			return true;
		}
		return false;
	}

}
