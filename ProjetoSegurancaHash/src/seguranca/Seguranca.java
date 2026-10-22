package seguranca;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import seguranca.hash.HashPanel;
import seguranca.simetrica.SimetricaPanel;
import seguranca.assimetrica.AssimetricaPanel;
import seguranca.assinatura.AssinaturaPanel;

public class Seguranca extends JFrame {
    public Seguranca() {
        setTitle("Segurança da Informação");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 560);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel(
            "<html><div style='text-align:center'>Segurança da Informação"
            + "<br><span style='font-size:11px'>Demonstrações de hash e criptografia</span></div></html>",
            SwingConstants.CENTER
        );
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Hash", new HashPanel());
        abas.addTab("Criptografia simétrica", new SimetricaPanel());
        abas.addTab("Criptografia assimétrica", new AssimetricaPanel());
        abas.addTab("Assinatura digital", new AssinaturaPanel());
        add(abas, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                    // Mantém o visual padrão caso o sistema não esteja disponível.
                }
                new Seguranca().setVisible(true);
            }
        });
    }
}
