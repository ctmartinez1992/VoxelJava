package PckgBlock;

import PckgMath.Float3D;

/**
 *
 * @author Carlos
 */
public class BlockColorSet {
    
    private Float3D[] dirtColorSet = new Float3D[10];
    private boolean dirtCreated = false;
    
    private Float3D[] grayColorSet = new Float3D[10];
    private boolean grayCreated = false;
    
    private Float3D[] greenColorSet = new Float3D[10];
    private boolean greenCreated = false;
    
    private Float3D[] whiteColorSet = new Float3D[10];
    private boolean whiteCreated = false;
    
    private Float3D[] yellowColorSet = new Float3D[10];
    private boolean yellowCreated = false;
    
    private Float3D[] redColorSet = new Float3D[10];
    private boolean redCreated = false;
    
    public BlockColorSet() {
        if (dirtCreated) createDirtColorSet();
        if (grayCreated) createGrayColorSet();
        if (greenCreated) createGreenColorSet();
        if (whiteCreated) createWhiteColorSet();
        if (yellowCreated) createYellowColorSet();
        if (redCreated) createRedColorSet();
    }
    
    private static BlockColorSet _i;
    
    public static BlockColorSet getInstance() {
        if(_i==null)
            _i = new BlockColorSet();
        return _i;
    }
    
    private void createDirtColorSet() {
        dirtColorSet[0] = new Float3D(200, 100, 50); dirtColorSet[1] = new Float3D(198, 98, 48);
        dirtColorSet[2] = new Float3D(202, 102, 52); dirtColorSet[3] = new Float3D(196, 96, 46);
        dirtColorSet[4] = new Float3D(204, 104, 54); dirtColorSet[5] = new Float3D(194, 94, 44);
        dirtColorSet[6] = new Float3D(206, 106, 56); dirtColorSet[7] = new Float3D(192, 92, 42);
        dirtColorSet[8] = new Float3D(208, 108, 58); dirtColorSet[9] = new Float3D(190, 90, 40);
        
        dirtCreated = true;
    }
    
    private void createGrayColorSet() {
        grayColorSet[0] = new Float3D(120, 130, 140); grayColorSet[1] = new Float3D(118, 128, 138);
        grayColorSet[2] = new Float3D(122, 132, 142); grayColorSet[3] = new Float3D(116, 126, 136);
        grayColorSet[4] = new Float3D(124, 134, 144); grayColorSet[5] = new Float3D(114, 124, 134);
        grayColorSet[6] = new Float3D(126, 136, 146); grayColorSet[7] = new Float3D(112, 122, 132);
        grayColorSet[8] = new Float3D(128, 138, 148); grayColorSet[9] = new Float3D(110, 120, 130);
        
        grayCreated = true;
    }
    
    private void createGreenColorSet() {
        greenColorSet[0] = new Float3D(10, 120, 10); greenColorSet[1] = new Float3D(8, 118, 8);
        greenColorSet[2] = new Float3D(12, 122, 12); greenColorSet[3] = new Float3D(6, 116, 6);
        greenColorSet[4] = new Float3D(14, 124, 14); greenColorSet[5] = new Float3D(4, 114, 4);
        greenColorSet[6] = new Float3D(16, 126, 16); greenColorSet[7] = new Float3D(2, 112, 2);
        greenColorSet[8] = new Float3D(18, 128, 18); greenColorSet[9] = new Float3D(0, 110, 0);
        
        greenCreated = true;
    }
    
    private void createWhiteColorSet() {
        whiteColorSet[0] = new Float3D(240, 240, 240); whiteColorSet[1] = new Float3D(238, 238, 238);
        whiteColorSet[2] = new Float3D(242, 242, 242); whiteColorSet[3] = new Float3D(236, 236, 236);
        whiteColorSet[4] = new Float3D(244, 244, 244); whiteColorSet[5] = new Float3D(234, 234, 234);
        whiteColorSet[6] = new Float3D(246, 246, 246); whiteColorSet[7] = new Float3D(232, 232, 232);
        whiteColorSet[8] = new Float3D(248, 248, 248); whiteColorSet[9] = new Float3D(230, 230, 230);
        
        whiteCreated = true;
    }
    
    private void createYellowColorSet() {
        yellowColorSet[0] = new Float3D(240, 190, 30); yellowColorSet[1] = new Float3D(238, 188, 28);
        yellowColorSet[2] = new Float3D(242, 192, 32); yellowColorSet[3] = new Float3D(236, 186, 26);
        yellowColorSet[4] = new Float3D(244, 194, 34); yellowColorSet[5] = new Float3D(234, 184, 24);
        yellowColorSet[6] = new Float3D(246, 196, 36); yellowColorSet[7] = new Float3D(232, 182, 22);
        yellowColorSet[8] = new Float3D(248, 198, 38); yellowColorSet[9] = new Float3D(230, 180, 20);
        
        yellowCreated = true;
    }
    
    private void createRedColorSet() {
        redColorSet[0] = new Float3D(180, 10, 10); redColorSet[1] = new Float3D(178, 8, 8);
        redColorSet[2] = new Float3D(182, 12, 12); redColorSet[3] = new Float3D(176, 6, 6);
        redColorSet[4] = new Float3D(184, 14, 14); redColorSet[5] = new Float3D(174, 4, 4);
        redColorSet[6] = new Float3D(186, 16, 16); redColorSet[7] = new Float3D(172, 2, 2);
        redColorSet[8] = new Float3D(188, 18, 18); redColorSet[9] = new Float3D(170, 0, 0);
        
        redCreated = true;
    }

    public Float3D[] getDirtColorSet() {
        if (!dirtCreated)
            createDirtColorSet();
        
        return dirtColorSet;
    }

    public Float3D[] getGrayColorSet() {
        if (!grayCreated)
            createGrayColorSet();
        
        return grayColorSet;
    }
    
    public Float3D[] getGreenColorSet() {
        if (!greenCreated)
            createGreenColorSet();
        
        return greenColorSet;
    }
    
    public Float3D[] getWhiteColorSet() {
        if (!whiteCreated)
            createWhiteColorSet();
        
        return whiteColorSet;
    }
    
    public Float3D[] getYellowColorSet() {
        if (!yellowCreated)
            createYellowColorSet();
        
        return yellowColorSet;
    }
    
    public Float3D[] getRedColorSet() {
        if (!redCreated)
            createRedColorSet();
        
        return redColorSet;
    }
}