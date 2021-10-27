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
        
        insert into site values(null, 'MySite','안녕하세요. 한주형의 mysite에 오신 걸 환영합니다','C:/upload-images/dokgo.png','이 사이트는 웹 프로그램밍 실습과제 예제 사이트입니다.\n
메뉴는 사이트 소개, 방명록, 게시판이 있구요.\n Java 수업 + 데이터베이스 수업 + 웹프로그래밍 수업 배운 거 있는거 없는 거 다 합쳐서 만들어 놓은 사이트 입니다.');
        
        select * from site;
alter table user add column role enum('USER', 'ADMIN') not null default 'USER';

select * from board;

select* from board limit 10,10;

select * from board order by reg_date desc limit 0,10;

select * from board where title='7g7g';

select * from user;

delete from board where no=59 and title=2222;
delete from board where no=105 and title='7g7g';

select no, url, comments from gallery;