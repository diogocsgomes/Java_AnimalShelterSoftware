# Project Title
## Contents
- [Team](#team)
- [Vision and Scope](#vision-and-scope)
- [Requirements](#requirements)
    - [Use case diagram](#use-case-diagram)
    - [User stories and prototypes](#user-stories-and-prototypes)
- [Architecture and Design](#architecture-and-design)
    - [Domain Model](#domain-model)
- [Implementation](#implementation)
    - [Product Increment 1](#product-increment-1)
    - [Product Increment 2](#product-increment-2)
    - [Product Increment 3](#product-increment-3)
    - [Product Increment 4](#product-increment-4)

## Team
- Diogo Gomes - 2021137427
- Diogo Fatia - 2020152804
- Ana Cardoso - 2021133158
- Bruno Fonseca - 2021129658
- Ricardo Tavares - 2021144581

***

## Vision and Scope
#### Problem Statement
##### Project background
Atualmente existem muitas organizações de proteção animal sem fins lucrativos que operam com recursos limitados e dependem muito do voluntariado. No entanto, a gestão de todos os animais resgatados pelas mesmas pode se tornar um processo complexo, uma vez que muitas das vezes estas não têm acesso a sistemas de gestão elaborados e eficazes o que pode pôr em risco o espaço e os animais. Neste contexto, uma aplicação para uma gestão qualificada iria ter um papel fundamental muito importante para estas visto que para além de ajudar numa melhor gestão dos animais, irá permitir às mesmas ajudar outros animais que também estejam a precisar de algum suporte e gerir melhor a organização.

##### Stakeholders
> - Organizações Sem Fins Lucrativos: necessidade de um software de gestão que facilite o acompanhamento e a administração eficaz de projetos, tarefas e voluntários.
> - Voluntários: necessidade de uma plataforma que simplifique a gestão de tarefas, projetos e horários do voluntariado.
> - Animais: melhorias no suporte e na eficácia das organizações/voluntários, resultando em uma maior qualidade de serviço e um alcance mais amplo, bem como o melhor fornecimento de condições de apoio.
> - Investidores/Financiadores/Doadores: necessidade de uma ferramenta que mostre onde estão a ser usados os seus donativos da organização em questão, vem como uma plataforma para gerir todos os “supporters”. 
> - Adotantes: necessidade de um sistema onde seja possível ver as pessoas que adotaram animais na instituição, para fazer um historial do animal.
> - Veterinários: necessidade de um sistema de notifcações para quando necessário informar os veterinários que certo animal está com problemas.

##### Users
> - Organização Sem Fins Lucrativos.
> - Voluntários (sendo estes os utilizadores principais uma vez que estariam a trabalhar no abrigo para animais).
> - Investidores/Financiadores/Doadores.

***

#### Vision & Scope of the Solution
##### Vision statement
O objetivo visa facilitar a coordenação entre voluntários que pretendam cuidar dos animais, membros da associação que cuida dos animais, veterinários e famílias temporárias de animais. Assim, o software visa melhorar a condição de vida dos animais, bem como fomentar a adoção e facilitar o trabalho dos contribuintes da associação.

##### List of features
> - Gestão dos voluntários (Adicionar, Remover, Editar).
> - Notificações para os voluntários sobre os afazeres.
> - Perfis dos animais na associação.
> - Gestão dos animais (Adicionar, Remover, Editar).
> - Registo de adoções dos animais.
> - Registo de doações.
> - Notificações para os veterinários sobre problemas nos animais ou a necessidade de dar vacinas/medicamentos.

##### Features that will not be developed
> - Processamento de pagamentos das doações.
> - Gestão de stock (Alimentação, Medicamentos, etc).
> - Criação de um website para marketing do software. 

##### Risk
> - Se houver um erro na aplicação, o bem estar de um animal pode ser posto em risco.
> - A informação dos voluntários, animais, veterinários, doadores e adotantes deve ser mantida em segurança.
> - Risco de inserção de dados incorretos ou imprecisos devido a erros de entrada por parte dos utilizadores da aplicação.


##### Assumptions
> - A primeira versão da aplicação será um protótipo de uma aplicação desktop.
> - Os voluntários estão dispostos a usar a aplicação.
> - As associações já têm os meios necessários para usar a aplicação, como acesso a internet e dispositivos.


***

## Requirements
#### Use Case Diagram
![Use case diagram](imgs/UML_use_case_example-800x707.png)

***

##### Use Case 1
- Actor: Actor x
- Description: Description of use case number 1
- Preconditions:
    - Precondition 1
    - Precondition 2
    - Without preconditions
- Postconditions:
    - Postcondition 1
    - Postcondition 2
    - Without postcondition
- Normal flow:
    - The user ...
    - The user ...
- Alternative flows:
    - The user ...
    - The user ...

***

##### Use Case 2

***

##### Use Case 3

***

#### User Stories and Prototypes

***

##### User Story 1
**Organização Sem Fins Lucrativos**

As ..., I want ..., so ...

###### Acceptance Criteria

```
An acceptance test should be written here
```

###### Prototype

A prototype of user story 1 should be here. You can see in (#use-case-diagram) how to import an image.

***

##### User Story 2
**Voluntários**

Como voluntário desta organização quero ter a possibilidade de controlar a alimentação dos animais, quais os animais existentes dentro da instituição e bem como o estado de saúde dos mesmos, para isso preciso de ter acesso á informação sobre total dos animais.

###### Acceptance Criteria
> - Quando o voluntário entra no sistema deverá ter a possibilidade de analisar a lista de todos os animais;
> - Este tem de ter a possibilidade de selecionar um animal, caso pretenda ter mais informações a cerca do mesmo;
> - O voluntário tem de ter acesso a informar os veterinários no sistema, que existe um animal doente;

***

##### User Story 3
**Investidores/Financiadores/Doadores**

As ..., I want ..., so ...

###### Acceptance Criteria

```
An acceptance test should be written here
```

***

## Architecture and Design

#### Domain Model

A domain model should be here. You can see in (#use-case-diagram) how to import an image.

***

## Implementation

#### Product Increment 1

##### Sprint Goal

The sprint goal was ...

##### Planned vs Implemented

For this iteration we planned to implement the:

- Feature 1
- Feature 2

For this iteration we implemented the:

- Feature 1
- Feature 2

##### Sprint Retrospective

- What we did well:
    - A
- What we did less well:
    - B
- How to improve to the next sprint:
    - C

***

#### Product Increment 2

***

#### Product Increment 3

***

#### Product Increment 4

***
