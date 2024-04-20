package Task;


import processing.core.*;
import ddf.minim.*;

public class Main extends PApplet {
private Minim minim;
private AudioPlayer player,player2,player3,player4,player5,player6;
    private boolean flag = true;
    private boolean flagR=false;
    private int score=0;
    private PImage balloonImg1;
    private PImage startBack, scoreBack, gameOver, morning, sunset, youLose, level1;
    private chr a;
    private boolean readyToFire = false;
    private int firedFlag = 0;
    private int firedArrows=0;
    private int boomBalloons=0;
    private final int totNumOfArrows=20;
    private final Arrow[] b = new Arrow[totNumOfArrows];
    private Balloon c;
    public void settings() {
        //size(1000, 800);
        fullScreen();
    }

    public void setup() {
        minim=new Minim(this);
        player=minim.loadFile("D:/java projects/Processing/sss.mp3");
        player2=minim.loadFile("D:/java projects/Processing/weapon-arrow-shot-01-153370 (1).mp3");
        player3=minim.loadFile("D:/java projects/Processing/balloon-pop-2-190091.mp3");
        player4=minim.loadFile("D:/java projects/Processing/WhatsApp Audio 2024-04-20 at 01.02.39_3d887baa.mp3");
        player5=minim.loadFile("D:/java projects/Processing/you-win-sequence-2-183949.mp3");
        player6=minim.loadFile("D:/java projects/Processing/crowd-cheer-ii-6263.mp3");
        PImage archerImg = loadImage("D:/java projects/Processing/green1.png");
        PImage archerImg2 = loadImage("D:/java projects/Processing/green2.png");
        PImage archerImg3 = loadImage("D:/java projects/Processing/green3.png");
        PImage arrowImg = loadImage("D:/java projects/Processing/arrow2.png");
        PImage balloonImg2 = loadImage("D:/java projects/Processing/boom.png");
        PImage balloonImg3 = loadImage("D:/java projects/Processing/yellow.png");
        balloonImg1 = loadImage("D:/java projects/Processing/redBalloon.png");
        startBack = loadImage("D:/java projects/Processing/backgroundPurple.jpg");
        scoreBack = loadImage("D:/java projects/Processing/citySkyline.png");
        gameOver = loadImage("D:/java projects/Processing/gameOver.jpeg");
        morning = loadImage("D:/java projects/Processing/morning.jpg");
        sunset = loadImage("D:/java projects/Processing/sunset.jpg");
        youLose = loadImage("D:/java projects/Processing/youlose.png");
        level1=loadImage("D:/java projects/Processing/level1.jpg");
        a = new chr(this, archerImg, archerImg2, archerImg3);

        for (int i = 0; i < b.length; i++) {
            b[i] = new Arrow(this, arrowImg);
        }
        c = new Balloon(this, balloonImg1, balloonImg2, balloonImg3,15);
        c.setNoOfBalloon();
        c.positionX();
        c.randomPositions();
    }

    public void draw() {
        startBack.resize(width,height);
        background(startBack);
        textSize(56);
        text("START GAME",((float) width /2)-150, ((float) height /2)+100);

        if(flagR){

            if (flag && !(b[totNumOfArrows-1].xPos-30>=width) && boomBalloons < c.noOfBalloon) {

                morning.resize(width,height);
                background(morning);
                textSize(32);
                text("Score: " + score, ((float) width / 2) - 50, 50);
                textSize(32);
                text(20- firedArrows + " arrows left..", ((float) width / 2) - 80, 80);

                a.display();
                for (Arrow arrow : b) {
                    
                    arrow.drawArrow();

                    for (int i = 0; i <= c.noOfBalloon; i++) {
                        if ((arrow.xPos + 30 >= c.xPosition.get(i) - 3 && arrow.xPos + 30 <= c.xPosition.get(i) + 10) && (arrow.yPos >= c.positionY - 25 && arrow.yPos <= c.positionY + 15)) {
                            player3.pause();
                            player3.rewind();
                            player3.play();
                            c.addBoom(i);
                            boomBalloons++;
                            score+=100;
                        }
                    }
                }
                for(Arrow arrow1:b) {
                    if(!arrow1.isFired&&boomBalloons==c.noOfBalloon){
                        score+=300;
                    }

                }
                c.displayBoom();
                c.GetFlag(flag);
                c.displayBalloon();

            }
            else if(boomBalloons == c.noOfBalloon && flag){
                level1.resize(width,height);
                background(level1);
                textSize(32);
                text("Score: " + score, ((float) width / 2) - 50, 50);
                player5.play();

                textSize(56);
                text("UP TO LVL 2", ((float) width / 2) - 180, ((float) height / 2) );
                textSize(36);
                text("Click the SPACE KEY to continue ^_^", ((float) width / 2) - 280, ((float) height / 2) +70 );
                if(keyCode== 32){
                    firedArrows=0;
                    boomBalloons = 0;
                    for(int i=0; i<=c.noOfBalloon; i++){
                        c.balloonList.add(i,balloonImg1);
                    }
                    for(int i=0; i<totNumOfArrows; i++){
                        b[i].isFired=false;
                    }

                    flag=false;
                }}
            else if(!flag && !(b[totNumOfArrows-1].xPos-30>=width) && !(boomBalloons == c.noOfBalloon)) {
                sunset.resize(width,height);
                background(sunset);
                textSize(32);
                text("Score: " + score, ((float) width / 2) - 50, 50);
                textSize(32);
                text(20- firedArrows + " arrows left..", ((float) width / 2) - 80, 80);

                a.display();
                for (Arrow arrow : b) {
                    arrow.drawArrow();

                    for (int i = 0; i <= c.noOfBalloon; i++) {
                        if ((arrow.xPos+30 >= c.randomXPosition.get(i) - 3 && arrow.xPos+30 <= c.randomXPosition.get(i) + 10) && (arrow.yPos >= c.randomYPosition.get(i) - 25 && arrow.yPos <= c.randomYPosition.get(i) + 15)) {
                            player3.pause();
                            player3.rewind();
                            player3.play();
                            c.addBoomR(i);
                            boomBalloons++;
                            score+=100;
                        }
                    }
                    for (int i = 0; i <= 3; i++) {
                        if ((arrow.xPos+30 >= c.randomYPositionX.get(i) - 3 && arrow.xPos+30 <= c.randomYPositionX.get(i) + 10) && (arrow.yPos >= c.randomYPositionY.get(i) - 25 && arrow.yPos <= c.randomYPositionY.get(i) + 15)) {
                            player4.pause();
                            player4.rewind();
                            player4.play();
                            c.addBoomY(i);

                            score+=200;

                        }
                    }
                }
                c.displayBoomR();
                c.GetFlag(flag);
                c.displayRandomBalloons();

            }
            else if(boomBalloons == c.noOfBalloon){
                player6.play();
                scoreBack.resize(width,height);
                background(scoreBack);
                textSize(56);
                text("Score: " + score, ((float) width / 2) - 150, (float) height /2);
                textSize(56);
                text("YOU WON", ((float) width / 2) -120, ((float) height / 2) + 100);

                noLoop();
            }
            else {
                player.play();
                if (flag) {
                    
                    youLose.resize(width,height);
                    background(youLose);
                } else {
                    gameOver.resize(width,height);
                    background(gameOver);
                }
                textSize(32);
                text("Score: " + score, ((float) width / 2) - 50, 50);
                noLoop();
            }
        }

    }
    public void mousePressed () {

        if (mouseButton == LEFT&&flagR) {
            if (firedFlag == 0) {
                readyToFire = true;
                firedFlag = 1;
            }
        } else if (mouseButton == RIGHT && firedFlag == 1) {

            firedFlag = 0;
        }
        if((mouseX<=(width/2)-10&&mouseX<=(width/2)+10)&&(mouseY>(height/2)-10&&mouseY>=(height/2)+10)){
            flagR=true;
        }
    }
    public void mouseReleased () {
        if (readyToFire && totNumOfArrows > firedArrows) {

            for (Arrow arrow : b) {

                if (!arrow.isFired) {
                    player2.pause();
                    player2.rewind();
                    player2.play();
                    arrow.fire(100, mouseY + 40);
                    firedArrows++;
                    break;
                }
            }
            readyToFire = false;
        }

    }

    public static void main(String[] args) {
        PApplet.main("Task.Main");
    }
}
