package TableEntities;

import java.util.List;

import javax.swing.table.AbstractTableModel;

class TopScorerStatsTableModel extends AbstractTableModel {

	private static final int PLAYERID_COL = 0;
	private static final int AVERAGEGOALSCORE_COL = 1;
	private static final int AVERAGESCORE_COL = 2;

	private String[] columnNames = { "PLAYER NO", "AVERAGE GOALS", "AVERAGE SCORE"};
	private List<TopScorerStats> players;

	public TopScorerStatsTableModel(List<TopScorerStats> thePlayers) {
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

		TopScorerStats tempPlayer = players.get(row);

		switch (col) {
		case PLAYERID_COL:
			return tempPlayer.getPlayerID();
		case AVERAGEGOALSCORE_COL:
			return tempPlayer.getAverageGoalScore();
		case AVERAGESCORE_COL:
			return tempPlayer. getAverageScore();
		default:
			return tempPlayer.getPlayerID();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}


