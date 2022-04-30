import com.opencsv.CSVWriter
import groovyjarjarantlr.collections.List
import groovyx.net.http.HttpBuilder
import groovyx.net.http.optional.Download

import static groovyx.net.http.HttpBuilder.configure
import org.jsoup.nodes.Document

//Task 1
Document paginaVersaoVigente = configure {
    request.uri = "https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/padrao-tiss-2013-marco-2022"
}.get() as Document

def tabelaVersaoVigente = paginaVersaoVigente.select("tbody")

for(int i = 0; i < 5; i++){
    def linhaTabelaVersaoVigente = tabelaVersaoVigente.select("tr").get(i)
    def botaoDownload = linhaTabelaVersaoVigente.select("td").get(2).getElementsByClass("btn btn-primary btn-sm center-block internal-link").attr("href")

    //não dá pra pegar a url desse aqui - tirar dúvida depois
    if(linhaTabelaVersaoVigente.select("td").get(0).text().contains("Representação")){
        continue
    }

    File arquivoTabelaVersaoVigente = configure {
        request.uri = botaoDownload
    }.get {
        def nomeArquivo = '../../../Downloads/' + linhaTabelaVersaoVigente.select("td").get(0).text()
        Download.toFile(delegate, nomeArquivo as File)
    } as File
}

//Task 2
Document paginaHistoricoComponentes = configure {
    request.uri = "https://www.gov.br/ans/pt-br/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-2013-tiss/padrao-tiss-historico-das-versoes-dos-componentes-do-padrao-tiss"
}.get() as Document

FileWriter planilha = new FileWriter("../../../Downloads/planilhaHistoricoComponentes.csv")
CSVWriter writer = new CSVWriter(planilha)
String header = ["Competência", "Publicação", "Início de Vigência"]
writer.writeNext(header)

List data = new ArrayList<String[]>() as List

def tabelaHistorico = paginaHistoricoComponentes.select("tbody")
def linhaTabelaHistorico = tabelaHistorico.select("tr")

for(linha in linhaTabelaHistorico.next()){
    if(linha.select("td").get(0).text() == "dez/2015"){
        break
    }
    else{
        String[] dados = [linha.select("td").get(0).text(), linha.select("td").get(1).text(), linha.select("td").get(2).text()]
        data.add(dados)
    }
    writer.writeAll(data as Iterable<String[]>)
}

//Task 3
File arquivoTabelaErroANS = configure {
    request.uri = "https://www.gov.br/ans/pt-br/arquivos/assuntos/prestadores/padrao-para-troca-de-informacao-de-saude-suplementar-tiss/padrao-tiss-tabelas-relacionadas/padrao-tiss-tabela-erros-envio-para-ans-padrao-tiss-08022019.xlsx"
}.get {
    Download.toFile(delegate, "../../../Downloads/tabelaErroANS.xlsx" as File)
} as File