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

        Raumschiff raumschiff = new Raumschiff(190, 850);

        Asteroiden[] asteroiden = new Asteroiden[5];
        for (int i = 0; i < asteroiden.length; i++)
        {
            int startX = Tools.randomNumber(0, 450);
            int startY = -i * 200 - 100;
            asteroiden[i] = new Asteroiden(startX, startY);
        }

        Schuss[] schuesse = new Schuss[3];

        Text punkteAnzeige = new Text(10, 30, "Punkte: 0", Color.white);
        Text lebenAnzeige  = new Text(10, 60, "Leben:  3", Color.white);

        while (leben > 0)
        {
            // Raumschiff steuern
            if (fenster.keyLeftPressed() && raumschiff.bild.getShapeX() >= 5)
                raumschiff.bewegeRaumschiff(-5);
            if (fenster.keyRightPressed() && raumschiff.bild.getShapeX() <= 375)
                raumschiff.bewegeRaumschiff(5);

            // Schuss abfeuern (Leertaste) - zentriert zum Raumschiff
            if (fenster.keyPressed(' ') && schussCooldown == 0)
            {
                for (int s = 0; s < schuesse.length; s++)
                {
                    if (schuesse[s] == null || !schuesse[s].aktiv)
                    {
                        double schussX = raumschiff.bild.getShapeX() + 58;
                        double schussY = raumschiff.bild.getShapeY();
                        schuesse[s] = new Schuss(schussX, schussY);
                        schussCooldown = 20;
                        break;
                    }
                }
            }
            if (schussCooldown > 0) schussCooldown--;

            // Schuesse bewegen und pruefen
            for (int s = 0; s < schuesse.length; s++)
            {
                if (schuesse[s] != null && schuesse[s].aktiv)
                {
                    schuesse[s].bewegeSchuss();
                    if (schuesse[s].getY() < 0)
                    {
                        schuesse[s].deaktiviere(fenster);
                    }
                    else
                    {
                        for (int a = 0; a < asteroiden.length; a++)
                        {
                            if (schuesse[s].bild.intersects(asteroiden[a].bild))
                            {
                                schuesse[s].deaktiviere(fenster);
                                int neuesX = Tools.randomNumber(0, 450);
                                asteroiden[a].setzePosition(neuesX, -50);
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
                asteroiden[a].bewegeAsteroiden(geschwindigkeit);

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
        fenster.remove(raumschiff.bild);
        for (int i = 0; i < asteroiden.length; i++)
            fenster.remove(asteroiden[i].bild);
        for (int s = 0; s < schuesse.length; s++)
            if (schuesse[s] != null && schuesse[s].aktiv)
                schuesse[s].deaktiviere(fenster);
        fenster.remove(punkteAnzeige);
        fenster.remove(lebenAnzeige);

        // Game Over Screen mit SaS
        Text gameOverText  = new Text(95,  430, "GAME OVER!", Color.red);
        gameOverText.setFontSansSerif(true, 48);
        Text punkteEndText = new Text(150, 510, "Punkte: " + punkte, Color.white);
        punkteEndText.setFontSansSerif(false, 30);
        Text restartText   = new Text(110, 580, "Enter = Neustart", Color.white);
        restartText.setFontSansSerif(false, 24);

        // Auf Enter warten
        fenster.keyBufferDelete();
        while (!fenster.keyEnterPressed())
        {
            try { Thread.sleep(16); } catch (InterruptedException e) {}
        }

        // Game Over Screen entfernen fuer Neustart
        fenster.remove(gameOverText);
        fenster.remove(punkteEndText);
        fenster.remove(restartText);
    }
}
