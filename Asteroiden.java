import sas.*;

public class Asteroiden
{
    Picture bild;

    Asteroiden(int pX, int pY)
    {
        bild = new Picture(pX, pY, 50, 50, "asteroid.png");
    }

    int bewege(int geschwindigkeit)
    {
        bild.move(0, geschwindigkeit);
        if (bild.getShapeY() > 1000)
        {
            respawn();
            return 1;
        }
        return 0;
    }

    boolean kollision(Picture anderes)
    {
        if (bild.intersects(anderes))
        {
            respawn();
            return true;
        }
        return false;
    }

    void respawn()
    {
        bild.moveTo(Tools.randomNumber(0, 450), -50);
    }
}
