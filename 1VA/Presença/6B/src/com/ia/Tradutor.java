package com.ia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Tradutor {

    static String[] quantificadorUniversal = {"Nenhum", "Para todo", "Qualquer", "Qualquer um", "Nada", "Tudo", "Todo"};
    static String[] quantificadorExistencial = {"Existe um", "Existe algum", "Há um", "Há uma"};
    static String[] condicional = {"Então", "É", "Implica"};
    static String[] conjuncao = {"E", "Com um", "que"};
    static String[] disjuncao = {"ou", "Ou um"};
    static String[] bicondicional = {"somente se", "só se"};
    static String[] negacao = {"não"};


    public static String tradutor(String entrada) {

        String[] total = {"Nenhum", "Para todo", "Qualquer", "Qualquer um", "Nada", "Tudo", "Todo", "Existe um", "Existe algum", "Há um", "Há uma", "Então", "É", "Implica", "E", "Com um", "ou", "Ou um", "somente se", "só se", "não"};


        String[] frase = entrada.split(" ");


        Vector<String> saida = new Vector<>(999);
        StringBuilder saidaConcatenada = new StringBuilder();

        int x = 0;
        String variavel = "";
        boolean freio = false;
        boolean flag = false;

        for (String palavra : frase) {

            x++;

            /*if(palavra.equalsIgnoreCase("que")){
                String palavraAnterior = frase[x-2];
                String palavraPosterior = frase[x];

                for(String tot : total){
                    if(palavraAnterior.equalsIgnoreCase(tot)){
                        flagAnterior = true;
                    }
                }

                for(String tot : total){
                    if(palavraPosterior.equalsIgnoreCase(tot)){
                        flagPosterior = true;
                    }
                }

                if(!flagAnterior){
                    saida.add(palavraAnterior + "(x)");
                }

                if(!flagPosterior){
                    saida.add(palavraPosterior + "(y)");
                }
            }*/

            for (String qtdUni : quantificadorUniversal) {
//                System.out.println("qtdUni: " + palavra);
                if (palavra.equalsIgnoreCase(qtdUni)) {
                    saida.add(0, "∀x");
                    break;
                } else if (palavra.equalsIgnoreCase("Para")) {
                    if (x < frase.length) {
                        if (frase[x].equalsIgnoreCase("todo")) {
                            saida.add(0, "∀x");
                            break;
                        }
                    }
                } else if (palavra.equalsIgnoreCase("Qualquer")) {
                    if (x < frase.length) {
                        if (frase[x].equalsIgnoreCase("um")) {
                            saida.add(0, "∀x");
                            saida.add(1, "Pessoa(x)");
                            break;
                        } else {
                            saida.add(0, "∀x");
                            saida.add(1, frase[x]+"(x)");

                            if(frase[x+1].equalsIgnoreCase("que")){
                                System.out.println("");
//                                saida.add("∧");
//
//                                saida.add(frase[x+2]+"(x)");
                            } else{
                                saida.add("∧");

                                saida.add(frase[x+1]+"(x)");
                                saida.add("->");
                            }

                            break;
                        }
                    }
                }
            }

//            System.out.println(saida);

            for (String qtdExi : quantificadorExistencial) {
//                System.out.println("qtdExi: " + palavra);
                if (palavra.equalsIgnoreCase(qtdExi)) {
                    saida.add(0, "∃x");
                    break;
                } else if (palavra.equalsIgnoreCase("Existe")) {
                    if (x < frase.length) {
                        if (frase[x].equalsIgnoreCase("um")) {
                            saida.add(0, "∃x");
                            break;
                        } else if (frase[x].equalsIgnoreCase("algum")) {
                            saida.add(0, "∃x");
                            break;
                        }
                    }
                } else if (palavra.equalsIgnoreCase("Há")) {
                    if (x < frase.length) {
                        if (frase[x].equalsIgnoreCase("um")) {
                            saida.add(0, "∃x");
                            break;
                        } else if (frase[x].equalsIgnoreCase("uma")) {
                            saida.add(0, "∃x");
                            break;
                        }
                    }
                }
            }

            for (String imp : condicional) {
//                System.out.println("imp: " + palavra);
                if (palavra.equalsIgnoreCase(imp)) {

                    if (frase[x].equalsIgnoreCase("um") || frase[x].equalsIgnoreCase("uma")) {

                        for (String tot : total) {
                            if (frase[x - 2].equalsIgnoreCase(tot)) {
                                freio = true;
                                flag = true;
                                break;
                            }
                        }

                        if (!flag) {
                            saida.add(frase[x - 2] + "(x)");

                            saida.add("->");

                            if (frase[0].equalsIgnoreCase("Nenhum")) {
                                saida.add("¬");
                            }


                        }

                        saida.add("->");

                        saida.add(frase[x + 1] + "(x)");

                        break;

                    }

                    for (String tot : total) {
                        if (frase[x - 2].equalsIgnoreCase(tot)) {
                            freio = true;
                            flag = true;
                            break;
                        }
                    }

                    if(!flag) {
                        saida.add(frase[x - 2] + "(x)");
                    }


                    saida.add("->");

                    if (frase[0].equalsIgnoreCase("Nenhum")) {
                        saida.add("¬");
                    }

                    saida.add(frase[x] + "(x)");

                    break;


                }

            }

            for (String conj : conjuncao) {
//                System.out.println("conj: " + palavra);

                if (palavra.equalsIgnoreCase(conj)) {
                    String varComparativoExclusaoRepetido = frase[x - 2] + "(x)";


                    if(!saida.contains(varComparativoExclusaoRepetido)){
                        saida.add(frase[x - 2] + "(x)");
                    }

                    saida.add("∧");

                    for (int i = x; i < frase.length; i++) {

                        for (String tot : total) {
//                            System.out.println(frase[i]);
                            if (frase[i + 1].equalsIgnoreCase(tot) || i+1 == frase.length-1) {
                                freio = true;
                                flag = true;
                                break;
                            }

//                            System.out.println("entrou aq");
                            /*if(frase[i+1].equalsIgnoreCase(tot)){
                                System.out.println("BREAAAAAAAAAAAAAAAAAAAAAKKK");


                                List<String> listaTemp = new ArrayList<String>(Arrays.asList(frase));
                                listaTemp.remove(i);
                                frase = listaTemp.toArray(new String[0]);

                                System.out.println("remove: "+Arrays.toString(frase));
                            }*/
                        }

                        if (flag) {
                            variavel = variavel + "(" + frase[i] + ")";
                            List<String> listaTemp = new ArrayList<String>(Arrays.asList(total));
//                            System.out.println("frase[i] "+frase[i]);
                            listaTemp.add(frase[i]);
                            total = listaTemp.toArray(new String[0]);
                        } else {
                            variavel = variavel + frase[i];
                        }

                        if (freio) {
                            break;
                        }
                    }

                    if (flag) {
                        saida.add(variavel);
                    } else {
                        saida.add(variavel + "(x)");
                    }

                    flag = false;

                } else if (palavra.equalsIgnoreCase("Com")) {
                    if (x < frase.length) {
                        if (frase[x].equalsIgnoreCase("um")) {
                            saida.add(frase[x - 2] + "(x)");

                            saida.add("∧");

                            for (int i = x + 1; i < frase.length; i++) {
                                variavel = variavel + frase[i];
                                for (String tot : total) {
//                                    System.out.println("entrou aq");
                                    if (frase[i].equalsIgnoreCase(tot)) {
//                                        System.out.println("BREAAAAAAAAAAAAAAAAAAAAAKKK");
                                        break;
                                    }
                                }
                            }

                            saida.add(variavel + "(x)");
                            break;
                        }
                    }
                }
            }

            for (String disj : disjuncao) {
//                System.out.println("disj: " + palavra);

                if (palavra.equalsIgnoreCase(disj)) {
                    saida.add(frase[x - 2] + "(x)");

                    saida.add("∨");

                    if (frase[x].equalsIgnoreCase("um")) {
                        for (int i = x + 1; i < frase.length; i++) {
                            variavel = variavel + frase[i];
                        }
                    } else if (frase[x].equalsIgnoreCase("uma")) {
                        for (int i = x + 1; i < frase.length; i++) {
                            variavel = variavel + frase[i];
                        }
                    } else {
                        for (int i = x; i < frase.length; i++) {
                            variavel = variavel + frase[i];
                        }
                    }

                    saida.add(variavel + "(x)");

                }
            }

            //Scrooge não ama nada que é estranho
            //Saída: ∀ t estranho (t) → ¬ ama (Scrooge, t)



            for (String neg : negacao) {
//                System.out.println("neg: " + palavra);

                if (palavra.equalsIgnoreCase(neg)) {
                    saida.add("¬");

                    saida.add(frase[x] + "(" + frase[x - 2] + ", x)");

                }
            }
        }

        for (String palavra : saida) {
            if (palavra.equalsIgnoreCase("¬")) {
                saidaConcatenada.append(palavra);
            } else {
                saidaConcatenada.append(palavra).append(" ");
            }
        }


        return saidaConcatenada.toString();

    }
}
