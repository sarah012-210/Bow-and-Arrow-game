package Task;

import processing.core.*;
class Arrow extends PApplet  {
    private final PApplet parent;
    private final PImage img;
    protected float xPos,yPos;
    protected boolean isFired;
    public Arrow(PApplet parent,PImage img) {
        this.parent = parent;
        this.img = img;
        this.isFired = false;
    }
    public void fire(float startX, float startY) {
        this.xPos = startX;
        this.yPos = startY;
        this.isFired = true;
    }
    public void drawArrow() {
        if (isFired) {
            img.resize(100, 50);
            parent.image(img, xPos, yPos);
            xPos += 8;
        }
    }
}