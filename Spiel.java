import sas.*;
import java.awt.Color;

class Spiel
{
    View fenster;
    Raumschiff raumschiff;
    Asteroiden[] asteroiden;

    int punkte = 0;
    int leben = 3;

    Spiel()
    {
        fenster = new View(500, 1000);
        fenster.setBackgroundColor(Color.black);

        raumschiff = new Raumschiff(190, 850);

        asteroiden = new Asteroiden[5];
        for (int i = 0; i < asteroiden.length; i++)
        {
            int startX = (int)(Math.random() * 450);
            int startY = -i * 200 - 100;
            asteroiden[i] = new Asteroiden(startX, startY);
        }

        while (leben > 0)
        {
            if (fenster.keyLeftPressed())
            {
                raumschiff.bewegeRaumschiff(-5);
            }
            if (fenster.keyRightPressed())
            {
                raumschiff.bewegeRaumschiff(5);
            }

            for (int a = 0; a < asteroiden.length; a++)
            {
                asteroiden[a].bewegeAsteroiden(3);

                if (asteroiden[a].getY() > 1000)
                {
                    int neuesX = (int)(Math.random() * 450);
                    asteroiden[a].setzePosition(neuesX, -50);
                    punkte++;
                    System.out.println("Punkte: " + punkte + "  Leben: " + leben);
                }

                if (kollision(raumschiff, asteroiden[a]))
                {
                    leben--;
                    System.out.println("Treffer! Leben: " + leben + "  Punkte: " + punkte);
                    int neuesX = (int)(Math.random() * 450);
                    asteroiden[a].setzePosition(neuesX, -50);
                }
            }

            try
            {
                Thread.sleep(16);
            }
            catch (InterruptedException e) {}
        }

        System.out.println("GAME OVER! Endpunkte: " + punkte);
    }

    boolean kollision(Raumschiff r, Asteroiden a)
    {
        return r.getX() < a.getX() + Asteroiden.GROESSE &&
               r.getX() + Raumschiff.BREITE > a.getX() &&
               r.getY() < a.getY() + Asteroiden.GROESSE &&
               r.getY() + Raumschiff.HOEHE > a.getY();
    }
}
