import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Screen1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen3 extends World
{
    public Screen3()
    {
        super(600, 600, 1);
        Screens screens = new Screens();
        if(screens.getUp()){
            addObject(new Player(), screens.getPlayerX(), getHeight()-2);
        }
        if(!screens.getUp()){
            addObject(new Player(), screens.getPlayerX(), 1);
        }
        
        for(int i=0; i<12; i++){
            addObject(new Block(), new Block().getImage().getWidth()*i, getHeight()); 
        }
    }
}
