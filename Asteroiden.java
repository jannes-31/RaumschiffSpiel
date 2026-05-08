import sas.*;

public class Asteroiden
{
    private Picture asteroid;
    static final int GROESSE = 50;

    Asteroiden(int pX, int pY)
    {
        asteroid = new Picture(pX, pY, GROESSE, GROESSE, "asteroid.png");
    }

    void bewegeAsteroiden(int pY)
    {
        asteroid.move(0, pY);
    }

    void setzePosition(int neuesX, int neuesY)
    {
        asteroid.moveTo(neuesX, neuesY);
    }

    double getY()
    {
        return asteroid.getShapeY();
    }

    Picture getShape()
    {
        return asteroid;
    }
}
