# ZGWebCrawler

Esse é o repositório do projeto **Web Crawler** do programa Acelera ZG, sendo que este é um programa intensivo promovido pela empresa ZG Soluções para a capacitação de novos estagiários em potencial.
Está dividindo em etapas, sendo que capa etapa contém trilhas semanais com 2 desafios: um projeto introdutório e um projeto avançado que simula uma rotina da empresa. Cada trilha introduz um assunto diferente, como uma prática de programação ou contato com uma nova linguagem. 

Esse repositório é exclusivo de uma trilha, no caso, a trilha 9 do kit 1 (K1-T9).

## Projeto Introdutório

**Assunto**: Web crawling/scraping com Java

**Descrição do desafio**: Desenvolvimento de um Web Crawler para coleta de dados do site OLX que automatiza o seguinte processo: 
- Entrar no site da OLX;
- Pegar os dados das primeiras 3 páginas do produto abaixo ordenado por menor preço:
> Nome: iPhone 11
> 
> Região: Goiás
> 
> DDD: 62 - Grande Goiânia e Anápolis
- Extrair as seguintes informações do anúncio: título, valor, endereço e URL do anúncio;
- Calcular o valor médio dos iPhones do conjunto de dados do passo 3;
- Remover todos os produtos que estiverem acima da média calculada no passo 4;
- Gerar um arquivo CSV e inserir os produtos que restaram no passo 5, com as colunas de título do anúncio, valor, endereço e URL

**Data de entrega**: 28/04/2022

## Projeto ZG HERO

**Assunto**: Web crawling/scraping com Groovy e HTTPBuilder 

**Descrição do desafio**: Desenvolvimento de um Web Crawler para coleta de dados do site do Governo que automatiza as seguintes tasks: 

**TASK 1**
- Acessar o site da ANS > "Espaço do Prestador de Serviços de Saúde" > "TISS - Padrão para Troca de Informação de Saúde Suplementar" > "Padrão TISS Versão Mês/Ano"
- Obter os elementos da tabela de documentos através de parser no HTML
- Baixar os arquivos e salvar na pasta de Downloads no mesmo diretório do projeto

**TASK 2**
- Acessar o campo "Histórico das versões dos Componentes do Padrão TISS"
- Coletar, através de parser no HTML, os dados de *Competência*, *Publicação* e *Início de Vigência* a partir de Janeiro/2016
- Gerar uma planilha com os dados coletados

**TASK 3**
- Acessar o campo "Tabelas relacionadas"
- Baixar a tabela de erros no envio para a ANS

**Data de entrega**: 29/04/2022
