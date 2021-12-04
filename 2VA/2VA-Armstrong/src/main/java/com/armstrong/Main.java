package com.armstrong;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.lemmatizer.DictionaryLemmatizer;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;

import java.io.File;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        try {
            // Leitura do arquivo com entrada de texto
            InputStream modelIn;

            FileInputStream fileStream = new FileInputStream("entrada.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream));

            String linha;
            StringBuilder texto = new StringBuilder();

            while ((linha = bufferedReader.readLine()) != null) {
                texto.append("\n").append(linha);
            }

            fileStream.close();


            // Tokenização do texto
            modelIn = new FileInputStream("tokens" + File.separator + "pt-token.bin");
            TokenizerModel model = new TokenizerModel(modelIn);
            TokenizerME tokenizer = new TokenizerME(model);
            String[] tokens = tokenizer.tokenize(texto.toString());


            // Processamento pós-tokenização do texto
            InputStream posModelIn = new FileInputStream("models" + File.separator + "pt-pos-maxent.bin");
            POSModel posModel = new POSModel(posModelIn);
            POSTaggerME posTagger = new POSTaggerME(posModel);
            String[] tags = posTagger.tag(tokens);


            // Lemmatização do texto processado
            InputStream dictLemmatizer = new FileInputStream("dicionarios" + File.separator + "pt-br-lemmatizer.dict");
            DictionaryLemmatizer lemmatizer = new DictionaryLemmatizer(dictLemmatizer);

            for (int a = 0; a < tags.length; a++) {

                for (int tam = 0; tam < tokens.length; tam++) {
                    String token = tokens[tam];
                    int tamanhoEleToken = token.length();
                    if (token.charAt(tamanhoEleToken - 1) == 's' && !tags[tam].equals("prop") && !tags[tam].equals("pron-det")) {
                        tags[tam] = "N";
                    }
                    if (tags[tam].equals("n") && token.charAt(tamanhoEleToken - 1) == 's') {
                        tags[tam] = "N";
                    }
                    if (tags[tam].equals("a") && token.charAt(tamanhoEleToken - 1) == 's') {
                        tags[tam] = "A";
                    }
                    if (tags[tam].equals("v-fin") && token.charAt(tamanhoEleToken - 1) == 's') {
                        tags[tam] = "V";
                    }
                }
            }
            String[] lemmas = lemmatizer.lemmatize(tokens, tags);


            // Tratamento das saídas obtidas para a Saída 1 e Saída 2
            ArrayList<String> saida1 = new ArrayList<>(); // Plural
            ArrayList<String> saida2 = new ArrayList<>(); // Singular

            for (int i = 0; i < tokens.length; i++) {
//                System.out.println(tokens[i]+" -"+tags[i]+" : "+lemmas[i]);
                if (tags[i].equals("N") && !lemmas[i].equals("O") && !tokens[i].equalsIgnoreCase("a") && !tokens[i].equalsIgnoreCase("o") && !tokens[i].equalsIgnoreCase("e") && !tokens[i].equalsIgnoreCase("as") && !tokens[i].equalsIgnoreCase("os")) {
                    if (!saida1.contains(tokens[i].toLowerCase()) && !saida1.contains(tokens[i].substring(0, 1).toUpperCase() + tokens[i].substring(1))) {
                        saida1.add(tokens[i]);
                        saida2.add(lemmas[i]);
                    }
                }
            }

            // Regra de singularização simples: Os sufixos diminutivos “-inho” e “-zinho” devem ser usados conforme o
            // final da palavra básica: usamos indiferentemente os sufixos “-inho” ou “-zinho” se a palavra
            // básica termina por vogal átona ou consoante (exceto “s” e “z”): corpinho ou corpozinho, florinha ou florzinha.
            //
            // Embora a palavra DEVAGAR seja advérbio, no português falado no Brasil consagrou-se o seu uso no diminutivo
            // em lugar do superlativo. Em vez de “muito devagar”, temos o “devagarinho” e o “devagarzinho”.
            //
            // É flagrante a preferência pelo uso do sufixo “-inho” para as palavras terminadas por vogal átona
            // (sapato-sapatinho, casa-casinha, dente-dentinho) e do sufixo “-zinho” para as terminadas por consoante
            // (mar-marzinho, papel-papelzinho).
            //
            // O sufixo “-inho” deve ser usado para as palavras terminadas por “s” ou “z” (lápis-lapisinho, cruz-cruzinha, rapaz-rapazinho).
            //
            // Devemos usar o sufixo “-zinho” para as palavras terminadas por vogal tônica, nasal ou ditongo
            // (café-cafezinho, siri-sirizinho, irmã-irmãzinha, bem-benzinho, álbum-albunzinho, mão-mãozinha, pônei-poneizinho, chapéu- chapeuzinho).
            //
            // Quanto ao plural dos diminutivos, há duas regras básicas:
            // 1a) Se usamos o sufixo “-inho”, basta a desinência “s” no final: livro – livr + inho = livrinhos;
            // casa – cas + inha = casinhas; rapaz – rapaz + inho = rapazinhos;
            // 2a) Se usamos o sufixo “-zinho”, devemos pôr os dois elementos no plural sem o “s” do substantivo:
            // animal = animal + zinho – animai(s) + zinhos = animaizinhos; pastel = pastel + zinho – pastei(s) + zinhos = pasteizinhos;
            // pão = pão + zinho – pãe(s) + zinhos = pãezinhos.
            //
            // Assim sendo, de acordo com as regras, teríamos: luz – luz + inha = luzinhas e flor – flor + zinha – flore(s) + zinhas = florezinhas.
            //
            // Para terminar duas observações:
            // 1a) Para o acadêmico Evanildo Bechara, pazinhas é o plural do diminutivo de pá (pazinha = pá + zinha – pazinhas).
            // E pazezinhas seria o plural do diminutivo de paz (pazinha = paz + inha – paze(s) + inhas = pazezinhas).
            // Em razão disso, também deveríamos aceitar as luzezinhas (luz + inha – luze(s) + inhas = luzezinhas).
            // 2a) Para o mestre José Oiticica, o normal seria “florinhas ou florzinhas”, que a língua padrão evita.
            //
            // Por isso tudo, não vejo por que impor uma forma como correta e outra como errada.
            // Em razão disso, podemos aceitar os dois plurais: florezinhas e florzinhas, barezinhos e barzinhos, luzinhas e luzezinhas.

            ArrayList<String> saida3 = new ArrayList<>(); // Singular

            // Auxiliares:
            // Acentos
            ArrayList<String> acentos = new ArrayList<>(Arrays.asList("à", "è", "ì", "ò", "ù", "À", "È", "Ì", "Ò", "Ù", "á", "é", "í", "ó", "ú", "Á", "É", "Í", "Ó", "Ú", "â", "ê", "î", "ô", "û", "Â", "Ê", "Î", "Ô", "Û", "ã", "õ"));
            ArrayList<String> vogaisFemMasc = new ArrayList<>(Arrays.asList("a", "á", "à", "ã", "â", "o", "ó", "õ", "ô", "ò"));
            ArrayList<String> vogais = new ArrayList<>(Arrays.asList("a", "e", "i", "o", "u"));


            for (String palavra : saida2) {
//                char[] duasUltimasLetrasDaPalavra = saida2.get(i).substring(saida2.get(i).length()-2).toCharArray();
                String ultimaLetraDaPalavra = palavra.substring(palavra.length() - 1);
                String palavraSemUltimaLetra = palavra.substring(0, palavra.length()-1);
//                System.out.println(generoDaPalavra);
//                System.out.println(palavra);

                if (!acentos.contains(ultimaLetraDaPalavra) && !ultimaLetraDaPalavra.equals("s") && !ultimaLetraDaPalavra.equals("z")) {
//                    System.out.println(palavra);
                    // Pela regra, tanto faz "inha" ou "zinha", aqui, utilizei o zinha.
                    // System.out.println(saida2.get(i).substring(0, saida2.get(i).length()-1));
                    if(vogaisFemMasc.contains(ultimaLetraDaPalavra)){
                        if (vogaisFemMasc.contains(ultimaLetraDaPalavra)) {
//                        System.out.println(saida2.get(i) + "zinh" + ultimaLetraDaPalavra);
                            saida3.add(palavraSemUltimaLetra + "inh" + ultimaLetraDaPalavra);
                        } else {
                            String generoDaPalavra = Crawler.main(palavra.toLowerCase());

                            if (generoDaPalavra.equalsIgnoreCase("feminino")) {
//                            System.out.println(saida2.get(i) + "zinh" + "a");
                                saida3.add(palavraSemUltimaLetra + "zinh" + "a");
                            } else {
                                saida3.add(palavraSemUltimaLetra + "zinh" + "o");

                            }
                        }
                    } else {
                        if(ultimaLetraDaPalavra.equalsIgnoreCase("m")){
                            String generoDaPalavra = Crawler.main(palavra.toLowerCase());

                            if (generoDaPalavra.equalsIgnoreCase("feminino")) {
//                            System.out.println(saida2.get(i) + "zinh" + "a");
                                saida3.add(palavraSemUltimaLetra + "nzinh" + "a");
                            } else {
                                saida3.add(palavraSemUltimaLetra + "nzinh" + "o");

                            }
                        } else {
                            String generoDaPalavra = Crawler.main(palavra.toLowerCase());

                            if (generoDaPalavra.equalsIgnoreCase("feminino")) {
//                            System.out.println(saida2.get(i) + "zinh" + "a");
                                saida3.add(palavra + "zinh" + "a");
                            } else {
                                saida3.add(palavra + "zinh" + "o");

                            }
                        }
                    }
//                    saida3.add(saida2.get(i));
                } else {
                    String generoDaPalavra = Crawler.main(palavra.toLowerCase());

                    if(ultimaLetraDaPalavra.equalsIgnoreCase("z")){
                        if (generoDaPalavra.equalsIgnoreCase("feminino")) {
//                            System.out.println(saida2.get(i) + "zinh" + "a");
                            saida3.add(palavra + "inh" + "a");
                        } else {
                            saida3.add(palavra + "inh" + "o");

                        }
                    } else if(ultimaLetraDaPalavra.equalsIgnoreCase("ô")){
                        if (generoDaPalavra.equalsIgnoreCase("feminino")) {
//                            System.out.println(saida2.get(i) + "zinh" + "a");
                            saida3.add(palavra + "zinh" + "a");
                        } else {
                            saida3.add(palavra + "zinh" + "o");

                        }
                    }  else if(ultimaLetraDaPalavra.equalsIgnoreCase("â")){
                        if (generoDaPalavra.equalsIgnoreCase("feminino")) {
//                            System.out.println(saida2.get(i) + "zinh" + "a");
                            saida3.add(palavra + "zinh" + "a");
                        } else {
                            saida3.add(palavra + "zinh" + "o");

                        }
                    } else {
                        if (generoDaPalavra.equalsIgnoreCase("feminino")) {
//                            System.out.println(saida2.get(i) + "zinh" + "a");
                            saida3.add(palavra + "zinh" + "a");
                        } else {
                            saida3.add(palavra + "zinh" + "o");

                        }
                    }
                }
            }

            // Saída 1 formatada
            System.out.print("Saída 1: ");
            saida1.forEach(x -> {
                if (x.equals(saida1.get(saida1.size() - 1))) {
                    System.out.print(x);
                } else {
                    System.out.print(x + ", ");
                }
            });

            System.out.println();

            // Saída 2 formatada
            System.out.print("Saída 2: ");
            saida2.forEach(x -> {
                if (x.equals(saida2.get(saida2.size() - 1))) {
                    System.out.print(x);
                } else {
                    System.out.print(x + ", ");
                }
            });

            System.out.println();

            // Saída 3 formatada
            System.out.print("Saída 3: ");
            saida3.forEach(x -> {
                if (x.equals(saida3.get(saida3.size() - 1))) {
                    System.out.print(x);
                } else {
                    System.out.print(x + ", ");
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}