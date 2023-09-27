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

This section contains a summary of the problem that the project will solve. It should provide a brief history of the
problem and an explanation of how the organization justified the decision to build software to address it. This section
should cover the reasons why the problem exists, the organization's history with this problem, any previous projects
that were undertaken to try to address it, and the way that the decision to begin this project was reached.


##### Stakeholders
- Organizações Sem Fins Lucrativos: necessidade de um software de gestão que facilite o acompanhamento e a administração eficaz de projetos, tarefas e voluntários.
- Voluntários: necessidade de uma plataforma que simplifique a gestão de tarefas, projetos e horários do voluntariado.
- Animais: melhorias no suporte e na eficácia das organizações/voluntários, resultando em uma maior qualidade de serviço e um alcance mais amplo, bem como o melhor fornecimento de condições de apoio.
- Investidores/Financiadores/Doadores: necessidade de uma ferramenta que mostre onde estão a ser usados os seus donativos da organização em questão, vem como uma plataforma para gerir todos os “supporters”. 
- Adotantes: necessidade de um sistema onde seja possível ver as pessoas que adotaram animais na instituição, para fazer um historial do animal.

This is a bulleted list of the stakeholders. Each stakeholder may be referred to by name, title, or role ("support group
manager," "CTO," "senior manager"). The needs of each stakeholder are described in a few sentences.


##### Users

- Organização Sem Fins Lucrativos
- Voluntários
- Investidores/Financiadores/Doadores
- Adotantes

This is a bulleted list of the users. As with the stakeholders, each user can either be referred to by name or role ("
support rep," "call quality auditor," "home web site user")however, if there are many users, it is usually inefficient
to try to name each one. The needs of each user are described.

***

#### Vision & Scope of the Solution

##### Vision statement
O objetivo visa facilitar a coordenação entre voluntários que pretendam cuidar dos animais, membros da associação que cuida dos animais, veterinários e famílias temporárias de animais. Assim, o software visa melhorar a condição de vida dos animais, bem como fomentar a adoção e facilitar o trabalho dos contribuintes da associação.

The goal of the vision statement is to describe what the project is expected to accomplish. It should explain what the
purpose of the project is. This should be a compelling reason, a solid justification for spending time, money, and
resources on the project. The best time to write the vision statement is after talking to the stakeholders and users and
writing down their needs; by this time, a concrete understanding of the project should be starting to jell.


##### List of features
- Gestão dos voluntários (Adicionar, Remover, Editar);
- Marcação de um horário para fazer voluntariado e cuidar do animal;
- Atribuição de um animal a um voluntário, de modo a garantir que todos os animais e voluntários têm acesso;
- Notificações para os voluntários sobre os afazeres;
- Perfis dos animais na associação;
- Gestão dos animais (Adicionar, Remover, Editar);
- Registo de adoções dos animais;
- Registo de doações;

This section contains a list of features. A feature is as a cohesive area of the software that fulfills a specific need
by providing a set of services or capabilities. Any software package, in fact, any engineered product, can be broken
down into features. The project manager can choose the number of features in the vision and scope document by changing
the level of detail or granularity of each feature, and by combining multiple features into a single one. Sometimes
those features are small ("screw-top cap," "holds one liter of liquid"); sometimes they are big ("four-wheel drive," "
seats seven passengers"). It is useful to describe a product in about 10 features in the vision and scope document,
because this usually yields a level of complexity that most people reading it are comfortable with. Adding too many
features will overwhelm most readers.

Each feature should be listed in a separate paragraph or bullet point. It should be given a name, followed by a
description of the functionality that it provides. This description does not need to be detailed; it can simply be a few
sentences that give a general explanation of the feature. However, if there is more information that a stakeholder or
project team member feels should be included, it is important to include that information. For example, it is sometimes
useful to include a use case (see Chapter 6), as long as it is written in such a way that all of the stakeholders can
read and understand it.


##### Features that will not be developed
- Processamento de pagamentos das doações;
- Gestão de stock (Alimentação, Medicamentos, etc);


Features are often left out of a project on purpose. When a feature is explicitly left out of the software, it should be
added to this section to tell the reader that a decision was made to exclude it. For example, one way to handle an
unrealistic deadline is by removing one or more features from the software, in which case the removed features should be
moved into this section. The reason these features should be moved rather than deleted from the document is that
otherwise, readers might assume that they were overlooked and bring them up in a review. This is especially important
during the review of the document because it allows everyone to agree on the exclusion of the feature (or object to it).


##### Risk
- Se houver um erro na aplicação, a vida do animal pode ser colocada em risco;
- A informação dos voluntários e dos animais deve ser mantida em segurança;

Risks are potential uncertainties or adverse events that can impact the achievement of objectives and require proactive
management to mitigate or minimize their negative effects.


##### Assumptions
- Os voluntários estão dispostos a usar a aplicação;
- As associações já têm os meios necessários para usar a aplicação, como acesso a internet e dispositivos.

This is the list of assumptions that the stakeholders, users, or project team have made. The team should hold a
brainstorming session to come up with a list of assumptions. (See Chapter 3 for more information on assumptions.)

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

As ..., I want ..., so ...

###### Acceptance Criteria

```
An acceptance test should be written here
```

###### Prototype

A prototype of user story 1 should be here. You can see in (#use-case-diagram) how to import an image.

***

##### User Story 2

***

##### User Story 3

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
