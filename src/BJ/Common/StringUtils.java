package BJ.Common;

public class StringUtils {
	
	public static String getFilePath(Class c) {
		return "src/" + c.getPackageName().replace(".", "/");
	}
}
