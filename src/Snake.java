import java.util.ArrayList;

public class Snake {

    //private int length;
    private int startXPos;
    private int startYPos;
    private ArrayList<Block> blockList;

    public Snake(int startX, int startY){
        Block firstBlock = new Block(startX, startY);
        startXPos = startX;
        startYPos = startY;
        blockList = new ArrayList<Block>();

        blockList.add(firstBlock);

    }

    /**
     * move -
     * @param direction
     */
    public void move(int direction) {
        // for moving in general: NEW BLOCK TO FRONT, ADDS ALL OTHER PREVIOUS BLOCKS BEHIND IT (in order)
        ArrayList<Block> newBlockList = new ArrayList<Block>();
        Block temp;


        // All the cases use basically duplicate code. How about we make a method to make it nicer?
        switch(direction){

            case 1: /* UP - x is same, y + 50 */
                temp = new Block(blockList.get(0).getX(), blockList.get(0).getY() + 50);
                newBlockList.add(temp);
                for(int i = 0; i < blockList.size() - 1; i++){
                    newBlockList.add(blockList.get(i));
                }
                break;

            case 2: //down
                temp = new Block(blockList.get(0).getX(), blockList.get(0).getY() - 50);
                newBlockList.add(temp);
                for(int i = 0; i < blockList.size() - 1; i++){
                    newBlockList.add(blockList.get(i));
                }
                break;

            case 3: //left
                temp = new Block(blockList.get(0).getX() - 50, blockList.get(0).getY());
                newBlockList.add(temp);
                for(int i = 0; i < blockList.size() - 1; i++){
                    newBlockList.add(blockList.get(i));
                }
                break;

            case 4: //right
                temp = new Block(blockList.get(0).getX() + 50, blockList.get(0).getY());
                newBlockList.add(temp);
                for(int i = 0; i < blockList.size() - 1; i++){
                    newBlockList.add(blockList.get(i));
                }
                break;

            default: //no change

        }


//            newBlockList.add( new head block given every second in main (blockList.get(0).setX/Y(blockList.getX/y +/- 50)) );
//            for(int i = 0; i < blockList.size(); i++){
//                newBlocKList.add(blockList.get(i));
//            }
            this.blockList = newBlockList;



    }

    /**
     * getBlockList - returns this.blockList
     * @return
     */
    public ArrayList<Block> getBlockList(){
        return this.blockList;
    }

    /**
     * getHead - returns the first element in blockList
     * @return Block
     */
    public Block getHead(){
        return this.blockList.get(0);
    }

    /**
     * eatFood - duplicates the last element of blockList to the end
     */
    public void eatFood(){
        // duplicate end of the list to the end of the list
        this.blockList.add(this.blockList.get(this.blockList.size() - 1));
    }

}
