package me.wheezygold.skLib.scoreboard;
/*
    Example:

	new BukkitRunnable()
	{
		Scroller scroller = new Scroller("&aThis is an &2important &amessage!", 16, 5, '&');
		Sign sign = a-sign-from-somewhere;
	
		public void run()
		{
			sign.setLine(0, scroller.next());
			sign.update();
		}
		
	}.runTaskTimer(plugin, 0L, 3L); // runs every 3 ticks

 */

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import me.wheezygold.skLib.Main;

/**
 * A util to scroll coloured Strings
 * @author Chinwe
 */
public class BetaScroller {
	private Player player;
    private Scoreboard scoreboard;
    private Objective test;
    private int scheduler;

    public BetaScroller(Player player){
        this.player = player;
        this.scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    
        this.test = scoreboard.registerNewObjective("test", "dummy");
        this.test.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "TEST");
        this.test.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score score = test.getScore("hi");
        score.setScore(7);
        player.getPlayer().setScoreboard(scoreboard);
    }

    public void scrollingMessage(String message, ChatColor color){
        scheduler = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
        
            int index = 0;
            String last = " " + message.substring(index, index + 16) + " ";
        
            @Override
            public void run() {
                if(player.getPlayer().isOnline()){
                    scoreboard.resetScores(color + last);
                
                    index++;
                
                    if(index + 16 >= message.length() + 1){
                        index = 0;
                    }
                
                    last = " " + message.substring(index, index + 16) + " ";
                    test .setDisplayName(color + last);
                    player.getPlayer().setScoreboard(scoreboard);
                }else{
                    Bukkit.getScheduler().cancelTask(scheduler);
                }
            }
        }, 0L, 3L);
    }
}