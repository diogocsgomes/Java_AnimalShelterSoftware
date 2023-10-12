# Shelter Wise
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
> - Organizações Sem Fins Lucrativos: necessidade de um software de gestão que facilite o acompanhamento e a administração eficaz de projetos, tarefas e voluntários.
> - Voluntários: necessidade de uma plataforma que simplifique a gestão de tarefas, projetos e horários do voluntariado.
> - Animais: melhorias no suporte e na eficácia das organizações/voluntários, resultando em uma maior qualidade de serviço e um alcance mais amplo, bem como o melhor fornecimento de condições de apoio.
> - Doadores: necessidade de uma ferramenta que mostre onde estão a ser usados os seus donativos da organização em questão, vem como uma plataforma para gerir todos os “supporters”. 
> - Adotantes: necessidade de um sistema onde seja possível ver as pessoas que adotaram animais na instituição, para fazer um historial do animal.
> - Veterinários: necessidade de um sistema de notifcações para quando necessário informar os veterinários que certo animal está com problemas.

##### Users
> - Organização Sem Fins Lucrativos.
> - Voluntários (sendo estes os utilizadores principais uma vez que estariam a trabalhar no abrigo para animais).
> - Doadores.

***

#### Vision & Scope of the Solution
##### Vision statement
Neste contexto, uma aplicação para uma gestão qualificada iria ter um papel fundamental para as organizações que para além de ajudar numa melhor gestão dos animais, irá permitir às mesmas ajudar outros animais que também estejam a precisar de algum suporte e gerir melhor a organização, através da gestão de voluntários e doações. Assim, o software visa melhorar a condição de vida dos animais, bem como fomentar a adoção e facilitar o trabalho dos contribuintes da associação.

##### List of features
> - Lista e informações sobre cada voluntário (separados por categorias como tratadores, participam em campanhas, participam com mão de obra na construção de casas para os animais ou fazem limpeza - eventualmente permitir apenas certas categorias de voluntários fazerem alterações às informações do animal);
> - Lista e informações de cada animal (raça, onde se encontra, estado de saúde, voluntário encarregado do animal);
> - Registo de adoções dos animais e das doações no mesmo local (quem doa dinheiro aparece no mesmo local que quem adotou um animal);
> - Lista de veterinários e informações sobre o mesmos.
> - Lista de casotas e informação da sua ocupação;

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

Como organização pretendo ajudar o máximo de animais que estão à responsabilidade da organização, e por isso quero poder gerir os voluntários.

###### Acceptance Criteria
> - Aceder à lista de todos os voluntários, incluindo detalhes;


***


##### User Story 2
**Organização Sem Fins Lucrativos**

Como organização, pretendo visualizar as doações de forma a ter acesso a todos os dados e de forma a que a organização prospere, e para isso necessito de ter acesso a todos os dados da organização.

###### Acceptance Criteria
> - Aceder à lista de todas as doações efetuadas.
> - Aceder a lista de doadores incluindo informações e histórico de doações.

***

##### User Story 3
**Organização Sem Fins Lucrativos**

Como organização, pretendo ter informação sobre as casotas, e por isso preciso de ter acesso à lista de todas as casotas na minha organização, bem como informação da sua ocupação.


###### Acceptance Criteria
> - Aceder à lista de todas as boxes, e a informação da sua ocupação.

###### Prototype
![Use case diagram](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgUseCase/Administrador.png)

***

##### User Story 4
**Voluntários**

Como voluntário desta organização quero ter a possibilidade de controlar a alimentação dos animais, para isso preciso de ter acesso à informação total sobre dos animais.

###### Acceptance Criteria
> - Aceder à informação sobre a alimentação do animal (horário de alimentação, bem como se já foi alimentado).

***

##### User Story 5
**Voluntarios**

Como voluntário quero saber quais os animais existentes dentro da instituição, e para isso preciso de acesso à lista com a informação dos mesmos.

###### Acceptance Criteria
> - Aceder à lista de animais presentes na organização.

***

#### User Story 4
**Voluntarios**

Como voluntário quero saber a informação de cada animal, e para isso preciso de acesso à lista com a informação dos mesmos.

###### Acceptance Criteria
> - Aceder à informação dos animais presentes na lista.

###### Prototype
![Use case diagram](https://gitlab.com/brun0f0nseca/gps_g21/-/raw/dev/imgUseCase/Voluntarios.png)

***

##### User Story 6
**Doadores**

Como doador quero ter a possibilidade de analisar os recursos em falta da organização para poder contribuir da melhor forma dentro das minhas possiblidades, para isso preciso de ter acesso às necessidades da organização.

***

##### User Story 7
**Doadores**

Como doador quero ter a possibilidade de saber onde estão a ser gastos os meus investimentos, para isso preciso de ter acesso à gestão dos recursos da organização.


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
