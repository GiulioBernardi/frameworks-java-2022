# Frameworks

Json para alugar um carro:
<p align="center">
  <img width="50%" src="https://user-images.githubusercontent.com/54187661/200117389-637816a4-a7a7-4adc-b1c8-8b5b1c242409.png" />
</p>

Consulta de comissão (essa consulta soma todas as comissões dos vendedores para que o RH saiba quanto pagar ao final do mês a cada vendedor, sem que precise perder tempo somando todo valor ganho):

<p align="center">
  <img width="50%" src="https://user-images.githubusercontent.com/54187661/200117421-cd2e84db-cfd1-49b2-8c28-df3cbea3300d.png" />
</p>

Também é possível consultar a comissão de um único vendedor, passando o cpf dele
<p align="center">
  <img width="50%" src="https://user-images.githubusercontent.com/54187661/200117446-f7b2674d-fc4f-4dc4-8b1d-577459d30cf6.png" />
</p>

Apesar de todas essas funcionalidades, a API realiza crud das entidades solicitadas (Carro, vendedor e cliente)

## Coisas que faltam: (serão adicionadas até dia 7/11)**
- Retornar ResponseEntity com códigos semânticos
- Tratar exceções para e exibir erros no front, não exibindo erros de log do backend
- Adicionar hypermedia nas requisições de comissão, aluguel, e no retorno de todos os métodos get de Carro, Cliente e Vendedor
- Adicionar logical delete para todas as entidades que estão guardadas no banco

## Para testar você mesmo
### Endpoints: 
**Cliente**
- GET: localhost:8080/clientes
- GET por cpf: localhost:8080/clientes/{cpf}
- Cadastrar: localhost:8080/clientes/cadastrar
```
{
		"nomeCompleto": "Alfredo dos Santos",
		"cpf": 52514252625,
		"endereco": null,
		"email": "alfredo@gmail.com",
		"celular": 945210120
}
```

- Atualizar: localhost:8080/clientes/atualizar/{cpf}
```
{
		"nomeCompleto": "Alfredo dos Santos Silva",
		"cpf": 52514252625,
		"endereco": null,
		"email": "alfredo@gmail.com",
		"celular": 945210120
}
```
- Deletar: localhost:8080/clientes/deletar/{cpf}

**Carro**
- GET: localhost:8080/carros
- GET por placa: localhost:8080/carros/{placa} 
- Cadastrar: localhost:8080/carros/cadastrar
```
{
		"placa": "POM-6585",
		"marca": "Volkswagen",
		"modelo": "Gol",
		"cor": "Branco",
		"ano": 2017,
		"quilometragem": 54200,
		"diaria": 25.5
}
```
- Atualizar: localhost:8080/carros/atualizar/{placa}
```
{
		"marca": "Volkswagen",
		"modelo": "Gol",
		"cor": "Branco",
		"ano": 2017,
		"quilometragem": 54200,
		"diaria": 100.0
}
```

- Deletar: localhost:8080/carros/deletar/{placa}

**Vendedor**
- GET: localhost:8080/vendedor
- GET por cpf: localhost:8080/vendedor/{cpf}
- Cadastrar: localhost:8080/vendedor/cadastrar
```
{
		"nomeCompleto": "Gustavo Nascimento",
		"cpf": 23562514584,
		"dataAdmissao": "2018-03-14"
}
```
- Atualizar: localhost:8080/vendedor/atualizar/{cpf}
```
{
		"nomeCompleto": "Gustavo Nascimento",
		"cpf": 23562514584,
		"dataAdmissao": "2020-03-14"
}
```
- Deletar: localhost:8080/vendedor/deletar/{cpf}

**Aluguel/comissao**
- Alugar carro: localhost:8080/aluguel/alugar
```
{
	"cpfCliente" : 39371890017,
	"cpfVendedor": 34378748819,
	"placaDoCarro": "EFD-6958",
	"qtdDiasAluguel" : 3
}
```

- Comissão de todos: localhost:8080/comissões
- Comissão por cpf: localhost:8080/comissões/63625148798


## Bem vindo ao desafio de frameworks do movimento codar.

A idéia deste desafio é forçar a prática deliberada do nosso conhecimento de Spring Boot, Spring MVC, JPA e Spring Data.

Para isso, iremos trabalhar com o desenvolvimento do back-end de uma aplicação de aluguel de carros.

Imagine que você foi contratado por um empresário, dono de uma locadora de carros.
Ele já tem um desenvolvedor front-end contratato, logo você só precisará fazer a parte do back-end.

O seu objetivo é fazer isso através de endpoints REST,
onde o desenvolvedor front-end fará chamadas HTTP.

Usaremos o banco de dados em memória H2 para o desenvolvimento desta tarefa.


Dados para conexão com o banco pelo browser:

* URL: http://localhost:8080/h2-console
* Driver Class: org.h2.Driver
* JDBC URL: jdbc:h2:mem:testdb
* User Name: movimentocodar
* Password: movimentocodar

A tabela carro, vendedor, conta corrente, cliente e endereço já existem e já possuem registros.

### Entidades

Na aplicação é necessário fazer o CRUD de 3 entidades:
Carro, Vendedor e Cliente.

#### Carro

O carro tem as seguintes informações:
1. Placa (texto)
2. Marca (texto)
3. Modelo (texto)
4. Cor (texto)
5. Ano (número)
6. Quilometragem (número)
7. Diária (Preço do carro por um dia de aluguel)

#### Vendedor

O vendedor tem as seguintes informações:
1. Nome completo (texto)
2. CPF (número)
3. Data admissão (data)
4. Conta corrente (o front-end enviará o nome do banco agência e conta)

#### Cliente

O cliente, que irá alugar os carros em nossas agências tem as seguintes informações:
1. Nome completo (texto)
2. CPF (número)
3. Endereço (texto)
4. E-mail (texto)
5. Celular (número)

### O sistema

Com estas informações salvas no sistema, temos a principal ação a ser feita: Cliente alugar um carro. Teremos uma chamada para algum endpoint criado por você.
As informações enviadas pelo front-end para representar esse aluguel será (via um json no corpo da requisição):

1. CPF do cliente
2. CPF do vendedor
3. Placa do carro
4. Quantidade de dias do aluguel

Essa ação irá gerar um aluguel e também uma comissão ao vendedor.
É do trabalho do desenvolvedor(a) criar a estrutura para armazenar o aluguel e a comissão.

#### Sobre a comissão

A comissão do vendedor é de 10% sobre o valor total da venda.
Ou seja: Se um cliente alugou um carro de 120 reais, por 2 dias, a comissão do vendedor
será de 24 reais. A não ser que o vendedor tenha mais de 5 anos de casa, neste caso, ele terá
uma comissão de 13%. No nosso exemplo seria de 31,20.

### Relatórios

#### Aluguéis

Com estas informações salvar, agora eu, como dono da locadora, preciso de um endpoint
para buscas as informações dos alugueis. O que eu preciso ver é:

1. Nome do cliente
2. Modelo do carro
3. Placa do carro
4. Nome do vendedor que fez a venda
5. Dias que o cliente ficou com o carro
6. Valor total do aluguel
7. Dia que o cliente fez esse aluguel

Exemplo: O dev front-end chamou o endpoint disponibilizado por você programador(a) e recebe como resposta o seguinte json:

{
    "nomeCliente": "Eduardo de Medeiros Branquinho",
    "modeloCarro": "HB20",
    "placaCarro": "ABC-1234",
    "vendedor": "Sergio Alberto Soares",
    "quantidadeDias": 5,
    "valorTotal": 100,
    "dataAluguel": "03/11/2020"
}

#### Comissões

É necessário também um endpoint para as comissões, para que a gerente do RH consiga extrair
as comissões de todos os vendedores, ou de um vendedor específico.
Logo, essa chamara terá um parâmetro opcional que será o CPF de um vendedor.

O resultado esperado é algo como:

[
{
"vendedor":"Rodrigo Silveira Batista",
"cpf":"131.123.466-18",
"valor":1500,
"conta":{
"banco":"Itaú",
"agencia":123,
"conta-corrente":46576
}
},
{
"vendedor":"Felipe Souza Cruz",
"cpf":"234.123.466-18",
"valor":4000,
"conta":{
"banco":"Itaú",
"agencia":567,
"conta-corrente":1235
}
}
]

### Observações

A aplicação tem dois arquivos. 
* schema sql onde nós iremos criar nossas tabelas
* data sql onde nós podemos criar inserts nas nossas tabelas

Ambos os arquivos serão executados pelo Spring Boot ao subir nossa aplicação.

Para interagir com nossa aplicação usaremos o Postman ou o Insomnia Rest que são
programas que podemos instalar em nosso computador.
Estes programas nós permite fazer chamadas GET, POST, DELETE e PUT para nossos endpoints.

É legal termos pelo menos uma JPQL e uma Query Nativa dentro do sistema.

Para conhecer um pouco o banco, rode as seguintes queries:

select * from carro;

select * from cliente c
inner join endereco e on e.cliente_key = c.cliente_key;

select * from vendedor v 
inner join conta_corrente cc on cc.vendedor_key = v.vendedor_key;


