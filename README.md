# Project Title
## Contents
- [Team](#team)
- [Vision and Scope](#vision-and-scope)
- [Requirements](#requirements)
    - [Use case diagram](#use-case-diagram)
    - [User stories and prototypes](#user-stories-and-prototypes)
- [Architecture and Design](#architecture-and-design)
    - [Domain Model](#domain-model)
- [Release Plan](#release-plan)
    - [Release 1](#release-1)
    - [Release 2](#release-2)
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
> - Secção de Adotante na aplicação.
> - Implementação de um sistema distribuido (Vários clientes para um servidor).




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

Como organização pretendo ajudar o máximo de animais que estão em risco de vida ou a sofrer maltratos, quero poder gerir os voluntários, as doações e os financiadores de forma a ter acesso a todos os dados e de forma a que a organização prospere. Para isso necessito de ter acesso a todos os dados da organização.


###### Acceptance Criteria
> - Quando o administrador entra no sistema deverá ter a possibilidade de analisar a lista de todos os animais, de todos os voluntários, de todos os investimentos, de todos os doadores, dos veterinários, stock de alimentos/medicamentos/utensílios, de todos os pedidos de ajuda que a organização recebe e dos que já recebeu;
> - Aceder à lista de todos os animais, incluindo detalhes;
> - Aceder à lista de todos os voluntários, incluindo detalhes;
> - Aceder à lista de todos os investimentos, incluindo informações;
> - Aceder à lista de todos os doadores, incluindo informações e histórico de doações;
> - Aceder às informações sobre veterinários colaboradores;
> - Fazer uma gestão do estoque de alimentos, medicamentos e utensílios;
> - Aceder à lista de todos os pedidos de ajuda recebidos, incluindo detalhes sobre casos;
> - Aceder às informações sobre os pedidos de ajuda atendidos, incluindo detalhes sobre os casos;

###### Prototype
![Use case diagram](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgUseCase/Administrador.png)

***

##### User Story 2
**Voluntários**

Como voluntário desta organização quero ter a possibilidade de controlar a alimentação dos animais, quais os animais existentes dentro da instituição e bem como o estado de saúde dos mesmos, para isso preciso de ter acesso à informação total sobre dos animais.

###### Acceptance Criteria
> - Quando o voluntário entra no sistema deverá ter a possibilidade de analisar a lista de todos os animais;
> - Este tem de ter a possibilidade de selecionar um animal, caso pretenda ter mais informações a cerca do mesmo;
> - O voluntário tem de ter acesso a informar os veterinários no sistema, que existe um animal doente;
> - Este tem a possibilidade de registar uma nova adoção e vem como um novo animal:

###### Prototype
![Use case diagram](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgUseCase/Voluntarios.png)

***

##### User Story 3
**Investidores/Financiadores/Doadores**

Como doador ou investidor quero ter a possibilidade de analisar os recursos em falta da organização para poder contribuir da melhor forma dentro das minhas possiblidades e do que a organização precisa. Além disso quero ter a possibilidade de saber onde estão a ser gastos os meus investimentos. Para isso preciso de ter acesso às necessidades da organização.


###### Acceptance Criteria

> - Quando o doador entra no sistema deverá ter a possibilidade de analisar a lista de todos os investimentos feitos pelo mesmo;
> - Este tem de ter a possibilidade obter informação à cerca dos recursos em falta da organização;
> - Receber atualizações sobre o progresso dos investimentos feitos;

###### Prototype
![Use case diagram](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgUseCase/Doadores.png)

***

## Architecture and Design

#### Domain Model
![Use case diagram](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgUseCase/ModeloDominio.png)

***

#### Risk
**Threshhold of Sucess**
- (metas ao longo dos sprints)
-
-

**Risk List**
- RSK1 - PxI: 4x5=20; Falhar a conclusão das metas autopropostas no planeamento atempadamente devido à falta de experiência da equipa com projetos deste tipo
- RSK2 - PxI: 2x5=12; Potenciais problemas de compatibilidade no desenvolvimento devido à tecnologia escolhida para o projeto (JavaFx)
- RSK3 - PxI: 2x4=8; Alterações ao projeto inicial durante o desenvolvimento podem causar um atraso no processo
- RSK4 - PxI: 2x5=10; Erros técnicos em fases críticas do projeto que podem implicar numa necessidade de revisão de grandes partes do projeto numa fase mais avançada, o que pode causar uma grande perda de tempo

**Mitigation Actions**
- RSK1 - MS: Verificar durante os sprints se estamos dentro das metas previstas e considerar mudanças no planejamento do tempo investido durante essas verificações
- RSK2 - AS: Pesquisa prévia de projetos que usem o mesmo tipo de tecnologia e implementam componentes semelhantes aos da nossa aplicação
- RSK3 - MS: Comunicação contínua sobre o desenvolvimento do projeto com o cliente, de modo a confirmar que tudo está dentro do planejado
- RSK4 - MS: Fazer um planejamento da arquitetura adequado ao projeto antes de começar a trabalhar diretamente no mesmo

***

## Release Plan
### Release 1
#### Goal
- Interface visual implementada e base de dados dos diferentes usuários da plataforma

#### Sprint 1
##### Dates 
5 a 18 de novembro, 2 semanas

##### Weight
//

##### Release
V0

##### Goal
- Planejamento e mockups da interface completos, com início da implementação dos principais e lineup inicial das bases de dados

#### Sprint 2
##### Dates
19 de novembro a 2 de dezembro, 2 semanas

##### Weight
//

##### Release
V0.1

##### Goal
- Finalização do desenvolvimento e implementação de todos os aspetos visuais da aplicação e das bases de dados com os diferentes usuários (voluntários, administradores)
- Desenvolvimento das funcionalidades simples da aplicação

***

### Release 2
#### Goal
Conclusão da aplicação

#### Sprint 3
##### Dates 
3 a 24 de dezembro, 3 semanas

##### Weight
//

##### Release
V1.0

##### Goal
- Conclusão das funcionalidades finais da aplicação para cada usuário
- Testes extensos das funcionalidades para uso final

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
