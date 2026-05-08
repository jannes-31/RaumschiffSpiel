import sas.*;

public class Raumschiff
{
    private Picture raumschiff;
    static final int BREITE = 120;
    static final int HOEHE  = 120;

    Raumschiff(int pX, int pY)
    {
        raumschiff = new Picture(pX, pY, BREITE, HOEHE, "rakete.png");
    }

    void bewegeRaumschiff(int bX)
    {
        double neuesX = raumschiff.getShapeX() + bX;
        if (neuesX >= 0 && neuesX <= 380)
        {
            raumschiff.move(bX, 0);
        }
    }

    Picture getShape()
    {
        return raumschiff;
    }
}
