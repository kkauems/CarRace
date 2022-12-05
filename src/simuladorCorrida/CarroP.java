package simuladorCorrida;

public class CarroP extends veiculoMotorizado implements ipva{

    private final int mov = 5;

    public CarroP(int x, int y) {
        super(x, y);
        calculaIpva();
    }

    public double calculaIpva(){
        return base*cte_passeio;
    }

    public int getMov(){
        return mov;
    }

    public void mover(){
        int cont=0;
        if(getGasolina()>0.75 && getIPVA()){
            for(int i=0;i<getQuantidadeRodas();i++){
                if(getCalibragemRoda(i)){
                   cont++;
                }
            }
            if(cont==getQuantidadeRodas()){
                setMover(true);
            }
            if(getMover()){
                setGasolina(getGasolina()-0.75);
            }
        }
    }
    
}
