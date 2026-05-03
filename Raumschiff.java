import sas.*;
import java.awt.Color;

public class Raumschiff
{
    Picture raumschiff;
    int x, y;
    static final int BREITE = 120;
    static final int HOEHE = 120;

    Raumschiff(int pX, int pY)
    {
        x = pX;
        y = pY;
        raumschiff = new Picture(x, y, BREITE, HOEHE, "rakete.png");
    }

    void bewegeRaumschiff(int bX)
    {
        int neuesX = x + bX;
        if (neuesX >= 0 && neuesX <= 380)
        {
            x = neuesX;
            raumschiff.move(bX, 0);
        }
    }

    int getX() { return x; }
    int getY() { return y; }
}
