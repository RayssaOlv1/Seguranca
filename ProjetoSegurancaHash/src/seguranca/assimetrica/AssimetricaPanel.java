package seguranca.assimetrica;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * PAINEL RESERVADO.
 * Implementar aqui a demonstração de criptografia assimétrica.
 */
public class AssimetricaPanel extends JPanel {
    public AssimetricaPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        add(new JLabel("Criptografia assimétrica", SwingConstants.CENTER), BorderLayout.NORTH);
        JTextArea info = new JTextArea(
            "MÓDULO RESERVADO PARA OUTRO INTEGRANTE\n\n"
            + "Implementar nesta classe:\n"
            + "- Gerar um par de chaves;\n"
            + "- Demonstrar o papel da chave pública e da chave privada;\n"
            + "- Cifrar e decifrar uma mensagem de exemplo;\n"
            + "- Explicar o algoritmo escolhido e suas limitações."
        );
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setOpaque(false);
        add(info, BorderLayout.CENTER);
    }
}
