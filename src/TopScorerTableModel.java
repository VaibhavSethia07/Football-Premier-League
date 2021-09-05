package TableEntities;

import java.util.List;

import javax.swing.table.AbstractTableModel;

class TopScorerTableModel extends AbstractTableModel {

	private static final int PLAYERID_COL = 0;
	private static final int MATCHID_COL = 1;
	private static final int NAME_COL = 2;
	private static final int POSITION_COL = 3;
	private static final int UEFA_COL = 4;

	private String[] columnNames = { "PLAYER NO", "MATCH", "PLAYER","POSITION", "UEFA TEAM" };
	private List<TopScorer> players;

	public TopScorerTableModel(List<TopScorer> thePlayers) {
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

		TopScorer tempPlayer = players.get(row);

		switch (col) {
		case PLAYERID_COL:
			return tempPlayer.getPlayerid();
		case MATCHID_COL:
			return tempPlayer.getMatchid();
		case NAME_COL:
			return tempPlayer.getName();
		case POSITION_COL:
			return tempPlayer.getPosition();
		case UEFA_COL:
			return tempPlayer.getUEFATeam();
		default:
			return tempPlayer.getPlayerid();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}

