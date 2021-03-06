package me.botsko.dhmcstats.commands;

import java.sql.SQLException;

import me.botsko.dhmcstats.Dhmcstats;
import me.botsko.dhmcstats.stats.StatsUtil;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.IllegalPluginAccessException;

public class PlayerstatsCommandExecutor implements CommandExecutor  {
	
	/**
	 * 
	 */
	private Dhmcstats plugin;
	
	
	/**
	 * 
	 * @param plugin
	 * @return 
	 */
	public PlayerstatsCommandExecutor(Dhmcstats plugin) {
		this.plugin = plugin;
	}
	
	
	/**
     * Handles all of the commands.
     * 
     * 
     * @param sender
     * @param command
     * @param label
     * @param args
     * @return
     */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) throws IllegalPluginAccessException {
    	if (sender instanceof Player){
    		Player player = (Player) sender;
    		if(player.hasPermission("dhmcstats.playerstats")){
				try {
					checkPlayerCounts( sender );
					return true;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
    	}
		return false;
	}

	
    /**
     * Checks the total playtime of a user
     * 
     * @param username
     * @throws SQLException
     */
    private void checkPlayerCounts(CommandSender sender) throws SQLException{

    	// Pull how many players joined in total
		int total = StatsUtil.getPlayerJoinCount( plugin );
		int playedtoday = StatsUtil.getPlayerJoinTodayCount( plugin );

		sender.sendMessage(ChatColor.GOLD  + "Players Online: " + plugin.getOnlineCount());
		sender.sendMessage(ChatColor.GOLD  + "Total Players: " + total);
		sender.sendMessage(ChatColor.GOLD  + "Unique Today: " + playedtoday);
		
    }
}