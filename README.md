# Projeto Prático 3

Projeto Prático 3 (PP3) da disciplina Processamento Gráfico do curso de Ciências da Computação da Universidade Federal de São Carlos campus Sorocaba.
Ministrada pela Profº Drº Mário Liziér.

### Integrantes do grupo:
- Gabriel de Paula e Lima - 587710
- Renato Araujo Rizzo - 587788
- Renato Candido Kurosaki - 587834

# Introdução

Objetivo desse projeto prático foi aplicar conhecimentos adquiridos na disciplina de processamento gráfico como 
renderização, redimensionamento e rasterização de objetos 3D através de sua implementação e exibição do objeto gerado. 

Os passos de implementação foram ler um objeto 3D com seus vértices e faces descritos em arquivo de objeto simples, 
gerar uma janela adicionando esse objeto para exibição, aplicar transformação de matrizes para possibilitar movimentação desse objeto
nos eixos x e y através de sliders controlados pelo usuário e por fim rasterizar o objeto com uma cor sólida.

# Como usar

Projeto implementado utilizando linguagem Java com bibliotecas auxiliares para geração do painel e sliders usados para 
exibição e manipulação do objeto. 

Desenvolvemos utilizando a IDE IntelliJ e para rodar o projeto basta abri-lo nessa ferramenta, compilar e rodar.

Também é possível rodar diretamente pelo terminal, nesse caso basta entrar na pasta src e executar os comandos abaixo:

```java
javac Face.java Vertice.java ObjLoader.java Matriz.java Main.vava 
```
```java
java Main 
```

Se tudo ocorrer certo, será aberto uma nova janela exibindo o objeto 3D.

# Desenvolvimento e implementação

Com base no arquivo de objeto disponibilizado que contém lista de pontos para representar vértices e faces, 
iniciamos criando classes para facilitar essa representação e também criamos uma classe para "carregar" o objeto.
Essa classe tem responsabilidade de ler o arquivo do objeto 3D, instânciar vértices e inclui-los em uma lista de faces 
referentes aos valores obtidos nesse arquivo. Após esse carregamento, a lista de faces que representa o objeto 3D é retornada.

Para efetuar a manipulação do objeto nos eixos x e y, utilizamos matrizes "in-code" e criamos a classe Matriz para auxiliar na multiplicação e transformação 
da lista de vértices. A ideia aqui também foi pré computar a multiplicação de matriz para utilizar na rotação esquerda-direita e cima-baixo.
Interligamos os métodos da transformação aos sliders que são instânciados na criação da janela.

Para a coloração do objeto (coloração das faces) utilizamos a ideia de rasterização por coordenadas baricêntricas, onde computamos
coordenadas para cada `pixel` que estão dentro de cada face até colorir todas.



# Referências 

- [Biblioteca JPanel](https://docs.oracle.com/javase/7/docs/api/javax/swing/JPanel.html)
- [Matrix Transformation](https://www.scilab.org/tutorials/computer-vision-%E2%80%93-image-transform)
- [Working with Images in Java](https://docs.oracle.com/javase/tutorial/2d/images/index.html)
- [Barycentric rasterization](https://www.scratchapixel.com/lessons/3d-basic-rendering/rasterization-practical-implementation/rasterization-stage)