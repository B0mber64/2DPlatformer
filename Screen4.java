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
            addObject(new Block(), getWidth()-7, getHeight()-screens.block()*i); 
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
        for(int i=0; i<3; i++){
            addObject(new Block(), screens.block()*4, getHeight()-screens.block()*i); 
        }
        //main
        for(int i=0; i<7; i++){
            addObject(new Block(), screens.block()*6, screens.block()*6+screens.block()*i); 
        }
        for(int i=0; i<3; i++){
            addObject(new Block(), screens.block()*5+screens.block()*i, screens.block()*10); 
        }
        addObject(new Block(), 0, getHeight()-screens.block()*4);
        addObject(new Block(), screens.block()*9, getHeight()-screens.block()*3);
        for(int i=0; i<2; i++){
            addObject(new Block(), screens.block()*9, getHeight()-screens.block()*6-screens.block()*i); 
        }
        for(int i=0; i<4; i++){
            addObject(new Block(), screens.block()*10, screens.block()*5+screens.block()*i); 
        }
        for(int i=0; i<2; i++){
            addObject(new Block(), screens.block()*9, screens.block()*4+screens.block()*i); 
        }
        addObject(new Block(), screens.block()*3, screens.block()*6);
        addObject(new Block(), 0, screens.block()*6);
        addObject(new Block(), getWidth(), screens.block()*6);
        //ceiling
        for(int i=0; i<4; i++){
            addObject(new Block(), screens.block()*6+screens.block()*i, screens.block()*3); 
        }
        addObject(new Chest(), screens.block()/2*15, screens.block()/4*7);
        for(int i=0; i<16; i++){
            addObject(new Block(), screens.block()*i, 0); 
        }
    }
}
