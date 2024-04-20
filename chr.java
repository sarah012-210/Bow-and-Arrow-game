package Task;


import processing.core.PApplet;
import processing.core.PImage;
public class chr  {
    private  final PApplet parent;
    private final PImage normalImg,reloadArrowImg,afterReloadImg;
    private PImage currentImage;
    private float startPosition=10;
    public chr(PApplet parent,PImage normalImg, PImage reloadArrowImg, PImage afterReloadImg){
        this.parent = parent;
        this.normalImg = normalImg;
        this.reloadArrowImg = reloadArrowImg;
        this.afterReloadImg = afterReloadImg;
        this.currentImage = normalImg;
    }

    public void display() {

        if (parent.mouseButton == parent.RIGHT && startPosition != parent.mouseY) {

            currentImage = normalImg;
            normalImg.resize(180,0);
        } else if (parent.mouseButton == parent.LEFT && parent.mousePressed && startPosition == parent.mouseY) {

            currentImage = reloadArrowImg;
            reloadArrowImg.resize(200, 0);

        }
        else {
            currentImage = afterReloadImg;
            afterReloadImg.resize(250,0);
            if(parent.mouseButton == parent.LEFT&&parent.mousePressed ){
                currentImage = reloadArrowImg;
                reloadArrowImg.resize(200, 0);
                mouseDragged();
            }

        }
        parent.image(currentImage, 0, startPosition);
    }
    public void mouseDragged() {
        startPosition =parent.mouseY;
    }

}


