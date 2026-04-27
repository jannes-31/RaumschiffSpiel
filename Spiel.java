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
        
        raumschiff = new Raumschiff(190,850);
        
        asteroiden = new Asteroiden[10];

        
    while(true)
    {
        
        
        if(fenster.keyLeftPressed())
        {
            raumschiff.bewegeRaumschiff(-5);
        }
        
        if(fenster.keyRightPressed())
        {
            raumschiff.bewegeRaumschiff(5);
        }
        
        asteroiden[i]= new Asteroiden(zX);
            
        for(int a = 1; a <= i; a++)
            {
             asteroiden[a].bewegeAsteroiden(1);
             
             
            
                 
            
            }  
        
        asteroiden[i].bewegeAsteroiden(20);    
    }
    }
}
