package simuladorCorrida;

public interface ipva {
    
    public final double cte_passeio = 1.3;
    public final double cte_moto = 0.75;
    public final double cte_esp = 3.15;
    public final double base = 500;

    public abstract double calculaIpva();

}
