package simuladorCorrida;

import java.io.Serializable;
import java.util.Random;

public class Roda implements Serializable {

    private boolean calibragemPneu;
   
    public Roda(){
        generateCalibragemPneu();
    }
    
    public boolean setcalibragemPneu(boolean calibragemPneu){
        this.calibragemPneu=calibragemPneu;
        return calibragemPneu;
    }
    public boolean getcalibragemPneu(){
        return calibragemPneu;
    }
   
    private void generateCalibragemPneu(){
        Random r = new Random();
        calibragemPneu = r.nextBoolean();
    }
	@Override
	public String toString() {
		if(calibragemPneu) {
			return "cheio";
		}
		return "vazio";
	}
	
}
