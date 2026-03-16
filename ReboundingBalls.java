import processing.core.PApplet;

public class ReboundingBalls extends PApplet {
    int x, y, lives, score, gamestate;
    float ballSpeedY;
    float ballSpeedX;

    public static void main(String[] args) {
        PApplet.main("ReboundingBalls");
    }

    public void settings() {
        size(600, 600);
    }

    public void setup() {
        y = 0;
        x = (int) random(10, 575);
        lives = 3;
        score = 0;
        gamestate = 0;
        ballSpeedY = 15;
        ballSpeedX = random(-7, 7);
    }

    public void draw() {
        background(50);

        if (gamestate == 0) {
            fill(255);
            textSize(38);
            text("Welcome to Bouncing Balls", 25, 250);
            textSize(28);
            text("...Press Space to Begin...", 20, 300);
        } else if (gamestate == 1) {
            if (lives > 0) {
                fill(255);
                textSize(25);
                text("Lives: " + lives, 480, 575);
                textSize(100);
                text(score, 275, 300);

                float constrainedX = constrain(mouseX, 0, width - 160);
                float constrainedY = constrain(mouseY, height / 2, height - 15);
                fill(255);
                rect(constrainedX, constrainedY, 160, 15);

                fill( 0);
                ellipse(x + 25, y + 25, 50, 50);

                y += ballSpeedY;
                x += ballSpeedX;

                if (y + 25 >= constrainedY && y <= constrainedY + 15 && x >= constrainedX && x <= constrainedX + 160) {
                    ballSpeedY = -ballSpeedY;
                    ballSpeedX = random(-7, 7);
                    y = (int) (constrainedY - 50);
                    score++;
                }

                if (y <= 0) {
                    ballSpeedY = -ballSpeedY;
                    y = 0;
                }

                if (y > height) {
                    y = 0;
                    lives--;
                    x = (int) random(10, width - 50);
                }

                if (x <= 0 || x >= width - 50) {
                    ballSpeedX = -ballSpeedX;
                }
            } else {
                fill(255);
                textSize(38);
                text("GAME OVER! Press 'R' to Restart", 20, 200);
                textSize(30);
                text("Your Score: " + score, 200, 250);
            }
        }
    }

    public void keyPressed() {
        if (key == ' ' && gamestate == 0) {
            gamestate = 1;
        } else if (key == 'r' || key == 'R') {
            resetGame();
        }
    }

    public void resetGame() {
        lives = 3;
        score = 0;
        gamestate = 1;
        y = 0;
        x = (int) random(10, 575);
        ballSpeedY = 12;
        ballSpeedX = random(-7, 7);
    }
}