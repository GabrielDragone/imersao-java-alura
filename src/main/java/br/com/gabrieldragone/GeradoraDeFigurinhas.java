package br.com.gabrieldragone;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class GeradoraDeFigurinhas {

    /*public static void main(String[] args) throws Exception {
        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        geradora.criar();
    }*/

    public void criar(InputStream inputStream, String nomeArquivo) { // Quem chamar o método ficará responsável por tratar a exceção, diferente do Try Catch. Através de Exception não é boa pratica.

        // Leitura da imagem:
        // InputStream: Classe abstrata que representa uma stream de fonte de bytes.
        //InputStream inputStream = new FileInputStream(new File("entrada/filme-maior.jpg")); // Fluxo de entrada de um arquivo.

        //InputStream inputStream =
        //        new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg")
        //                .openStream(); // Pega a imagem da internet.
        try {
            BufferedImage imagemOriginal = ImageIO.read(inputStream);

            // Criar nova imagem em memória com transparência e com tamanho novo:
            int largura = imagemOriginal.getWidth();
            int altura = imagemOriginal.getHeight();
            int novaAltura = altura + 200; // 200 pixels.

            BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

            // Copiar a imagem original para nova imagem (em memória):
            Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
            graphics.drawImage(imagemOriginal, 0, 0, null);

            // Configurar uma nova fonte para escrever na imagem:
            Font font = new Font(Font.SANS_SERIF, Font.BOLD, 64);
            graphics.setColor(Color.GREEN);
            graphics.setFont(font);

            // Escrever uma frase na nova imagem:
            graphics.drawString("TOPZERA!", 100, novaAltura - 100);

            // Escrever a nova imagem em um arquivo:
            File meuAquivo = new File("saida/" + nomeArquivo);
            meuAquivo.getParentFile().mkdirs(); // Se o caminho não existir, ele é criado. Se existir não é feito nada.
            ImageIO.write(novaImagem, "png", meuAquivo);
        } catch (Exception e) {

        }



    }

}
