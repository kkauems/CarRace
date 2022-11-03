package simuladorCorrida;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;


@SuppressWarnings("serial")
public class Veiculo extends Rectangle implements Serializable{
    private int id;
    private final int quantidadeRodas=4;
    private boolean ipva;
    private double gasolina=2.5;
    private Roda roda[]=new Roda[quantidadeRodas];
    private boolean mover=false;
    private static BufferedImage image;
    
    public Veiculo(int x,int y){
    	super(x,y,32,32);
    	generateId();
    	ipvaPago();
        generateRoda();
        generateImage();
    }
    private void ipvaPago(){
        Random r=new Random();
        ipva=r.nextBoolean();
    }
   
    public void generateId(){
        Random r=new Random();
        id=r.nextInt(100)+1;
 
    }
    public static void generateImage() {
    	try {
			image=ImageIO.read(Veiculo.class.getResource("/Carro.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void generateRoda(){
        for(int i=0;i<quantidadeRodas;i++){
            roda[i]=new Roda();
        }
    }
   
    public void mover(){
        int cont=0;
        if(gasolina>0.55&&ipva==true){
            for(int i=0;i<4;i++){
                if(roda[i].getcalibragemPneu()){
                   cont++;
                }
            }
            if(cont==4){
                mover=true;
            }
            if(mover==true){
                gasolina-=0.55;
            }
        }
    }
    
    public void esvaziar(int id) {
    	roda[id].setcalibragemPneu(false);
    }
    
    public void calibrar(int id){
    	roda[id].setcalibragemPneu(true);
    }
    
    
    public int getId() {
    	return id;
    }
    
    public boolean getIPVA() {
    	return ipva;
    }
    
    public void setIPVA(boolean ipva){
    	 this.ipva=ipva;
    
    }
    
    public void setGasolina(double gasolina) {
    	this.gasolina=gasolina;
    }
    
    public int getQuantidadeRodas() {
    	return quantidadeRodas;
    }
	
    public boolean getMover() {
    	return mover;
    }
    
    public void setMover(boolean mover){
    	this.mover=mover;
    }
	
    public static BufferedImage getImage() {
		return image;
	}
    
	
	@Override
	public String toString() {
		if(ipva) {
		return "veiculo "+id+" percorreu "+x+"km, est� com o ipva pago, tem "+gasolina
				+"l de gasolina, "+quantidadeRodas+" rodas, os pneus est�o: " +Arrays.toString(roda);
		}
		return "veiculo "+id+" percorreu "+x+"km, est� com o ipva  n�o pago, tem "+gasolina
				+"l de gasolina, "+quantidadeRodas+" rodas, os pneus est�o: " +Arrays.toString(roda);
		
	}
}