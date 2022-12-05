package simuladorCorrida;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CarroE extends veiculoMotorizado implements ipva{

    private static BufferedImage image;
    private final int quantidadeRodas = 4;
    private final int mov = 10;

    public CarroE(int x, int y) {
        super(x, y);
        calculaIpva();
    }

    public double calculaIpva(){
        return base*cte_esp;
    }

    @Override
    public void generateImage() {
    	try {
            File f = new File("./Source/carroE.jpeg");
			CarroE.image = ImageIO.read(f);
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
        if(getGasolina()>2.3 && getIPVA()){
            for(int i=0;i<getQuantidadeRodas();i++){
                if(getCalibragemRoda(i)){
                   cont++;
                }
            }
            if(cont==getQuantidadeRodas()){
                setMover(true);
            }
            if(getMover()==true){
                setGasolina(getGasolina()-2.3);
            }
        }
    }
}
