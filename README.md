# 🚀 Trak_In - Monitoramento Inteligente de Motos
Track In é uma solução inovadora desenvolvida para o Challenge 2025 da FIAP,  pelos alunos Julia, Leandro e Victor da turma 2TDSPG, voltada à automação do monitoramento de motos em pátios de filiais da Mottu. Combinando tecnologias de Visão Computacional e IoT, o projeto permite a identificação precisa e a localização em tempo real das motos, mesmo em ambientes com grande volume e variedade de veículos.

## 🧑‍💻 Desenvolvido por
Julia – RM558831
Leandro Correia - RM 556203
Victor Antonopoulos - RM556313

## 🎯 Endpoints
Camera: [GET /camera, GET /camera/{id}, POST /camera, PUT /camera/{id}, DELETE /camera/{id}]

Detecção Visual: [GET /deteccao, GET /deteccao/{id}, POST /deteccao, DELETE /deteccao/{id}]

Evento Moto: [GET /evento, GET /evento/{id}, POST /evento, PUT /evento/{id}, DELETE /evento/{id}]

Filial: [GET /filial, GET /filial/{id}, POST /filial, PUT /filial/{id}, DELETE /filial/{id}]

Localizacao: [GET /localizacao, GET /localizacao/{id}, POST /localizacao, DELETE /localizacao/{id}]

Moto: [GET /moto, GET /moto/{id}, POST /moto, PUT /moto/{id}, DELETE /moto/{id}]

Patio: [GET /patio, GET /patio/{id}, POST /patio, PUT /patio/{id}, DELETE /patio/{id}]

Usuario: [GET /user, GET /user/{id}, POST /user, PUT /user/{id}, DELETE /user/{id}]

ZonaPatio: [GET /zona, GET /zona/{id}, POST /zona, PUT /zona/{id}, DELETE /zona/{id}]

## Filtros
Camera

status: filtra câmeras pelo status (ativo, inativo, manutenção).

idPatio: filtra câmeras por pátio (ID do pátio).

DeteccaoVisual

dataInicio e dataFim: filtra detecções visuais pelo intervalo de datas.

placaMoto: filtra por placa da moto.

idCamera: filtra por ID da câmera.

Evento

tipo: filtra eventos pelo tipo.

dataInicio e dataFim: filtra eventos pelo intervalo de datas.

usuarioId: filtra eventos por usuário.

placaMoto: filtra eventos por placa da moto.

Filial

nome: filtra filiais pelo nome.

responsavelId: filtra por ID do responsável da filial.

patioId: filtra por ID do pátio associado.

Localizacao

status: filtra localização da moto pelo status (ex: ESTACIONADA, EM_MOVIMENTO).

fonteDados: filtra pela fonte dos dados (ex: RFID, VISÃO_COMPUTACIONAL).

moto: filtra por placa da moto.

patioId: filtra por ID do pátio.

Moto

placa: filtra motos pela placa.

modelo: filtra pelo modelo da moto.

rfidTag: filtra pelo RFID.

status: filtra pelo status da moto (ex: DISPONIVEL, EM_MANUTENCAO, ALUGADA).

dataAquisicao: filtra motos adquiridas a partir da data especificada.

ultimaManutencaoInicio e ultimaManutencaoFim: filtra motos com última manutenção dentro do intervalo de datas.

Patio

nome: filtra pátios pelo nome (contém, case-insensitive).

estado: filtra pátios pelo estado (igual, case-insensitive).

pais filtra pátios pelo país (igual, case-insensitive; ex: Brasil, México).

Usuario

nome: filtra usuários pelo nome.

email: filtra usuários pelo email.

perfil: filtra pelo perfil do usuário (ex: ADMIN, OPERADOR, GERENTE).

filialId: filtra pelo ID da filial associada.

Zona (ZonaPatio)

nome: filtra zonas pelo nome.

cor: filtra zonas pela cor.

idPatio: filtra por ID do pátio relacionado.

## 🔧 Como rodar o projeto
1- Importar o projeto na IDE:
Abra sua IDE e importe o projeto
Espere baixar as dependências

2- Build e execução:
Pelo terminal:
bash
./mvnw spring-boot:run    

Pela IDE:
Localize a classe principal anotada com @SpringBootApplication
Rode essa classe como aplicação Java

3-Testar a API:
Use o Postman, Insomnia, curl ou navegador
Teste as rotas que o projeto expõe (ex: GET http://localhost:8080/moto) 

## 📄 Nosso Pitch
🎥 [Clique aqui para assistir ao vídeo no YouTube: https://youtu.be/PCHSHT0CuMA]
