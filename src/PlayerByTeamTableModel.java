package TableEntities;

import java.util.List;

import javax.swing.table.AbstractTableModel;

class PlayerByTeamTableModel extends AbstractTableModel {

	private static final int NAME_COL = 1;
	private static final int SCORE_COL = 2;
	private static final int MATCHID_COL = 3;

	private String[] columnNames = { "TEAM", "UEFA TEAM", "MATCH" };
	private List<PlayerByTeam> players;

	public PlayerByTeamTableModel(List<PlayerByTeam> thePlayers) {
		players = thePlayers;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return players.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		PlayerByTeam tempPlayer = players.get(row);

		switch (col) {
		case NAME_COL:
			return tempPlayer.getName();
		case SCORE_COL:
			return tempPlayer.getScore();
		case MATCHID_COL:
			return tempPlayer.getMatchID();
		default:
			return tempPlayer.getName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
