package br.com.cedugenio.cm.visao;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.com.cedugenio.cm.modelo.Tabuleiro;

@SuppressWarnings("serial")
public class PainelTabuleiro extends JPanel{

	public PainelTabuleiro(Tabuleiro tabuleiro) {
		setLayout(new GridLayout(
				tabuleiro.getLinhas(), tabuleiro.getColunas()));
		
	tabuleiro.paracadaCampo(c -> add(new BotaoCampo(c)));
	
	tabuleiro.registrarObservador(e -> {
		
		SwingUtilities.invokeLater(() -> {
			if(e.isGanhou()) {
				JOptionPane.showMessageDialog(this, "Ganhou Miserável :-)");
			}else {
				JOptionPane.showMessageDialog(this, "Perdeu :-(");
			}
			tabuleiro.reiniciar();
		});
		
	});
			
		}
		
}


