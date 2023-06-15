
# Progettazione di Applicazioni Web e Mobili (6 CFU) - De Vitis

Progetto realizzato individualmente per l'esame di Progettazione di Applicazioni Web e Mobili presso l'università di Camerino. 
Il progetto è una semplice applicazione per facilitare il management di una palestra.
Difatti sarà possibile inserire una carta per il cliente che avrà dei parametri ben definiti come una data di inizio, una data di scadenza e la tipologia di abbonamento associato alla carta.
Il cliente potrà accedere ad una propria area personale dove sarà possibile accedere alle informazioni riguardanti la propria carta e prendere visione del suo stato.
Vi sarà inoltre una dashboard per l'amministratore della piattaforma dove potrà effettuare diverse operazioni.

## Tecnologie utilizzate

 - [Wamp Server](https://www.wampserver.com/en/)
 - [Spring Boot](https://spring.io/projects/spring-boot)
 - [Angular](https://angular.io/)


## Curiosità sul progetto
## Jwt Security

![Logo](https://cdn.fs.teachablecdn.com/S5mcwqSCTqqyZYsvzSJn)

A livello di sicurezza è stata implementata la Jwt Security su Spring boot 3.0.6 per login/autenticazione del cliente.
Il flusso tipico di utilizzo della sicurezza JWT è il seguente:

- L'utente invia le proprie credenziali (ad esempio, nome utente e password) al server per l'autenticazione.
- Il server verifica le credenziali e genera un token JWT contenente le informazioni pertinenti sull'utente e le autorizzazioni.
- Il server restituisce il token JWT all'utente come risposta.
- L'utente invia il token JWT nelle richieste successive come meccanismo di autenticazione. Il token viene solitamente incluso nell'header di autorizzazione della richiesta HTTP.
- Il server riceve la richiesta e verifica la firma del token JWT per garantire che il token non sia stato manomesso e sia valido. In tal caso, il server autorizza l'utente a eseguire l'azione richiesta.
- L'utente può quindi accedere alle risorse o eseguire azioni autorizzate.


La sicurezza JWT presenta diversi vantaggi, tra cui l'eliminazione della necessità di archiviare lo stato di sessione lato server e la possibilità di scalare l'autenticazione su diversi server o servizi. Tuttavia, è importante gestire correttamente la chiave segreta utilizzata per la firma e assicurarsi che il token venga trasmesso in modo sicuro per evitare attacchi di intercettazione o manomissione.

## Quartz come job scheduler

![Logo](https://examples.javacodegeeks.com/wp-content/uploads/2019/05/quartz-architecture.jpg)

Quartz è un framework open-source per la pianificazione e l'esecuzione di task, noti come job, all'interno di un'applicazione. Funziona come un job scheduler, consentendo agli sviluppatori di definire il momento in cui determinate operazioni devono essere eseguite in modo automatico e ripetitivo.

Il funzionamento di Quartz si basa su alcuni concetti chiave:

- Job: Un job rappresenta l'unità di lavoro che deve essere eseguita. Può essere una classe Java o un componente eseguibile che implementa l'interfaccia Job di Quartz. È possibile definire i job in modo che vengano eseguiti in determinati intervalli di tempo o secondo un calendario specifico.

- Trigger: Un trigger specifica quando e come un job deve essere eseguito. I trigger possono essere basati su orari specifici, come ad esempio ogni giorno alle 10:00, oppure possono essere configurati per essere attivati da eventi scatenanti, come ad esempio l'avvio dell'applicazione o il completamento di un altro job. I trigger definiscono anche come il job deve essere ripetuto, ad esempio ogni 5 minuti o ogni settimana.

- Scheduler: Il scheduler rappresenta il core di Quartz. È responsabile dell'orchestrazione dei job e dei trigger. Gestisce la pianificazione, l'esecuzione e il monitoraggio dei job. Il scheduler può essere configurato per avviarsi all'avvio dell'applicazione o può essere programmato per essere avviato in un momento specifico. Una volta avviato, il scheduler si occupa di eseguire i job in base ai trigger specificati.

Quartz è stato utilizzato all'interno del progetto per verificare quando le carte presenti all'interno del database siano scadute. Ovvero quando la data odierna è maggiore alla data di scadenza/fine abbonamento. Di conseguenza verrà settato il valore boolean della carta chiamato isValid a false.
## Authors

- [@De Vitis Fabio Michele](https://github.com/FabioDevIsTyping)


