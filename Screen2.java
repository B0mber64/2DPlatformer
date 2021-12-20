import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Screen1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen2 extends World
{
    public Screen2()
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
            addObject(new Block(), screens.block()*i, getHeight()); 
        }
        //left piece
        for(int i=0; i<3; i++){
            addObject(new Block(), 0, getHeight() 
            - screens.block()*1
            - screens.block()*i); 
        }
        for(int i=0; i<3; i++){
            addObject(new Block(), screens.block() + screens.block()*i, getHeight() 
            - screens.block()*3);
        }
        for(int i=0; i<4; i++){
            addObject(new Block(), screens.block()*3, getHeight() 
            - screens.block()*4
            - screens.block()*i); 
        }
        //middle
        for(int i=3; i<9; i++){
            if(i%4==0){
                addObject(new Block(), screens.block()*4, getHeight() 
                + screens.block()
                - screens.block()*i);
            }
        }
        addObject(new Block(), screens.block()*8, getHeight()-screens.block()*5);
        addObject(new Block(), screens.block()*6, getHeight()-screens.block());
        //right side
        for(int i=0; i<7; i++){
            addObject(new Block(), screens.block()*9, screens.block()*5+screens.block()*i); 
        }
        for(int i=0; i<2; i++){
            addObject(new Block(), screens.block()*10 + screens.block()*i, getHeight() 
            - screens.block()*4);
        }
        //ceiling
        for(int i=0; i<2; i++){
            addObject(new Block(), screens.block()*8+screens.block()*i, screens.block()*4); 
        }
        for(int i=0; i<5; i++){
            addObject(new Block(), screens.block()*i, screens.block()*4); 
        }
        addObject(new Block(), screens.block()*8, screens.block());
        for(int i=0; i<8; i++){
            addObject(new Block(), screens.block()*8+screens.block()*i, 0); 
        }
    }
}
