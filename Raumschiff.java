import sas.*;

public class Raumschiff
{
    Picture bild;
    private int schussCooldown = 0;

    Raumschiff(int pX, int pY)
    {
        bild = new Picture(pX, pY, 120, 120, "rakete.png");
    }

    void schiessen(Schuss[] schuesse, View fenster)
    {
        if (schussCooldown > 0) { schussCooldown--; return; }
        if (!fenster.keyPressed(' ')) return;

        for (Schuss s : schuesse)
        {
            if (!s.istAktiv())
            {
                s.abfeuern(bild.getShapeX() + 58, bild.getShapeY());
                schussCooldown = 20;
                return;
            }
        }
    }
}
