package me.wheezygold.skLib.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import me.wheezygold.skLib.Main;

public class SbManager {
	
	/*
	 Hey LimeGlass (If you are viewing this) sorry about using ya scoreboard handler thing here, it's just godly.
	 Contact me if you want me to pay you for licensing.
	 */
	
//	private static final HashMap<String, Scoreboard> magicBoards = new HashMap<>();
//	private static final HashMap<String, MagicBoard> memoryData = new HashMap<>();
//	
//	public static HashMap<String, MagicBoard> getMemory() {
//		return memoryData;
//	}
//	
//	public static void dump() {
//		magicBoards.clear();
//		memoryData.clear();
//	}
//	
//	public static void createBoard(String ID) {
//		if (!magicBoards.containsKey(ID)) {
//			Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
//			board.registerNewObjective("MagicBoards", "dummy");
//			board.getObjective("MagicBoards").setDisplaySlot(DisplaySlot.SIDEBAR);
//			magicBoards.put(ID, board);
//		}
//	}
//	
//	public static void deleteBoard(String ID) {
//		if (magicBoards.containsKey(ID)) {
//			magicBoards.get(ID).getObjective("MagicBoards").unregister();
//			magicBoards.remove(ID);
//		}
//	}
//	
//	public static void setTitle(String board, String title) {
//		if (magicBoards.containsKey(board)) {
//			magicBoards.get(board).getObjective("MagicBoards").setDisplayName(title);
//		}
//	}
//	
//	public static String getTitle(String board) {
//		if (magicBoards.containsKey(board)) {
//			magicBoards.get(board).getObjective("MagicBoards").getDisplayName();
//		}
//		return null;
//	}
//	
//	public static void createScore(String ID, String board, String value, int slot) {
//		if (magicBoards.containsKey(board)) {
//			String prefix = null, name = null, suffix = null;
//			if (value.length() > 48) {
//				value = value.substring(0, 47);
//				
//			}
//			if (value.length() <= 16) {
//				name = value;
//			} else if (value.length() <= 32) {
//				name = value.substring(0, 16);
//				suffix = value.substring(16, value.length());
//			} else {
//				prefix = value.substring(0, 16);
//				name = value.substring(16, 32);
//				suffix = value.substring(32, value.length());
//			}
//			if (memoryData.containsKey(ID)) {
//				deleteScore(ID, board);
//			}
//			Team team = magicBoards.get(board).getEntryTeam(name);
//			if (team == null) {
//				team = magicBoards.get(board).registerNewTeam(name);
//			}
//			team.addEntry(name);
//			if ((prefix != null) || (suffix != null)) {
//				if (prefix != null) {
//					team.setPrefix(prefix);
//				}
//				team.setSuffix(suffix);
//			}
//			Score score = magicBoards.get(board).getObjective("MagicBoards").getScore(name);
//			score.setScore(slot);
//			memoryData.put(ID, new MagicBoard(score, magicBoards.get(board), slot, team));
//		}
//	}
//	public static Boolean shouldUpdate(String ID, String board, String value, int slot) {
//		if (magicBoards.containsKey(board)) {
//			MagicBoard scoreboard = memoryData.get(ID);
//			if (!getTeamValue(scoreboard.getTeam()).equals(value)) {
//				return true;
//			}
//			Score score = magicBoards.get(board).getObjective("MagicBoards").getScore(scoreboard.getTeam().getName());
//			if (score.getScore() != slot) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public static void deleteScore(String ID, String board) {
//		if (magicBoards.containsKey(board) && memoryData.containsKey(ID)) {
//			MagicBoard style = memoryData.get(ID);
//			magicBoards.get(board).resetScores(style.getScore().getEntry());
//			if (magicBoards.get(board).getTeam(style.getScore().getEntry()) != null) {
//				magicBoards.get(board).getTeam(style.getScore().getEntry()).unregister();
//			}
//			memoryData.remove(ID);
//		}
//	}
//	
//	public static void updateScore(String ID, String board, String value, int slot) {
//		if (magicBoards.containsKey(board)) {
//			if (shouldUpdate(ID, board, value, slot)) {
//				if (memoryData.containsKey(ID)) {
//					deleteScore(ID, board);
//				}
//				createScore(ID, board, value, slot);
//			}
//		}
//	}
//	
//	public static void updateScore(String ID, Scoreboard scoreboard, String value, int slot) {
//		for (String b : getBoards()) {
//			if (magicBoards.get(b) == scoreboard) {
//				updateScore(ID, b, value, slot);
//			}
//		}
//	}
//	
//	public static void updateScore(String ID, Scoreboard scoreboard, Team team, int slot) {
//		for (String b : getBoards()) {
//			if (magicBoards.get(b) == scoreboard) {
//				String update = getTeamValue(team);
//				updateScore(ID, b, update, slot);
//			}
//		}
//	}
//	
//	public static String getTeamValue(Team team) {
//		String update = team.getName();
//		if (team.getPrefix() != null) {
//			update = team.getPrefix() + update;
//		}
//		if (team.getSuffix() != null) {
//			update = update + team.getSuffix();
//		}
//		return update;
//	}
//	
//	public static Scoreboard get(String board) {
//		if (magicBoards.containsKey(board)) {
//			return magicBoards.get(board);
//		}
//		return null;
//	}
//	
//	public static Boolean contains(String board) {
//		if (magicBoards.containsKey(board)) {
//			return true;
//		}
//		return false;
//	}
//	
//	public static String[] getBoards() {
//		ArrayList<String> boards = new ArrayList<>();
//		for (final String s : magicBoards.keySet()) {
//			boards.add(s);
//		}
//		return boards.toArray(new String[boards.size()]);
//	}
	
	
	private static Objective playerScoreboard;

	public static void createscrollsb(Player p, String title) {
		Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		playerScoreboard = scoreboard.registerNewObjective("sklib", "dummy"); //The name of the scoreboard is the name of the player, not that it really needs to be
		playerScoreboard.setDisplayName("Placeholder"); //Will not show to the user, we just need to set this to make bukkit happy
		playerScoreboard.setDisplaySlot(DisplaySlot.SIDEBAR);
		p.setScoreboard(scoreboard);
		new BukkitRunnable()
    	{
    		Scroller scroller = new Scroller(p, title, 16, 4, '&');
    	
    		public void run()
    		{
    			playerScoreboard.setDisplayName(scroller.next(p));
    		}
    		
    	}.runTaskTimer(Main.getInstance(), 0L, 3L); // runs every 3 ticks
		
	}
	
	public static void setscoresb(Player p, int slot, String value) {
		Scoreboard sb = p.getScoreboard();
		Objective ob = sb.getObjective(DisplaySlot.SIDEBAR);
		Score score = ob.getScore(value);
		score.setScore(slot);
	}
//	
//	
	

}
