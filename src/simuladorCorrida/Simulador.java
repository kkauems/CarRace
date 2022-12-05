package simuladorCorrida;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Simulador{
	private Veiculo veiculo[] = new Veiculo[20];
	private JFrame frame=new JFrame("Menu");
	private JButton[] buttons=new JButton[14];
	private static final int quantMaxVeiculos=20;
	private final int size=700;
	private static final String deskPath = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\";
	private File file=new File(deskPath+"save.txt");
	
	public void generateFrame(){
		int cont=0;
		frame.setSize(size, size);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane();
		generateButtons(buttons);
		
		for(int i=0;i<14;i++){
			if(i%2==0) {
				buttons[i].setBounds(0,size-65-(25*cont),size/2, 25);
			}
			else{
				buttons[i].setBounds(size/2,size-65-(25*cont),size/2, 25);
				cont++;
			}
			buttons[i].setVisible(true);
			frame.add(buttons[i]);		
			buttonsActions(buttons[i]);
		}
	}

	private void generateButtons(JButton b[]) {
		b[0]=new JButton("Sair da aplicacao");
		b[1]=new JButton("Ler veiculos em arquivo");
		b[2]=new JButton("Gravar veiculos em arquivo");
		b[3]=new JButton("Pagar IPVA de um veiculo especifico");
		b[4]=new JButton("Esvaziar/calibrar os pneus de um veiculo especifico");
		b[5]=new JButton("Esvaziar/calibrar um pneu especifico");
		b[6]=new JButton("Imprimir todos os dados de todos os veiculos");
		b[7]=new JButton("Imprimir todos os dados de um veiculo");
		b[8]=new JButton("Movimentar todos os veiculos de um tipo");
		b[9]=new JButton("Movimentar todos os veiculos");
		b[10]=new JButton("Movimentar um veiculo");
		b[11]=new JButton("Abastecer veiculo");
		b[12]=new JButton("Incluir veiculo");
		b[13]=new JButton("Remover veiculo");
		
	}
	
	private void buttonsActions(JButton button) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==buttons[0]) {
					System.exit(0);
				}
				
				if(e.getSource()==buttons[1]) {
					if(file.exists()) {
						load();
						JOptionPane.showMessageDialog(null, "arquivo carregado com sucesso");
					}else {
						JOptionPane.showMessageDialog(null, "arquivo nao encontrado");
					}
					
				}
				
				if(e.getSource()==buttons[2]) {
					save();
					JOptionPane.showMessageDialog(null, "arquivo criado com sucesso");
				}
				
				if(e.getSource()==buttons[3]) {
					String id=JOptionPane.showInputDialog("Qual veiculo deseja pagar IPVA?");
					if(id!=null) {
						int intId=Integer.parseInt(id);
						if(idIndex(intId)!=-1) {
							((veiculoMotorizado) veiculo[idIndex(intId)]).setIPVA(true);
						}
						else {
							JOptionPane.showMessageDialog(null, "Veiculo nao encontrado");
						}
					}
				}
			
				if(e.getSource()==buttons[4]){
					String id=JOptionPane.showInputDialog("Qual veiculo deseja alterar as rodas?");
					if(id!=null){
						int intId=Integer.parseInt(id);
						if(idIndex(intId)!=-1) {
							String opt=JOptionPane.showInputDialog("Esvaziar ou calibrar?").toLowerCase();
							if(opt.equals("calibrar")){	
								for(int i=0;i<veiculo[idIndex(intId)].getQuantidadeRodas();i++){
									veiculo[idIndex(intId)].calibrar(i);
								}
							}
							else if(opt.equals("esvaziar")){
								for(int i=0;i<veiculo[idIndex(intId)].getQuantidadeRodas();i++) {
									veiculo[idIndex(intId)].esvaziar(i);
								}
							}
							else {
								JOptionPane.showMessageDialog(null,"Opcao nao encontrada");
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Veiculo nao encontrado");
						}
					}
				}
				
				if(e.getSource()==buttons[5]){
					String id=JOptionPane.showInputDialog("Qual veiculo deseja alterar a roda?");
					if(id!=null){
						int intId=Integer.parseInt(id);
						if(idIndex(intId)!=-1){
						String opt=JOptionPane.showInputDialog("Esvaziar ou calibrar?").toLowerCase();
						if(opt.equals("calibrar")){	
								String rodaId=JOptionPane.showInputDialog("Qual roda deseja calibrar? 1-"+veiculo[idIndex(intId)].getQuantidadeRodas());
								if(rodaId!=null){
									int rodaIntId=Integer.parseInt(rodaId)-1;
									if(rodaIntId>=0&&rodaIntId<=veiculo[idIndex(intId)].getQuantidadeRodas()) {
										veiculo[idIndex(intId)].calibrar(rodaIntId);
									}
									else{
										JOptionPane.showMessageDialog(null,"Roda nao encontrada");
									}
								}
							}
							else if(opt.equals("esvaziar")){
								String rodaId=JOptionPane.showInputDialog("Qual roda deseja calibrar? 1-"+veiculo[idIndex(intId)].getQuantidadeRodas());
								if(rodaId!=null){
									int rodaIntId=Integer.parseInt(rodaId)-1;
									if(rodaIntId>=0&&rodaIntId<=veiculo[idIndex(intId)].getQuantidadeRodas()) {
										veiculo[idIndex(intId)].calibrar(rodaIntId);
									}
									else{
										JOptionPane.showMessageDialog(null,"Roda nao encontrada");
									}
								}
							}
							else {
								JOptionPane.showMessageDialog(null,"Opcao nao encontrada");
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Veiculo nao encontrado");
						}
					}
				}
				
				if(e.getSource()==buttons[6]) {
					for(int i=0;i<quantMaxVeiculos;i++) {
						if(veiculo[i]!=null) {
							JOptionPane.showMessageDialog(null,veiculo[i]);
						}
					}
				}
				
				if(e.getSource()==buttons[7]) {
					String id=JOptionPane.showInputDialog("Qual veiculo deseja ver os dados?");
					if(id!=null) {
						int intId=Integer.parseInt(id);
						if(idIndex(intId)!=-1){
							JOptionPane.showMessageDialog(null,veiculo[idIndex(intId)]);
						}
						else{
							JOptionPane.showMessageDialog(null,"Veiculo nao encontrado");
						}
					}
				}

				if(e.getSource()==buttons[8]) {
					String option = JOptionPane.showInputDialog("Que tipo de veiculo voce quer mover?");
					if(option.equals("b") || option.equals("B")){
						for(int i=0;i<quantMaxVeiculos;i++) {
							if(veiculo[i]!=null) {
								if(veiculo[i] instanceof Bicicleta) {
									veiculo[i].mover();
								}
								if(veiculo[i].getX()>size) {
									JOptionPane.showMessageDialog(null, "O veiculo "+veiculo[i].getId()+" ganhou!");
								}
							}
						}
					}
					if(option.equals("m")  || option.equals("M")){
						for(int i=0;i<quantMaxVeiculos;i++) {
							if(veiculo[i]!=null) {
								if(veiculo[i] instanceof Motocicleta) {
									veiculo[i].mover();
								}
								if(veiculo[i].getX()>size) {
									JOptionPane.showMessageDialog(null, "O veiculo "+veiculo[i].getId()+" ganhou!");
								}
							}
						}
					}
					if(option.equals("c")  || option.equals("C")){
						for(int i=0;i<quantMaxVeiculos;i++) {
							if(veiculo[i]!=null) {
								if(veiculo[i] instanceof CarroP) {
									veiculo[i].mover();
								}
								if(veiculo[i].getX()>size) {
									JOptionPane.showMessageDialog(null, "O veiculo "+veiculo[i].getId()+" ganhou!");
								}
							}
						}
					}
					if(option.equals("e") || option.equals("E")){
						for(int i=0;i<quantMaxVeiculos;i++) {
							if(veiculo[i]!=null) {
								if(veiculo[i] instanceof CarroE) {
									veiculo[i].mover();
								}
								if(veiculo[i].getX()>size) {
									JOptionPane.showMessageDialog(null, "O veiculo "+veiculo[i].getId()+" ganhou!");
								}
							}
						}
					}
				}
				
				if(e.getSource()==buttons[9]) {
					for(int i=0;i<quantMaxVeiculos;i++) {
						if(veiculo[i]!=null) {
							veiculo[i].mover();
							if(veiculo[i].getX()>size) {
								JOptionPane.showMessageDialog(null, "O veiculo "+veiculo[i].getId()+" ganhou!");
							}
						}
					}
				}
				
				if(e.getSource()==buttons[10]) {
					String id=JOptionPane.showInputDialog("Qual veiculo deseja mover?");
					if(id!=null) {
						int intId=Integer.parseInt(id);
						if(idIndex(intId)!=-1) {
							mover(intId);
						}
						else {
							JOptionPane.showMessageDialog(null,"Veiculo nao encontrado");
						}
					}
				}
				
				if(e.getSource()==buttons[11]){
					String id=JOptionPane.showInputDialog("Qual veiculo deseja abastecer?");
					if(id!=null){
						int intId=Integer.parseInt(id);
						if(veiculo[idIndex(intId)] instanceof Bicicleta){
							JOptionPane.showMessageDialog(null,"Bicicletas nao precisam de combustivel!");
						}
						else if(idIndex(intId)!=-1) {
							String gasolina=JOptionPane.showInputDialog("Quantos litros?");
							if(gasolina!=null){
								double intGas=Double.parseDouble(gasolina);
								if(intGas>=0){
									((veiculoMotorizado) veiculo[idIndex(intId)]).abastecer(intGas);
								}
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"Veiculo nao encontrado");
						}
					}
				}

				if(e.getSource()==buttons[12]){
					String option = JOptionPane.showInputDialog("Que tipo de veiculo voce quer incluir? (B, M, C ou E)");
					if(option!=null) {
						if(option.equals("B") || option.equals("b")){
							incluirBicicleta();
						}
						else if(option.equals("M") || option.equals("m")){
							incluirMoto();
						}
						else if(option.equals("C") || option.equals("c")){
							incluirCarroP();
						}
						else if(option.equals("E") || option.equals("e")){
							incluirCarroE();
						}
						else{
							JOptionPane.showMessageDialog(null,"veiculo invalido");
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"opcao invalida");
					}
				}
				
				if(e.getSource()==buttons[13]){
					String id=JOptionPane.showInputDialog("Qual veiculo deseja remover?");
					if(id!=null){
						int intId=Integer.parseInt(id);
						if(idIndex(intId)!=-1) {
							removerVeiculo(idIndex(intId));
						}
						else {
							JOptionPane.showMessageDialog(null,"veiculo nao encontrado");
						}
					}
				}
			}
		});
	}
	
	private int idIndex(int id){
		for(int i=0;i<quantMaxVeiculos;i++) {
			if(veiculo[i]!=null){
			if(veiculo[i].getId()==id) {
				return i;
				}
			}
		}
		return -1;
	}
	
	private void incluirBicicleta(){
		int cont=0;

		for(int i=0;i<quantMaxVeiculos;i++){
			if(veiculo[i]==null&&cont==0){
				veiculo[i] = new Bicicleta(0, i*32);
				cont+=1;
			}
		}

		for(int i=1;i<quantMaxVeiculos;i++){
			for(int j=0;j<i;j++){
				if(veiculo[j]!=null&&veiculo[i]!=null) {
					if(veiculo[j].getId()==veiculo[i].getId()) {
						veiculo[i].generateId();
					}
				}
			}
		}
	}

	private void incluirMoto(){
		int cont=0;

		for(int i=0;i<quantMaxVeiculos;i++){
			if(veiculo[i]==null&&cont==0){
				veiculo[i] = new Motocicleta(0, i*32);
				cont+=1;
			}
		}

		for(int i=1;i<quantMaxVeiculos;i++){
			for(int j=0;j<i;j++){
				if(veiculo[j]!=null&&veiculo[i]!=null) {
					if(veiculo[j].getId()==veiculo[i].getId()) {
						veiculo[i].generateId();
					}
				}
			}
		}
	}

	private void incluirCarroP(){
		int cont=0;

		for(int i=0;i<quantMaxVeiculos;i++){
			if(veiculo[i]==null&&cont==0){
				veiculo[i] = new CarroP(0, i*32);
				cont+=1;
			}
		}

		for(int i=1;i<quantMaxVeiculos;i++){
			for(int j=0;j<i;j++){
				if(veiculo[j]!=null&&veiculo[i]!=null) {
					if(veiculo[j].getId()==veiculo[i].getId()) {
						veiculo[i].generateId();
					}
				}
			}
		}
	}

	private void incluirCarroE(){
		int cont=0;

		for(int i=0;i<quantMaxVeiculos;i++){
			if(veiculo[i]==null&&cont==0){
				veiculo[i] = new CarroE(0, i*32);
				cont+=1;
			}
		}

		for(int i=1;i<quantMaxVeiculos;i++){
			for(int j=0;j<i;j++){
				if(veiculo[j]!=null&&veiculo[i]!=null) {
					if(veiculo[j].getId()==veiculo[i].getId()) {
						veiculo[i].generateId();
					}
				}
			}
		}
	}

	
	private void removerVeiculo(int id){
		veiculo[id]=null;
	}

	
	private void mover(int id) {
		int i=idIndex(id);
		if(i!=-1) {
			veiculo[i].mover();
			if(veiculo[i].getX()>size) {
				JOptionPane.showMessageDialog(null, "o veiculo "+veiculo[i].getId()+" ganhou");
			}
		}
	}
	
	private void save() {
		try {
			FileOutputStream fos= new FileOutputStream(file);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(veiculo);
			oos.flush();
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void load() {
		try {
			FileInputStream fis= new FileInputStream(file);
			ObjectInputStream ois=new ObjectInputStream(fis);
			Veiculo[]  save=(Veiculo[]) ois.readObject();
			ois.close();
			fis.close();
			for(int i=0;i<20;i++){
				veiculo[i]=save[i];
			}
			
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	 
	public void tick() {
		for(int i=0;i<quantMaxVeiculos;i++) {	
			if(veiculo[i]!=null) {
				if(veiculo[i].getMover()) {
					veiculo[i].x+=(32*veiculo[i].getMov());
					veiculo[i].setMover(false);
				}
			}
			
		}
	}
	
	public void render(Graphics g) {
		for(int i=0;i<quantMaxVeiculos;i++) {	
			if(veiculo[i]!=null){
				g.setColor(Color.black);
				g.drawString(Integer.toString(veiculo[i].getId()),0,veiculo[i].y+16);
				g.drawImage(veiculo[i].getImage(),veiculo[i].x+32,veiculo[i].y,veiculo[i].width,veiculo[i].height,null);
			}
		}
	}
}