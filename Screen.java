import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Screen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen extends World
{
    private Player player;
    /**
     * Constructor for objects of class Screen.
     * 
     */
    public Screen()
    {    
        // Create a new world with 600x600 cells with a cell size of 1x1 pixels.
        super(600, 600, 1);
        player=new Player();
        addObject(player, new Block().getImage().getWidth()*2, getHeight()-100);
        for(int i=0; i<16; i++)
        { 
            addObject(new Block(), new Block().getImage().getWidth()*i, getHeight());
            addObject(new Block(), new Block().getImage().getWidth()*i, 
            getHeight() - new Block().getImage().getHeight());
        }
        //stairs
        for(int i=1; i<=6; i++)
        {
            for(int x=0; x<=i; x++){
            addObject(new Block(), new Block().getImage().getWidth()*6
            + new Block().getImage().getWidth()*i, getHeight() 
            - new Block().getImage().getHeight()*2
            - new Block().getImage().getHeight()*x);
            }
        }
        for(int i=0; i<=2; i++)
        {
            for(int x=0; x<=3; x++){
            addObject(new Block(), new Block().getImage().getWidth()*13
            + new Block().getImage().getWidth()*i, getHeight() 
            - new Block().getImage().getHeight()*2
            - new Block().getImage().getHeight()*x);
            }
        }
        //left side blocks
        for(int i=0; i<3; i++)
        {
            addObject(new Block(), new Block().getImage().getWidth()*i, 
            getHeight() - new Block().getImage().getHeight()*5);  
        }
        for(int i=0; i<2; i++){
            addObject(new Block(), new Block().getImage().getWidth()*3, 
            getHeight() - new Block().getImage().getHeight()*5 -
            new Block().getImage().getHeight()*i);  
        }
        for(int i=0; i<4; i++){
            addObject(new Block(), new Block().getImage().getWidth()*i, 
            new Block().getImage().getHeight()*(5));  
        }
        //gapped blocks
        for(int i=1; i<7; i++){
            if(i%3==0){
                addObject(new Block(), new Block().getImage().getWidth()*3
                + new Block().getImage().getWidth()*i, 
                new Block().getImage().getHeight()*4);
            }
        }
        //ceiling
        for(int i=0; i<12; i++){
            addObject(new Block(), new Block().getImage().getWidth()*i, 0); 
        }
    }
}
