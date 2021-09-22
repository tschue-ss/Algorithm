package BJ.Common;

import BJ.BJ1753.Main;

public class StringUtils {
	
	public static String getFilePath(Class c) {
		return "src/" + c.getPackageName().replace(".", "/");
	}
}
