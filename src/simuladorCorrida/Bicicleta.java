package simuladorCorrida;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bicicleta extends Veiculo{

    private static BufferedImage image;
    private final int quantidadeRodas = 2;
    private final int mov = 2;

    public Bicicleta(int x, int y) {
        super(x,y);
    }

    @Override
    public void generateImage() {
    	try {
            File f = new File("./Source/bicicleta.jpeg");
			Bicicleta.image = ImageIO.read(f);
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
        for(int i=0;i<getQuantidadeRodas();i++){
            if(getCalibragemRoda(i)){
                cont++;
            }
        }
        if(cont==getQuantidadeRodas()){
            setMover(true);
        }
    }

}
