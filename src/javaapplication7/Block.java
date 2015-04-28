package javaapplication7;

import java.awt.*;
import Value.*;

/**
 *
 * @author husnimun
 */
public class Block extends Rectangle {
    private int groundID;
    private int airID;
    
    public Block(int x, int y, int width, int height, int groundID, int airID) {
        setBounds(x, y, width, height);
        this.groundID = groundID;
        this.airID = airID;
    }
    
    public void draw(Graphics g) {
        g.drawImage(Screen.getTilesetGround(groundID), x, y, width, height, null);
        
        if (airID != Value.getairAir()) {
            g.drawImage(Screen.getTilesetAir(airID), x, y, width, height, null);
        }
    }
    
    public void physics() {
        
    }
    
    public int getGroundID() { return groundID; }
    
    public void setGroundID(int groundID) { this.groundID = groundID; }
    
    public int getAirID() { return airID; }
    
    public void setAirID(int airID) { this.airID = airID; }
    
}
