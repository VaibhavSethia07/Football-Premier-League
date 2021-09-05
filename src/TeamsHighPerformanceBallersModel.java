package TableEntities;

import java.util.List;

import javax.swing.table.AbstractTableModel;

class TeamsHighPerformanceBallersModel extends AbstractTableModel {

	private static final int TEAMNAME_COL = 0;
	private static final int OWNERID_COL = 1;
	private static final int MATCHID_COl = 2;
	private static final int LEAGUENAME_COl = 3;
	private static final int LEAGUECOMMISSIONERID_COl = 4;
	private static final int SCORE_COl = 5;

	private String[] columnNames = { "TEAM", "OWNER ID", "MATCH ID","LEAGUE","LEAGUE COMMISSIONER ID","SCORE"};
	private List<TeamsHighPerformanceBallers> teams;

	public TeamsHighPerformanceBallersModel(List<TeamsHighPerformanceBallers> theTeams) {
		teams = theTeams;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return teams.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		TeamsHighPerformanceBallers tempTeam = teams.get(row);

		switch (col) {
		case TEAMNAME_COL:
			return tempTeam.getTeamname();
		case OWNERID_COL:
			return tempTeam.getOwnerID();
		case LEAGUENAME_COl:
			return tempTeam.getLeague();
		case LEAGUECOMMISSIONERID_COl:
			return tempTeam.getLeagueCommissionerID();
		case SCORE_COl:
			return tempTeam.getScore();
		default:
			return tempTeam.getTeamname();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
