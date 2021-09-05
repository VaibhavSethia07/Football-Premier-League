
/* 1 .	Retrieve all the players associated with the teams for the owner  James Y Adams */
select * from players 
where playerid in 
(select playerid from teamplayers 
where (teamname,ownerid) in
(select teamname,ownerid from team where ownerid in (select ownerid from owners where fname like "%James")));


/* 2 .	Retrieve all Real Madrid players associated with the team with name Barce-loners  and their scores in individual matches*/

select name,score,matchid from playermatchscore 
natural join 
(select playerid,name from players 
where playerid in
(select playerid from teamplayers where teamname='Barce-loners') and uefateamname='Real Madrid')as R;

/* 3 .	Retrieve the highest performing player for each match with his score */

select * from players
natural join
(select playerid,PS.matchid,score from playermatchscore PS
join  
(select matchid,max(score)as MaxScore from playermatchscore group by matchid) as R on( R.MaxScore=PS.score and PS.matchid=R.matchid))as R2 order by score desc;

/* 4 .	Retrieve league winner team for each league with their scores in the league*/ 

select R2.* from (select teamname,leaguename,leaguecommissionerid,ownerid,sum(score) as LeagueScore from teamleaguematchperfomance group by teamname,leaguename,leaguecommissionerid,ownerid) as R2
join 
(select leaguename,leaguecommissionerid,max(leaguescore)as MaxLeagueScore from(
select teamname,leaguename,leaguecommissionerid,ownerid,sum(score) as LeagueScore from teamleaguematchperfomance group by teamname,leaguename,leaguecommissionerid,ownerid) as R group by leaguename,leaguecommissionerid) as R3 on (R3.MaxLeagueScore=R2.LeagueScore and R2.leaguename=R3.leaguename and R2.leaguecommissionerid=R3.leaguecommissionerid); 


/* 5 .	Retrieve the highest perfoming player of all time */ 

select * from players 
natural join(
select playerid from (select playerid,sum(score)as all_time_score from playermatchscore group by playerid)as R2 
where R2.all_time_score in
(select max(all_time_score)as Max_all_time_score from (
select playerid,sum(score)as all_time_score from playermatchscore group by playerid)as R))as R4;

/* 6 .	Retrieve the teams which have the player who has scored maximum no of goals in at least two matches */

select teamname,ownerid from(
select teamname,ownerid, count(distinct matchid)as no_of_matches from (select * from teamplayers R4 where R4.playerid
in  
(select playerid from players
natural join(
select playerid from (
select playerid,sum(goals)as no_of_goals from playermatchperformance group by playerid)as R2
where R2.no_of_goals  
in (select max(no_of_goals) as max_no_of_goals from (
select playerid,sum(goals)as no_of_goals from playermatchperformance group by playerid)as R))as R3))as R4 group by teamname,ownerid)as R4 where no_of_matches>1;

/* 7. Retrieve player with golden boot */

select name from players
natural join(
select playerid from (
select playerid,sum(goals)as no_of_goals from playermatchperformance group by playerid)as R2
where R2.no_of_goals  
in (select max(no_of_goals) as max_no_of_goals from (
select playerid,sum(goals)as no_of_goals from playermatchperformance group by playerid)as R))as R3;

/* 8 .	Retrieve the teams that have not won any league  */ 

select * from team T
where
(T.teamname,T.ownerid)
not in 
(select R2.teamname,R2.ownerid from (select teamname,leaguename,leaguecommissionerid,ownerid,sum(score) as LeagueScore from teamleaguematchperfomance group by teamname,leaguename,leaguecommissionerid,ownerid) as R2
join 
(select leaguename,leaguecommissionerid,max(leaguescore)as MaxLeagueScore from(
select teamname,leaguename,leaguecommissionerid,ownerid,sum(score) as LeagueScore from teamleaguematchperfomance group by teamname,leaguename,leaguecommissionerid,ownerid) as R group by leaguename,leaguecommissionerid) as R3 on (R3.MaxLeagueScore=R2.LeagueScore and R2.leaguename=R3.leaguename and R2.leaguecommissionerid=R3.leaguecommissionerid));
  

/* 9 .	Retrieve teams that are Real Madrid dominant who have higher than average perfomance each match in a league name Ballers */ 

select R1.* from (
select * from teamleaguematchperfomance TLP
where (TLP.teamname,TLP.ownerid,TLP.matchid)
in 
(select teamname,ownerid,matchid from(
select matchid,uefateamname,teamname,ownerid,count(playerid)as NoOfPlayers from(
select	* from teamplayers natural join players) as R group by matchid,uefateamname,teamname,ownerid)as R1 where noofplayers>=6))as R1
natural join 
(select matchid,leaguename,leaguecommissionerid,avg(score) as AVGscore from teamleaguematchperfomance TLP group by matchid,leaguename,leaguecommissionerid)as R2 where R1.score > R2.avgscore;

/* 10 . Retrive the teams having players having higher than average perfomance in all of their played matches */

select * from playermatchscore PMS
where 
PMS.playerid
not in
(select playerid from playermatchscore PS
join 
(select matchid,avg(score)as AverageScore from playermatchscore group by matchid) as R on (R.matchid=PS.matchid and PS.score < R.averagescore)); 

/* 11.1 . Retrive the players having higher than average perfomance in all of their played matches */ 

select * from players PL where PL.playerid  not in 
(select playerid from playermatchscore PS
join (select matchid,avg(score)as AverageScore from playermatchscore group by matchid) as R on (R.matchid=PS.matchid and PS.score < R.averagescore)); 

/* 11.2 . Retrive the players having higher than average perfomance in all of their played matches search by team */

select * from players PL where PL.playerid  not in 
(select playerid from playermatchscore PS
join (select matchid,avg(score)as AverageScore from playermatchscore group by matchid) as R on (R.matchid=PS.matchid and PS.score < R.averagescore)) and UEFATeamName="Real Madrid" order by playerid; 


/* 12 . Retrieve the player and the uefa teamname of the player having max score in each match */

select matchid,name,uefateamname from 
(select playerid,matchid from playermatchscore PMS 
where 
(PMS.matchid,PMS.score)
in
(select matchid,max(score) from playermatchscore PS group by matchid)) as R1 natural join players order by matchid;


/* 13 .Retrieve names of the top 3 players with average goals/shots percentage higher than 35 in the real uefa league and high average score*/ 

select * from
(select * from
(select playerid,avg(shotsongoalratio) as avgGS,avg(score) as avgScore from
(select * from
(select playerid,matchid, (goals*100)/shots as ShotsOnGoalratio from playermatchperformance where shots>0)as R1
natural join
(select * from playermatchscore)as R2) as R3 group by playerid)as R4 where avgGS>0)as R4 order by avgscore desc ;


/* 14.1	Retrieve the top 4 teams with highest gaps(difference between the player with maximum score and minimum score selected for the team) for the owner teams with the matchid with their ownernames*/ 

select matchid,uefateamname,(max(score)-min(score))as Team_Perfomance_Gap from playermatchscore natural join players group by matchid,uefateamname order by matchid;

select * from
(select teamname,matchid,ownerid,(max(score)-min(score))as Team_Perfomance_Gap from
(select * from team natural join players natural join playermatchscore) as R1 group by teamname,matchid,ownerid)as R2 order by team_perfomance_gap limit 4;

/* 14.2	Retrieve the uefateams that scored more than 75 percent of goals of their opponent and lost */

select R1.uefateamname,R1.matchid from 
(select uefateamname,matchid,sum(goals)as no_of_goals from playermatchperformance natural join players group by uefateamname,matchid)
as R1
join
(select uefateamname,matchid,sum(goals)as no_of_goals from playermatchperformance natural join players group by uefateamname,matchid) as R2 on(R1.matchid=R2.matchid and R1.no_of_goals<R2.no_of_goals and R1.no_of_goals > 0.75*R2.no_of_goals);


/* 15 . Retrieve the owner email of owners that have player with maximum no of goals in any of his team during any match */ 

select * from owners
natural join

(select distinct ownerid from teamplayers
natural join
(select playerid from

(select playerid,avg(steals)as Avg_steals from playermatchperformance group by playerid) as R1
 
join

(select max(Avg_steals)as max_steals from
(select playerid,avg(steals)as Avg_steals from playermatchperformance group by playerid)as R1) as R2
on (R1.avg_steals=R2.max_steals))as Rin) as Rinin;
 	 