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
    - [Tabela Tarefas](#tabela-tarefas)
    - [Risk Plan](#risk-plan)
- [Pre Game](#pre-game)
  - [Sprint 0](#sprint-0)
  - [Sprint 1](#sprint-1)
  - [Sprint 2](#sprint-2)
  - [Sprint 3](#sprint-3)
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
Este projeto é um investimento para transformar a gestão de um abrigo de animais. Ambicionamos otimizar o cuidado com os animais, a coordenação dos voluntários, a manutenção das instalações, a gestão de recursos e a interação com os doadores. Para isso, temos a visão de gerir os voluntários de forma a coordenar o que cada um tem de fazer e gerir os doadores para que a organização saiba quem é que doa o que e para o que. Além disso queremos gerir os animais, de forma a saber as informações de cada um, bem como gerir stock, medicamentos e instalações. 

##### List of features
> - **Gestão de Voluntários**
>   - Lista e informações sobre cada voluntário (separados por categorias como tratadores, participam em campanhas, participam com mão de obra na construção de casas para os animais ou fazem limpeza - eventualmente permitir apenas certas categorias de voluntários fazerem alterações às informações do animal);
>   - Lista de veterinários e informações sobre o mesmos.
> - **Gestão de Animais**
>   - Plano de alimentação dos mesmos, tendo em conta o seu estado de saude e bem como a ultima vez que o mesmo comeu;
>   - Lista e informações de cada animal (raça, onde se encontra, estado de saúde);
>   - Adotar animal;
> - **Gestão de Instalações**
>   - Lista de casotas e informação da sua ocupação;
>   - Lista de stock;
> - **Gestão de Adoções e Doações**
>   - Registo de adoções dos animais e das doações no mesmo local (quem doa dinheiro aparece no mesmo local que quem adotou um animal);


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

![Use case diagram](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgUseCase/Todo.jpg)


***

### Mockups
- **Painel de Login**

![Login Page](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgMockups/mockup_login.png)

- **Painel de Administrador**

![Painel de Admin.](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgMockups/mockup_admin.png)

- **Painel de Voluntário**

![Painel de Vol.](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgMockups/mockup_voluntario.png)

- **Lista de Voluntários**

![Lista de Voluntários](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgMockups/mockup_lista_voluntarios.png)

- **Info Voluntários**

![Info Voluntários](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgMockups/mockup_info_voluntarios.png)

- **Edit Voluntário**

![Edit Voluntários](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgMockups/mockup_edit_voluntarios.png)

- **Lista de Animais**

![Lista de Animais](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgMockups/mockup_lista_animais.png)

- **Info Animais**

![Info Animais](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgMockups/mockup_info_animais.png)

- **Adicionar Animal**

![Edit Animais](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgMockups/mockup_edit_animais.png)

- **Lista de Veterinários**

![Lista de Veterinários](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgMockups/mockup_lista_veterinarios.png)

- **Info Veterinários**

![Info Veterinários](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgMockups/mockup_info_veterinarios.png)
***

### User Stories
- [US1 - Adminstrador (Gerir voluntários)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/1)
- [US2 - Administrador (Mostrar doações)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/2)
- [US3 - Administrador (Gerir instalações)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/3)
- [US4 - Voluntários (Gerir a atividade dos animais)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/4)
- [US5 - Voluntários (Ver lista de animais)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/5)
- [US6 - Voluntários (Gerir informação do animal)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/6)
- [US7 - Voluntários (Gerir adoações)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/22)
- [US8 - Administrador (Gerir produtos)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/36)
- [US9 - Administrador (Gerir animais)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/37)
- [US10 - Administrador (Gerir veterinários)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/38)
- [US11 - Voluntários (Ver perfil)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/39)
- [US12 - Voluntários (Editar e ver produtos)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/40)
- [US13 - Voluntários (Informação casotas)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/41)
- [US14 - Voluntários (Ver veterinários)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/42)
- [US15 - Administrador (Login)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/47)
- [US16 - Voluntários (Login)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/48)
- [US17 - Gerir doações ](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/120)
- [US18 - Administrador (Gerir Calendário)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/128)
- [US19 - Voluntário (Gerir Calendário)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/130)
- [US20 - Administrador (Gerir beneméritos)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/140)

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
![Use case diagram](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgUseCase/ModeloDominio.png)

#### Tabela Tarefas
![Tabela de tarefas](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgUseCase/TabelaDeTarefas.png)

***
### Risk Plan
***

**Threshhold of Sucess**
- Concluir ≥ 50% das US planeadas até ao primeiro release.
- Garantir que o programa na sua primeira release já é intuitivo o suficiente com a informação do feedback para maior parte dos utilizadores.
- Permitir a gestão dos animais, voluntários, instalações, da atividade dos animais e o estado de adoção do animal.

**Risk List**
- RSK1 - PxI: 3x5=15; Devido à falta de experiência da equipa com este tipo de projetos pode acontecer uma falha na conclusão das metas propostas.
- RSK3 - PxI: 1x5=5; Devido a possíveis erros técnicos em fases críticas de desenvolvimento do projeto pode surgir a necessidade de refazer parte do código.
- RSK5 - PxI = 2x5=10; Dificuldades na parte da apredizagem do funcionamento da aplicação, por parte dos elementos da organização.
- RSK6 - PxI: 1x4=4; Devido a potenciais problemas de compatibilidade no desenvolvimento do projeto devido às tecnologias usadas podem surgir erros de compatibilidade com o nosso planeamento.

**Mitigation Actions (threats>=20)**
- RSK1 - MS: Verificar durante os sprints se as metas propostas estão a ser cumpridas e se necessário falar com o cliente para negociar melhor as US a serem desenvolvidas.

**Nota:** _Contingency Plan (CP), Avoidance Strategy (AS) or Minimization Strategy (MS)_

***
## Pre Game
### Sprint 0 Plan
- Goal: Terminar o README as Mockups e os boards
- Dates: from 12/Oct to 26/Oct, 2 weeks
- Roles:
  - Product Owner: **Ricardo Tavares**
  - Scrum Master: **Diogo Fatia**
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
- Dates: 30/Nov
- Release: V1.0

***

### Release 2
- Goal: (Final release – description​)
  - Conclusão das funcionalidades finais da aplicação para cada utilizador;
  - Testes extensos das funcionalidades para uso final;
- Date: 14/Dec
- Release: V2.0

***

## Increments
#### Sprint 1
##### Sprint Plan
- Goal: Criar as funcionalidades básicas para os Administradores e Voluntários
- Dates: from 26/Oct to 16/Nov, 3 weeks
- Roles:
  - Product Owner: **Ricardo Tavares**
  - Scrum Master: **Diogo Fatia**
- To do:
  - **US3 - Administrador (Gerir instalações)**
    - Task1: Visualizar informação sobre as boxes do estabelecimento.
    - Task2: Visualizar quais são os animais dentro de cada boxe.
    - Task3: Alterar, remover ou adicionar informações das boxes.  
  - **US4 - Voluntários (Gerir a atividade dos animais)**
    - Task1: Visualizar uma lista com todos os animais não adotados. 
    - Task2: Visualizar o historial da alimentação e banhos do animal. 
    - Task3: Visualizar o estado de saúde do animal. 
    - Task4: Visualizar o estado de alimentação do animal. 
    - Task5: Alterar, remover ou adicionar informações dos animais.  
  - **US7 - Voluntários (Gerir adoções)** 
    - Task1: Visualizar uma lista com todos os animais não adotados.
    - Task2: Inserir os dados do adotante.
    - Task3: Criar uma nova adoção.
    - Task4: Visualizar lista de adoções.
  - **US1 - Administrador (Gerir voluntários)**
    - Task1: Visualizar a lista de voluntários
    - Task2: Alterar, remover ou adicionar informações dos voluntários
  - **US5 - Voluntários (Gerir animais)**
    - Task2: Alterar, remover ou adicionar informações dos animais
  - **US15 - Administrador (Login)**
    - Task1: Efetuar o login na aplicação
  - **US16 - Voluntários (Login)**
    - Task1: Efetuar o login na aplicação
  - Task1 - Criar uma base de dados
  - **US8 - Administrador (Gerir produtos)**
    - Task1: Ver os produtos em stock
    - Task2: Criar um novo produto no stock
    - Task3: Editar um produto do stock
  - **US12 - Voluntários (Gerir produtos)**  
    - Task1: Ver os produtos em stock
    - Task2: Editar um produto em stock

- Story Points: 2H+2H+3H+2H+4H+3H+3H+3H+2H+3H+3H+4H+2H+4H+1H+1H+5H+2H+2H+1H+1H+1H = 54H 
- Analysis: O utilizador deve ter a possibilidade de efetuar uma adoção e veem como gerir os animais, instalações e das casotas. O mesmo também tem de ter uma interface de login, aberta a todos os utilizadores da aplicação.

##### Sprint Review
- Analysis: 
  - [US12 - Como voluntário quero poder editar e ver produtos no stock](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/40)(New US)
- Story Points: 3S+5M+0X+0H = 9+35+0+0 = 44
- Version: 0.1 
- Client analysis: O cliente aprovou o projeto.
- Conclusions: O cliente pediu para fazermos algumas alterações em termos de interface, e para corrigir alguns bugs que surgiram durante a demonstração final da sprint. 

##### Sprint Retrospective
- What we did well:
    - Boa gestão de tempo por parte de cada elemento facilitou um pouco a parte do desenvolvimento de cada uma das tasks.
- What we did less well:
    - Deveríamos ter pensado melhor acerca do design e de quais as informações que iriam ser demonstradas em cada view (interface), dependendo de cada utilizador.
    - Falta de comunicação entre a execução das diversas tasks, para verificar se estamos todos "alinhados".
- How to improve to the next sprint:
    - Aquando da finalização de uma task realizar posteriormente os testes unitários relativos à mesma.
    - Fazer uma melhor "distribuição" de tasks por todos os elementos do grupo.
    - Melhorar um pouco a comunicação entre Product Owner e Scrum Master, e o grupo em si.

***

#### Sprint 2
##### Sprint Plan
- Goal: Adicionar funcionalidades aos voluntários e administradores.
- Dates: from 23/Nov to 7/Dez, 2 weeks
- Roles:
  - Product Owner: **Bruno Fonseca**
  - Scrum Master: **Ana Cardoso**
- To do:
  - **US2 - Administrador (Gerir doações)**
    - Task1: Visualizar a lista de doações
    - Task2: Filtrar a lista de doações
  - **US6 - Voluntários (Gerir informação do animal)**
    - Task1: Visualizar a informação de um animal
    - Task2: Adicionar e editar informação de um animal
  - **US10 - Administrador (Gerir veterinários)**
    - Task1: Visualizar a lista de veterinários
    - Task2: Alterar e adicionar informações dos veterinários
  - **US11 - Voluntários (Ver perfil)**
    - Task1: Visualizar informação do seu perfil
    - Task2: Alterar informação do seu perfil
  - **US13 - Voluntários (Informação casotas)**
    - Task1: Visualizar lista de casotas
    - Task2: Visualizar informação de uma casota em especifico
  - **US14 - Voluntários (Ver veterinários)**
    - Task1: Visualizar lista de veterinários
    - Task2: Visualizar informação de um veterinário
  - **US17 - Administrador (Inserir doações)**
    - Task1: Adicionar doação
  - Task2 - Correção de bugs e alterações pedidas pelo cliente (Parte Animais)
  - Task3 - Pedido do cliente para ter textos em português
    
- Story Points: 2H+2H+1H+1H+2H+2H+2H+2H+2H+1H+1H+1H+2H+2H+1H = 24H
- Analysis: Existir a possibilidade dos voluntários terem várias tarefas associadas a desenvolver no estabelecimento, e ter a parte do administrador completa, exceto a parte do calendario de tarefas dos voluntários.

##### Sprint Review
- Analysis:
  - [Dado que o administrador quer editar uma adoção, quando seleciona o botão para editar adoção (botão que existe na página ao ver dados do animal), deve aparecer um formulário com os dados pré-definidos para depois serem editados.](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/105) Incompleto.
- Story Points: 2S+2M+0X+0H = 16+10+0+0 = 26
- Version: 0.2
- Client analysis: O cliente aprovou o projeto.
- Conclusions: Devem ser feitas algumas alterações relativas ao planeamento do projeto.

##### Sprint Retrospective
- What we did well:
    - Aquando da finalização das tasks das US, implementamos de imediato os testes unitários a serem efetuados.
- What we did less well:
    - Nada a apontar.
- How to improve to the next sprint:
    - Melhorar o esclarecimento em relação ao que cada taks tem como objetivo.
***

#### Sprint 3
##### Sprint Plan
- Goal: Realizar todas as US propostas pois essas vão gerar o produto final, fazer a verificação de bugs finais e realizar a proposta do cliente para a criação de um calendario de agendamento de tarefas para os voluntários.
- Dates: from 30/Nov to 14/Dez, 2 weeks
- Roles:
  - Product Owner: **Diogo Gomes**
  - Scrum Master: **Diogo Fatia**
- To do:
  - **US18 - Administrador (Gerir calendário de tarefas)**
    - Task1: Visualizar o calendario
    - Task2: Filtrar as diferentes tarefas a serem visiveis
    - Task3: Adicionar e editar uma tarefa
    - Task4: Visualizar informações sobre a tarefa (Quais voluntarios inseridos, notas caso seja necessário)
  - **US19 - Voluntário (Gerir calendário de tarefas)**
    - Task1: Visualizar o calendario
    - Task2: Filtrar as diferentes tarefas a serem visiveis
    - Task3: Inscrever-se numa tarefa
    - Task4: Visualizar informações sobre a tarefa (Quantos voluntarios inseridos, informações adicionais)
  - **US20 - Administrador (Gerir beneméritos)**
    - Task1: Visualizar lista de beneméritos
    - Task2: Filtrar a lista de beneméritos
    - Task3: Inserir e editar beneméritos
  
  -Task1: Filtrar os diferentes acessos a aplicação (aplicar as restricoes de visibilidade por cargos)
    
- Story Points: 4H+2H+3H+2H+4H+2H+2H30M+2H+2H40M+2H+1H+1H = 28H10M
- Analysis: short analysis of the planning meeting

##### Sprint Review
- Analysis:
  - [US 18 - Visualizar informações sobre a tarefa (Quais voluntarios inseridos, notas caso seja necessário)](https://gitlab.com/brun0f0nseca/gps_g21/-/issues/134)
- Story Points: 2S+1M+2X+2H
- Version: 0.3
- Client analysis:
- Conclusions: Foram concluídas todas as User Stories propostas para o projeto. 

##### Sprint Retrospective
- What we did well:
    - Identificação e correção de problemas existentes no código anterior 
- What we did less well:
    - Gestão do tempo durante a sprint
***
