create database FootballPremierLeague;
show databases;
use FootballPremierLeague;
show tables;
create table Matches(
	MatchID INT PRIMARY KEY
);

create table Owners(
	ownerid	INT  PRIMARY KEY,
	Fname	varchar(10),
	Minit char,
	Lname varchar(10),
	Email varchar(50)
);

create table Players(
	PlayerID INT PRIMARY KEY,
	Name varchar(10),
	Position varchar(10)
);

create table Team(
	TeamName varchar(10),
	Location varchar(10) NOT NULL,
	OwnerID	INT references Owners(ownerid),
	PRIMARY KEY (TeamName,OwnerID)
);

create table PlayerMatchPerformance(
	PlayerID INT references Players(PlayerID),
	MatchID INT references Matches(MatchID),
	Overtimelosses int ,
	Saves	int,
	Overtimewins int,
	GoalsAllowed int,
	GoalsSaved int,
	BlockedShots int,
	PenaltyKickGoals int,
	Goals int,
	PenaltyKickAttemptsDefended int,
	PenaltyKickGoalsDefended int,
	Assists int,
	Shots int,
	ShotsOnGoal int,
	PenaltyKickAttempts int,
	Steals int,
	GameWinningGoals int,
	Ejections int,
	primary key (PlayerID,MatchID)
);

create table League(
	LeagueName varchar(10) ,
	CommissionerId INT REFERENCES Owners(ownerid),
	PRIMARY KEY (LeagueName,CommissionerId) 
);

create table TeamPlayers(
	TeamName varchar(10) ,
	OwnerID INT ,
	PlayerID INT references Players(PlayerID),
	MatchID INT references Matches(MatchID),
	foreign key (TeamName,OwnerID) references Team(TeamName,ownerid),
	PRIMARY KEY (TeamName,OwnerID,PlayerID,MatchID)
);

create table LeagueMatches(
	MatchID INT references Matches(MatchID),
	LeagueName varchar(10),
	LeagueCommissionerID INT,

	PRIMARY key (MatchID,LeagueName,LeagueCommissionerID),
	foreign key (LeagueName,LeagueCommissionerID) references League(LeagueName,commissionerId)
);

create table Participations(
	TeamName varchar(10) ,
	OwnerID INT ,
	LeagueName varchar(10),
	LeagueCommissionerID INT,
	PRIMARY key (TeamName,OwnerID,LeagueName,LeagueCommissionerID),
	foreign key (LeagueName,LeagueCommissionerID) references League(LeagueName,CommissionerID),
	foreign key (LeagueName,LeagueCommissionerID) references League(LeagueName,commissionerId)
);

create table TeamLeagueMatchPerfomance(
	TeamName varchar(10) ,
	OwnerID INT ,
	MatchID INT references Matches(MatchID),
	LeagueName varchar(10),
	LeagueCommissionerID INT,
	score INT,
	PRIMARY KEY (TeamName,OwnerID,MatchID,LeagueName,LeagueCommissionerID),
	foreign key (LeagueName,LeagueCommissionerID) references League(LeagueName,CommissionerID),
	foreign key (TeamName,OwnerID) references Team(TeamName,ownerid)

);

create table UEFA_TEAMS(
	Teamname varchar(50) primary key  
);

create table matchUEFATEAMS(
	matchid integer references matches(matchid),
	teamname varchar(50) references uefa_teams(teamname),
	primary key (matchid,teamname)
);

create table playermatchscore(
	playerid integer ,
	matchid  integer,
	score decimal(4,2),
	foreign key (playerid,matchid) references playermatchperformance(playerid,matchid) 
);
use footballpremierleague;
