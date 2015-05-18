use oms;

SET SQL_SAFE_UPDATES = 0;

delete from  `oms`.`OrderItems`;
alter table OrderItems auto_increment=1;

delete from  `oms`.`Orders`;
alter table Orders auto_increment=1;

delete from   `oms`.`Users`;
alter table Users auto_increment=1;

delete from  `oms`.`Products`;
alter table Products auto_increment=1;

delete from  `oms`.`CustomerTypes`;
alter table CustomerTypes auto_increment=1;

delete from `oms`.`Dimensions`;
alter table Dimensions auto_increment=1;

delete from  `oms`.`OrderStatuses`;
alter table OrderStatuses auto_increment=1;

delete from  `oms`.`Regions`;
alter table Regions auto_increment=1;

delete from  `oms`.`Roles`;
alter table Roles auto_increment=1;

SET SQL_SAFE_UPDATES = 1;