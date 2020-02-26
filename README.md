First Commit = Modelagem das Regras de Modelo.

Second Commit = adicionado metodo de validar email, usando a Query method
do SpringData

Third Commit = implementado o teste de integraçao do metodo ValidarEmail.

Fourth Commit = implementado os metodos de salvar e autenticar na camada
UsuarioServiceImplemetation. Correçao do erro-01

*Fifth Commit* = desenvolvido o Controller para Usuario, metodos (POST) autenticar e salvar.

Error-01: não esta conectando na base de dados.// Corrigido, na relaçao @ManyToOne
entre a entidade Usuario e Lançamento deve-se usar @JoinColumn ao inves de
@Column

