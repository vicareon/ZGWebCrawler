import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class WebCrawler {
    public static void main(String[] args) throws IOException {
        final String urlPrimeiraPagina = "https://go.olx.com.br/grande-goiania-e-anapolis/celulares?q=iphone%2011&sp=2";
        final String urlSegundaPagina = "https://go.olx.com.br/grande-goiania-e-anapolis/celulares?o=2&q=iphone%2011&sp=2";
        final String urlTerceiraPagina = "https://go.olx.com.br/grande-goiania-e-anapolis/celulares?o=3&q=iphone%2011&sp=2";

        ArrayList<Iphone> listaIphones = new ArrayList<>();

        metodoGeral(urlPrimeiraPagina, listaIphones);
        metodoGeral(urlSegundaPagina, listaIphones);
        metodoGeral(urlTerceiraPagina, listaIphones);

        calculaPrecoMedio(listaIphones);

        removePrecoAcima(listaIphones);
    }

    public static void metodoGeral(String url, ArrayList<Iphone> listaIphones) throws IOException {
        final Document documento = Jsoup.connect(url).get();
        Elements listaAnuncios = documento.getElementsByClass("sc-12rk7z2-0 bDLpyo");
        for(Element anuncio : listaAnuncios){
            Iphone iphone = new Iphone();
            iphone.setNome(anuncio.getElementsByTag("h2").text());
            Elements preco = anuncio.getElementsByClass("sc-1kn4z61-1 hzqyCO").select("span");
            iphone.setPreco(transformaPreco(preco));
            iphone.setEndereco(anuncio.getElementsByClass("sc-1c3ysll-0 btflKt").text());
            Element urlAnuncio = anuncio.select("a").first();
            iphone.setUrl(urlAnuncio.attr("href"));
            listaIphones.add(iphone);
        }
        System.out.println(listaIphones.size());
    }

    public static int transformaPreco(Elements preco){
        String precoTexto = preco.text().replaceAll("[^0-9]", "");
        int c = Integer.parseInt(precoTexto);
        return c;
    }

    public static int calculaPrecoMedio(ArrayList<Iphone> listaIphones){
        int somaPreco = 0;
        for(Iphone iphone : listaIphones){
            somaPreco += iphone.getPreco();
        }
        return somaPreco / listaIphones.size();
    }

    public static void removePrecoAcima(ArrayList<Iphone> listaIphones){
        int precoMedio = calculaPrecoMedio(listaIphones);
        listaIphones.removeIf(iphone -> iphone.getPreco() > precoMedio);
        System.out.println(listaIphones.size());
    }
}