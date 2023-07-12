
# Progettazione di Applicazioni Web e Mobili (OmegaGym) - FabioDev
Progetto realizzato individualmente per l'esame di Progettazione di Applicazioni Web e Mobili presso l'università di Camerino. 
Il progetto è una semplice applicazione user friendly per facilitare il management di una palestra.
Vi sarà infatti una porzione dell'app dove l'admin, ad esempio il titolare della palestra o un segretario, potrà effettuare operazioni di gestione del database in maniera semplificata e confortevole e gestire facilmente i clienti presenti all'interno dell'applicazione, le carte dei clienti e potrà inserire nuove tipologie di abbonamento a seconda dei criteri che si ritengono più opportuni.
D'altra parte il cliente, ovvero l'utente che 
Il cliente potrà accedere ad una propria area personale dove sarà possibile accedere alle informazioni riguardanti la propria carta e prendere visione del suo stato e di informazioni quali la scadenza del proprio abbonamento ecc.



## FAQ

#### Da cosa è nata l'idea per il progetto?

In questi anni all'interno del territorio italiano stanno aumentando sempre di più le palestre aperte h24. Questa idea innovativa nata in america ha riscosso un forte successo. Difatti il principale vantaggio è il non doversi adattare agli orari della palestra ed avere la libertà di scegliere l'orario in cui andare ad allenarsi a seconda dei propri impegni. Proprio questo è il punto su cui traggono maggiore forza le palestre h24, ci ritroviamo infatti in una società sempre più dinamica dettata da ritmi molto frenetici a causa del lavoro o qualsiasi altro aspetto occupa la nostra vita personale e la possibilità di allenarsi a qualsiasi orario è vista come un pregio da molti. In italia soprattutto nelle grandi città sono state introdotte delle palestre h24 mentre in piccoli contesti questo passo avanti ancora non è stato fatto.

#### Qual'è l'obiettivo del progetto?

L'obiettivo del progetto è quello di fornire alle piccole palestre una facile soluzione per poter diventare delle palestre aperte h24.
Attraverso l'introduzione di un applicazione che fornisce un metodo al proprietario della palestra per poter interagire e gestire i clienti che hanno diritto di accedere alla propria palestra e di customizzare i tipi di abbonamenti disponibili ai clienti. Inoltre l'applicazione è anche rivolta all'utente che potrà vedere lo stato attuale della propria carta e potrà effettuare molte altre operazioni che non sono state ancora implementate.

#### Che cos'è il pattern MVC?

L'MVC è un design pattern, frequentemente utilizzato nell' elaborazione di applicazioni web, che sta a significare Model-View-Controller Pattern ed è stato adottato per lo sviluppo di questa applicazione. 
In breve il significato è il seguente:
- Model : contiene i metodi di accesso ai dati
- View : si occupa di visualizzare i dati all'utente e gestisce l'interazione fra quest'ultimo e l'infrastruttura sottostante
- Controller: riceve i comandi dell'utente attraverso il View e reagisce eseguendo delle operazioni che possono interessare il Model e che portano generalmente ad un cambiamento di stato del View.

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

Quartz è stato utilizzato all'interno del progetto per verificare quando le carte presenti all'interno del database siano scadute. Ovvero quando la data odierna è maggiore alla data di scadenza/fine abbonamento. Di conseguenza la carta verrà eliminata dal database.
## Authors

- [@De Vitis Fabio Michele](https://github.com/FabioDevIsTyping)


