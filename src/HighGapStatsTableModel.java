package TableEntities;

import java.util.List;

import javax.swing.table.AbstractTableModel;

class HighGapStatsTableModel extends AbstractTableModel {

	private static final int TEAM_COL = 0;
	private static final int MATCHID_COL = 1;
	private static final int OWNERID_COL = 2;
	private static final int TEAMPERFORMANCEGAP_COL = 3;
	
	private String[] columnNames = { "TEAM","MATCH", "OWNER", "PERFORMANCE GAP"};
	private List<HighGapStats> gaps;

	public HighGapStatsTableModel(List<HighGapStats> theGaps) {
		gaps = theGaps;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return gaps.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		HighGapStats tempGap = gaps.get(row);

		switch (col) {
		case TEAM_COL:
			return tempGap.getTeam();
		case MATCHID_COL:
			return tempGap.getMatchid();
		case OWNERID_COL:
			return tempGap.getOwnerID();
		case TEAMPERFORMANCEGAP_COL:
			return tempGap.getTeamPerformanceGap();
		default:
			return tempGap.getTeam();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
