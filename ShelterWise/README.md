# Shelter Wise
## Contents
- [Team](#team)
- [Vision and Scope](#vision-and-scope)
- [Requirements](#requirements)
    - [Use case diagram](#use-case-diagram)
    - [Mockups](#mockups)
    - [User stories](#user-stories)
- [Definition of Done](#definition-of-done)
- [Architecture and Design](#architecture-and-design)
    - [Domain Model](#domain-model)
    - [Risk Plan](#risk-plan)
- [Pre Game](#pre-game)
  - [Sprin 0 Plan](#sprint-0-plan)
  - [Sprin 1 Plan](#sprint-1-plan)
  - [Sprin 2 Plan](#sprint-2-plan)
  - [Sprin 3 Plan](#sprint-3-plan)
- [Release Plan](#release-plan)
    - [Release 1](#release-1)
    - [Release 2](#release-2)
- [Implementation](#implementation)
    - [Product Increment 1](#product-increment-1)
    - [Product Increment 2](#product-increment-2)
    - [Product Increment 3](#product-increment-3)
    - [Product Increment 4](#product-increment-4)

## Team
- Diogo Gomes - 2021137427 - a2021137427@isec.pt
- Diogo Fatia - 2020152804 - diogofatia@gmail.com
- Ana Cardoso - 2021133158 - cardosinha2003@gmail.com
- Bruno Fonseca - 2021129658 - brunofonseca2003@gmail.com
- Ricardo Tavares - 2021144581 - a2021144581@isec.pt

***

## Vision and Scope
#### Problem Statement
##### Project background
Atualmente existem muitas organizações de proteção animal sem fins lucrativos que operam com recursos limitados e dependem muito do voluntariado. No entanto, a gestão de todos os animais resgatados pelas mesmas pode se tornar um processo complexo, uma vez que muitas das vezes estas não têm acesso a sistemas de gestão elaborados e eficazes o que pode pôr em risco o espaço e os animais.

##### Stakeholders
> - Administrador: necessidade de um software de gestão que facilite o acompanhamento e a administração eficaz de projetos, tarefas, voluntários, animais e doadores.
> - Voluntários: necessidade de uma plataforma que simplifique a gestão de tarefas, projetos e horários do voluntariado.
> - Doadores: necessidade de uma ferramenta que mostre onde estão a ser usados os seus donativos da organização em questão, vem como uma plataforma para gerir todos os “supporters”. 
> - Adotantes: necessidade de um sistema onde seja possível ver as pessoas que adotaram animais na instituição, para fazer um historial do animal.
> - Veterinários: necessidade de um sistema de notifcações para quando necessário informar os veterinários que certo animal está com problemas.

##### Users
> - Administrador
> - Voluntários
> - Doadores

***

#### Vision & Scope of the Solution
##### Vision statement
Este projeto é um investimento estratégico na transformação da gestão de um abrigo de animais. Ambicionamos otimizar o cuidado com os animais, a coordenação dos voluntários, a manutenção das instalações, a gestão de recursos e a interação com os doadores. O tempo e recursos financeiros investidos são fundamentais para alcançar uma operação mais eficiente e sustentável, que não apenas melhora a qualidade de vida dos animais, mas também facilita a adoção e fortalece a colaboração comunitária.

##### List of features
> - **Gestão de Voluntários**
>   - Lista e informações sobre cada voluntário (separados por categorias como tratadores, participam em campanhas, participam com mão de obra na construção de casas para os animais ou fazem limpeza - eventualmente permitir apenas certas categorias de voluntários fazerem alterações às informações do animal);
>   - Lista de veterinários e informações sobre o mesmos.
> - **Gestão de Animais**
>   - Plano de alimentação dos mesmos, tendo em conta o seu estado de saude e bem como a ultima vez que o mesmo comeu;
>   - Lista e informações de cada animal (raça, onde se encontra, estado de saúde, voluntário encarregado do animal);
>   - Registo de adoções dos animais e das doações no mesmo local (quem doa dinheiro aparece no mesmo local que quem adotou um animal;
> - **Gestão de Instalações**
>   - Lista de casotas e informação da sua ocupação;
>   - Lista de stock;

##### Features that will not be developed
> - Processamento de pagamentos das doações.
> - Gestão de stock (Alimentação, Medicamentos, etc).
> - Criação de um website para marketing do software. 
> - Secção de Adotante na aplicação.
> - Implementação de um sistema distribuido (Vários clientes para um servidor).
> - Gestão dos pedidos de ajuda.


##### Assumptions
> - A primeira versão da aplicação será um protótipo de uma aplicação desktop.
> - Os voluntários estão dispostos a usar a aplicação.
> - As associações já têm os meios necessários para usar a aplicação, como acesso a internet e dispositivos.



***

## Requirements
#### Use Case Diagram

![Use case diagram](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/ShelterWise/imgUseCase/Todo.jpg)


***

### Mockups
**Login**

![Login Page](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/ShelterWise/src/main/resources/com/example/shelterwise/mockups_imgs/948f84d01c5ddb5c22145b28ada64c1e.png)

**Painel de Administrador**

![Painel de Admin.](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/ShelterWise/src/main/resources/com/example/shelterwise/mockups_imgs/89a3ff465213e3f67020c4a968708d4e.png)

**Painel de voluntário**

![Painel de Vol.](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/ShelterWise/src/main/resources/com/example/shelterwise/mockups_imgs/89f6506e749cd0b2eaa78f38717efd8c.png)

**Lista de Voluntários**

![Lista de Voluntários](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/ShelterWise/src/main/resources/com/example/shelterwise/mockups_imgs/41e90f652d91e53f68d8421189d1b238.png)

**Lista de Animais**

![Lista de Animais](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/ShelterWise/src/main/resources/com/example/shelterwise/mockups_imgs/3967a40f65af05a846e0113317cdae0c.png)

**Lista de Veterinários**

![Lista de Veterinários](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/ShelterWise/src/main/resources/com/example/shelterwise/mockups_imgs/712d17d1c6a722c6c9608cd8853dd55c.png)
***

### User Stories
- [US1 - Adminstrador (Gerir voluntários)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/1)
- [US2 - Administrador (Mostrar doações)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/2)
- [US3 - Administrador (Gerir instalações)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/3)
- [US4 - Voluntários (Gerir a atividade dos animais)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/4)
- [US5 - Voluntários (Gerir animais)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/5)
- [US6 - Voluntários (Gerir informação do animal)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/6)
- [US7 - Doadores (Gerir recursos organização)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/7)
- [US8 - Doadores (Gerir historial das doações)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/8)

***

## Definition of done
(This section is already written, do not edit)
It is a collection of criteria that must be completed for a User Story to be considered “done.”

1. All tasks done:
- CI – built, tested (Junit), reviewed (SonarCloud)
- Merge request to qa (code review)
2. Acceptance tests passed
3. Accepted by the client
4. Code merged to main

***

## Architecture and Design
#### Domain Model
![Use case diagram](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/ShelterWise/imgUseCase/ModeloDominio.png)

***
### Risk Plan
***

**Threshhold of Sucess**
- Concluir pelo menos 70% das funcionalidades planeadas até ao primeiro release, com atenção aos possíveis riscos técnicos que possam surgir (RSK6) e atrasar o desenvolvimento do projeto.
- Alcançar uma taxa de resolução dos bugs detetados ao longo do primeiro e segundo sprints em 80% até o final do segundo sprint, através de testes contínuos para evitar problemas de compatibilidade (RSK5).
- Garantir que o programa na sua primeira release já é intuitivo o suficiente com a informação do feedback para 80% dos utilizadores (RSK8)
- Garantir que o programa na release final está protegido contra toads as vulnerabilidades conhecidas para o tipo de tecnologias que o programa usa (RSK2)
- Garantir que o programa na release final já suporta números elevados de utilizadores ao mesmo tempo

**Risk List**
- RSK1 - PxI: 4x5=20; Devido à falta de experiência da equipa com este tipo de projetos, pode acontecer uma falha na conclusão das metas propostas
- RSK2 - PxI: 2x5=10; Devido a possíveis falhas de segurança na implementação do projeto, podem acontecer vulnerabilidades que comprometam a segurança da informação dos utilizadores do programa
- RSK3 - PxI: 2x5=10;  Devido a possíveis erros técnicos em fases críticas de desenvolvimento do projeto, pode surgir a necessidade de fazer refactor a grande parte do código
- RSK4 - PxI = 2x5=10; Devido à falta de testes suficientes, o programa pode não aguentar o overload de utilizadores quando em uso
- RSK5 - PxI: 2x4=8; Devido a potenciais problemas de compatibilidade no desenvolvimento do projeto devido às tecnologias usadas, podem surgir erros de compatibilidade com o nosso planejamento
- RSK6 - PxI: 2x4=8; Devido a alterações ao planejamento inicial, podem acontecer atrasos no desenvolvimento
- RSK7 - PxI: 2x4=8; Devido à falta de user feedback suficiente, o programa pode não ser intuitivo que chegue e a taxa de adoção ao programa ser baixa

**Mitigation Actions (threats>=20)**
- RSK1 - MS: Verificar durante os sprints se estamos dentro das metas previstas e considerar mudanças no planeamento do tempo investido durante essas verificações

**Nota:** _Contingency Plan (CP), Avoidance Strategy (AS) or Minimization Strategy (MS)_

***
## Pre Game
### Sprint 0 Plan
- Goal: Terminar o README as Mockups e os boards
- Dates: from 12/Oct to 26/Oct, 2 weeks
- Roles:
  - Product Owner: Ricardo Tavares
  - Scrum Master: Diogo Fatia
- Sprint 0 Backlog (don't edit this list):
    - Task1 – Write Team
    - Task2 – Write V&S
    - Task3 – Write Requirements
    - Task4 – Write DoD
    - Task5 – Write Architecture&Design
    - Task6 – Write Risk Plan
    - Task7 – Write Pre-Gane
    - Task8 – Write Release Plan
    - Task9 – Write Product Increments
    - Task10 – Create Product Board
    - Task11 – Create Sprint 0 Board
    - Task12 – Write US in PB, estimate (SML), prioritize (MoSCoW), sort
    - Task13 – Create repository with “GPS Git” Workflow
***

## Release Plan
### Release 1
- Goal: (MVP - description) 
  - Para a primeira release iremos ter a possibilidade a parte da gestão das boxes dos animais, gestão de atividades dos animais e o estado da adoção do animal;
- Dates: 28-30/Nov
- Release: V1.0

***

### Release 2
- Goal: (Final release – description​)
  - Conclusão das funcionalidades finais da aplicação para cada utilizador;
  - Testes extensos das funcionalidades para uso final;
- Date: 12-15/Dec
- Release: V2.0

***

## Increments
#### Sprint 1
##### Sprint Plan
- Goal: what’s the goal for this sprint
- Dates: from 24-27/Oct to 7-10/Nov, 3 weeks
- Roles:
  - Product Owner: name
  - Scrum Master: name
- To do:
  - US1 - name
    - Task1: Some task
    - Task2: Some task
  - US2 - name
    - Task1: Some task
    - Task2: Some task
- Story Points (Small: 1, Medium: 2, Large: 3): 
- Analysis: short analysis of the planning meeting

##### Sprint Review
- Analysis: what was not done or what was added (Link to US or Task from the PB)
- Story Points: 2S+1M+2X+2H
- Version: 0.1 
- Client analysis: client feedback
- Conclusions: what to add/review

##### Sprint Retrospective
- What we did well:
    - A
- What we did less well:
    - B
- How to improve to the next sprint:
    - C

***

#### Sprint 2
##### Sprint Plan
- Goal: what’s the goal for this sprint
- Dates: from 24-27/Oct to 7-10/Nov | 14-17/Nov, 2 | 3 weeks
- Roles:
  - Product Owner: name
  - Scrum Master: name
- To do:
  - (list of US or Tasks from the PB)
  - US1: As … I want … so that …
  - Task1: Some task
- Story Points: 2S+3M+3X+2H
- Analysis: short analysis of the planning meeting

##### Sprint Review
- Analysis: what was not done or what was added (Link to US or Task from the PB)
- Story Points: 2S+1M+2X+2H
- Version: 0.1 
- Client analysis: client feedback
- Conclusions: what to add/review

##### Sprint Retrospective
- What we did well:
    - A
- What we did less well:
    - B
- How to improve to the next sprint:
    - C
***

#### Sprint 3
##### Sprint Plan
- Goal: what’s the goal for this sprint
- Dates: from 24-27/Oct to 7-10/Nov | 14-17/Nov, 2 | 3 weeks
- Roles:
  - Product Owner: name
  - Scrum Master: name
- To do:
  - (list of US or Tasks from the PB)
  - US1: As … I want … so that …
  - Task1: Some task
- Story Points: 2S+3M+3X+2H
- Analysis: short analysis of the planning meeting

##### Sprint Review
- Analysis: what was not done or what was added (Link to US or Task from the PB)
- Story Points: 2S+1M+2X+2H
- Version: 0.1 
- Client analysis: client feedback
- Conclusions: what to add/review

##### Sprint Retrospective
- What we did well:
    - A
- What we did less well:
    - B
- How to improve to the next sprint:
    - C
***
