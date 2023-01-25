# LotecaAtualizada
Projeto de loteria - (estudo)

Front-end desenvolvido em android nativo
Back-end - Java - Spring boot - Spring security - Spring batch - Jpa 
Banco de dados: Postgree


Resumo do projeto:
É um aplicativo de apostas baseado nos resultados da mega-sena

BACK-END
O back-end usa a API da loteria para pegar os numeros sorteados e verificar se houve algum ganhador que fizeram apostas no nosso aplicativo.
O scheddule tem inicio 5 minutos após o resultado oficial da mega-sena, onde são salvos os numeros do sorteio em nossa base
Outro schedduler inicia 5 minutos após o primeiro, para fazer as validações do ganhadores, enviando um email para o jogador ganhador.
  
FRONT-END
É necessario fazer o cadastro no aplicativo para ter acesso as demais funções da aplicação
O cliente ja cadastrado terá de fazer suas apostas até 30 minutos antes dos sorteios oficias da mega-sena. Sendo assim o botão para cadastrar apostas
ficará inativo até o final da rodada. Após isso o botão será reativado e as apostas poderão ser cadastradas novamente para o novo sorteio.

Será disponibilizada no aplicativo a lista de jogares e seus numeros apostados 15 minutos antes do primeiro sorteio, assim evitando alguma fraude no
aplicativo e garantindo sua credibilidade.

Telas do aplicativo
Tela de Login
<img width="258" alt="image" src="https://user-images.githubusercontent.com/52975130/214453984-5e6f31a1-18e3-469e-895b-490c3b0a5f58.png">
Tela de Perfil
<img width="259" alt="image" src="https://user-images.githubusercontent.com/52975130/214454182-565c8fe9-adcc-46da-8913-bbec665f1b3f.png">
Tela de Apostas
<img width="258" alt="image" src="https://user-images.githubusercontent.com/52975130/214454264-677a604e-16f5-48a6-a72c-be3eb457609f.png">
Tela de Listagem das Apostas
<img width="252" alt="image" src="https://user-images.githubusercontent.com/52975130/214454532-18e94c3e-18a9-4ea6-a668-eae8a1f0e0dc.png">
Tela de sucesso
<img width="254" alt="image" src="https://user-images.githubusercontent.com/52975130/214455064-2b4f711b-9d37-40ca-b383-8b653cff7fae.png">
Tela de erro
<img width="257" alt="image" src="https://user-images.githubusercontent.com/52975130/214455207-d1e3eefb-f63e-440e-8051-ec522deef5b1.png">




