/* 1. Get league matches for a given team name and his ownerid */
DROP PROCEDURE IF EXISTS  get_league_matches_for_team;
delimiter $$
CREATE PROCEDURE get_league_matches_for_team(IN oid int,IN tname varchar(50))
	BEGIN
		select leaguename,leaguecommissionerid,matchid from participations natural join leaguematches where ownerid=oid and teamname=tname;
	END $$
CALL get_league_matches_for_team(1,"PatheticoMadrid");

/* 2. Get league score board given leaguecommissionerid and league name */
DROP PROCEDURE IF EXISTS  get_league_score_board;
delimiter $$
CREATE PROCEDURE get_league_score_board(comid int, lname varchar(50))
	BEGIN
		select ownerid,Score from
		(select * from
		(select leaguename,leaguecommissionerid,ownerid,sum(score)as 
		Score from teamleaguematchperfomance 
		group by leaguename,leaguecommissionerid,ownerid) 
		as R where R.leaguename=lname and R.leaguecommissionerid=comid) 
		as R2 order by score;
    END $$
CALL get_league_score_board(4,"Ballers");

/* 3 Get league winner team given leaguecommissionerid and his commissionerid */
DROP PROCEDURE IF EXISTS  get_league_winner_team;
delimiter $$
CREATE PROCEDURE get_league_winner_team(comid int, lname varchar(50))
  BEGIN
	select team.* from (select teamname,ownerid from
	(select R2.* from (select teamname,leaguename,leaguecommissionerid,ownerid,sum(score) as LeagueScore from teamleaguematchperfomance group by teamname,leaguename,leaguecommissionerid,ownerid) as R2
	join 
	(select leaguename,leaguecommissionerid,max(leaguescore)as MaxLeagueScore from(
	select teamname,leaguename,leaguecommissionerid,ownerid,sum(score) as LeagueScore from teamleaguematchperfomance group by teamname,leaguename,leaguecommissionerid,ownerid) as R group by leaguename,leaguecommissionerid) as R3 on 
	(R3.MaxLeagueScore=R2.LeagueScore and R2.leaguename=R3.leaguename and R2.leaguecommissionerid=R3.leaguecommissionerid)) as R5 where R5.leaguename=lname and R5.leaguecommissionerid=comid) as Rf natural join team;
  END $$
CALL get_league_winner_team(4,"Ballers");

/* Trigger */
delimiter $$
CREATE TRIGGER get_players_for_match
  BEFORE INSERT 
  ON Player FOR EACH ROW
	BEGIN
		update Player
		set position="Mid Fielder" where name="Burgui";
		select players.* from (playermatchperformance natural join players )where playermatchperformance.matchid=matchid;
	END $$