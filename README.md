# MovieFlix :hourglass_flowing_sand:
Sistema baseado em um banco de filmes. Esse Sistema foi um trabalho desenvolvido no Bootcamp de Spring React da DevSuperior.

# Descrição
O sistema MovieFlix consiste em um banco de filmes, os quais podem ser listados e avaliados pelos usuários. Usuários podem ser visitantes (VISITOR) e membros (MEMBER). Apenas usuários membros podem inserir avaliações no sistema.

Ao acessar o sistema, o usuário deve fazer seu login. Apenas usuários logados podem navegar nos filmes. Logo após fazer o login, o usuário vai para a listagem de filmes, que mostra os filmes de forma paginada, ordenados alfabeticamente por título. O usuário pode filtrar os filmes por gênero.

Ao selecionar um filme da listagem, é mostrada uma página de detalhes, onde é possível ver todas informações do filme, e também suas avaliações. Se o usuário for MEMBER, ele pode ainda registrar uma avaliação nessa tela.

Um usuário possui nome, email e senha, sendo que o email é seu nome de usuário. Cada filme possui um título, subtítulo, uma imagem, ano de lançamento, sinopse, e um gênero. Os usuários membros podem registrar avaliações para os filmes. Um mesmo usuário membro pode deixar mais de uma avaliação para o mesmo filme.

# Modelo relacional da aplicação
![img](https://user-images.githubusercontent.com/37542212/144457209-15f2f075-67e6-496c-9f8a-14846a7c06d4.png)

OBS: não irei colocar o atributo de subtítulo na minha implementação, pois nem todo filme tem subtítulo, então achei meio irrelevante a existência desse atributo no sistema.

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- Jpa / Hibernate
- Spring Security / OAuth 2.0 e Token JWT
- Maven

## Teste automatizados
- Unidade
- Integração

## Implantação em desenvolvimento
- Banco de Dados: H2

## Padrões utilizados
- Padrão arquitetural: REST
- Padrões estruturais: DTO e Factory Method
