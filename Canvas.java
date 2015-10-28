import javax.swing.*;
import java.awt.*;

/**
 * Created by buu1 on 22-10-2015.
 * Based on Processing function to simplify drawing
 */
public class Canvas extends JPanel {

    private Graphics2D g2d;
    final static int CORNER = 0;
    final static int CORNERS = 1;
    final static int RADIUS = 2;
    final static int CENTER = 3;

    private int rectangleMode = CORNER;
    private int ellipseMode = CENTER;
    private Color bgColor = Color.white;
    private Color strokeColor = Color.black;
    private Color fillColor = Color.cyan;
    private BasicStroke currentStroke = new BasicStroke();

    public Canvas () {
//        println("double buffered: " + isDoubleBuffered());

    }

    public void setDC(Graphics2D g2d) {
        this.g2d = g2d;
    }

    public void line(int i1, int i2, int i3, int i4) {
        g2d.setColor(strokeColor);
        g2d.drawLine(i1, i2, i3, i4);
    }

    public void rect(int i1, int i2, int i3, int i4) {

        //x,y, width, height
        //CORNER
        switch (rectangleMode) {
            case CORNER:
                g2d.setColor(fillColor);
                g2d.fillRect(i1, i2, i3, i4);
                g2d.setColor(strokeColor);
                g2d.drawRect(i1, i2, i3, i4);
                break;
            case CORNERS:
                g2d.fillRect(i1, i2, i3+i1, i4+i2);
                break;
            default:
                g2d.drawRect(i1, i2, i3, i4);
                break;
        }

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
        currentStroke = new BasicStroke(w);
        g2d.setStroke(currentStroke);

    }

    public void ellipse(int x, int y, int r1, int r2) {
        switch (ellipseMode) {
            case CENTER:
                g2d.fillOval(x, y, r1, r2);
                break;
            default:
                g2d.fillOval(x, y, r1, r2);
                break;
        }

    }

    public void drawImage(Image img, int x, int y, int sizeX, int sizeY) {
        // laatste is voor de imaga observer.
        g2d.drawImage(img, x, y, sizeX, sizeY, null);
    }


}
