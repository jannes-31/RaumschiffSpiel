import sas.*;
import java.awt.Color;

public class Raumschiff
{
    Rectangle raumschiff;
    
    
    Raumschiff(int pX,int pY)
    {
        raumschiff = new Rectangle(pX,pX,40,80,Color.white);
        
        
        
        
        
    }
    
    void bewegeRaumschiff(int bX)
    {
        raumschiff.move(bX,0);
    }
}