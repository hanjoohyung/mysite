-- scheme user
desc user;

select* from guestbook;
-- select01
select no,name from user where email='ghd167@naver.com' and password='1234';

-- select02
select *
	from guestbook;
    
select *
	from user;
select* from board where title = "김경도";
-- insert
insert into user values(null, '둘리', 'dooly@gmail.com', '1234', 'male', now());

insert into guestbook values(null, '둘리', '1234', 'dooly', now());

desc board;

select * from board;

select *
	from board
	where title =?;

delete from board where no=4 and title = 123;

select title, contents
	from board
	where no=12;
    
select hit
	from board
	where title=김경도1;