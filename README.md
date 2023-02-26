<h1 align="center"> APLICATIVO DE APOSTAS </h1>


## LOTECA DO BETOLA
Este aplicativo foi desenvolvido para fins de estudo do Android nativo e tem como objetivo fornecer uma experiência de apostas. Ele utiliza activitys e fragments para navegação, bem como outros componentes, como o bottomnavigationView e adapters.

Com uma interface intuitiva, o usuário pode facilmente selecionar seus números e fazer suas apostas. O aplicativo permite que o jogador faça sua aposta até 30 minutos antes dos sorteios oficiais da mega-sena. Após esse período, o botão para cadastrar apostas é desativado e fica inativo até o final da rodada.

Para garantir a credibilidade do aplicativo, a lista de jogadores e seus números apostados é disponibilizada 15 minutos antes do primeiro sorteio, evitando qualquer possibilidade de fraude. Além disso, o back-end utiliza a API da loteria para pegar os números sorteados e verificar se houve algum ganhador que tenha feito apostas no nosso aplicativo.

O back-end também possui schedulers que iniciam 5 minutos após o resultado oficial da mega-sena, onde são salvos os números do sorteio em nossa base de dados. Outro scheduler inicia 5 minutos após o primeiro, para fazer as validações dos ganhadores, enviando um e-mail para o jogador ganhador.

Este aplicativo de apostas é uma excelente maneira de se divertir e testar a sorte em jogos de loteria, além de ser um ótimo exemplo para quem está estudando o desenvolvimento de aplicativos para Android nativo.

# ✔️ Back-end
O back-end usa a API da loteria para pegar os numeros sorteados e verificar se houve algum ganhador que fizeram apostas no nosso aplicativo.
O scheddule tem inicio 5 minutos após o resultado oficial da mega-sena, onde são salvos os numeros do sorteio em nossa base
Outro schedduler inicia 5 minutos após o primeiro, para fazer as validações do ganhadores, enviando um email para o jogador ganhador.

# ✔️ Front-end
É necessario fazer o cadastro no aplicativo para ter acesso as demais funções da aplicação
O cliente ja cadastrado terá de fazer suas apostas até 30 minutos antes dos sorteios oficias da mega-sena. Sendo assim o botão para cadastrar apostas
ficará inativo até o final da rodada. Após isso o botão será reativado e as apostas poderão ser cadastradas novamente para o novo sorteio.

<p>Será disponibilizada no aplicativo a lista de jogares e seus numeros apostados 15 minutos antes do primeiro sorteio, assim evitando alguma fraude no
aplicativo e garantindo sua credibilidade.</p>


# 🔨 Funcionalidades do aplicativo.
- `Splash`: Animação criada no proprio android studio para introduçao do aplicativo.
- `Cadastro de Usuários`: Para acesso ao aplicativo, o usuário deve se cadastrar informando (Nome - Apelido - Email - Celular - CPF e Senha).
- `Jogo Mega-Tola`: Jogo de aposta no estilo 'mega-sena', integrado com o back-end para cadastro de apostas e controle dos sorteios e ganhadores.
- `Jogo Roletola`: Jogo de aposta no estilo roleta, necessário escolher um numero e um valor de apostas para liberar o giro da roleta.
- `Jogo Caça-Niquel`: Jogo de caça-niquel.
- `BlackJack`: Em construção.


# ✔️ Técnicas e tecnologias utilizadas

- ``Java 11``
- ``Android Studio``
- ``Retrofit``
- ``InteliJ IDEA``
- ``Spring Boot``
- ``Spring Security``
- ``Spring Batch``
- ``JPA``
- ``Postgree``

<br>
# 📁 Acesso ao projeto
Você pode acessar os arquivos do projeto clicando [aqui](https://github.com/CarlosHoff/LotecaAtualizada).

<br><br>
<p align="center">
<img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge"/>
</p>
