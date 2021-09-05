package TableEntities;

import java.util.List;

import javax.swing.table.AbstractTableModel;

class HighGapTableModel extends AbstractTableModel {

	private static final int MATCHID_COL = 0;
	private static final int UEFA_COL = 1;
	private static final int TEAMPERFORMANCEGAP_COL = 2;
	
	private String[] columnNames = { "MATCH", "UEFA TEAM", "PERFORMANCE GAP"};
	private List<HighGap> gaps;

	public HighGapTableModel(List<HighGap> theGaps) {
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

		HighGap tempGap = gaps.get(row);

		switch (col) {
		case MATCHID_COL:
			return tempGap.getMatchid();
		case UEFA_COL:
			return tempGap.getUEFATeam();
		case TEAMPERFORMANCEGAP_COL:
			return tempGap.getTeamPerformanceGap();
		default:
			return tempGap.getMatchid();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
