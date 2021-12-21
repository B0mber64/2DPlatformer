import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int moveSpeed=4;
    private static int vSpeed=0;
    private int acceleration=3;
    private int accelerationTimer=0;
    private int initialJumpStrength=-4;
    private int jumpStrength=-8;
    private static int jumpTimer=5;
    private static int x;
    private static int floor=1;
    private static boolean loot=false;
    private boolean canHitBlock=true;
    private Actor rightFootWidth[]=new Actor[2];
    private Actor leftFootWidth[]=new Actor[2];
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
        collision();
        toTop();
        findPlatform();
        floors();
        checkLoot();
        win();
        wrapAround();
    }
    /**
     * checks if you touch a chest
     */
    public void checkLoot(){
        Chest c = (Chest) getOneIntersectingObject(Chest.class);
        if(c != null){
            loot=true;
        }
    }
    public boolean getLoot(){
        return loot;
    }
    public void win(){
        if(loot==true && floor==1 && getX()<=0 && getY()>450){
            Greenfoot.setWorld(new Win());
        }
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
        canHitBlock=true;
        fall();
    }
    /**
     * Sets collision for facing left or right.
     */
    public void collision(){
        if(facingRight==true){
            rightFootWidth[0]=getOneObjectAtOffset(18,getImage().getHeight()/2, Platform.class);
            rightFootWidth[1]=getOneObjectAtOffset(18,getImage().getHeight()/2-1, Platform.class);
            leftFootWidth[0]=getOneObjectAtOffset(-8, getImage().getHeight()/2, Platform.class);
            leftFootWidth[1]=getOneObjectAtOffset(-8,getImage().getHeight()/2-1, Platform.class);
            rightSideWidth[0]=getOneObjectAtOffset(24, getImage().getHeight()/-2+5, Block.class);
            rightSideWidth[1]=getOneObjectAtOffset(24, 0, Block.class);
            rightSideWidth[2]=getOneObjectAtOffset(24, getImage().getHeight()/2-5, Block.class);
            leftSideWidth[0]=getOneObjectAtOffset(-20, getImage().getHeight()/-2+5, Block.class);
            leftSideWidth[1]=getOneObjectAtOffset(-20, 0, Platform.class);
            leftSideWidth[2]=getOneObjectAtOffset(-20, getImage().getHeight()/2-5, Block.class);
            }
        else{
            rightFootWidth[0]=getOneObjectAtOffset(8,getImage().getHeight()/2, Platform.class);
            rightFootWidth[1]=getOneObjectAtOffset(8,getImage().getHeight()/2-1, Platform.class);
            leftFootWidth[0]=getOneObjectAtOffset(-18, getImage().getHeight()/2, Platform.class);
            leftFootWidth[1]=getOneObjectAtOffset(-18,getImage().getHeight()/2-1, Platform.class);
            rightSideWidth[0]=getOneObjectAtOffset(20, getImage().getHeight()/-2+5, Block.class);
            rightSideWidth[1]=getOneObjectAtOffset(20, 0, Block.class);
            rightSideWidth[2]=getOneObjectAtOffset(20, getImage().getHeight()/2-5, Block.class);
            leftSideWidth[0]=getOneObjectAtOffset(-24, getImage().getHeight()/-2+5, Block.class);
            leftSideWidth[1]=getOneObjectAtOffset(-24, 0, Block.class);
            leftSideWidth[2]=getOneObjectAtOffset(-24, getImage().getHeight()/2-5, Block.class);
            }
    }
    /**
     * Allows player to drop through ledges while holding "s"
     */
    public boolean drop(){
         Boolean canDrop;
         Actor leftFoot = getOneObjectAtOffset(-8,getImage().getHeight()/2+1, Ledge.class);
         Actor rightFoot = getOneObjectAtOffset(18, getImage().getHeight()/2+1, Ledge.class);
         if((leftFoot!=null||rightFoot!=null)&&Greenfoot.isKeyDown("s")){
             canDrop=true;
             fall();
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
            Actor leftFoot = leftFootWidth[1];
            Actor rightFoot = rightFootWidth[1];
            if(leftFoot!=null||rightFoot!=null){
                setLocation(getX(), getY()-1);
            }
        }
    }
    /**
     * looks for a platfrom below/above the player as they fall/jump 
     * to make sure they don't move through them.
     * This method is a great way to detect possible collisions with anything!
     */
    public void findPlatform(){
        if(!Greenfoot.isKeyDown("s")){
            for(int i=0; i<vSpeed; i++){
                Actor leftFoot = getOneObjectAtOffset(-8,getImage().getHeight()/2+i, Platform.class);
                Actor rightFoot = getOneObjectAtOffset(8,getImage().getHeight()/2+i, Platform.class);
                if(leftFoot!=null||rightFoot!=null&&!drop()){
                    vSpeed=i;
                }
            }
        }
        for(int i=0; i>vSpeed; i--){
            Actor leftHead;
            Actor rightHead;
            if(facingRight){
                leftHead = getOneObjectAtOffset(-8,getImage().getHeight()/-2-i, Block.class);
                rightHead = getOneObjectAtOffset(18,getImage().getHeight()/-2-i, Block.class);
            }
            else{
                leftHead = getOneObjectAtOffset(-18,getImage().getHeight()/-2-i, Block.class);
                rightHead = getOneObjectAtOffset(8,getImage().getHeight()/-2-i, Block.class);
            }
            if(leftHead!=null||rightHead!=null){
                vSpeed=i+1;
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
                Actor leftFoot = leftFootWidth[0];
                Actor rightFoot = rightFootWidth[0];
                if(leftFoot!=null || rightFoot!=null){
                    under=true;
                    jumpTimer=5;
                    canHitBlock=false;
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
        Actor topLeft = leftSideWidth[0];
        Actor midLeft = leftSideWidth[1];
        Actor bottomLeft = leftSideWidth[2];
        Actor topRight = rightSideWidth[0];
        Actor midRight = rightSideWidth[1];
        Actor bottomRight = rightSideWidth[2];
        if(bottomLeft!=null || midLeft!=null || topLeft!=null){
            touching=1;
        }
        else if(bottomRight!=null || midRight!=null || topRight!=null){
            touching=2;
        }
        return touching;
    }
    /**
     * Checks if you're not standing on a platform.
     */
    public void checkFall()
    {
        if(onPlatform()&&!drop()){
            vSpeed=0;
        }
        else{
            fall();
        }
    }
    /**
     * let's the player wrap from one side of the screen to the other.
     */
    public void wrapAround(){
        if(getX()<=0){
            setLocation(getWorld().getWidth()-2, getY());
            if(getOneObjectAtOffset(-24, getImage().getHeight()/-2+5, Platform.class)!=null ||
            getOneObjectAtOffset(-24, 0, Platform.class)!=null || 
            getOneObjectAtOffset(-24, getImage().getHeight()/2-5, Platform.class)!=null){
                setLocation(0, getY());
            }
        }
        
        if(getX()>=getWorld().getWidth()-1){
            setLocation(0, getY());
            if(getOneObjectAtOffset(24, getImage().getHeight()/-2+5, Platform.class)!=null ||
            getOneObjectAtOffset(24, 0, Platform.class)!=null || 
            getOneObjectAtOffset(24, getImage().getHeight()/2-5, Platform.class)!=null){
                setLocation(getWorld().getWidth()-2, getY());
            }
        }
        
    }
    public void floors(){
        Screens screens = new Screens();
        //  Up a floor
        if(getY()<=0 && floor==1){
            screens.setPlayerX(getX());
            screens.setUp(true);
            Greenfoot.setWorld(new Screen2());
            setLocation(x, getWorld().getHeight()-4);
            floor=2;
        }
        if(getY()<=0 && floor==2){
            screens.setPlayerX(getX());
            screens.setUp(true);
            Greenfoot.setWorld(new Screen3());
            setLocation(x, getWorld().getHeight()-4);
            floor=3;
        }
        if(getY()<=0 && floor==3){
            screens.setPlayerX(getX());
            screens.setUp(true);
            Greenfoot.setWorld(new Screen4());
            setLocation(x, getWorld().getHeight()-4);
            floor=4;
        }
        if(getY()<=0 && floor==4){
            screens.setPlayerX(getX());
            screens.setUp(true);
            Greenfoot.setWorld(new Screen5());
            setLocation(x, getWorld().getHeight()-4);
            floor=5;
        }
        //  Down a floor
        if(getY()>=getWorld().getHeight()-1 && floor==2){
            screens.setPlayerX(getX());
            screens.setUp(false);
            Greenfoot.setWorld(new Screen1());
            setLocation(x, 1);
            floor=1;
        }
        if(getY()>=getWorld().getHeight()-1 && floor==3){
            screens.setPlayerX(getX());
            screens.setUp(false);
            Greenfoot.setWorld(new Screen2());
            setLocation(x, 1);
            floor=2;
        }
        if(getY()>=getWorld().getHeight()-1 && floor==4){
            screens.setPlayerX(getX());
            screens.setUp(false);
            Greenfoot.setWorld(new Screen3());
            setLocation(x, 1);
            floor=3;
        }
        if(getY()>=getWorld().getHeight()-1 && floor==5){
            screens.setPlayerX(getX());
            screens.setUp(false);
            Greenfoot.setWorld(new Screen4());
            setLocation(x, 1);
            floor=4;
        }
    }
}
