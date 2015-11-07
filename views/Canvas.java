package views;

import javax.swing.*;
import java.awt.*;

/**
 * Created by buu1 on 22-10-2015.
 * Based on Processing function to simplify drawing
 */
public class Canvas extends JPanel {

    public Graphics2D g2d;
    final static int CORNER = 0;
    final static int CORNERS = 1;
    final static int RADIUS = 2;
    final static int CENTER = 3;

    final static int TOP = 4;
    final static int BOTTOM = 5;

    private int rectangleMode = CORNER;
    private int ellipseMode = CENTER;
    private Color bgColor = Color.white;
    private Color strokeColor = Color.black;
    private Color fillColor = Color.cyan;
    private BasicStroke currentStroke = new BasicStroke();
    private boolean fill = true;

    private int horizontalAlign = CENTER;
    private int vericalAlign = CENTER;

    private Font f = new Font("SansSerif", Font.BOLD, 12);



    public Canvas () {


    }

    public void setDC(Graphics2D g2d) {
        this.g2d = g2d;
        g2d.setFont(f);
    }

    public void line(int i1, int i2, int i3, int i4) {
        //beginpoint, eindpoint)
        g2d.setColor(strokeColor);
        g2d.drawLine(i1, i2, i3, i4);
    }


    public void rect(int i1, int i2, int i3, int i4) {

        //x,y, width, height
        //CORNER
        switch (rectangleMode) {
            case CORNER:
                if(fill) {
                    g2d.setColor(fillColor);
                    g2d.fillRect(i1, i2, i3, i4);
                }
                g2d.setColor(strokeColor);
                g2d.drawRect(i1, i2, i3, i4);
                break;
            case CORNERS:
                if(fill) {
                    g2d.setColor(fillColor);
                    g2d.fillRect(i1, i2, i3+i1, i4+i2);
                }
                g2d.setColor(strokeColor);
                g2d.drawRect(i1, i2, i3+i1, i4+i2);
                break;
            default:
                g2d.drawRect(i1, i2, i3, i4);
                break;
        }

    }

    public void rect(float i1, float i2, float i3, float i4) {
        this.rect((int) i1, (int) i2, (int) i3, (int) i4);
    }

    public void background(int r, int g, int b) {
        bgColor = new Color(r,g,b);
        setBackground(bgColor);
    }

    public void stroke (int r, int g, int b){
        strokeColor = new Color(r,g,b);
        g2d.setColor(strokeColor);
    }

    public void stroke (int rgb, float alpha){
        Color tmpColor  = new Color(rgb);
        float r = (float)(tmpColor.getRed()/255.0);
        float g = (float)(tmpColor.getGreen()/255.0);
        float b = (float)(tmpColor.getBlue()/255.0);

        strokeColor = new Color(r,g,b, alpha);
        g2d.setColor(strokeColor);
    }

    public void fill (int r, int g, int b) {
        fillColor = new Color(r,g,b);
        g2d.setColor(fillColor);
        fill =  true;
    }

    public void println (String s){
//        System.out.println(s);
    }

    public void println (int i) {
//        System.out.println(i);
    }

    public void rectMode (int mode) {
        rectangleMode = mode;
    }

    public void ellipseMode (int mode ) {
        ellipseMode = mode;
    }

    public void strokeWeight (float w) {
        //TODO copy van bestaande stroke en dan alleen weight aanpassen.
        currentStroke = new BasicStroke(w, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND);

        g2d.setStroke(currentStroke);

    }

    public void ellipse(int x, int y, int r1, int r2) {
        int dx = (int)(r1*0.5);
        int dy = (int)(r2*0.5);

        switch (ellipseMode) {
            case CENTER:
                if(fill) {
                    g2d.setColor(fillColor);
                    g2d.fillOval(x - dx, y - dy, r1, r2);
                }
                g2d.setColor(strokeColor);
                g2d.drawOval(x-dx, y-dy, r1, r2);
                break;
            default:
                if(fill) {
                    g2d.setColor(fillColor);
                    g2d.fillOval(x, y, r1, r2);
                }
                g2d.setColor(strokeColor);
                g2d.drawOval(x, y, r1, r2);
                break;
        }

    }

    //TODO: Centreren van de tekst gaat nog niet goed.
    public void text(String s, int x, int y) {

        FontMetrics fm = g2d.getFontMetrics();

//        int stringLen = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
//        int stringHeight = (int)g2d.getFontMetrics().getStringBounds(s, g2d).getHeight();
        int stringLen = (int)fm.stringWidth(s);
        int stringHeight = fm.getHeight() ;
//                (int)fm.getAscent() + fm.getDescent();


        int startx = 0;
        int starty = 0;

        if(horizontalAlign == CENTER) {
            startx = -stringLen/2;
        }

        if (vericalAlign == CENTER) {
            starty = stringHeight/2;
        }

        g2d.drawString(s,  x+startx,  y+starty);
       // g2d.drawString(s, x, y);
    }

    public void drawImage(Image img, int x, int y, int sizeX, int sizeY) {
        // laatste is voor de imaga observer.
        g2d.drawImage(img, x, y, sizeX, sizeY, null);
    }

    public void textSize(int size) {
        //TODO set textsize
    }

    public void noFill() {
        this.fill = false;
    }

    public void textAlign(int horAlign, int verAlign) {
        //TODO set alignment of text
        this.horizontalAlign = horAlign;
        this.vericalAlign = verAlign;
    }

}
