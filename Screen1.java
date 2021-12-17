import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Screen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen1 extends World
{
    private int playerX=new Block().getImage().getWidth()*2;
    private boolean up=true;
    public Screen1()
    {    
        super(600, 600, 1);
        if(up){
            addObject(new Player(), playerX, getHeight()-100);
        }
        if(!up){
            addObject(new Player(), playerX, 540);
        }
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
        for(int i=1; i<2; i++){
                addObject(new Ledge(), new Block().getImage().getWidth()*11
                + new Block().getImage().getWidth()*i, 
                new Block().getImage().getHeight()*3);
        }
        for(int i=1; i<2; i++){
            if((i+1)%2==0){
                addObject(new Ledge(), new Block().getImage().getWidth()*13
                + new Block().getImage().getWidth()*i, 
                new Block().getImage().getHeight()*2);
            }   
        }
        //ceiling
        for(int i=0; i<12; i++){
            addObject(new Block(), new Block().getImage().getWidth()*i, 0); 
        }
    }
    
    public void setPlayerX(int playerX){
        this.playerX=playerX;
    }
    
    public void setUp(boolean up){
        this.up=up;
    }
}
