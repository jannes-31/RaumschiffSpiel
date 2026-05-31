import sas.*;
import java.awt.Color;

public class Schuss
{
    Rectangle bild;
    private boolean aktiv;

    Schuss()
    {
        bild = new Rectangle(0, 0, 5, 15, Color.yellow);
        bild.setHidden(true);
        aktiv = false;
    }

    void abfeuern(double pX, double pY)
    {
        bild.moveTo(pX, pY);
        bild.setHidden(false);
        aktiv = true;
    }

    boolean istAktiv()
    {
        return aktiv;
    }

    int update(Asteroiden[] asteroiden)
    {
        if (!aktiv) return 0;

        bild.move(0, -10);

        if (bild.getShapeY() < 0)
        {
            deaktiviere();
            return 0;
        }

        for (Asteroiden a : asteroiden)
        {
            if (bild.intersects(a.bild))
            {
                a.respawn();
                deaktiviere();
                return 2;
            }
        }
        return 0;
    }

    private void deaktiviere()
    {
        bild.setHidden(true);
        aktiv = false;
    }
}
