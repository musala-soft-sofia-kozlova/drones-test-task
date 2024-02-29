delete from drones;
delete from medications;
delete from "flights-log";
delete from "drone-states-log";
insert into medications (code, name, weight)
	values
		('MED_CODE_1', 'MED-NAME-1', 100),
		('MED_CODE_2', 'MED-NAME-2', 200),
		('MED_CODE_3', 'MED-NAME-3', 300);