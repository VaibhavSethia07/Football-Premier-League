alter table playermatchperformance
alter column overtimelosses set default '0';
alter table playermatchperformance
alter column  goalssaved set default 0;
alter table playermatchperformance
alter column overtimelosses set default 0;
alter table playermatchperformance
alter column saves set default 0;
alter table playermatchperformance
alter column overtimewins set default 0;
alter table playermatchperformance
alter column goalsallowed set default 0;
alter table playermatchperformance
alter column blockedshots set default 0;
alter table playermatchperformance
alter column penaltykickattemptsdefended set default 0;
alter table playermatchperformance
alter column penaltykickgoalsdefended set default 0;
alter table playermatchperformance
alter column assists set default 0;
alter table playermatchperformance
alter column goals set default 0;
alter table playermatchperformance
alter column shotsongoal set default 0;
alter table playermatchperformance
alter column steals set default 0;
alter table playermatchperformance
alter column penaltykickattempts set default 0;
alter table playermatchperformance
alter column gamewinninggoals set default 0;
alter table playermatchperformance
alter column ejections set default 0;

alter table players
add UEFATeamName varchar(50);

alter table players 
add foreign key (UEFATeamName)
references uefa_teams(teamname);

alter table league 
modify column leaguename varchar(50);

alter table owners 
modify column email varchar(50);
alter table owners 
modify column fname varchar(50);
alter table owners 
modify column lname varchar(50);

alter table players
modify name varchar(50);

alter table team
modify teamname varchar(50);

alter table teamplayers 
modify column teamname varchar(50);

alter table leaguematches
modify column LeagueName varchar(50);

alter table participations
modify column TeamName varchar(50);

alter table participations
modify column LeagueName varchar(50);

alter table teamleaguematchperfomance
modify column teamname varchar(50);

alter table teamleaguematchperfomance
modify column leaguename varchar(50);

alter table teamleaguematchperfomance
modify column score numeric;

alter table playermatchperformance
add column score integer;



