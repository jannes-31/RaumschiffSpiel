import sas.*;
import java.awt.Color;

public class Spiel
{
    View fenster;

    public static void main(String[] args)
    {
        new Spiel();
    }

    Spiel()
    {
        fenster = new View(500, 1000, "Raumschiff-Spiel");
        fenster.setBackgroundColor(Color.black);
        while (true)
        {
            spielen();
        }
    }

    void spielen()
    {
        int punkte          = 0;
        int leben           = 3;
        int geschwindigkeit = 3;
        int schussCooldown  = 0;
        int frameZaehler    = 0;

        Picture raumschiff = new Picture(190, 850, 120, 120, "rakete.png");

        Picture[] asteroiden = new Picture[5];
        for (int i = 0; i < asteroiden.length; i++)
        {
            asteroiden[i] = new Picture(
                Tools.randomNumber(0, 450), -i * 200 - 100, 50, 50, "asteroid.png");
        }

        Rectangle[] schuesse = new Rectangle[3];
        for (int s = 0; s < schuesse.length; s++)
        {
            schuesse[s] = new Rectangle(0, 0, 5, 15, Color.yellow);
            schuesse[s].setHidden(true);
        }

        Text punkteAnzeige = new Text(10, 30, "Punkte: 0", Color.white);
        Text lebenAnzeige  = new Text(10, 60, "Leben:  3", Color.white);

        while (leben > 0)
        {
            // Raumschiff steuern
            if (fenster.keyLeftPressed() && raumschiff.getShapeX() >= 5)
                raumschiff.move(-5, 0);
            if (fenster.keyRightPressed() && raumschiff.getShapeX() <= 375)
                raumschiff.move(5, 0);

            // Schuss abfeuern (Leertaste)
            if (fenster.keyPressed(' ') && schussCooldown == 0)
            {
                for (int s = 0; s < schuesse.length; s++)
                {
                    if (schuesse[s].getHidden())
                    {
                        schuesse[s].moveTo(raumschiff.getShapeX() + 58, raumschiff.getShapeY());
                        schuesse[s].setHidden(false);
                        schussCooldown = 20;
                        break;
                    }
                }
            }
            if (schussCooldown > 0) schussCooldown--;

            // Schuesse bewegen und pruefen
            for (int s = 0; s < schuesse.length; s++)
            {
                if (!schuesse[s].getHidden())
                {
                    schuesse[s].move(0, -10);
                    if (schuesse[s].getShapeY() < 0)
                    {
                        schuesse[s].setHidden(true);
                    }
                    else
                    {
                        for (int a = 0; a < asteroiden.length; a++)
                        {
                            if (schuesse[s].intersects(asteroiden[a]))
                            {
                                schuesse[s].setHidden(true);
                                asteroiden[a].moveTo(Tools.randomNumber(0, 450), -50);
                                punkte += 2;
                                punkteAnzeige.setText("Punkte: " + punkte);
                                break;
                            }
                        }
                    }
                }
            }

            // Asteroiden bewegen
            for (int a = 0; a < asteroiden.length; a++)
            {
                asteroiden[a].move(0, geschwindigkeit);

                if (asteroiden[a].getShapeY() > 1000)
                {
                    asteroiden[a].moveTo(Tools.randomNumber(0, 450), -50);
                    punkte++;
                    punkteAnzeige.setText("Punkte: " + punkte);
                }

                if (raumschiff.intersects(asteroiden[a]))
                {
                    leben--;
                    lebenAnzeige.setText("Leben:  " + leben);
                    asteroiden[a].moveTo(Tools.randomNumber(0, 450), -50);
                    if (leben <= 0) break;
                }
            }

            // Schwierigkeit alle ~5 Sekunden erhoehen
            frameZaehler++;
            if (frameZaehler % 300 == 0 && geschwindigkeit < 12)
                geschwindigkeit++;

            try { Thread.sleep(16); } catch (InterruptedException e) {}
        }

        // Alle Spielobjekte entfernen
        fenster.remove(raumschiff);
        for (int i = 0; i < asteroiden.length; i++)
            fenster.remove(asteroiden[i]);
        for (int s = 0; s < schuesse.length; s++)
            fenster.remove(schuesse[s]);
        fenster.remove(punkteAnzeige);
        fenster.remove(lebenAnzeige);

        // Game Over Screen
        Text gameOverText  = new Text(95,  430, "GAME OVER!", Color.red);
        gameOverText.setFontSansSerif(true, 48);
        Text punkteEndText = new Text(150, 510, "Punkte: " + punkte, Color.white);
        punkteEndText.setFontSansSerif(false, 30);
        Text restartText   = new Text(110, 580, "Enter = Neustart", Color.white);
        restartText.setFontSansSerif(false, 24);

        fenster.keyBufferDelete();
        while (!fenster.keyEnterPressed())
        {
            try { Thread.sleep(16); } catch (InterruptedException e) {}
        }

        fenster.remove(gameOverText);
        fenster.remove(punkteEndText);
        fenster.remove(restartText);
    }
}
