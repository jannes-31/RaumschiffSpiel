import sas.*;

public class Raumschiff
{
    Picture bild;

    Raumschiff(int pX, int pY)
    {
        bild = new Picture(pX, pY, 120, 120, "rakete.png");
    }

    void steuerung(View fenster)
    {
        if (fenster.keyLeftPressed() && bild.getShapeX() >= 5)
            bild.move(-5, 0);
        if (fenster.keyRightPressed() && bild.getShapeX() <= 375)
            bild.move(5, 0);
    }
}
