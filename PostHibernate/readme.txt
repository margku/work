//Лабораторная №2
Данные таблицы генерируются в DataGenerator
Встроенный класс - washingMashine
//клиент по id
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab2 get 1
//клиент по имени
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab2 by_name Client1

//Лабораторная №4
Данные таблицы генерируются в DataGenerator
Отображение коллекции типа List - address
Отображение коллекции тип Map - класс document
Отображение коллекции типа Set - класс phone

//клиент по id
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab4 get 1
//клиент по имени
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab4 by_name Client1 


//Лабораторная №5
Данные таблицы генерируются в DataGenerator
Ассоциация один-к-одному Client - Document
Ассоциация многие-ко-многим Client - Company
Ассоциация один-ко-многим Client - Letter
//клиент по id
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab5 get 1
//клиент по имени
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab5 by_name Client1 


//Лабораторная №3
Данные таблицы генерируются в DataGenerator
// Стратегия MappedSuperclass
Все свойства потомка включая свойства родителя отображаются в одной таблице.
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 mapped letter 1
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 mapped money 1
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 mapped valpack 1

//Стратегия Joined table
Таблица на каждую сущность иерархии классов.
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 joined letter 2
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 joined money 1
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 joined valpack 3

//Стратегия Single table
Вся  иерархия классов описывается одной таблицей
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 single letter 2
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 single money 1
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 single valpack 3

//Стратегия Table per class
Каждая таблица определяет пресистентное состояние каждого класса иерархии.
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 tpc letter 2
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 tpc money 1
java -jar -Dhb=hibernate.cfg.xml -Dfile_db=emdDb -Dlog4j.configurationFile=log4j2.properties postHibernate.jar lab3 tpc valpack 3
