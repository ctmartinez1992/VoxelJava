package PckgUtil;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 *
 * @author Carlos
 */
public class LoadNativeLibraries {
    
    public static void load() throws Exception {
        switch (System.getProperty("os.name")) {
            case "Mac OS X":
                addLibraryPath("natives/macosx");
                break;
            case "Linux":
                addLibraryPath("natives/linux");
                break;
            default:
                addLibraryPath("natives/windows");
                break;
        }
    }

    private static void addLibraryPath(String so) throws Exception {
        final Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
        usrPathsField.setAccessible(true);

        final String[] paths = (String[]) usrPathsField.get(null);

        for (String path : paths)
            if (path.equals(so))
                return;

        final String[] newPaths = Arrays.copyOf(paths, paths.length + 1);
        newPaths[newPaths.length - 1] = so;
        usrPathsField.set(null, newPaths);
    }
}
