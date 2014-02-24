package PckgEngine;

import PckgUtil.LoadNativeLibraries;

/**
 *
 * @author Carlos
 */
public class VoxelEngine {

    public static void main(String[] args) throws Exception {
        LoadNativeLibraries.load();
        
        Game game = new Game();
	game.Start();
    }
}
