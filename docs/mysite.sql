-- scheme user
desc user;

-- select01
select no,name from user where email='ghd167@naver.com' and password='1234';

-- select02
select *
	from guestbook;
    
select *
	from user;

-- insert
insert into user values(null, '둘리', 'dooly@gmail.com', '1234', 'male', now());

insert into guestbook values(null, '둘리', '1234', 'dooly', now());

desc board;

insert into board values(null, '123', '123');
