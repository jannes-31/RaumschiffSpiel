import sas.*;

public class Raumschiff
{
    Picture bild;
    int breite = 120;
    int hoehe  = 120;

    Raumschiff(int pX, int pY)
    {
        bild = new Picture(pX, pY, breite, hoehe, "rakete.png");
    }

    void bewegeRaumschiff(int bX)
    {
        bild.move(bX, 0);
    }
}
