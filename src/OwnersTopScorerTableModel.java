package TableEntities;

import java.util.List;

import javax.swing.table.AbstractTableModel;

class OwnersTopScorerTableModel extends AbstractTableModel {

	private static final int ID_COL = 0;
	private static final int FIRST_COL = 1;
	private static final int MINIT_COL = 2;
	private static final int LAST_COL = 3;
	private static final int EMAIL_COL = 4;

	private String[] columnNames = { "OWNER", "FIRST NAME", "M. INIT","LAST NAME", "EMAIL" };
	private List<Owner> owners;

	public OwnersTopScorerTableModel(List<Owner> theOwners) {
		owners = theOwners;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return owners.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Owner tempOwner = owners.get(row);

		switch (col) {
		case ID_COL:
			return tempOwner.getOwnerID();
		case FIRST_COL:
			return tempOwner.getFirstName();
		case MINIT_COL:
			return tempOwner.getmInit();
		case LAST_COL:
			return tempOwner.getLastName();
		case EMAIL_COL:
			return tempOwner.getEmail();
		default:
			return tempOwner.getOwnerID();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}

