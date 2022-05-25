# teste-tecnico

Maven Project Para manter o cadastro pessoas seus scores e afinidades

Possui um crud básico:
 - Cadastrar afinidades
 - Cadastrar scores
 - Cadastrar pessoas
 - Buscar pessoas por id juntamente com a descrição de seu score
 
Libs utilizadas:
 - Maven
 - Spring boot
 - Java 11 ou 17
 - Hibernate como ORM
 - Spring data jpa como gerenciador de consultas
 - Banco de dados em memória H2
 - Lombok para tratar getters, setters, construtores...
 - Spring security com autenticação JWT
 - Mapstruct para converter entidades em DTOs
 - Spring fox com swagger documentado
 - JUnit e Mockito para testes unitários

Como utilizar 
 - Por se tratar de um banco de dados em memória, os dados são apagados a cada execução da aplicação
 - Ao iniciar a aplicação é necessário chamar o endpoint de autenticação informando um usuário e senha para obter o token:
   - Usar como usuário e senha padrão admin.
   - O token tem validade de 30 minutos, pode ser configurado no application.properties
 - Após, utilizar os endpoints da aplicação usando o Bearer <token> no header (Autorizar no swagger)
