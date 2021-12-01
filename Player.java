import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 *      note:       probably should make different collision with left and right facing character
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int moveSpeed=4;
    private int vSpeed=0;
    private int acceleration=3;
    private int accelerationTimer=0;
    private int initialJumpStrength=-4;
    private int jumpStrength=-8;
    private int jumpTimer=5;
    private Actor rightFootWidth;
    private Actor leftFootWidth;
    private Actor rightSideWidth[]=new Actor[3];
    private Actor leftSideWidth[]=new Actor[3];
    private Actor rightHeadWidth;
    private Actor leftHeadWidth;
    private Boolean facingRight=true;
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        checkKeys();
        checkFall();
        checkBlock();
        canJump();
        toTop();
        wrapAround();
    }
    /**
     * Checks keys for moving or jumping.
     */ 
    public void checkKeys()
    {
        if (Greenfoot.isKeyDown("d")&&(checkBlock()!=2))
        {
            moveRight();
        }
        
        if (Greenfoot.isKeyDown("a")&&(checkBlock()!=1))
        {
            moveLeft();
        }
        
        if(Greenfoot.isKeyDown("space")&&(canJump()))
        {
            jump();
        }
    }
    /**
     * Move right while facing right.
     */ 
    public void moveRight()
    {
        setLocation (getX() + moveSpeed, getY());
        setImage(new GreenfootImage("Adventurer.png"));
        facingRight=true;
    }
    /**
     * Move left while facing left.
     */ 
    public void moveLeft()
    {
        setLocation (getX() - moveSpeed, getY());
        setImage(new GreenfootImage("Adventurer2.png"));
        facingRight=false;
    }
    /**
     * Set vertical speed to send you up and then run the fall method.
     */ 
    public void jump()
    {
        vSpeed=jumpStrength;
        accelerationTimer=0;
        jumpTimer=0;
        fall();
    }
    /**
     * Sets collision for facing left or right.
     */
    public void collision(){
        if(facingRight==true){
            rightFootWidth=getOneObjectAtOffset(18,getImage().getHeight()/2, Ledge.class);
            leftFootWidth=getOneObjectAtOffset(-8, getImage().getHeight()/2, Ledge.class);
            rightSideWidth[0]=getOneObjectAtOffset(24, getImage().getHeight()/-2, Ledge.class);
            rightSideWidth[1];
            rightSideWidth[2];
            leftSideWidth=getImage().getWidth()/-2+4;
            rightHeadWidth=getImage().getWidth()/2-4;
            leftHeadWidth=getImage().getWidth()/-2+6;
        }
        else{
            rightFootWidth=getImage().getWidth()/2-16;
            leftFootWidth=getImage().getWidth()/-2+6;
            rightSideWidth=getImage().getWidth()/2-4;
            leftSideWidth=getImage().getWidth()/-2;
            rightHeadWidth=getImage().getWidth()/2-6;
            leftHeadWidth=getImage().getWidth()/-2+4;
        }
    }
    /**
     * Allows player to drop through ledges while holding "s"
     */
    public boolean drop(){
         Boolean canDrop;
         Actor leftFoot = getOneObjectAtOffset(getImage().getWidth()/2-5, 
                getImage().getHeight()/2, Ledge.class);
         Actor rightFoot = getOneObjectAtOffset(getImage().getWidth()/-2+5, 
                getImage().getHeight()/2, Ledge.class);
         if((leftFoot!=null||rightFoot!=null)&&Greenfoot.isKeyDown("s")){
             canDrop=true;
         }
         else{
             canDrop=false;
         }
         return canDrop;
    }
    /** 
     * Controls vertical location. 
     * Accelerates at a fixed rate with capped falling speed.
     */
    public void fall()
    {
        
        setLocation(getX(),getY()+vSpeed);
        if(vSpeed<14){
            accelerationTimer++;
            if(accelerationTimer>=6){ 
            vSpeed+=acceleration;
            accelerationTimer=0;
            }
        }
    }
    /**
     * Glides the player to the top of platforms so they don't appear 
     * inside of them.
     */
    public void toTop()
    {   
        if(!drop()){
            Actor leftFoot = getOneObjectAtOffset(getImage().getWidth()/2-5, 
                    getImage().getHeight()/2-1, Platform.class);
            Actor rightFoot = getOneObjectAtOffset(-getImage().getWidth()/2+5, 
                    getImage().getHeight()/2-1, Platform.class);
            if(leftFoot!=null||rightFoot!=null){
                setLocation(getX(), getY()-1);
            }
        }
    }
    /**
     * looks for a platfrom belowm the player as the fall to make sure 
     * they don't fall through them.
     * This method is a great way to detect possible collisions with anything!
     */
    public void findPlatform(){
        for(int i=0; i<vSpeed; i++){
            Actor leftFoot = getOneObjectAtOffset(getImage().getWidth()/2-5, 
                    getImage().getHeight()/2+i, Platform.class);
            Actor rightFoot = getOneObjectAtOffset(-getImage().getWidth()/2+5, 
                    getImage().getHeight()/2+i, Platform.class);
            if(leftFoot!=null||rightFoot!=null){
                vSpeed=i+2;
            }
        }
    }
    /**
     * Checks if the player is on a platform. Prevents falling through blocks.
     */
    public boolean onPlatform()
    {
        boolean under;
        if(!drop()){
                Actor leftFoot = getOneObjectAtOffset(getImage().getWidth()/2+5, 
                getImage().getHeight()/2, Platform.class);
                Actor rightFoot = getOneObjectAtOffset(getImage().getWidth()/2-5, 
                getImage().getHeight()/2, Platform.class);
                if(leftFoot!=null || rightFoot!=null){
                    under=true;
                    jumpTimer=5;
                }
                else{
                under=false;
                }
        }
        else{
                under=false;
        }
        return under;
    }
    /**
     * allows buffer zone for jump for less strict inputs
     */
    public boolean canJump()
    {
        boolean jump;
        if(jumpTimer>0){
                jump=true;
                jumpTimer--;
        }
        else{
                jump=false;
        }
        return jump;
    }
    /**
     * Checks if the player is touching a block on it's sides to 
     * stop the player from walking through blocks.
     * Forces the player down when it hits the bottom of a block.
     */
    public int checkBlock()
    {
        int touching=0;
        Actor headLeft = getOneObjectAtOffset(new Player().getImage().getWidth()/-2+5, -38, Block.class);
        Actor headRight = getOneObjectAtOffset(new Player().getImage().getWidth()/2-5, -38, Block.class);
        Actor bottomLeft = getOneObjectAtOffset(new Player().getImage().getWidth()/-2-2, 31, Block.class);
        Actor midLeft = getOneObjectAtOffset(new Player().getImage().getWidth()/-2-2, -10, Block.class);
        Actor topLeft = getOneObjectAtOffset(new Player().getImage().getWidth()/-2-2, -35, Block.class);
        Actor bottomRight = getOneObjectAtOffset(new Player().getImage().getWidth()/2, 31, Block.class);
        Actor midRight = getOneObjectAtOffset(new Player().getImage().getWidth()/2, -10, Block.class);
        Actor topRight = getOneObjectAtOffset(new Player().getImage().getWidth()/2, -35, Block.class);
        if(bottomLeft!=null || midLeft!=null || topLeft!=null){
            touching=1;
        }
        if(bottomRight!=null || midRight!=null || topRight!=null){
            touching=2;
        }
        if(headLeft!=null || headRight!=null){
            blockHit();
        }
        return touching;
    }
    /**
     * pushes you down when you hit the bottom of a block.
     */
    public void blockHit(){
        vSpeed=1;
        fall();
    }
    /**
     * Checks if you're not standing on a platform.
     */
    public void checkFall()
    {
        if(onPlatform()){
            vSpeed=0;
        }
        else{
            fall();
        }
    }
    public void wrapAround(){
        if(getX()<=0){
            setLocation(getWorld().getWidth()-2, getY());
            if(checkBlock()==1){
                setLocation(0, getY());
            }
        }
        
        if(getX()>=getWorld().getWidth()-1){
            setLocation(0, getY());
            if(checkBlock()==2){
                setLocation(getWorld().getWidth()-2, getY());
            }
        }
        
    }
}
