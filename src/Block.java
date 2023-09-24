public class Block {

    private int x;
    private int y;

    public Block(int newX, int newY){
        this.x = newX;
        this.y = newY;
    }

    /**
     * getX - returns X value of block
     * @return this.x
     */
    public int getX(){
        return this.x;
    }

    /**
     * setX - sets this.x to newX
     * @param newX
     */
    public void setX(int newX){
        this.x = newX;
    }

    /**
     * getY - returns Y value of block
     * @return this.y
     */
    public int getY(){
        return this.y;
    }

    /**
     * setY - sets this.y to newY
     * @param newY
     */
    public void setY(int newY){
        this.y = newY;
    }

    /**
     * checkContact - checks if two blocks have the same x & y values
     * @param b1
     * @param b2
     * @return boolean
     */
    public static boolean checkContact(Block b1, Block b2){
        if(b1.getX() == b2.getX() && b1.getY() == b2.getY()){
            return true;
        }
        return false;
    }
}
