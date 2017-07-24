package me.wheezygold.skLib.common;

import me.nrubin29.pastebinapi.*;
import me.wheezygold.skLib.Main;

import java.io.IOException;

public class publicPaster {
	
	public static String createPaste(String title, String body) throws PastebinException, IOException {
		PastebinAPI api = new PastebinAPI(Main.getInstance().getConfig().getString("values.pastebin.apikey"));
		
		CreatePaste paste = api.createPaste()
				.withName(title)
				.withFormat(Format.None)
				.withPrivacyLevel(PrivacyLevel.PUBLIC)
				.withExpireDate(ExpireDate.NEVER)
				.withText(body);
		String url = paste.post();
		return url;
	}

}
