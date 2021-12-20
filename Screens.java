import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Screens here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screens extends World
{
    private static int playerX=new Block().getImage().getWidth()*2;
    private static boolean up=true;
    public Screens()
    {    
        super(600, 400, 1); 
    }
    
    public void setPlayerX(int playerX){
        this.playerX=playerX;
    }
    
    public int getPlayerX(){
        return playerX;
    }
    
    public void setUp(boolean up){
        this.up=up;
    }
    
    public boolean getUp(){
        return up;
    }
    
    public int ledgeStart(){
        int x=new Ledge().getImage().getWidth()/3;
        return x;
    }
    
    public int ledge(){
        int x=new Ledge().getImage().getWidth();
        return x;
    }
    
    public int block(){
        int x=new Block().getImage().getWidth();
        return x;
    }
}
