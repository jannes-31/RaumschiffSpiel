import sas.*;
import java.awt.Color;

class Spiel
{
    View fenster;
    Raumschiff raumschiff;
    Asteroiden[] asteroiden;
    Schuss[] schuesse;
    Text punkteAnzeige;
    Text lebenAnzeige;
    Text tempoAnzeige;

    int punkte        = 0;
    int leben         = 3;
    int geschwindigkeit = 3;
    int schussCooldown  = 0;
    int frameZaehler    = 0;

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

        schuesse = new Schuss[3];

        punkteAnzeige = new Text(10, 30, "Punkte: 0", Color.white);
        lebenAnzeige  = new Text(10, 60, "Leben:  3", Color.white);
        tempoAnzeige  = new Text(10, 90, "Tempo:  1", Color.white);

        while (leben > 0)
        {
            // Raumschiff steuern
            if (fenster.keyLeftPressed() && raumschiff.bild.getShapeX() >= 5)
            {
                raumschiff.bewegeRaumschiff(-5);
            }
            if (fenster.keyRightPressed() && raumschiff.bild.getShapeX() <= 375)
            {
                raumschiff.bewegeRaumschiff(5);
            }

            // Schuss abfeuern (Leertaste)
            if (fenster.keyPressed(' ') && schussCooldown == 0)
            {
                for (int s = 0; s < schuesse.length; s++)
                {
                    if (schuesse[s] == null || !schuesse[s].aktiv)
                    {
                        double schussX = raumschiff.bild.getShapeX() + 57;
                        double schussY = raumschiff.bild.getShapeY();
                        schuesse[s] = new Schuss(schussX, schussY);
                        schussCooldown = 20;
                        break;
                    }
                }
            }
            if (schussCooldown > 0) schussCooldown--;

            // Schuesse bewegen und auf Treffer pruefen
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

            // Schwierigkeit alle ~5 Sekunden erhoehen (300 Frames * 16ms = 4,8s)
            frameZaehler++;
            if (frameZaehler % 300 == 0 && geschwindigkeit < 12)
            {
                geschwindigkeit++;
                tempoAnzeige.setText("Tempo:  " + (geschwindigkeit - 2));
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
