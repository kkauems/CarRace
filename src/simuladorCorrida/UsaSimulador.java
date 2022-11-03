package simuladorCorrida;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class UsaSimulador extends Canvas implements Runnable {
	private final int size=700;
	private static Simulador simulador;
	
	public UsaSimulador() {
		this.setPreferredSize(new Dimension(size,size));
		simulador=new Simulador();
		simulador.generateFrame();
	}

	private void tick() {
		simulador.tick();
	}
	
	private void render() {
		BufferStrategy bs=this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g=bs.getDrawGraphics();
		g.setColor(Color.white);
		g.fillRect(0,0, size, size);
		simulador.render(g);
		bs.show();
		
		
	}

	public static void main(String[] args) {
		UsaSimulador usaSimulador=new UsaSimulador();
		JFrame frame=new JFrame("Corrida");
		frame.add(usaSimulador);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
		new Thread(usaSimulador).start();
	}

	@Override
	public void run() {
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
