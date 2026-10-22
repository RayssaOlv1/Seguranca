package seguranca.hash;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HashPanel extends JPanel {
    private final JTextArea mensagemArea = new JTextArea(5, 50);
    private final JTextField hashCalculado = new JTextField();
    private final JTextField hashComparacao = new JTextField();
    private final JLabel resultado = new JLabel("Insira uma mensagem ou selecione um arquivo.", SwingConstants.LEFT);
    private File arquivoSelecionado;

    public HashPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel conteudo = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        conteudo.add(new JLabel("1. Calcular hash SHA-256 de uma mensagem"), c);

        c.gridy++;
        mensagemArea.setLineWrap(true);
        mensagemArea.setWrapStyleWord(true);
        conteudo.add(new JScrollPane(mensagemArea), c);

        c.gridy++;
        c.gridwidth = 1;
        JButton calcularTexto = new JButton("Calcular hash do texto");
        calcularTexto.addActionListener(e -> calcularHashTexto());
        conteudo.add(calcularTexto, c);

        JButton limparTexto = new JButton("Limpar texto");
        limparTexto.addActionListener(e -> {
            mensagemArea.setText("");
            hashCalculado.setText("");
            arquivoSelecionado = null;
            resultado.setText("Insira uma mensagem ou selecione um arquivo.");
        });
        c.gridx = 1;
        conteudo.add(limparTexto, c);

        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 2;
        conteudo.add(new JLabel("2. Calcular hash SHA-256 de um arquivo"), c);

        c.gridy++;
        JPanel arquivoBotoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton selecionarArquivo = new JButton("Selecionar arquivo...");
        selecionarArquivo.addActionListener(e -> selecionarArquivo());
        JButton calcularArquivo = new JButton("Calcular hash do arquivo");
        calcularArquivo.addActionListener(e -> calcularHashArquivo());
        arquivoBotoes.add(selecionarArquivo);
        arquivoBotoes.add(calcularArquivo);
        conteudo.add(arquivoBotoes, c);

        c.gridy++;
        conteudo.add(new JLabel("Hash calculado (copie este valor para comparar):"), c);

        c.gridy++;
        hashCalculado.setEditable(false);
        conteudo.add(hashCalculado, c);

        c.gridy++;
        conteudo.add(new JLabel("3. Conferir hash: cole abaixo o hash de referência"), c);

        c.gridy++;
        hashComparacao.setToolTipText("Cole aqui o hash esperado para comparação.");
        conteudo.add(hashComparacao, c);

        c.gridy++;
        JButton comparar = new JButton("Comparar hashes");
        comparar.addActionListener(e -> compararHashes());
        conteudo.add(comparar, c);

        c.gridy++;
        resultado.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        conteudo.add(resultado, c);
        add(new JScrollPane(conteudo), BorderLayout.CENTER);
    }

    private void calcularHashTexto() {
        String texto = mensagemArea.getText();
        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite uma mensagem antes de calcular o hash.",
                "Campo vazio", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            hashCalculado.setText(paraHexadecimal(calcularSHA256(texto.getBytes(StandardCharsets.UTF_8))));
            resultado.setText("Hash do texto calculado. Copie-o para comparar com uma referência.");
        } catch (NoSuchAlgorithmException ex) {
            mostrarErro("Não foi possível calcular o hash: " + ex.getMessage());
        }
    }

    private void selecionarArquivo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecione um arquivo para calcular o hash");
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            arquivoSelecionado = chooser.getSelectedFile();
            resultado.setText("Arquivo selecionado: " + arquivoSelecionado.getName());
        }
    }

    private void calcularHashArquivo() {
        if (arquivoSelecionado == null) {
            JOptionPane.showMessageDialog(this, "Selecione um arquivo primeiro.",
                "Nenhum arquivo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            try (FileInputStream entrada = new FileInputStream(arquivoSelecionado)) {
                byte[] buffer = new byte[8192];
                int quantidade;
                while ((quantidade = entrada.read(buffer)) != -1) {
                    digest.update(buffer, 0, quantidade);
                }
            }
            hashCalculado.setText(paraHexadecimal(digest.digest()));
            resultado.setText("Hash do arquivo calculado: " + arquivoSelecionado.getName());
        } catch (NoSuchAlgorithmException ex) {
            mostrarErro("SHA-256 não está disponível: " + ex.getMessage());
        } catch (IOException ex) {
            mostrarErro("Erro ao ler o arquivo: " + ex.getMessage());
        }
    }

    private void compararHashes() {
        String calculado = hashCalculado.getText().trim();
        String referencia = hashComparacao.getText().trim();
        if (calculado.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Calcule primeiro um hash.",
                "Hash não calculado", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (referencia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cole o hash de referência para comparar.",
                "Hash de referência vazio", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (calculado.equalsIgnoreCase(referencia)) {
            resultado.setText("RESULTADO: os hashes conferem. Nenhuma diferença foi detectada.");
            JOptionPane.showMessageDialog(this, "Os hashes são iguais.",
                "Integridade conferida", JOptionPane.INFORMATION_MESSAGE);
        } else {
            resultado.setText("RESULTADO: os hashes são diferentes. O conteúdo pode ser diferente.");
            JOptionPane.showMessageDialog(this, "Os hashes são diferentes.",
                "Divergência encontrada", JOptionPane.WARNING_MESSAGE);
        }
    }

    private byte[] calcularSHA256(byte[] dados) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-256").digest(dados);
    }

    private String paraHexadecimal(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    private void mostrarErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
