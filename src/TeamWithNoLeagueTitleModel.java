package TableEntities;

import java.util.List;

import javax.swing.table.AbstractTableModel;

class TeamWithNoLeagueTitleModel extends AbstractTableModel {

	private static final int TEAMNAME_COL = 0;
	private static final int LOCATION_COl = 1;
	private static final int OWNERID_COL = 2;

	private String[] columnNames = { "TEAM", "LOCATION", "OWNER ID" };
	private List<TeamWithNoLeagueTitle> teams;

	public TeamWithNoLeagueTitleModel(List<TeamWithNoLeagueTitle> theTeams) {
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

		TeamWithNoLeagueTitle tempTeam = teams.get(row);

		switch (col) {
		case TEAMNAME_COL:
			return tempTeam.getTeamname();
		case LOCATION_COl:
			return tempTeam.getLocation();
		case OWNERID_COL:
			return tempTeam.getOwnerid();
		default:
			return tempTeam.getTeamname();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
