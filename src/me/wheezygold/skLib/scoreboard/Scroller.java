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

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * A util to scroll coloured Strings
 * @author Chinwe
 */
public class Scroller
{
	private static final char COLOUR_CHAR = '§';
	private int position;
	private List<String> list;
	private ChatColor colour = ChatColor.RESET;
	private String message;


	/**
	 * @param message      The String to scroll
	 * @param width        The width of the window to scroll across (i.e. 16 for signs)
	 * @param spaceBetween The amount of spaces between each repetition
	 * @param colourChar   The colour code character you're using (i.e. & or §)
	 */
	public Scroller(Player p, String message, int width, int spaceBetween, char colourChar)
	{
		this.list = new ArrayList<String>();
		
		this.message = message;

		// Validation
		// String is too short for window
		if (this.message.length() < width)
		{
			StringBuilder sb = new StringBuilder(this.message);
			while (sb.length() < width)
				sb.append(" ");
			this.message = sb.toString();
		}

		// Allow for colours which add 2 to the width
		width -= 2;

		// Invalid width/space size
		if (width < 1)
			width = 1;
		if (spaceBetween < 0)
			spaceBetween = 0;

		// Change to §
		if (colourChar != '§')
			this.message = ChatColor.translateAlternateColorCodes(colourChar, this.message);


		// Add substrings
		for (int i = 0; i < this.message.length() - width; i++)
			this.list.add(this.message.substring(i, i + width));

		// Add space between repeats
		StringBuilder space = new StringBuilder();
		for (int i = 0; i < spaceBetween; ++i)
		{
			this.list.add(this.message.substring(this.message.length() - width + (i > width ? width : i), this.message.length()) + space);
			if (space.length() < width)
				space.append(" ");
		}

		// Wrap
		for (int i = 0; i < width - spaceBetween; ++i)
			this.list.add(this.message.substring(this.message.length() - width + spaceBetween + i, this.message.length()) + space + this.message.substring(0, i));

		// Join up
		for (int i = 0; i < spaceBetween; i++)
		{
			if (i > space.length())
				break;
			this.list.add(space.substring(0, space.length() - i) + this.message.substring(0, width - (spaceBetween > width ? width : spaceBetween) + i));
		}
	}

	/**
	 * @return Gets the next String to display
	 */
	public String next(Player p)
	{
		StringBuilder sb = getNext();
		if (sb.charAt(sb.length() - 1) == COLOUR_CHAR)
			sb.setCharAt(sb.length() - 1, ' ');

		if (sb.charAt(0) == COLOUR_CHAR)
		{
			ChatColor c = ChatColor.getByChar(sb.charAt(1));
			if (c != null)
			{
				colour = c;
				sb = getNext();
				if (sb.charAt(0) != ' ')
					sb.setCharAt(0, ' ');
			}
		}

		return colour + sb.toString();

	}

	private StringBuilder getNext()
	{
		return new StringBuilder(this.list.get(position++ % this.list.size()).substring(0));
	}

}