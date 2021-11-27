# Redes Bayesianas - Problema do Alarme

- Exemplo: “Você possui um novo alarme contra ladrões em casa. Este alarme é
muito confiável na detecção de ladrões, entretanto, ele também pode
disparar caso ocorra um terremoto. Você tem dois vizinhos, João e Maria,
os quais prometeram telefonar-lhe no trabalho caso o alarme dispare.
João sempre liga quando ouve o alarme, entretanto, algumas vezes
confunde o alarme com o telefone e também liga nestes casos. Maria,
por outro lado, gosta de ouvir música alta e às vezes não escuta o
alarme.”


- Estados:
  - Ladrão
  - Terremoto
  - Alarme
  - João
  - Maria.

## Pré-requisitos

Para executar o projeto é necessário a instalação do:

- [Python 3.8](https://www.python.org/downloads/release/python-380/)
- [PyCharm](https://www.jetbrains.com/pt-br/pycharm/)

## Instalação de dependências

Para rodar o projeto sem erros, instale as seguintes dependêcias:

- [Anaconda](https://www.anaconda.com/download/) (Contém todos os pacotes necessários para instalção posteior).

Após instalado o Anaconda, abrir o projeto no Pycharm e executar os seguintes passos:


No canto superior esquerdo, selecione a opção `File` e depois clique em `Settings`, assim como abaixo:
![t1](https://user-images.githubusercontent.com/30741312/143661740-e8ded6f1-1744-41d0-bf44-ea6d6cb6ad0e.png)


Em seguida, expanda a opção `Project: Alarm-Bayesiano`, e selecione a opção `Python Interpreter`:
![t2](https://user-images.githubusercontent.com/30741312/143661743-27d7157b-a36e-4325-9ea9-05ac30fb1fc2.png)


Nesta tela, no canto superior direito, clique na engrenagem e marque a opção `Add`:

![t3](https://user-images.githubusercontent.com/30741312/143661744-38d0b715-bcb4-4789-b0df-012d68481aba.png)


Então, clique na opção `Conda Environment`, nela, onde informa `Pyhton Version` escolha a versão 3.8, após isso, 
clique em `OK` e feche/aplique as janelas anteriores para as modificações surtirem efeito.
![t4](https://user-images.githubusercontent.com/30741312/143661745-6e9f6c14-4dbe-4612-abe7-8fa14aec8150.png)

Para concluir, no terminal do Projeto, execute os seguintes comandos para a instalação das dependências (leia com atenção e selecione `y` _(yes)_ para todas as opções requisitadas em ambos os comandos) :

```bash
conda install -c ankurankan pgmpy
conda install -c anaconda scikit-learn
```

## Uso

Para executar o projeto, clique em executar na IDE, ou selecione o botão play (verde) e aguarde a execução.

![t5](https://user-images.githubusercontent.com/30741312/143662142-f7b7384f-4125-4a74-8d85-a8a2ebff7524.png)

## Desenvolvido por

[![Armstrong](https://avatars0.githubusercontent.com/u/30741312?s=64&v=4)](https://github.com/lohhans) |  [![Isaac](https://avatars.githubusercontent.com/u/43857949?s=64&v=4)](https://github.com/IsaacBraga) | [![David](https://avatars.githubusercontent.com/u/55093303?s=64&v=4)](https://github.com/davidmanuelpe) |  
|-------------------|-------------------|-------------------|
| **Armstrong L. M. G. Q.** | **Isaac Braga** | **David Emanuel**  |
| <a href="https://github.com/lohhans" title="Commits de @lohhans">💻 @lohhans</a> | <a href="https://github.com/IsaacBraga" >💻 @IsaacBraga</a>  | <a href="https://github.com/davidmanuelpe" >💻 @davidmanuelpe</a> |