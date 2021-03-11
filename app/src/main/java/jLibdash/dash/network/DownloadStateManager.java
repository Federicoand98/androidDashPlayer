package jLibdash.dash.network;

import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DownloadStateManager {
	
	private DownloadState state;
	private Vector<IDownloadObserver> observers;
	private Lock lock;
	private Condition condition;
	
	public DownloadStateManager() {
		state = DownloadState.NOT_STARTED;
		observers = new Vector<IDownloadObserver>();
		lock = new ReentrantLock();
		condition = lock.newCondition();
	}
	
	public DownloadState getState() {
		DownloadState toReturn;
		try {
			lock.lock();
			toReturn = this.state;
		} finally {lock.unlock();}
		return toReturn;
	}
	
	public void setState(DownloadState state) {
		try {
			lock.lock();
			this.state = state;
			Notify();
			condition.signalAll();
		} finally {lock.unlock();}
		
	}
	
	public void waitState(DownloadState state) {
		try {
			lock.lock();
			while(!this.state.equals(state)) condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {lock.unlock();}
	}
	
	public void checkAndWait(DownloadState check, DownloadState wait) {
		try {
			lock.lock();
			if(this.state.equals(check))
				while(!this.state.equals(wait))
					condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {lock.unlock();}
	}
	
	public void attach(IDownloadObserver observer) {
		try {
			lock.lock();
			this.observers.add(observer);
		} finally {lock.unlock();}
	}
	
	public void detach(IDownloadObserver observer) {
		try {
			lock.lock();
			this.observers.remove(observer);
		} finally {lock.unlock();}
	}
	
	public void checkAndSet(DownloadState check, DownloadState set) {
		try {
			lock.lock();
			if(this.state.equals(check))
				this.state = set;
		} finally {lock.unlock();}
	}
	
	private void Notify() {
		for(int i=0; i<this.observers.size(); i++)
			this.observers.get(i).onDownloadStateChanged(this.state);
	}
	

}
