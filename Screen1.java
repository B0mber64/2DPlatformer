import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Screen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen1 extends World
{
    public Screen1()
    {    
        super(600, 600, 1);
        Screens screens = new Screens();
        if(screens.getUp()){
            addObject(new Player(), screens.block()*2, getHeight()-100);
        }
        if(!screens.getUp()){
            addObject(new Player(), screens.getPlayerX(), 1);
        }
        
        for(int i=0; i<16; i++)
        { 
            addObject(new Block(), screens.block()*i, getHeight());
            addObject(new Block(), screens.block()*i, 
            getHeight() - screens.block());
        }
        //stairs
        for(int i=1; i<=6; i++)
        {
            for(int x=0; x<=i; x++){
            addObject(new Block(), screens.block()*6
            + screens.block()*i, getHeight() 
            - screens.block()*2
            - screens.block()*x);
            }
        }
        for(int i=0; i<=2; i++)
        {
            for(int x=0; x<=3; x++){
            addObject(new Block(), screens.block()*13
            + screens.block()*i, getHeight() 
            - screens.block()*2
            - screens.block()*x);
            }
        }
        //left side blocks
        for(int i=0; i<3; i++)
        {
            addObject(new Block(), screens.block()*i, 
            getHeight() - screens.block()*5);  
        }
        for(int i=0; i<2; i++){
            addObject(new Block(), screens.block()*3, 
            getHeight() - screens.block()*5 -
            screens.block()*i);  
        }
        for(int i=0; i<4; i++){
            addObject(new Block(), screens.block()*i, 
            screens.block()*(5));  
        }
        //gapped blocks
        for(int i=1; i<7; i++){
            if(i%3==0){
                addObject(new Block(), screens.block()*3
                + screens.block()*i, 
                screens.block()*4);
            }
        }
        addObject(new Ledge(), screens.ledgeStart()*20, 
        screens.block()*3 - screens.block());
        
        //ceiling
        for(int i=0; i<12; i++){
            addObject(new Block(), screens.block()*i, 0); 
        }
        for(int i=0; i<2; i++){
            addObject(new Block(), 0, screens.block()+screens.block()*i); 
        }
        for(int i=0; i<2; i++){
            addObject(new Block(), screens.block(), screens.block()+screens.block()*i); 
        }
    }
    
    
}
