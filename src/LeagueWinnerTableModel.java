package TableEntities;

import java.util.List;

import javax.swing.table.AbstractTableModel;

class LeagueWinnerTableModel extends AbstractTableModel {

	private static final int TEAMNAME_COL = 0;
	private static final int LEAGUENAME_COL = 1;
	private static final int LEAGUESCORE_COL = 2;

	private String[] columnNames = { "TEAM", "NAME", "LEAGUE", "SCORE" };
	private List<LeagueWinner> leagues;

	public LeagueWinnerTableModel(List<LeagueWinner> theLeagues) {
		leagues = theLeagues;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return leagues.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		LeagueWinner tempLeague = leagues.get(row);

		switch (col) {
		case TEAMNAME_COL:
			return tempLeague.getTeamName();
		case LEAGUENAME_COL:
			return tempLeague.getLeagueName();
		case LEAGUESCORE_COL:
			return tempLeague.getLeagueScore();
		default:
			return tempLeague.getTeamName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
