//������������ �2
������ ������� ������������ � DataGenerator
���������� ����� - washingMashine
//������ �� id
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab2 get 1
//������ �� �����
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab2 by_name Client1

//������������ �4
������ ������� ������������ � DataGenerator
����������� ��������� ���� List - address
����������� ��������� ��� Map - ����� document
����������� ��������� ���� Set - ����� phone

//������ �� id
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab4 get 1
//������ �� �����
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab4 by_name Client1 


//������������ �5
������ ������� ������������ � DataGenerator
���������� ����-�-������ Client - Document
���������� ������-��-������ Client - Company
���������� ����-��-������ Client - Letter
//������ �� id
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab5 get 1
//������ �� �����
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab5 by_name Client1 


//������������ �3
������ ������� ������������ � DataGenerator
// ��������� MappedSuperclass
��� �������� ������� ������� �������� �������� ������������ � ����� �������.
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 mapped letter 1
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 mapped money 1
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 mapped valpack 1

//��������� Joined table
������� �� ������ �������� �������� �������.
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 joined letter 2
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 joined money 1
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 joined valpack 3

//��������� Single table
���  �������� ������� ����������� ����� ��������
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 single letter 2
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 single money 1
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 single valpack 3

//��������� Table per class
������ ������� ���������� ������������� ��������� ������� ������ ��������.
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 tpc letter 2
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 tpc money 1
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 tpc valpack 3
