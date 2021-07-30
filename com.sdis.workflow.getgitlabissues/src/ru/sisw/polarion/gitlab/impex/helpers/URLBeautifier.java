package ru.sisw.polarion.gitlab.impex.helpers;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class URLBeautifier {
	private static final Map<String, String> map = new HashMap<>();
	static {
		map.put(" ", "%20");
		map.put("\n", "%0D%0A");
	}
	public static String beautify(String url) {
		//FIXME: nikitin! replace for more effective code!
		for (Entry<String, String> es : map.entrySet())
			url = url.replace(es.getKey(), es.getValue());
		return url;
	}
}
