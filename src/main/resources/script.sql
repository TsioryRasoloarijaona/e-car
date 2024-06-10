delete from car_images;

delete from appointement;

delete from car;

select * from car_images where images ilike '%volvo%';

update car_images set images = 'https://cdn.automobile-propre.com/uploads/2016/02/porsche-918-spyder-0011-620x413.jpg'
where car_id = 353;

delete from car_images where car_id = 503 or car_id = 504;

delete from car where id = 503 or id = 504;

alter table appointement alter column status set default 'pending';

select count(*) , car_id from appointement group by car_id;

update car set status = true ;