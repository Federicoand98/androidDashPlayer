package jLibdash.dash.metrics;

import java.util.Vector;

public interface IDASHMetrics {
	
	public Vector<ITCPConnection> getTCPConnectionList();
	public Vector<IHTTPTransaction> getHTTPTransactionList();

}
