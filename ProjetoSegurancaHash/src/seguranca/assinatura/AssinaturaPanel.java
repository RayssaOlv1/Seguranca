package seguranca.assinatura;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

/**
 * PAINEL RESERVADO.
 * Implementar aqui a assinatura e verificação de assinatura de arquivos.
 */
public class AssinaturaPanel extends JPanel {
    public AssinaturaPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        add(new JLabel("Assinatura digital", SwingConstants.CENTER), BorderLayout.NORTH);
        JTextArea info = new JTextArea(
            "MÓDULO RESERVADO PARA OUTRO INTEGRANTE\n\n"
            + "Implementar nesta classe:\n"
            + "- Selecionar um arquivo;\n"
            + "- Gerar ou carregar um par de chaves;\n"
            + "- Assinar o arquivo e salvar a assinatura;\n"
            + "- Verificar a assinatura com a chave pública;\n"
            + "- Explicar como as chaves e o certificado digital foram gerados.\n\n"
            + "Documentar o algoritmo e os passos de teste."
        );
        info.setEditable(false);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setOpaque(false);
        add(info, BorderLayout.CENTER);
    }
}
