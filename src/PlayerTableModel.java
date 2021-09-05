package TableEntities;

import java.util.List;

import javax.swing.table.AbstractTableModel;

class PlayerTableModel extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int NAME_COL = 1;
	private static final int POSITION_COL = 2;
	private static final int UEFA_COL = 3;

	private String[] columnNames = { "Player ID", "Name", "Position", "UEFA Team" };
	private List<Player> players;

	public PlayerTableModel(List<Player> thePlayers) {
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

		Player tempPlayer = players.get(row);

		switch (col) {
		case ID_COL:
			return tempPlayer.getPlayerID();
		case NAME_COL:
			return tempPlayer.getName();
		case POSITION_COL:
			return tempPlayer.getPosition();
		case UEFA_COL:
			return tempPlayer.getUEFATeamName();
		default:
			return tempPlayer.getPlayerID();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
