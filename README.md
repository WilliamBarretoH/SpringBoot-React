**First Commit** = Modelagem das Regras de Modelo.

**Second Commit** = adicionado metodo de validar email, usando a Query method
do SpringData

**Third Commit** = implementado o teste de integraçao do metodo ValidarEmail.

**Fourth Commit** = implementado os metodos de salvar e autenticar na camada
UsuarioServiceImplemetation. Correçao do erro-01

**Fifth Commit** = desenvolvido o Controller para Usuario, metodos (POST) autenticar e salvar.

**Sixth Commit** = criado os metodos das Regras de Negocio do Lancamento na camada Service e Implementation

**Seventh Commit** = Começo do desenvolvimento do controller lancamento, LancamentoResource. Implementado o metodo ObterPorID no Service Lancamento.

**Eighth Commit** = Implementado os metodos Salvar e Atualizar no Controller Lancamento e o metodo obterPorID no Service Lancamento.

Error-01: não esta conectando na base de dados.// Corrigido, na relaçao @ManyToOne
entre a entidade Usuario e Lançamento deve-se usar @JoinColumn ao inves de
@Column

