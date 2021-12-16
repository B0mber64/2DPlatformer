import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Screen1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen3 extends Screens
{

    /**
     * Constructor for objects of class Screen1.
     * 
     */
    public Screen3()
    {
        addObject(new Player(), 350, 500);
        for(int i=0; i<12; i++){
            addObject(new Block(), new Block().getImage().getWidth()*i, getHeight()); 
        }
    }
}
