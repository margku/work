Основные методы использования : 
*добавление посылки, письма или денежного перевода. При добавлении письма сразу проверяется создание клиента, конверта, марки и почты и их получение.
*получение истории клиента (полученные или отправленные посылки)
*выдача посылки. удаление невозможно, если посылка не выдана.

//добавление письма
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar csv letter add 3  1  1 addressfrom  1 addressto  1 333 date 333  1 333  1 
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar xml letter add 3  1  1 addressfrom  1 addressto  1 333 date 333  1 333  1 

// обновление письма 
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar xml letter upd 3  1  1 new 1 new  1 333 date 333  1 333  1 
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar csv letter upd 3  1  1 new 1 new  1 333 date 333  1 333  1

//получение письма
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar xml letter get 3
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar csv letter get 3

// получение конверта
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar xml envelope get 1
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar csv envelope get 1

//история клиента
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar csv client history 1

//выдача письма 
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar xml letter give 3
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar csv letter give 3

//удаление письма 
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar xml letter del 3
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar csv letter del 3


