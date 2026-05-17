import sas.*;
import java.awt.Color;

public class Schuss
{
    Rectangle bild;
    boolean aktiv;

    Schuss(double pX, double pY)
    {
        bild = new Rectangle(pX, pY, 5, 15, Color.yellow);
        aktiv = true;
    }

    void bewegeSchuss()
    {
        bild.move(0, -10);
    }

    double getY()
    {
        return bild.getShapeY();
    }

    void deaktiviere(View fenster)
    {
        fenster.remove(bild);
        aktiv = false;
    }
}
