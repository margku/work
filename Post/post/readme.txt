�������� ������ ������������� : 
*���������� �������, ������ ��� ��������� ��������. ��� ���������� ������ ����� ����������� �������� �������, ��������, ����� � ����� � �� ���������.
*��������� ������� ������� (���������� ��� ������������ �������)
*������ �������. �������� ����������, ���� ������� �� ������.

//���������� ������
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar csv letter add 3  1  1 addressfrom  1 addressto  1 333 date 333  1 333  1 
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar xml letter add 3  1  1 addressfrom  1 addressto  1 333 date 333  1 333  1 

// ���������� ������ 
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar xml letter upd 3  1  1 new 1 new  1 333 date 333  1 333  1 
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar csv letter upd 3  1  1 new 1 new  1 333 date 333  1 333  1

//��������� ������
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar xml letter get 3
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar csv letter get 3

// ��������� ��������
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar xml envelope get 1
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar csv envelope get 1

//������� �������
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar csv client history 1

//������ ������ 
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar xml letter give 3
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar csv letter give 3

//�������� ������ 
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar xml letter del 3
java -Dlog4j2.configurationFile=log4j2.properties -Dconfig=config.properties -jar mailing.jar csv letter del 3


