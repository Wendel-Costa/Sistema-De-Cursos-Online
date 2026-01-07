# Instrução da atividade
Desenvolva aplicação com interface gráfica em Java que efetue o cadastro, alteração e exclusão de registros, utilizando os conceitos de herança e polimorfismo. A interface gráfica deve ser feita utilizando o pacote java.swing.

-Itens obrigatórios:
*Explicação do programa;
*Uso de Collections
*Uso de herança e polimorfismo (reescrita do método toString não conta)
*Uso de algum meio de armazenamento permanente (arquivos ou banco
de dados)
*Interface gráfica


# Projeto que escolhi fazer
Aplicação desktop para gerenciamento de um sistema de cursos online. O sistema permite o cadastro, alteração, exclusão e listagem de alunos, professores e cursos, além do controle de matrículas de alunos em cursos. O objetivo principal do projeto é aplicar, de forma prática, os conceitos estudados na disciplina de Paradigmas de Programação, como herança, polimorfismo, uso de collections, persistência de dados e interfaces gráficas.

# Estrutura do Sistema
O projeto foi organizado seguindo uma separação clara de responsabilidades, utilizando o padrão MVC (Model–View–DAO (DAO sendo o equivalente a controller)).

sistemacursos.model
Contém as classes de domínio (modelo), representando as entidades do sistema.

sistemacursos.dao
Responsável pela comunicação com o banco de dados SQLite, utilizando JDBC.

sistemacursos.view
Contém as telas e formulários desenvolvidos com Java Swing.

sistemacursos.app
Contém a classe principal (Main) responsável por iniciar a aplicação.

# Herança

O projeto utiliza herança e polimorfismo de forma explícita e funcional, indo além da simples reescrita de toString.

Classe abstrata Usuario que é estendida pelas classes: Aluno e Professor;

Classe abstrata Curso que é estendida pelas classes: CursoGravado, CursoAoVivo e Workshop

Interface Produto que é implementada em Curso

# Polimorfismo
Métodos sobrescritos nas subclasses: calcularPrecoFinal(); getModalidade(); getDescricao(); getCurso(); entre outros.
Esses métodos apresentam comportamentos diferentes dependendo do tipo real do objeto, mesmo quando manipulados por uma referência da classe base (Curso ou Produto).

# Uso de Collections
O sistema faz uso de Collections da API Java, principalmente: List; ArrayList


# Armazenamento Permanente
O armazenamento dos dados é feito através de um banco de dados SQLite, garantindo persistência das informações mesmo após o encerramento da aplicação.

Tabelas principais do banco: aluno; professor; curso; aluno_curso (tabela associativa para matrículas)

O acesso ao banco é feito utilizando JDBC, com classes DAO específicas: AlunoDAO; ProfessorDAO; CursoDAO; MatriculaDAO

# Interface Gráfica (utilizando Swing)
A interface gráfica foi desenvolvida utilizando o pacote javax.swing

A aplicação possui telas para: Cadastro, visualização, edição e exclusão de alunos, professores e cursos; além do gerenciamento da matrícula de alunos em cursos
