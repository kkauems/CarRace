package simuladorCorrida;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;

public abstract class Veiculo extends Rectangle{

    private int id;
    private final int quantidadeRodas = 4;
    private Roda[] roda = new Roda[getQuantidadeRodas()];
    private boolean mover = false;
    private static BufferedImage image; 
    
    public Veiculo(int x,int y){
    	super(x,y,32,32);
    	generateId();
        generateRoda();
        generateImage();
    }
   
    public void generateId(){
        Random r = new Random();
        id = r.nextInt(100)+1;
    }
    
    public void generateRoda(){
        for(int i=0;i<getQuantidadeRodas();i++){
            roda[i] = new Roda();
        }
    }

    public void generateImage() {
    	try {
            File f = new File("./Source/carroP.jpeg");
            image = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
   
    public abstract void mover();
    
    public void esvaziar(int id) {
    	roda[id].setcalibragemPneu(false);
    }
    
    public void calibrar(int id){
    	roda[id].setcalibragemPneu(true);
    }

    public abstract int getMov();
    
    public int getId() {
    	return id;
    }

    public Roda[] getRodas(){
        return roda;
    }
    
    public int getQuantidadeRodas() {
    	return this.quantidadeRodas;
    }

    public boolean getCalibragemRoda(int i){
        return roda[i].getcalibragemPneu();
    }
	
    public BufferedImage getImage() {
		return image;
	}

    public boolean getMover() {
    	return mover;
    }
    
    public void setMover(boolean mover){
    	this.mover = mover;
    }
    
	public String toString() {
		return "O veiculo "+getId()+" percorreu "+x+"km, tem "+getQuantidadeRodas()+
        " rodas e os pneus estao: " +Arrays.toString(getRodas())+".";
	}

}