import sas.*;
import java.awt.Color;

public class Raumschiff
{
    Picture raumschiff;
    
    
    Raumschiff(int pX,int pY)
    {
        raumschiff = new Picture(pX,pY,120,120,"rakete.png");
        
        
        
        
        
    }
    
    void bewegeRaumschiff(int bX)
    {
        raumschiff.move(bX,0);
    }
}
