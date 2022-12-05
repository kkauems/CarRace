package simuladorCorrida;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Motocicleta extends veiculoMotorizado implements ipva{

    private final int quantidadeRodas = 2;
    private static BufferedImage image;
    private final int mov = 3;

    public Motocicleta(int x, int y) {
        super(x,y);
        calculaIpva();
    }

    public double calculaIpva(){
        return base*cte_moto;
    }
    
    @Override
    public void generateImage() {
    	try {
            File f = new File("./Source/moto.jpeg");
			Motocicleta.image = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public BufferedImage getImage() {
		return image;
	}

    public int getQuantidadeRodas(){
        return this.quantidadeRodas;
    }

    public int getMov(){
        return mov;
    }

    public void mover(){
        int cont=0;
        if(getGasolina()>0.25 && getIPVA()){
            for(int i=0;i<getQuantidadeRodas();i++){
                if(getCalibragemRoda(i)){
                   cont++;
                }
            }
            if(cont==getQuantidadeRodas()){
                setMover(true);
            }
            if(getMover()==true){
                setGasolina(getGasolina()-0.25);
            }
        }
    }

}
