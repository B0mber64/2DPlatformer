import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Screen1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen4 extends World
{
    public Screen4()
    {
        super(600, 600, 1);
        Screens screens = new Screens();
        if(screens.getUp()){
            addObject(new Player(), screens.getPlayerX(), getHeight()-2);
        }
        if(!screens.getUp()){
            addObject(new Player(), screens.getPlayerX(), 1);
        }
        
        for(int i=0; i<3; i++){
            addObject(new Ledge(), screens.ledge()*i, getHeight()); 
        }
        for(int i=0; i<2; i++){
            addObject(new Ledge(), screens.block()*12+screens.ledge()*i, getHeight()); 
        }
        for(int i=0; i<7; i++){
            addObject(new Block(), screens.block()*4+screens.block()*i, getHeight()); 
        }
        for(int i=0; i<4; i++){
            addObject(new Block(), getWidth(), getHeight()-screens.block()*i); 
        }
        for(int i=0; i<4; i++){
            addObject(new Block(), getWidth()-screens.block()*4, getHeight()-screens.block()*i); 
        }
        for(int i=0; i<5; i++){
            addObject(new Block(), getWidth()-screens.block()*i, getHeight()-screens.block()*4); 
        }
    }
}
