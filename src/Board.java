import java.awt.*;
import java.util.*;

public class Board {

    private int size;
    private int width;
    private int height;
    // Grid size: 17 x 15
    final int MAX_GRID_X = 15; //17
    final int MAX_GRID_Y = 15; //15
    final int BLOCK_SIZE = 50;
    private Block food;
    private int score;

    /**
     *
     */
    public Board(){
        //this.size = 500;
        this.width = MAX_GRID_X * BLOCK_SIZE;
        this.height = MAX_GRID_Y * BLOCK_SIZE;
        food = new Block(0,0);
        score = 0;
        setFood();
    }

    /**
     * setup - sets a board with black background and lines
     */
    public void setup(){
        //StdDraw.setCanvasSize(size, size);
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0, width);
        StdDraw.setYscale(0, height);

        this.drawBoard();
    }

    /**
     * drawBoard -
     */
    public void drawBoard(){
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);

        //draw vertical lines
        for(int i = 0; i < MAX_GRID_X + 1; i++){
            int x;
            StdDraw.line((width * i) / (double)MAX_GRID_X,0,(width * i) / (double)MAX_GRID_X,height);
        }

        //draw horizontal lines
        for(int i = 0; i < MAX_GRID_Y + 1; i++){
            int y;
            StdDraw.line(0,(height * i) / (double)MAX_GRID_Y,width,(height * i) / (double)MAX_GRID_Y);
        }
    }

    /**
     * endGame -
     */
    public void endGame(){
        StdDraw.clear(StdDraw.BLACK);
        //StdDraw.setPenColor(StdDraw.LIGHT_GRAY);

        for(int i = 0; i <= 360; i++){
            int k = i * 225 / 360;
            StdDraw.setPenColor(k, k, k);
            //StdDraw.setFont(Font.);
            StdDraw.setPenRadius(10);
            StdDraw.text(width / 2, height / 2, "Game Over", i);
            StdDraw.pause(1);
            StdDraw.show();
            StdDraw.clear();
        }
    }

    /**
     * setFood - changes location of the food block on the board
     */
    public void setFood(Snake snake){
        Random r = new Random();
        boolean isNotValid;
        int randX;
        int randY;

        do {
            isNotValid = false; //sets it to be valid
            randX = r.nextInt(MAX_GRID_X) * BLOCK_SIZE + (BLOCK_SIZE / 2);
            randY = r.nextInt(MAX_GRID_Y) * BLOCK_SIZE + (BLOCK_SIZE / 2);
            this.food.setX(randX);
            this.food.setY(randY);

            for (int i = 0; i < snake.getBlockList().size(); i++) {

                //if they are at the same location -> the location is NOT valid
                if( Block.checkContact(food, snake.getBlockList().get(i)) ){
                    isNotValid = true;
                }//end if
            }//end for


        } while(isNotValid);

        this.food.setX(randX);
        this.food.setY(randY);
    }

    /**@Override
     * setFood - changes location of the food block on the board
     */
    public void setFood(){
        Random r = new Random();
        int randX = r.nextInt(MAX_GRID_X) * BLOCK_SIZE + (BLOCK_SIZE / 2);
        int randY = r.nextInt(MAX_GRID_Y) * BLOCK_SIZE + (BLOCK_SIZE / 2);
        this.food.setX(randX);
        this.food.setY(randY);
    }

    //=====GETTERS=====\\
    public int getSize(){
        return this.size;
    }

    public int getHeight(){
        return this.height;
    }

    public int getWidth(){
        return this.width;
    }

    public int getMAX_GRID_X(){
        return this.MAX_GRID_X;
    }

    public int getMAX_GRID_Y(){
        return this.MAX_GRID_Y;
    }

    public int getBLOCK_SIZE(){
        return this.BLOCK_SIZE;
    }

    public Block getFood(){
        return this.food;
    }

    public int getScore(){
        return this.score;
    }

    //=====SETTERS=====\\
    public void setSize(int newSize){
        this.size = newSize;
    }

    public void setWidth(int newWidth){
        this.width = newWidth;
    }

    public void setHeight(int newHeight){
        this.height = newHeight;
    }

    public void setScore(int newScore){
        this.score = newScore;
    }
}
