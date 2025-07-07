# desafio-cdc

API para suportar parte do funcionamento da casa do código. Este componente também será utilizado para experimentações 
no desenvolvimento, como por exemplo o uso da recém lançada JDK 24.

# Stack

* Java 24
* Spring Boot 3.5

# 1. Cadastro de um novo autor

## Necessidades

É necessário cadastrar um novo autor no sistema. Todo autor tem um nome, email e uma descrição. Também queremos saber o instante exato que ele foi registrado.

## Restrições

* O instante não pode ser nulo 
* O email é obrigatório 
* O email tem que ter formato válido 
* O nome é obrigatório 
* A descrição é obrigatória e não pode passar de 400 caracteres


## Resultado esperado

Um novo autor criado e status 200 retornado



# 2. E-mail do autor é único

## Necessidades

O e-mail do autor precisa ser único no sistema 

## Resultado esperado

Erro de validação no caso de e-mail duplicado



# 3. Cadastro de uma categoria

## Necessidades

Toda categoria precisa de um nome

## Restrições

* O nome é obrigatório
* O nome não pode ser duplicado

## Resultado esperado

Uma nova categoria cadastrada no sistema e status 200 retorno.
Caso alguma restrição não seja atendida, retorne 400 e um json informando os problemas de validação.



# 4. Criar um novo livro

## Necessidades

* Um título
* Um resumo do que vai ser encontrado no livro
* Um sumário de tamanho livre. O texto deve entrar no formato markdown, que é uma string. Dessa forma ele pode ser formatado depois da maneira apropriada.
* Preço do livro
* Número de páginas
* Isbn(identificador do livro)
* Data que ele deve entrar no ar(de publicação)
* Um livro pertence a uma categoria
* Um livro é de um autor

## Restrições

* Título é obrigatório
* Título é único
* Resumo é obrigatório e tem no máximo 500 caracteres
* Sumário é de tamanho livre.
* Preço é obrigatório e o mínimo é de 20
* Número de páginas é obrigatória e o mínimo é de 100
* Isbn é obrigatório, formato livre
* Isbn é único
* Data que vai entrar no ar precisa ser no futuro
* A categoria não pode ser nula
* O autor não pode ser nulo

## Resultado esperado

* Um novo livro precisa ser criado e status 200 retornado
* Caso alguma restrição não seja atendida, retorne 400 e um json informando os problemas de validação