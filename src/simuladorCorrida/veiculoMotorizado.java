package simuladorCorrida;

import java.util.Random;

public abstract class veiculoMotorizado extends Veiculo{

    private double gasolina = 2.5;
    private boolean ipva;
        
    public veiculoMotorizado(int x, int y){
        super(x,y);
        ipvaPago();
    }

    public double getGasolina(){
        return gasolina;
    }

    public void setGasolina(double gasolina) {
    	this.gasolina = gasolina;
    }

    public void abastecer(double gasolina){
        setGasolina(gasolina);
    }

    public void ipvaPago(){
        Random r = new Random();
        ipva = r.nextBoolean();
    }

    public boolean getIPVA() {
    	return ipva;
    }
    
    public void setIPVA(boolean ipva){
    	 this.ipva = ipva;
    }

    public abstract void mover();

    @Override
	public String toString() {
		if(getIPVA()) {
		return super.toString()+" Alem disso, o veiculo esta com o ipva pago e tem "+getGasolina()
				+"l de gasolina.";
		}
		return super.toString()+" Alem disso, o veiculo nao esta com o ipva pago e tem "+getGasolina()
				+"l de gasolina.";
	}
}
