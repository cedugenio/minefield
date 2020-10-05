package br.com.cedugenio.cm.visao;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import br.com.cedugenio.cm.modelo.Campo;
import br.com.cedugenio.cm.modelo.CampoEvento;
import br.com.cedugenio.cm.modelo.CampoObservador;

@SuppressWarnings("serial")
public class BotaoCampo extends JButton implements CampoObservador, MouseListener{
	private final Color BG_PADRAO = new Color(184,184,184);
	private final Color BG_MARCAR = new Color(8,179,247);
	private final Color BG_EXPLODIR = new Color(189,66,68);
	private final Color TEXTO_VERDE = new Color(0,100,0);

	private Campo campo;  

	public BotaoCampo(Campo campo) {
		this.campo = campo;  
		setBorder(BorderFactory.createBevelBorder(0));
		setOpaque(true);
		setBackground(BG_PADRAO);

		addMouseListener(this);
		campo.registrarObservador(this);


	}

	public void eventoOcorreu(Campo campo , CampoEvento evento) {
		switch (evento) {
		case ABRIR:
			aplicarEstiloAbrir();
			break;
		case MARCAR:
			aplicarEstiloMarcar();
			break;
		case EXPLODIR:
			aplicarEstiloExplodir();
			break;
		default:
			aplicarEstiloDefault();
		}
		SwingUtilities.invokeLater(() -> {
			repaint();
			validate();
		});

	}

	private void aplicarEstiloDefault() {
		setBackground(BG_PADRAO);
		setBorder(BorderFactory.createBevelBorder(0));
		
		setText("");

	}

	private void aplicarEstiloExplodir() {
		setBackground(BG_EXPLODIR);
		setForeground(Color.WHITE);
		setText("X");

	}

	private void aplicarEstiloMarcar() {
		setBackground(BG_MARCAR);
		setForeground(Color.BLACK);
		setText("M");
	}

	private void aplicarEstiloAbrir() {
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		
		if(campo.isMinado()) {
			setBackground(BG_EXPLODIR);
			return;
		}
		setBackground(BG_PADRAO);
		
		
		switch (campo.minasNaVizinhanca()) {
		case 1:
			setForeground(TEXTO_VERDE);
			break;
		case 2:
			setForeground(Color.BLUE);
			break;
		case 3:
			setForeground(Color.YELLOW);
			break;
		case 4:
		case 5:
		case 6:
			setForeground(Color.RED);
			break;	
		default:
			setForeground(Color.CYAN);
			break;
		}
		String valor = !campo.vizinhacaSegura() ? 
				campo.minasNaVizinhanca() + "" : "";
		setText(valor);

	}
	//Interfaces do Evento do mouse
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() ==1) {
			campo.abrir();
		}else {
			campo.alterarMarcacao();
		}
	}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	
}
