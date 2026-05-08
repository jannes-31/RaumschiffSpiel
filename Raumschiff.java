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
        int neuesX = (int) bild.getShapeX() + bX;
        if (neuesX >= 0 && neuesX <= 380)
        {
            bild.move(bX, 0);
        }
    }
}
