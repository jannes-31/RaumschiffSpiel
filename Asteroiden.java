import sas.*;
import java.awt.Color;

public class Asteroiden
{
    Picture asteroid;
    
    
    Asteroiden(int pX)
    {
    asteroid = new Picture(pX,0,50,50,"asteroid.png");
    
        
        
        
        
    }
    
    void bewegeAsteroiden(int pY)
    {
        asteroid.move(0,pY);
        
        
    }
}
