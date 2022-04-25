import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class WebCrawler {
    public static void main(String[] args){
        final String urlPrimeiraPagina = "https://go.olx.com.br/grande-goiania-e-anapolis/celulares?q=iphone%2011&sp=2";
        final String urlSegundaPagina = "https://go.olx.com.br/grande-goiania-e-anapolis/celulares?o=2&q=iphone%2011&sp=2";
        final String urlTerceiraPagina = "https://go.olx.com.br/grande-goiania-e-anapolis/celulares?o=3&q=iphone%2011&sp=2";
        try{
            System.out.println("*** PAGINA 1 ***");
            final Document document = Jsoup.connect(urlPrimeiraPagina).get();
            Elements listaAnuncios = document.select("li");
            //System.out.println(document.outerHtml());
            for(Element anuncio : listaAnuncios){
                String titulo = anuncio.getElementsByTag("h2").attr("title");
                System.out.println(titulo);
                Elements preco = anuncio.getElementsByClass("sc-1kn4z61-1 hzqyCO");
                String a = preco.text();
                System.out.println(preco.text());
                String endereco = anuncio.getElementsByClass("sc-1c3ysll-0 btflKt").tagName("span").attr("title");
                System.out.println(endereco);
                String urlTexto = anuncio.getElementsByTag("a").attr("href");
                System.out.println(urlTexto);
                transformaPreco(a);
            }

            System.out.println("*** PAGINA 2 ***");
            final Document document2 = Jsoup.connect(urlSegundaPagina).get();
            Elements listaAnuncios2 = document2.select("li");
            //System.out.println(document.outerHtml());
            for(Element anuncio : listaAnuncios2){
                Elements titulo = anuncio.getElementsByTag("h2");
                System.out.println(titulo.text());
                Elements preco = anuncio.getElementsByClass("sc-1kn4z61-1 hzqyCO");
                System.out.println(preco.text());
                Elements endereco = anuncio.getElementsByClass("sc-1c3ysll-0 btflKt");
                System.out.println(endereco.text());
            }

            System.out.println("*** PAGINA 3 ***");
            final Document document3 = Jsoup.connect(urlTerceiraPagina).get();
            Elements listaAnuncios3 = document3.select("li");
            //System.out.println(document.outerHtml());
            for(Element anuncio : listaAnuncios3){
                Elements titulo = anuncio.getElementsByTag("h2");
                System.out.println(titulo.text());
                Elements preco = anuncio.getElementsByClass("sc-1kn4z61-1 hzqyCO");
                System.out.println(preco.text());
                Elements endereco = anuncio.getElementsByClass("sc-1c3ysll-0 btflKt");
                System.out.println(endereco.text());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void transformaPreco(String preco){
        String a = preco.replaceAll("[^0-9]", "");
        System.out.println(a);
        Double b = Double.parseDouble(a);
        System.out.println(b);
    }
}