import sas.*;
import java.awt.Color;

class Spiel
{
    View fenster;
    Raumschiff raumschiff;
    Asteroiden[] asteroiden;
    Text punkteAnzeige;
    Text lebenAnzeige;

    int punkte = 0;
    int leben  = 3;

    Spiel()
    {
        fenster = new View(500, 1000, "Raumschiff-Spiel");
        fenster.setBackgroundColor(Color.black);

        raumschiff = new Raumschiff(190, 850);

        asteroiden = new Asteroiden[5];
        for (int i = 0; i < asteroiden.length; i++)
        {
            int startX = Tools.randomNumber(0, 450);
            int startY = -i * 200 - 100;
            asteroiden[i] = new Asteroiden(startX, startY);
        }

        punkteAnzeige = new Text(10, 30, "Punkte: 0", Color.white);
        lebenAnzeige  = new Text(10, 60, "Leben:  3", Color.white);

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
                    int neuesX = Tools.randomNumber(0, 450);
                    asteroiden[a].setzePosition(neuesX, -50);
                    punkte++;
                    punkteAnzeige.setText("Punkte: " + punkte);
                }

                if (raumschiff.bild.intersects(asteroiden[a].bild))
                {
                    leben--;
                    lebenAnzeige.setText("Leben:  " + leben);
                    int neuesX = Tools.randomNumber(0, 450);
                    asteroiden[a].setzePosition(neuesX, -50);
                }
            }

            try
            {
                Thread.sleep(16);
            }
            catch (InterruptedException e) {}
        }

        Tools.message("GAME OVER!\nEndpunkte: " + punkte, "Spiel beendet");
    }
}
