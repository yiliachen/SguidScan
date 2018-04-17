package com.example.xmlpaser;

import java.util.Set;

public abstract class ProcessorBase implements Processor{
	String mEntryName = null;
	boolean mDebug = false;
	Set fes = null;
	public String getmEntryName() {
		return mEntryName;
	}
	public void setmEntryName(String mEntryName) {
		this.mEntryName = mEntryName;
	}
	public boolean ismDebug() {
		return mDebug;
	}
	public void setmDebug(boolean mDebug) {
		this.mDebug = mDebug;
	}
	public Set getFes() {
		return fes;
	}
	public void setFes(Set fes) {
		this.fes = fes;
	}
}
