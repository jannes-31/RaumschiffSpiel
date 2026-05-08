import sas.*;

public class Asteroiden
{
    Picture bild;
    int groesse = 50;

    Asteroiden(int pX, int pY)
    {
        bild = new Picture(pX, pY, groesse, groesse, "asteroid.png");
    }

    void bewegeAsteroiden(int pY)
    {
        bild.move(0, pY);
    }

    void setzePosition(int neuesX, int neuesY)
    {
        bild.moveTo(neuesX, neuesY);
    }

    double getY()
    {
        return bild.getShapeY();
    }
}
