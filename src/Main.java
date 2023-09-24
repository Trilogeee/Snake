import java.awt.*;
import java.util.concurrent.TimeUnit;
import java.awt.event.KeyEvent;

/* TODO
    2. Adding queue data structure for inputs might resolve issue with pause and input not registering. -- **maybe changing how we get key inputs**
    3. IDEA: increase size of board.
    4. Check random location of food so that it is not inside of the snake.
    5. Add winning condition
        a. Add winning screen
    6. *** Add score and timer ***
        a. Display the score without breaking board
    7. Play sounds
 */

public class Main {

    private final static String failSound = "oh_no_no_no.wav";
    private final static String scoreSound = "bruh2-sound.wav";
    //private final static String winSound = "crazy-frog.wav";

    public static void main(String[] args) throws InterruptedException {
        Board board = new Board();
        Snake snake = new Snake(board.getWidth() / 2, board.getHeight() / 2); //sets snake initial position to center of board
        int direction = 0;
        boolean game = false;
        boolean win = false;

        board.setup();
        draw(snake, board);
        //StdDraw.enableDoubleBuffering();

        // waits for user to enter the initial direction
        while (!game) {

            // ends while loop if the player inputs a direction
            if(getDirection(0) != 0){
                direction = getDirection(0);
                game = true;
            }//end if

        }//end while(!game)

        //drawFood(board);
        StdDraw.enableDoubleBuffering();
        // loops game
        while(game) {
            direction = getDirection(direction);

            snake.move(direction);
            draw(snake, board);
            drawFood(board);
            //TimeUnit.MILLISECONDS.sleep(200);
            StdDraw.pause(100);
            //maybe refresh every t time but also gets input in the meantime

          /* CHECK GAME RULES */
            //1) OutOfBounds
            if( snake.getBlockList().get(0).getX() > board.getWidth() || snake.getBlockList().get(0).getY() > board.getHeight() || snake.getBlockList().get(0).getX() < 0 || snake.getBlockList().get(0).getY() < 0){
                game = false;
                win = false;
                System.out.println("Game over.");
                StdAudio.playInBackground(failSound);
            }//end if

            //2) Makes contact with itself
            for(int i = 1; i < snake.getBlockList().size(); i++){
                //if newest block has the same location as any other block in the ArrayList
                if(Block.checkContact(snake.getBlockList().get(0) , snake.getBlockList().get(i))){
                    game = false;
                    win = false;
                    System.out.println("Game over.");
                    StdAudio.playInBackground(failSound);
                }//end if
            }//end loop

            //3) Check if the snake is on the food
            if( Block.checkContact(snake.getHead(), board.getFood()) ){
                snake.eatFood();
                board.setFood(snake);
                drawFood(board);
                board.setScore(board.getScore() + 1);
                StdAudio.playInBackground(scoreSound);
            }//end if

        }//end while(game)

        board.endGame();

    }//end main

    /**
     * draw() - draws everything on the board | updates the board
     * @param snake
     * @param board
     */
    public static void draw(Snake snake, Board board){
        //draw board - does before the loop ;)
        board.drawBoard();

        int red = 255;
        int green = 0;
        int blue = 0;
        int interval = 255 / snake.getBlockList().size();
        //draw snake - loops through each element in the snake's blockList and adds the rectangle to the screen
        for(int i = 0; i < snake.getBlockList().size(); i++){
            //maybe changes color for each block depending on length
            StdDraw.setPenColor(red, green, blue);
            red -= interval;
            green += interval;
            blue += interval;

            //filledRectangle() requires x, y, halfWidth, and halfHeight parameters
            StdDraw.filledRectangle(snake.getBlockList().get(i).getX(), snake.getBlockList().get(i).getY(), (board.getBLOCK_SIZE() / 2.0) - 5, (board.getBLOCK_SIZE() / 2.0) - 5);
        }

        StdDraw.text(snake.getHead().getX(), snake.getHead().getY(), String.valueOf(board.getScore()));
        StdDraw.show();
    }//end method

    /**
     * drawFood() - draws the food as a pink rectangle at the food's location
     * @param board
     */
    public static void drawFood(Board board){
        //Print food before snake so that snake can go on top of it
        //StdDraw.setPenColor(Color.pink);
        //StdDraw.filledRectangle(board.getFood().getX(), board.getFood().getY(), (board.getBLOCK_SIZE() / 2.0), (board.getBLOCK_SIZE() / 2.0) );
        StdDraw.picture(board.getFood().getX(), board.getFood().getY(), "food.PNG", board.getBLOCK_SIZE(), board.getBLOCK_SIZE());
        StdDraw.show();
    }

    /**
     * getDirection() - Returns an integer that corresponds the key input from the user.
     * @Format up = 1, down = 2, left = 3, right = 4. Returns lastDir if no input.
     * @param lastDir - Last direction the snake was moving.
     * @return int direction
     */
    public static int getDirection(int lastDir){
        //lastDir is the last direction

        if( (StdDraw.isKeyPressed(KeyEvent.VK_W) || StdDraw.isKeyPressed(KeyEvent.VK_UP)) && (lastDir != 2) ) {
            System.out.println("direction: up");
            return 1;
        }//end if
        else if( (StdDraw.isKeyPressed(KeyEvent.VK_S) || StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) && (lastDir != 1) ) {
            System.out.println("direction: down");
            return 2;
        }//end else if
        else if( (StdDraw.isKeyPressed(KeyEvent.VK_A) || StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) && (lastDir != 4) ) {
            System.out.println("direction: left");
            return 3;
        }//end else if
        else if( (StdDraw.isKeyPressed(KeyEvent.VK_D) || StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) && (lastDir != 3) ) {
            System.out.println("direction: right");
            return 4;
        }//end else if

        return lastDir;
    }

}
