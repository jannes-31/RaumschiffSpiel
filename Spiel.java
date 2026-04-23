 import sas.*;
import java.awt.Color;

class Spiel
{
    View fenster;
    Raumschiff raumschiff;
    Asteroiden[] asteroiden;
    
    int i = 1;
    
    int zX;
    int zGroeße;    
    Spiel()
    {
        fenster = new View(500,1000);
        fenster.setBackgroundColor(Color.black);
        
        raumschiff = new Raumschiff(400,800);
        
        asteroiden = new Asteroiden[5];
        
    while(true)
    {
        
        if(fenster.keyLeftPressed())
        {
            raumschiff.bewegeRaumschiff(-1);
        }
        
        if(fenster.keyRightPressed())
        {
            raumschiff.bewegeRaumschiff(1);
        }
        
        asteroiden[i]= new Asteroiden(10);
            
        for(int a = 1; a < i; a++)
            {
             asteroiden[a].bewegeAsteroiden(1);                 
            }    
        
        asteroiden[i].bewegeAsteroiden(20);    
    }
    }
}