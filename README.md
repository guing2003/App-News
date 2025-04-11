# App de Notícias Android - Kotlin

Este é um aplicativo Android de notícias desenvolvido com Kotlin, utilizando a arquitetura MVP. O app consome dados da API NewsApi e oferece uma experiência de leitura personalizada, com funcionalidades como filtro de busca e armazenamento de notícias favoritas localmente utilizando Room.

## Funcionalidades

- **Consumo da API NewsApi**: O app puxa notícias em tempo real de uma API externa.
- **Busca Personalizada**: Filtro de busca utilizando o SearchView para encontrar rapidamente tópicos de interesse.
- **Favoritos**: Permite ao usuário salvar suas notícias preferidas, que são armazenadas localmente com o Room.
- **Armazenamento Local**: Utilização do Room para persistir dados localmente e oferecer acesso offline às notícias favoritas.

## Tecnologias Usadas

- **Kotlin**: Linguagem principal para o desenvolvimento do app.
- **MVP**: Arquitetura aplicada para manter o código organizado e com responsabilidades bem definidas.
- **NewsApi**: API externa para busca de notícias em tempo real.
- **Room**: Biblioteca de persistência de dados locais.
- **ViewBinding**: Para vinculação de layouts de maneira mais segura e eficiente.
  
