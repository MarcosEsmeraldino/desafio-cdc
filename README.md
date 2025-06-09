# desafio-cdc

API para suportar parte do funcionamento da casa do código. Este componente também será utilizado para experimentações 
no desenvolvimento, como por exemplo o uso da recém lançada JDK 24.

# Stack

* Java 24
* Spring Boot 3.5

# Cadastro de um novo autor

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