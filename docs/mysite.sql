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
    
    update board set title='야미야미1', contents='야미' where hit=25;
    
select u.name
	from user u join board b on u.no = b.no;
    
    select * from user;
    
    delete from user where name='마이콜' and password='1234';
    
select b.no,name,title,contents,hit,reg_date,group_no,order_no,depth,user_no
	from user a, board b
    where a.no = b.user_no;
    
    update board set user_no='2' where title='123';
    
    select name
		from user a join board b
        where b.user_no = a.no;
        
	update board b join user a set user_no=a.no where name='한주형';
    select * from board limit 0,5;
    select * from user;
	update board set title='야미야미1', contents='야미' where hit=25;
    
    select count(*) as totalCount from board;
    
    update board b join user u set b.user_no = u.no where u.name='한주형';
    
    insert into board values(null, ?, ?, 0, now(), 1, 1, 1, ?);
    
    select no, name, date_format(reg_date, '%Y/%m/%D %H:%i:%s') as regdate, message
		from guestbook;
insert into user values(null, '관리자', 'admin@mysite.com', '1234', 'male', now(), 'ADMIN');
        select * from user;
        
        
alter table user add column role enum('USER', 'ADMIN') not null default 'USER';