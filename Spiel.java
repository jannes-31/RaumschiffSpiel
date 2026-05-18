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
            asteroiden[i] = new Asteroiden(Tools.randomNumber(0, 450), -i * 200 - 100);

        Schuss[] schuesse = new Schuss[3];
        for (int s = 0; s < schuesse.length; s++)
            schuesse[s] = new Schuss();

        Text punkteAnzeige = new Text(10, 30, "Punkte: 0", Color.white);
        Text lebenAnzeige  = new Text(10, 60, "Leben:  3", Color.white);

        while (leben > 0)
        {
            raumschiff.steuerung(fenster);

            if (fenster.keyPressed(' ') && schussCooldown == 0)
            {
                for (Schuss s : schuesse)
                {
                    if (!s.istAktiv())
                    {
                        s.abfeuern(raumschiff.bild.getShapeX() + 58, raumschiff.bild.getShapeY());
                        schussCooldown = 20;
                        break;
                    }
                }
            }
            if (schussCooldown > 0) schussCooldown--;

            for (Schuss s : schuesse)
                punkte += s.update(asteroiden);

            for (Asteroiden a : asteroiden)
            {
                punkte += a.bewege(geschwindigkeit);
                if (a.kollision(raumschiff.bild))
                    leben--;
            }

            punkteAnzeige.setText("Punkte: " + punkte);
            lebenAnzeige.setText("Leben:  " + leben);

            frameZaehler++;
            if (frameZaehler % 300 == 0 && geschwindigkeit < 12)
                geschwindigkeit++;

            try { Thread.sleep(16); } catch (InterruptedException e) {}
        }

        // Alle Spielobjekte entfernen
        fenster.remove(raumschiff.bild);
        for (Asteroiden a : asteroiden) fenster.remove(a.bild);
        for (Schuss s : schuesse)       fenster.remove(s.bild);
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
            try { Thread.sleep(16); } catch (InterruptedException e) {}

        fenster.remove(gameOverText);
        fenster.remove(punkteEndText);
        fenster.remove(restartText);
    }
}
