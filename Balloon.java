package Task;




import processing.core.*;
import java.util.ArrayList;
public class Balloon {
    private final PApplet parent;
    private final PImage img1,img2,img4;
    protected int noOfBalloon;
    private boolean flag=true;
    protected ArrayList<PImage> balloonList=new ArrayList <>();
    private final ArrayList<PImage> balloonBoom=new ArrayList<>();
    private final ArrayList<Float> BoomX=new ArrayList<>();
    private final ArrayList<Float> BoomY=new ArrayList<>();
    private final ArrayList<PImage> YBalloonList = new ArrayList<>();
    private final float initialPosition;
    protected float positionY;
    private int index;
    private int index1=-1;
    private final float speed;
    protected ArrayList<Float> xPosition = new ArrayList<>();
    protected ArrayList<Float> randomXPosition = new ArrayList<>();
    protected ArrayList<Float> randomYPosition = new ArrayList<>();
    protected ArrayList<Float> randomYPositionY = new ArrayList<>();
    protected ArrayList<Float> randomYPositionX = new ArrayList<>();

    public Balloon(PApplet parent,PImage img1,PImage img2,PImage img4,int noOfBalloon){
        this.parent = parent;
        this.img1 = img1;
        this.initialPosition =parent.height;
        this.positionY=parent.height;
        this.img2 = img2;
        this.noOfBalloon =noOfBalloon;
        this.speed = parent.random(2, 4);
        this.img4 = img4;
    }
    public void setNoOfBalloon(){

        for(int i=0; i<=noOfBalloon; i++){
            balloonList.add(img1);
        }
        for (int i = 0; i<3; i++) {
            YBalloonList.add(img4);
        }

    }
    public void displayBalloon(){

        for(int i=0; i<=noOfBalloon; i++){
            img1.resize(30, 0);
            if(balloonList.get(i)==img1) {
                parent.image(balloonList.get(i),xPosition.get(i),positionY);
            }}
        // positionY--;
        if(positionY<-100){
            positionY= initialPosition;
        }else positionY--;
    }
    public void positionX() {
        for(int i=0; i<=noOfBalloon; i++) {
            xPosition.add(i,(float) (parent.width-30*i));
        }
    }
    public void addBoom(int index) {
        this.index = index;
        balloonList.set(index, img2);
        if(!BoomX.contains(xPosition.get(index)-70)&&!BoomY.contains(positionY)) {
            BoomX.add(xPosition.get(index)-70);
            BoomY.add(positionY);
            balloonBoom.add(img2);
        }


    }
    public void addBoomR(int index) {
        this.index = index;
        balloonList.set(index, img2);
        if(!BoomX.contains(randomXPosition.get(index))&&!BoomY.contains(randomYPosition.get(index))) {
            BoomX.add(randomXPosition.get(index));
            BoomY.add(randomYPosition.get(index));
            balloonBoom.add(img2);
        }


    }
    public void addBoomY(int index) {
        this.index1 = index;
        YBalloonList.set(index, img2);

    }
    public void displayBoom() {
        if(flag) {
            for(int i =0 ;i<balloonBoom.size();i++) {
                img2.resize(200, 0);
                parent.image(img2, BoomX.get(i), BoomY.get(i));
                BoomY.set(i,BoomY.get(i)+1);
            }
        }
        else {
            balloonBoom.clear();
            BoomX.clear();
            BoomY.clear();
            xPosition.clear();
        }
        xPosition.set(index,(float) -2000);}
    public void displayBoomR() {
        if(!flag) {
            for(int i =0 ;i<balloonBoom.size();i++) {
                img2.resize(200, 0);
                parent.image(img2, BoomX.get(i), BoomY.get(i));
                BoomY.set(i,BoomY.get(i)+1);
            }
            randomXPosition.set(index,(float) -2000);
            if(index1!=-1) {
                randomYPositionX.set(index1,(float) -2000);}
        }
        else {
            balloonBoom.clear();
            BoomX.clear();
            BoomY.clear();
        }
    }

    public void randomPositions() {
        for (int i = 0; i <= noOfBalloon; i++) {
            randomXPosition.add(parent.random(200, parent.width - 30));
            randomYPosition.add(parent.random(parent.height, parent.height + 200));
        }
        for (int i = 0; i <= 3; i++) {
            randomYPositionX.add(parent.random(200, parent.width - 30));
            randomYPositionY.add(parent.random(parent.height, parent.height + 200));
        }
    }

    public void displayRandomBalloons() {
        img1.resize(30 , 0);
        for (int i = 0; i <= noOfBalloon; i++) {
            if (balloonList.get(i) == img1) {
                parent.image(img1 , randomXPosition.get(i) , randomYPosition.get(i));
            }
            randomYPosition.set(i , randomYPosition.get(i)-2);
            if (randomYPosition.get(i) <= -100) {
                randomYPosition.set(i , initialPosition);
            }
        }
        img4.resize(30 ,0);
        for (int i = 0; i < 3; i++) {
            if (YBalloonList.get(i) == img4) {
                parent.image(img4 , randomYPositionX.get(i) , randomYPositionY.get(i));
            }
            randomYPositionY.set(i , randomYPositionY.get(i)-speed);
            if (randomYPositionY.get(i) <= -100) {
                randomYPositionY.set(i , initialPosition);
            }
        }
    }
    public void GetFlag(boolean flag) {
        this.flag = flag;
    }

}

