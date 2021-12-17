import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Screen1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen2 extends World
{
    private int playerX;
    private boolean up;
    public Screen2()
    {
        super(600, 600, 1);
        if(up){
            addObject(new Player(), playerX, 540);
        }
        if(!up){
            addObject(new Player(), playerX, 540);
        }
        for(int i=0; i<12; i++){
            addObject(new Block(), new Block().getImage().getWidth()*i, getHeight()); 
        }
    }
    
    public void setPlayerX(int playerX){
        this.playerX=playerX;
    }
    
    public void setUp(boolean up){
        this.up=up;
    }
}
