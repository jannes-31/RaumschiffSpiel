import sas.*;
import java.awt.Color;

public class Asteroiden
{
    Picture asteroid;
    int x, y;
    static final int GROESSE = 50;

    Asteroiden(int pX, int pY)
    {
        x = pX;
        y = pY;
        asteroid = new Picture(x, y, GROESSE, GROESSE, "asteroid.png");
    }

    void bewegeAsteroiden(int pY)
    {
        y += pY;
        asteroid.move(0, pY);
    }

    void setzePosition(int neuesX, int neuesY)
    {
        int dx = neuesX - x;
        int dy = neuesY - y;
        x = neuesX;
        y = neuesY;
        asteroid.move(dx, dy);
    }

    int getX() { return x; }
    int getY() { return y; }
}
