package seguranca.simetrica;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * PAINEL RESERVADO.
 * Implementar aqui a demonstração de criptografia simétrica.
 * Sugestão acadêmica: AES; documentar geração/uso da chave e tratamento de erros.
 */
public class SimetricaPanel extends JPanel {
    public SimetricaPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        add(new JLabel("Criptografia simétrica", SwingConstants.CENTER), BorderLayout.NORTH);
        JTextArea info = new JTextArea(
            "MÓDULO RESERVADO PARA OUTRO INTEGRANTE\n\n"
            + "Implementar nesta classe:\n"
            + "- Campo para texto original;\n"
            + "- Criptografar e exibir o texto cifrado;\n"
            + "- Descriptografar e recuperar o texto original;\n"
            + "- Explicar que a mesma chave é usada nas duas operações.\n\n"
            + "Sugestão: usar AES com a API padrão do Java 8."
        );
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setOpaque(false);
        add(info, BorderLayout.CENTER);
    }
}
