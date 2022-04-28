import com.opencsv.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WebCrawler {
    public static void main(String[] args) throws IOException {
        final String urlPrimeiraPagina = "https://go.olx.com.br/grande-goiania-e-anapolis/celulares?q=iphone%2011&sp=2";
        final String urlSegundaPagina = "https://go.olx.com.br/grande-goiania-e-anapolis/celulares?o=2&q=iphone%2011&sp=2";
        final String urlTerceiraPagina = "https://go.olx.com.br/grande-goiania-e-anapolis/celulares?o=3&q=iphone%2011&sp=2";

        ArrayList<Iphone> listaIphones = new ArrayList<>();

        webCrawlerOLX(urlPrimeiraPagina, listaIphones);
        webCrawlerOLX(urlSegundaPagina, listaIphones);
        webCrawlerOLX(urlTerceiraPagina, listaIphones);

        calcularMediaPreco(listaIphones);

        removerPrecoAcimaMedia(listaIphones);

        gerarPlanilhaCsv(listaIphones);
    }

    public static void webCrawlerOLX(String url, ArrayList<Iphone> listaIphones) throws IOException {
        final Document documento = Jsoup.connect(url).get();
        Elements listaAnuncios = documento.getElementsByClass("sc-12rk7z2-0 bDLpyo");
        for(Element anuncio : listaAnuncios){
            Iphone iphone = new Iphone();
            iphone.setNome(anuncio.getElementsByTag("h2").text());
            Elements preco = anuncio.getElementsByClass("sc-1kn4z61-1 hzqyCO").select("span");
            iphone.setPreco(preco.text().replaceAll("[^0-9]", ""));
            iphone.setEndereco(anuncio.getElementsByClass("sc-1c3ysll-0 btflKt").text());
            Element urlAnuncio = anuncio.select("a").first();
            iphone.setUrl(urlAnuncio.attr("href"));
            listaIphones.add(iphone);
        }
        System.out.println(listaIphones.size());
    }

    public static int transformarPrecoStringEmInt(String preco){
        return Integer.parseInt(preco);
    }

    public static int calcularMediaPreco(ArrayList<Iphone> listaIphones){
        int somaPreco = 0;
        for(Iphone iphone : listaIphones){
            somaPreco += transformarPrecoStringEmInt(iphone.getPreco());
        }
        System.out.println("O preço médio é: " + somaPreco / listaIphones.size());
        return somaPreco / listaIphones.size();
    }

    public static void removerPrecoAcimaMedia(ArrayList<Iphone> listaIphones){
        int precoMedio = calcularMediaPreco(listaIphones);
        listaIphones.removeIf(iphone -> transformarPrecoStringEmInt(iphone.getPreco()) > precoMedio);
        System.out.println(listaIphones.size());
    }

    public static void gerarPlanilhaCsv(ArrayList<Iphone> listaIphones) throws IOException {
        FileWriter planilha = new FileWriter("planilhaDeIphones.csv");
        CSVWriter writer = new CSVWriter(planilha);
        String[] header = {"TÍTULO", "ENDEREÇO", "PREÇO", "URL"};
        writer.writeNext(header);
        List<String []> data = new ArrayList<>();

        for(Iphone iphone: listaIphones){
            data.add(new String[]{iphone.getNome(), iphone.getEndereco(), iphone.getPreco(), iphone.getUrl()});
        }

        writer.writeAll(data);
        writer.close();
        planilha.close();
    }
}