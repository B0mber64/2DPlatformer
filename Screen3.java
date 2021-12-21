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
        
        for(int i=0; i<8; i++){
            addObject(new Block(), screens.block()*8+screens.block()*i, getHeight()); 
        }
        for(int i=0; i<3; i++){
            addObject(new Block(), screens.block()*2+screens.block()*i, getHeight()); 
        }
        //first row
        for(int i=0; i<5; i++){
            addObject(new Block(), screens.block()*i, getHeight()-screens.block()*3); 
        }
        for(int i=0; i<3; i++){
            addObject(new Block(), screens.block()*5+screens.block()*i, getHeight()-screens.block()*5);
        }
        for(int i=0; i<2; i++){
            addObject(new Ledge(), screens.block()*11+screens.block()*i, getHeight()-screens.block()*3); 
        }
        for(int i=0; i<3; i++){
            addObject(new Block(), screens.block()*8+screens.block()*i, getHeight()-screens.block()*3); 
        }
        for(int i=0; i<3; i++){
            addObject(new Block(), getWidth()-screens.block()*i, getHeight()-screens.block()*3); 
        }
        for(int i=1; i<3; i++){
            for(int x=0; x<3; x++){
                addObject(new Block(), getWidth()-screens.block()*x, getHeight()-screens.block()*i);
            }
        }
        //second row
        for(int i=0; i<8; i++){
            addObject(new Block(), screens.block()*8+screens.block()*i, getHeight()-screens.block()*7); 
        }
        for(int i=0; i<2; i++){
            addObject(new Block(), screens.block()*5+screens.block()*i, getHeight()-screens.block()*9);
        }
        addObject(new Block(), getWidth(), getHeight()-screens.block()*8);
        addObject(new Block(), 0, getHeight()-screens.block()*8);
        //third row
        for(int i=0; i<8; i++){
            addObject(new Block(), screens.block()*8+screens.block()*i, getHeight()-screens.block()*11); 
        }
        addObject(new Ledge(), getWidth()-screens.ledgeStart()*3, screens.block()*2); 
        addObject(new Ledge(), screens.ledgeStart()*3, screens.block()*2); 
        //ceiling
        for(int i=0; i<8; i++){
            addObject(new Block(), screens.block()*4+screens.block()*i, 0); 
        }
        addObject(new Block(), getWidth(), 0);
    }
}
