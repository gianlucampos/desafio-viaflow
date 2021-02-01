## Avaliação técnica DIMED

Aplicação feita com o objetivo de ilustrar a resolução para o desafio técnico proposto pela Viaflow para DIMED.
Sistema é capaz de integrar operações da API do http://datapoa.com.br/group/about/mobilidade, podendo filtrar 
informações importadas da API.

## Tecnologias utilizadas
Neste projeto foram utilizadas as seguintes tecnologias:

- Java 11
- Spring Boot 
- Maven 
- JPA
- PostgreSQL 9.6
- Flyway
- Docker 

Foi escolhido o Spring boot por ser altamente personalizável e abranger diversas camadas para desenvolvimento,
combinando o modelo Spring MVC com controllers Rest e Spring Data JPA para mapeamento ORM utilizando hibernate.

Além disso, para facilitar a implantação do sistema, o projeto foi encapsulado em Docker e implementado o esquema 
de migrations para banco de dados com FlyWay no banco PostgresSQL.

---

### Pré-requisitos
Para executar aplicação é necessário instalar 2 ferramentas: **Docker** & **Docker Compose**.

**Docker**
Para ambientes [Linux](https://docs.docker.com/install/linux/docker-ce/ubuntu/), [Windows](https://docs.docker.com/docker-for-windows/install/) e [Mac](https://docs.docker.com/docker-for-mac/install/) .

**Docker Compose** 
Nos ambientes *Windows* e *Mac* já são instalados juntos com o Docker, para Linux basta [seguir as instruções](https://docs.docker.com/compose/install/) .


### Como rodar a aplicação?

Para subir aplicação:

```
$ docker-compose up -d
```

Para parar aplicação:

```
$ docker-compose down
```

### Como testar a aplicação?
Recomendo utilizar uma ferramenta de teste de serviços RestFul como [Postman](https://www.postman.com/downloads/) ou [Insomnia](https://insomnia.rest/download/).

## Endpoints para teste
Listagem de linhas cadastradas [@GET] **http://localhost:8082/mobilidade/linhas**

Listagem de linhas por Nome [@GET] **http://localhost:8082/mobilidade/linhas{nome}**

Listagem de linhas por Coordenadas [@GET] **http://localhost:8082/mobilidade/linhas/{latitude}/{longitude}/{raio}**

Listagem de itinerario cadastrados [@GET] **http://localhost:8082/mobilidade/itinerario**

Importa linhas da API [@GET] **http://localhost:8082/mobilidade/linhas/import**

Importa itinerarios da API [@GET] **http://localhost:8082/mobilidade/itinerario/import**

---
